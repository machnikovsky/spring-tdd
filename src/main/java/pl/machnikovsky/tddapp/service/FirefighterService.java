package pl.machnikovsky.tddapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.comparator.Comparators;
import pl.machnikovsky.tddapp.model.Firefighter;
import pl.machnikovsky.tddapp.repository.FirefighterRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FirefighterService {

    FirefighterRepository firefighterRepository;

    @Autowired
    public FirefighterService(FirefighterRepository firefighterRepository) {
        this.firefighterRepository = firefighterRepository;
    }

    public ResponseEntity<List<Firefighter>> getFirefighters() {
        return new ResponseEntity<>(firefighterRepository.findAll(), HttpStatus.OK);
    }

    public Optional<Firefighter> getBestFirefighter() {
        return firefighterRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Firefighter::getPoints).reversed())
                .findFirst();
    }

    public Optional<Firefighter> getHighestRank() {
        return firefighterRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Firefighter::getRank).reversed())
                .findFirst();
    }

    public List<Firefighter> getFirefightersOver(int points) {
        return firefighterRepository
                .findAll()
                .stream()
                .filter(firefighter -> firefighter.getPoints() > points)
                .collect(Collectors.toList());
    }
}
