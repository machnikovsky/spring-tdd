package pl.machnikovsky.tddapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.machnikovsky.tddapp.model.Fire;
import pl.machnikovsky.tddapp.model.Firestation;
import pl.machnikovsky.tddapp.service.FireService;
import pl.machnikovsky.tddapp.service.FirestationService;

import java.util.List;

@RestController
public class FireController {

    FireService fireService;

    @Autowired
    public FireController(FireService fireService) {
        this.fireService = fireService;
    }

    @GetMapping("fires")
    @ResponseStatus
    public ResponseEntity<List<Fire>> getAllFires() {
        return fireService.getAllFires();
    }


    @GetMapping("fires/{id}")
    @ResponseStatus
    public ResponseEntity<Fire> getFireById(@PathVariable("id") int id) {
        return fireService.getFireById(id);
    }


    @GetMapping("/fires/danger")
    @ResponseStatus
    public ResponseEntity<List<Fire>> getWithCertainDanger(@RequestParam("danger") int danger) {
        return fireService.getWithCertainDanger(danger);
    }

    @GetMapping("/fires/danger/atleast")
    @ResponseStatus
    public ResponseEntity<List<Fire>> getWithAtLeastCertainDanger(@RequestParam("danger") int danger) {
        return fireService.getWithAtLeastCertainDanger(danger);
    }

    @GetMapping("/fires/active")
    @ResponseStatus
    public ResponseEntity<List<Fire>> getActiveFires() {
        return fireService.findActiveFires();
    }
}

