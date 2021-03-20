package pl.machnikovsky.tddapp.e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import pl.machnikovsky.tddapp.model.Fire;
import pl.machnikovsky.tddapp.model.Firestation;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class FireE2ETest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldReturnAllFires() {
        String URLprefix = "http://localhost:" + port;
        ResponseEntity<Fire[]> responseEntity = testRestTemplate.exchange(
                URLprefix + "/fires",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                Fire[].class
        );

        Assertions.assertEquals(4, responseEntity.getBody().length);
    }

    @Test
    public void shouldReturnAllActiveFires() {
        String URLprefix = "http://localhost:" + port;
        ResponseEntity<Fire[]> responseEntity = testRestTemplate.exchange(
                URLprefix + "/fires/active",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                Fire[].class
        );

        Assertions.assertEquals(3, responseEntity.getBody().length);
    }

    @Test
    public void shouldReturnFiresWithDangerAtLeast() {
        String URLprefix = "http://localhost:" + port;
        ResponseEntity<Fire[]> responseEntity = testRestTemplate.exchange(
                URLprefix + "/fires/danger/atleast?danger=7",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                Fire[].class
        );

        Assertions.assertEquals(2, responseEntity.getBody().length);
    }
}
