package pl.machnikovsky.tddapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.machnikovsky.tddapp.model.Fire;
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
}

