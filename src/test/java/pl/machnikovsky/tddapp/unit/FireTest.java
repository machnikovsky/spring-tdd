package pl.machnikovsky.tddapp.unit;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.machnikovsky.tddapp.model.Fire;
import pl.machnikovsky.tddapp.model.Firestation;
import pl.machnikovsky.tddapp.repository.FireRepository;
import pl.machnikovsky.tddapp.repository.FirestationRepository;
import pl.machnikovsky.tddapp.service.FireService;
import pl.machnikovsky.tddapp.service.FirestationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static pl.machnikovsky.tddapp.unit.MockLists.mockListOfFires;
import static pl.machnikovsky.tddapp.unit.MockLists.mockListOfFirestations;

@ExtendWith(MockitoExtension.class)
class FireTest {

    private FireService fireService;
    private FireRepository fireRepository;
    private FirestationRepository firestationRepository;

    @BeforeEach
    void init() {
        firestationRepository = mock(FirestationRepository.class);
        fireRepository = mock(FireRepository.class);
        fireService = new FireService(fireRepository, firestationRepository);
    }

    @Test
    void shouldReturnActiveFires() {
        //given
        List<Fire> fires = mockListOfFires();
        doReturn(fires).when(fireRepository).findAll();

        //when
        List<Fire> activeFires = fireService.findActiveFires();

        //then
        Assertions.assertEquals(3, activeFires.size());
    }

    @Test
    void shouldNotReturnActiveFires() {
        //given
        List<Fire> fires = new ArrayList<>();
        doReturn(fires).when(fireRepository).findAll();

        //when
        List<Fire> activeFires = fireService.findActiveFires();

        //then
        Assertions.assertEquals(0, activeFires.size());
    }

    @Test
    void shouldReturnSuitableFirestations() {
        //given
        List<Firestation> firestations = mockListOfFirestations();
        doReturn(firestations).when(firestationRepository).findAll();
        List<Fire> fires = mockListOfFires();

        //when
        List<Firestation> suitableFirestations = fireService.findSuitableFirestations(fires.get(1));

        //then
        Assertions.assertEquals(1, suitableFirestations.size());
    }

    @Test
    void shouldNotReturnSuitableFirestations() {
        //given
        List<Firestation> firestations = mockListOfFirestations();
        doReturn(firestations).when(firestationRepository).findAll();
        List<Fire> fires = mockListOfFires();

        //when
        List<Firestation> suitableFirestations = fireService.findSuitableFirestations(fires.get(0));

        //then
        Assertions.assertEquals(0, suitableFirestations.size());
    }
}
