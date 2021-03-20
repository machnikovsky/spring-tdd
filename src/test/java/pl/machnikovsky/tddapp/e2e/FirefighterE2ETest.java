package pl.machnikovsky.tddapp.e2e;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import pl.machnikovsky.tddapp.exception.FirefighterNotFoundException;
import pl.machnikovsky.tddapp.model.Firefighter;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class FirefighterE2ETest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;


    @Test
    public void shouldReturnAllFirefighters() {
        String URLprefix = "http://localhost:" + port;
        ResponseEntity<Firefighter[]> responseEntity = testRestTemplate.exchange(
                URLprefix + "/firefighters",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                Firefighter[].class
        );

        Assertions.assertEquals(4, responseEntity.getBody().length);
    }

    @Test
    public void shouldReturnBestFirefighter() {
        String URLprefix = "http://localhost:" + port;
        ResponseEntity<Firefighter> responseEntity = testRestTemplate.exchange(
                URLprefix + "/firefighters/best",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                Firefighter.class
        );

        Assertions.assertEquals("Frank", responseEntity.getBody().getName());
    }

    @Test
    public void shouldReturnFirefighterNotFoundException() {
        String URLprefix = "http://localhost:" + port;
        ResponseEntity<FirefighterNotFoundException> responseEntity = testRestTemplate.exchange(
                URLprefix + "/firefighters/6",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                FirefighterNotFoundException.class
        );

        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
