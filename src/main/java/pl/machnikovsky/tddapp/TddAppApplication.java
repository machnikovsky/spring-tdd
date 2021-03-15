package pl.machnikovsky.tddapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pl.machnikovsky.tddapp.model.Firefighter;
import pl.machnikovsky.tddapp.repository.FirefighterRepository;

@SpringBootApplication
public class TddAppApplication {

    FirefighterRepository firefighterRepository;

    @Autowired
    public TddAppApplication(FirefighterRepository firefighterRepository) {
        this.firefighterRepository = firefighterRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(TddAppApplication.class, args);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void init() {
//        firefighterRepository.save(new Firefighter(1, "Sam", "Sammy", "Master"));
//    }

}
