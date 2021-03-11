package pl.machnikovsky.tddapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/hello")
    @ResponseStatus
    public ResponseEntity<String> helloMethod() {
        return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
    }

    @GetMapping("/firefighters")
    @ResponseStatus
    public ResponseEntity<List<Firefighter>>  getFirefighters() {
        return firefighterService.getFirefighters();
    }
}
