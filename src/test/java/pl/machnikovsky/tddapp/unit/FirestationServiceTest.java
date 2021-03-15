package pl.machnikovsky.tddapp.unit;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.machnikovsky.tddapp.model.Firestation;
import pl.machnikovsky.tddapp.repository.FirestationRepository;
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

    @Test
    void shouldReturnFirestationWithMostFirefighters() {
        //given
        FirestationRepository firestationRepository = mock(FirestationRepository.class);
        List<Firestation> firestations = mockListOfFirestations();
        doReturn(firestations).when(firestationRepository).findAll();
        firestationService = new FirestationService(firestationRepository);

        //when
        Optional<Firestation> firestation = firestationService.findMostFirefighters();

        //then
        Assertions.assertEquals(2, firestation.get().getId());
    }

    @Test
    void shouldNotReturnFirestationWithMostFirefighters() {
        //given
        FirestationRepository firestationRepository = mock(FirestationRepository.class);
        List<Firestation> firestations = new ArrayList<>();
        doReturn(firestations).when(firestationRepository).findAll();
        firestationService = new FirestationService(firestationRepository);

        //when
        Optional<Firestation> firestation = firestationService.findMostFirefighters();

        //then
        Assertions.assertEquals(Optional.empty(), firestation);
    }

    @Test
    void shouldReturnFirestationsWithChief() {
        //given
        FirestationRepository firestationRepository = mock(FirestationRepository.class);
        List<Firestation> firestations = mockListOfFirestations();
        doReturn(firestations).when(firestationRepository).findAll();
        firestationService = new FirestationService(firestationRepository);

        //when
        List<Firestation> firestationsWithChief = firestationService.findFirestationWithChief();

        //then
        Assertions.assertEquals(2, firestationsWithChief.size());
    }

    @Test
    void shouldNotReturnFirestationsWithChief() {
        //given
        FirestationRepository firestationRepository = mock(FirestationRepository.class);
        List<Firestation> firestations = new ArrayList<>();
        doReturn(firestations).when(firestationRepository).findAll();
        firestationService = new FirestationService(firestationRepository);

        //when
        List<Firestation> firestationsWithChief = firestationService.findFirestationWithChief();

        //then
        Assertions.assertEquals(0, firestationsWithChief.size());
    }

}
