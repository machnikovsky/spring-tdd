package pl.machnikovsky.tddapp.e2e;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import pl.machnikovsky.tddapp.model.Firestation;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class FirestationE2ETest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void shouldReturnAllFirestations() {
        String URLprefix = "http://localhost:" + port;
        ResponseEntity<Firestation[]> responseEntity = testRestTemplate.exchange(
                URLprefix + "/firestations",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                Firestation[].class
        );

        Assertions.assertEquals(4, responseEntity.getBody().length);
    }

    @Test
    public void shouldReturnFirestationsFromCertainCity() {
        String URLprefix = "http://localhost:" + port;
        ResponseEntity<Firestation[]> responseEntity = testRestTemplate.exchange(
                URLprefix + "/firestations/filter?city=Paris",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                Firestation[].class
        );

        Assertions.assertEquals(1, responseEntity.getBody().length);
    }

    @Test
    public void shouldReturnFirestationsWithCertainNumberOfFirefighters() {
        String URLprefix = "http://localhost:" + port;
        ResponseEntity<Firestation[]> responseEntity = testRestTemplate.exchange(
                URLprefix + "/firestations/filter?firefighters=2",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                Firestation[].class
        );

        Assertions.assertEquals(1, responseEntity.getBody().length);
    }
}
