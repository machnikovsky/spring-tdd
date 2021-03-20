package pl.machnikovsky.tddapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.machnikovsky.tddapp.model.Firefighter;
import pl.machnikovsky.tddapp.model.Firestation;
import pl.machnikovsky.tddapp.service.FirefighterService;
import pl.machnikovsky.tddapp.service.FirestationService;

import java.util.List;

@RestController
public class FirestationController {

    FirestationService firestationService;

    @Autowired
    public FirestationController(FirestationService firestationService) {
        this.firestationService = firestationService;
    }

    @GetMapping("/firestations")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Firestation>> getAllFirestations() {
        return firestationService.getAllFirestations();
    }

    @GetMapping("/firestations/findByFirefighters")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Firestation>> getFirestationsWithMoreThanSomeNumberFirefighters(@RequestParam("firefighters") int firefighters) {
        return firestationService.getFirestationsWithMoreThanSomeNumberFirefighters(firefighters);
    }

    @GetMapping("/firestations/findByCity")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Firestation>> getFirestationsFromCity(@RequestParam("city") String city) {
        return firestationService.getFirestationsFromCity(city);
    }

    @GetMapping("/firestations/filter")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Firestation>> getFilteredFirestations(
            @RequestParam(name = "city", required = false, defaultValue = "empty") String city,
            @RequestParam(name = "firefighters", required = false, defaultValue = "-1") int firefighters
            ) {
        return firestationService.getFilteredFirestations(city, firefighters);
    }
}
