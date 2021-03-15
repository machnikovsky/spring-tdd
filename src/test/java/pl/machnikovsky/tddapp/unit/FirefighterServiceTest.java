package pl.machnikovsky.tddapp.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.machnikovsky.tddapp.model.Fire;
import pl.machnikovsky.tddapp.model.Firefighter;
import pl.machnikovsky.tddapp.model.Rank;
import pl.machnikovsky.tddapp.repository.FirefighterRepository;
import pl.machnikovsky.tddapp.service.FirefighterService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class FirefighterServiceTest {


    FirefighterService firefighterService;

    @Test
    void shouldReturnBestFirefighter() {
        //given
        FirefighterRepository firefighterRepository = mock(FirefighterRepository.class);
        List<Firefighter> firefighters = mockListOfFirefighters();
        doReturn(firefighters).when(firefighterRepository).findAll();
        firefighterService = new FirefighterService(firefighterRepository);

        //when
        Optional<Firefighter> bestFirefighter = firefighterService.getBestFirefighter();

        //then
        Assertions.assertEquals(930, bestFirefighter.get().getPoints());
    }

    @Test
    void shouldReturnHighestRank() {
        //given
        FirefighterRepository firefighterRepository = mock(FirefighterRepository.class);
        List<Firefighter> firefighters = mockListOfFirefighters();
        doReturn(firefighters).when(firefighterRepository).findAll();
        firefighterService = new FirefighterService(firefighterRepository);

        //when
        Optional<Firefighter> bestFirefighter = firefighterService.getHighestRank();

        //then
        Assertions.assertEquals(Rank.CHIEF, bestFirefighter.get().getRank());
    }



    private List<Firefighter> mockListOfFirefighters() {

        Firefighter firefighterOne = new Firefighter();
        firefighterOne.setId(1);
        firefighterOne.setName("John");
        firefighterOne.setLastname("Doe");
        firefighterOne.setRank(Rank.LIEUTENANT);
        firefighterOne.setPoints(610);

        Firefighter firefighterTwo = new Firefighter();
        firefighterTwo.setId(2);
        firefighterTwo.setName("James");
        firefighterTwo.setLastname("Morrison");
        firefighterTwo.setRank(Rank.PARAMEDIC);
        firefighterTwo.setPoints(755);

        Firefighter firefighterThree = new Firefighter();
        firefighterThree.setId(3);
        firefighterThree.setName("Anthony");
        firefighterThree.setLastname("Johnson");
        firefighterThree.setRank(Rank.CHIEF);
        firefighterThree.setPoints(720);

        Firefighter firefighterFour = new Firefighter();
        firefighterFour.setId(4);
        firefighterFour.setName("Frank");
        firefighterFour.setLastname("Adams");
        firefighterFour.setRank(Rank.CAPTAIN);
        firefighterFour.setPoints(930);


        List<Firefighter> mockedList = Arrays.asList(
                firefighterOne,
                firefighterTwo,
                firefighterThree,
                firefighterFour);

        return mockedList;
    }

}
