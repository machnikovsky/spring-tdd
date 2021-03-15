package pl.machnikovsky.tddapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
