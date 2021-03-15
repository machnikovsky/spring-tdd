package pl.machnikovsky.tddapp.unit;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.machnikovsky.tddapp.model.Firestation;
import pl.machnikovsky.tddapp.repository.FirefighterRepository;
import pl.machnikovsky.tddapp.repository.FirestationRepository;
import pl.machnikovsky.tddapp.service.FirefighterService;
import pl.machnikovsky.tddapp.service.FirestationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static pl.machnikovsky.tddapp.unit.MockLists.mockListOfFirestations;


@ExtendWith(MockitoExtension.class)
class FirestationServiceTest {

    private FirestationService firestationService;
    private FirestationRepository firestationRepository;

    @BeforeEach
    void init() {
        firestationRepository = mock(FirestationRepository.class);
        firestationService = new FirestationService(firestationRepository);
    }

    @Test
    void shouldReturnFirestationWithMostFirefighters() {
        //given
        List<Firestation> firestations = mockListOfFirestations();
        doReturn(firestations).when(firestationRepository).findAll();

        //when
        Optional<Firestation> firestation = firestationService.findMostFirefighters();

        //then
        Assertions.assertEquals(2, firestation.get().getId());
    }

    @Test
    void shouldNotReturnFirestationWithMostFirefighters() {
        //given
        List<Firestation> firestations = new ArrayList<>();
        doReturn(firestations).when(firestationRepository).findAll();

        //when
        Optional<Firestation> firestation = firestationService.findMostFirefighters();

        //then
        Assertions.assertEquals(Optional.empty(), firestation);
    }

    @Test
    void shouldReturnFirestationsWithChief() {
        //given
        List<Firestation> firestations = mockListOfFirestations();
        doReturn(firestations).when(firestationRepository).findAll();

        //when
        List<Firestation> firestationsWithChief = firestationService.findFirestationWithChief();

        //then
        Assertions.assertEquals(2, firestationsWithChief.size());
    }

    @Test
    void shouldNotReturnFirestationsWithChief() {
        //given
        List<Firestation> firestations = new ArrayList<>();
        doReturn(firestations).when(firestationRepository).findAll();

        //when
        List<Firestation> firestationsWithChief = firestationService.findFirestationWithChief();

        //then
        Assertions.assertEquals(0, firestationsWithChief.size());
    }

}
