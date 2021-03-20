package pl.machnikovsky.tddapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.machnikovsky.tddapp.model.Fire;
import pl.machnikovsky.tddapp.model.Firestation;
import pl.machnikovsky.tddapp.repository.FireRepository;
import pl.machnikovsky.tddapp.repository.FirestationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FireService {

    FireRepository fireRepository;
    FirestationRepository firestationRepository;

    @Autowired
    public FireService(FireRepository fireRepository) {
        this.fireRepository = fireRepository;
    }

    public FireService(FireRepository fireRepository, FirestationRepository firestationRepository) {
        this.fireRepository = fireRepository;
        this.firestationRepository = firestationRepository;
    }

    public List<Fire> findActiveFires() {
        return fireRepository
                .findAll()
                .stream()
                .filter(Fire::isActive)
                .collect(Collectors.toList());
    }

    public List<Firestation> findSuitableFirestations(Fire fire) {
        return firestationRepository
                .findAll()
                .stream()
                .filter(firestation -> {
                     return firestation.getFirefightersNumber() >= fire.getFirefightersRequired() &&
                            firestation.getCity().equals(fire.getCity());
                })
                .collect(Collectors.toList());
    }

    public ResponseEntity<List<Fire>> getAllFires() {
        return new ResponseEntity<>(fireRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<List<Fire>> getWithCertainDanger(int danger) {
        List<Fire> fires = fireRepository.findAll().stream().filter(f -> f.getDangerLevel() == danger).collect(Collectors.toList());
        return new ResponseEntity<>(fires, HttpStatus.OK);
    }

    public ResponseEntity<List<Fire>> getWithAtLeastCertainDanger(int danger) {
        List<Fire> fires = fireRepository.findAll().stream().filter(f -> f.getDangerLevel() >= danger).collect(Collectors.toList());
        return new ResponseEntity<>(fires, HttpStatus.OK);
    }
}
