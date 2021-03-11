package pl.machnikovsky.tddapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.machnikovsky.tddapp.model.Firefighter;
import pl.machnikovsky.tddapp.repository.FirefighterRepository;

import java.util.List;

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




}
