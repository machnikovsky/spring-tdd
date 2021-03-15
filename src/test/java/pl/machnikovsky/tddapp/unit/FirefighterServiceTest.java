package pl.machnikovsky.tddapp.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import pl.machnikovsky.tddapp.model.Firefighter;
import pl.machnikovsky.tddapp.model.Rank;
import pl.machnikovsky.tddapp.repository.FirefighterRepository;
import pl.machnikovsky.tddapp.service.FirefighterService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static pl.machnikovsky.tddapp.unit.MockLists.mockListOfFirefighters;

@ExtendWith(MockitoExtension.class)
class FirefighterServiceTest {


    private FirefighterService firefighterService;
    private FirefighterRepository firefighterRepository;


    @BeforeEach
    void init() {
        firefighterRepository = mock(FirefighterRepository.class);
        firefighterService = new FirefighterService(firefighterRepository);
    }


    @Test
    void shouldReturnBestFirefighter() {
        //given
        List<Firefighter> firefighters = mockListOfFirefighters();
        doReturn(firefighters).when(firefighterRepository).findAll();

        //when
        Optional<Firefighter> bestFirefighter = firefighterService.getBestFirefighter();

        //then
        Assertions.assertEquals(930, bestFirefighter.get().getPoints());
    }

    @Test
    void shouldNotReturnBestFirefighter() {
        //given
        List<Firefighter> firefighters = new ArrayList<>();
        doReturn(firefighters).when(firefighterRepository).findAll();

        //when
        Optional<Firefighter> bestFirefighter = firefighterService.getBestFirefighter();

        //then
        Assertions.assertEquals(Optional.empty(), bestFirefighter);
    }

    @Test
    void shouldReturnHighestRank() {
        //given
        List<Firefighter> firefighters = mockListOfFirefighters();
        doReturn(firefighters).when(firefighterRepository).findAll();

        //when
        Optional<Firefighter> bestFirefighter = firefighterService.getHighestRank();

        //then
        Assertions.assertEquals(Rank.CHIEF, bestFirefighter.get().getRank());
    }

    @Test
    void shouldNotReturnHighestRank() {
        //given
        List<Firefighter> firefighters = new ArrayList<>();
        doReturn(firefighters).when(firefighterRepository).findAll();

        //when
        Optional<Firefighter> bestFirefighter = firefighterService.getHighestRank();

        //then
        Assertions.assertEquals(Optional.empty(), bestFirefighter);
    }

    @Test
    void shouldReturnAllFirefighters() {
        //given
        List<Firefighter> firefighters = mockListOfFirefighters();
        doReturn(firefighters).when(firefighterRepository).findAll();

        //when
        ResponseEntity<List<Firefighter>> responseEntity = firefighterService.getFirefighters();

        //then
        Assertions.assertEquals(4, responseEntity.getBody().size());
    }

    @Test
    void shouldNotReturnAllFirefighters() {
        //given
        List<Firefighter> firefighters = new ArrayList<>();
        doReturn(firefighters).when(firefighterRepository).findAll();

        //when
        ResponseEntity<List<Firefighter>> responseEntity = firefighterService.getFirefighters();

        //then
        Assertions.assertEquals(0, responseEntity.getBody().size());
    }

    @Test
    void shouldReturnOverCertainPoint() {
        //given
        List<Firefighter> firefighters = mockListOfFirefighters();
        doReturn(firefighters).when(firefighterRepository).findAll();

        //when
        List<Firefighter> firefightersList = firefighterService.getFirefightersOver(700);

        //then
        Assertions.assertEquals(3, firefightersList.size());
    }

    @Test
    void shouldNotReturnOverCertainPoint() {
        //given
        List<Firefighter> firefighters = mockListOfFirefighters();
        doReturn(firefighters).when(firefighterRepository).findAll();

        //when
        List<Firefighter> firefightersList = firefighterService.getFirefightersOver(1000);

        //then
        Assertions.assertEquals(0, firefightersList.size());
    }


}
