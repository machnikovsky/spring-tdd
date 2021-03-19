package pl.machnikovsky.tddapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.machnikovsky.tddapp.model.Firefighter;
import pl.machnikovsky.tddapp.service.FirefighterService;

import java.util.List;

@RestController
public class FirefighterController {

    FirefighterService firefighterService;

    @Autowired
    public FirefighterController(FirefighterService firefighterService) {
        this.firefighterService = firefighterService;
    }


    @GetMapping("/firefighters")
    @ResponseStatus
    public ResponseEntity<List<Firefighter>>  getFirefighters() {
        return firefighterService.getFirefighters();
    }

    @GetMapping("/firefighters/{id}")
    @ResponseStatus
    public ResponseEntity<Firefighter>  getFirefighters(@PathVariable("id") int id) {
        return firefighterService.getFirefighterById(id);
    }

    @PostMapping("/firefighters")
    @ResponseStatus
    public ResponseEntity<Firefighter>  addFirefighter(@RequestBody Firefighter firefighter) {
        return firefighterService.addFirefighter(firefighter);
    }

    @DeleteMapping("/firefighters/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFirefighter(@PathVariable("id") int id) {
        firefighterService.deleteFirefighterById(id);
    }
}
