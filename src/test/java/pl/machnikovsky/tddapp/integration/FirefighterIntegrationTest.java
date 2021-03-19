package pl.machnikovsky.tddapp.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.machnikovsky.tddapp.model.Firefighter;


import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class FirefighterIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    public void shouldReturnAllFirefighters() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/firefighters"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
        Firefighter[] firefighters = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Firefighter[].class);
        Assertions.assertEquals(4, firefighters.length);
    }

    @Test
    public void shouldReturn404() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/firefighters/8"))
                .andExpect(MockMvcResultMatchers.status().is(404))
                .andReturn();

        String actualMessage = Objects.requireNonNull(mvcResult.getResolvedException()).getMessage();
        Assertions.assertEquals("Firefighter with ID 8 was not found.", actualMessage);
    }

    @Test
    public void shouldReturnCertainFirefighter() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/firefighters/3"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();

        Firefighter firefighter = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Firefighter.class);
        Assertions.assertEquals("Anthony", firefighter.getName());
        Assertions.assertEquals("Johnson", firefighter.getLastname());
    }

    @Test
    public void shouldAddAndRemoveNewFirefighter() throws Exception {

        Firefighter newFirefighter = new Firefighter();
        newFirefighter.setName("Jim");
        newFirefighter.setId(5);
        MvcResult mvcResult = mockMvc
                .perform(
                        MockMvcRequestBuilders
                                .post("/firefighters")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(newFirefighter))
                )
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
        Firefighter firefighter = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Firefighter.class);

        Assertions.assertEquals("Jim", firefighter.getName());

        MvcResult mvcResult1 = mockMvc.perform(MockMvcRequestBuilders.get("/firefighters"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
        Firefighter[] firefighters = objectMapper.readValue(mvcResult1.getResponse().getContentAsString(), Firefighter[].class);


        mockMvc.perform(MockMvcRequestBuilders.delete("/firefighters/{id}", newFirefighter.getId()))
                .andExpect(status().is(204));


    }

}
