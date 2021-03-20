package pl.machnikovsky.tddapp.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.machnikovsky.tddapp.exception.FirefighterNotFoundException;
import pl.machnikovsky.tddapp.exception.FirestationNotFoundException;
import pl.machnikovsky.tddapp.model.Fire;
import pl.machnikovsky.tddapp.model.Firefighter;
import pl.machnikovsky.tddapp.model.Firestation;
import pl.machnikovsky.tddapp.repository.FirefighterRepository;
import pl.machnikovsky.tddapp.repository.FirestationRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FirefighterService {

    FirefighterRepository firefighterRepository;
    FirestationRepository firestationRepository;


    public FirefighterService(FirefighterRepository firefighterRepository) {
        this.firefighterRepository = firefighterRepository;
    }

    @Autowired
    public FirefighterService(FirefighterRepository firefighterRepository, FirestationRepository firestationRepository) {
        this.firefighterRepository = firefighterRepository;
        this.firestationRepository = firestationRepository;
    }

    public ResponseEntity<List<Firefighter>> getFirefighters() {
        return new ResponseEntity<>(firefighterRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Optional<Firefighter>> getBestFirefighter() {
        Optional<Firefighter> firefighter = firefighterRepository
                .findAll()
                .stream()
                .max(Comparator.comparing(Firefighter::getPoints));
        return new ResponseEntity<>(firefighter, HttpStatus.OK);
    }

    public Optional<Firefighter> getHighestRank() {
        return firefighterRepository
                .findAll()
                .stream()
                .max(Comparator.comparing(Firefighter::getRank));
    }

    public List<Firefighter> getFirefightersOver(int points) {
        return firefighterRepository
                .findAll()
                .stream()
                .filter(firefighter -> firefighter.getPoints() > points)
                .collect(Collectors.toList());
    }

    public ResponseEntity<Firefighter>  getFirefighterById(int id) {
        Firefighter firefighter = firefighterRepository.findById(id).orElseThrow(() -> new FirefighterNotFoundException(id));
        return new ResponseEntity<>(firefighter, HttpStatus.OK);
    }

    public ResponseEntity<Firefighter> addFirefighter(Firefighter firefighter) {
        return new ResponseEntity<>(firefighterRepository.save(firefighter), HttpStatus.OK);
    }

    public void deleteFirefighterById(int id) {
        Optional<Firefighter> firefighter = firefighterRepository
                .findById(id);

        firestationRepository
                .findAll()
                .stream()
                .filter(fs -> fs
                        .getFirefighters()
                        .stream()
                        .anyMatch(ff -> ff.getId() == id))
                .findFirst().ifPresent(el -> el.removeFirefighter(firefighter.get()));

        firefighter
                .ifPresent(ff -> firefighterRepository.deleteById(ff.getId()));

    }
}
