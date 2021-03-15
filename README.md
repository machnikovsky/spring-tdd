In that project I'm trying to make little Firefighter system with three basic entities - Firefighter, Firestation and Fire. There's also additional enum - Rank, and it's an attribute of Firefighter. The main goal of this project is to build it according to TDD approach, BDD specifically (given/when/then). I'm also trying to write three different types of tests - Unit Tests, Integration Tests, E2E Tests. The core project is written in Spring and I'm using JUnit and Mockito for test purposes, but I'm going to add PostgreSQL databses for later tests.

## Unit tests


- FirefighterTest - that class tests logic inside Firefighter Service. It also mocks FirefighterRepository. Main focus of tests in that class is to check if certain functionality returns correct values or if empty result is returned when wanted.

```
@ExtendWith(MockitoExtension.class)
class FirefighterTest {


    private FirefighterService firefighterService;
    private FirefighterRepository firefighterRepository;


    @BeforeEach
    void init() {
        firefighterRepository = mock(FirefighterRepository.class);
        firefighterService = new FirefighterService(firefighterRepository);
    }

.............
Actual tests
.............

}
```


- FirestationTest - that class tests logic inside FirestationService. It also mocks FirestationRepository. Main focus of tests in that class is to check if certain functionality returns correct values or if empty result is returned when wanted.

```
@ExtendWith(MockitoExtension.class)
class FirestationTest {

    private FirestationService firestationService;
    private FirestationRepository firestationRepository;

    @BeforeEach
    void init() {
        firestationRepository = mock(FirestationRepository.class);
        firestationService = new FirestationService(firestationRepository);
    }

.............
Actual tests
.............

}
```


- FireTest - that class tests logic inside FireService. It also mocks FireRepository and FirestationRepository. Main focus of tests in that class is to check if certain functionality returns correct values or if empty result is returned when wanted. That class is different from the two above, because it also mocks second Repository, because functionality of one function covers both of them.

```
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

.............
Actual tests
.............

}
```


- MockLists - that class provides mocked lists of various objects used in other test classes.

```
class MockLists {

    public static List<Firefighter> mockListOfFirefighters() {...}

    public static List<Firestation> mockListOfFirestations() {...}

    public static List<Fire> mockListOfFires() {...}

}
```



