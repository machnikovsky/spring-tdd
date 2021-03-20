package pl.machnikovsky.tddapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.machnikovsky.tddapp.model.Fire;
import pl.machnikovsky.tddapp.model.Firefighter;
import pl.machnikovsky.tddapp.model.Firestation;
import pl.machnikovsky.tddapp.model.Rank;
import pl.machnikovsky.tddapp.repository.FirestationRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FirestationService {

    FirestationRepository firestationRepository;

    @Autowired
    public FirestationService(FirestationRepository firestationRepository) {
        this.firestationRepository = firestationRepository;
    }


    public Optional<Firestation> findMostFirefighters() {
        return firestationRepository
                .findAll()
                .stream().max(Comparator.comparing(Firestation::getFirefightersNumber));
    }

    public List<Firestation> findFirestationWithChief() {
        return firestationRepository
                .findAll()
                .stream()
                .filter(firestation -> firestation.getFirefighters()
                                                .stream()
                                                .anyMatch(firefighter -> firefighter.getRank().equals(Rank.CHIEF))
                )
                .collect(Collectors.toList());
    }

    public ResponseEntity<List<Firestation>> getAllFirestations() {
        return new ResponseEntity<>(firestationRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<List<Firestation>> getFirestationsWithMoreThanSomeNumberFirefighters(int firefighters) {
        List<Firestation> firestations = firestationRepository.findAll().stream().filter(fs -> fs.getFirefighters().size() >= firefighters).collect(Collectors.toList());
        return new ResponseEntity<>(firestations, HttpStatus.OK);
    }

    public ResponseEntity<List<Firestation>> getFirestationsFromCity(String city) {
        List<Firestation> firestations = firestationRepository.findAll().stream().filter(fs -> fs.getCity().equals(city)).collect(Collectors.toList());
        return new ResponseEntity<>(firestations, HttpStatus.OK);
    }
}
