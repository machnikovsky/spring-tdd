package pl.machnikovsky.tddapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class FireNotFoundException extends RuntimeException {
    public FireNotFoundException(int message) {
        super("Firefighter with ID " + message + " was not found.");
    }
}
