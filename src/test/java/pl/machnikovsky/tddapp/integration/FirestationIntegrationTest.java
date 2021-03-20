package pl.machnikovsky.tddapp.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.machnikovsky.tddapp.model.Firestation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class FirestationIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldReturnAllFirestations() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/firestations"))
                .andExpect(status().is(200))
                .andReturn();

        Firestation[] firestations = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Firestation[].class);
        Assertions.assertEquals(4, firestations.length);
    }

    @Test
    public void shouldReturnFirestationsWithMoreThanSomeNumberFirefighters() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/firestations")
                .param("firefighters", "3"))
                .andExpect(status().is(200))
                .andReturn();

        Firestation[] firestations = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Firestation[].class);
        Assertions.assertNotEquals(0, firestations.length);

    }

    @Test
    public void shouldReturnFirestationsFromCertainCity() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/firestations")
                .param("city", "London"))
                .andExpect(status().is(200))
                .andReturn();

        Firestation[] firestations = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Firestation[].class);
        Assertions.assertNotEquals(0, firestations.length);

    }
}
