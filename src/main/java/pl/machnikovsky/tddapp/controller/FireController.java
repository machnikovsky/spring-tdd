package pl.machnikovsky.tddapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.machnikovsky.tddapp.service.FireService;
import pl.machnikovsky.tddapp.service.FirestationService;

@RestController
public class FireController {

    FireService fireService;

    @Autowired
    public FireController(FireService fireService) {
        this.fireService = fireService;
    }

}

