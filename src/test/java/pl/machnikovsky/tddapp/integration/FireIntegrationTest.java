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
import pl.machnikovsky.tddapp.model.Fire;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class FireIntegrationTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldReturnAllFires() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/fires"))
                .andExpect(status().is(200))
                .andReturn();
        Fire[] fires = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Fire[].class);

        Assertions.assertEquals(4, fires.length);
    }

    @Test
    public void shouldReturnFiresWithCertainDanger() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/fires/danger").param("danger","6"))
                .andExpect(status().is(200))
                .andReturn();
        Fire[] fires = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Fire[].class);

        Assertions.assertEquals("Paris", fires[0].getCity());
    }

    @Test
    public void shouldReturnFiresWithAtLeastCertainDanger() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/fires/danger/atleast").param("danger","6"))
                .andExpect(status().is(200))
                .andReturn();
        Fire[] fires = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Fire[].class);

        Assertions.assertEquals(3, fires.length);
    }




}
