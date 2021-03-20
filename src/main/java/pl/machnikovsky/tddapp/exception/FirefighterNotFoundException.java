package pl.machnikovsky.tddapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class FirefighterNotFoundException extends RuntimeException {

    public FirefighterNotFoundException() {
    }

    public FirefighterNotFoundException(int message) {
        super("Firefighter with ID " + message + " was not found.");
    }
}
