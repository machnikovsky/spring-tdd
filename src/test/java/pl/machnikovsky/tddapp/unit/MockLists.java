package pl.machnikovsky.tddapp.unit;

import pl.machnikovsky.tddapp.model.Fire;
import pl.machnikovsky.tddapp.model.Firefighter;
import pl.machnikovsky.tddapp.model.Firestation;
import pl.machnikovsky.tddapp.model.Rank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MockLists {

    public static List<Firefighter> mockListOfFirefighters() {

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

    public static List<Firestation> mockListOfFirestations() {

        List<Firefighter> firefighters = mockListOfFirefighters();

        Firestation firestationOne = new Firestation();
        firestationOne.setId(1);
        firestationOne.setCity("London");
        firestationOne.setFirefighters(Arrays.asList(firefighters.get(0),firefighters.get(2)));
        firestationOne.setFirefightersNumber(2);

        Firestation firestationTwo = new Firestation();
        firestationTwo.setId(2);
        firestationTwo.setCity("Paris");
        firestationTwo.setFirefighters(firefighters);
        firestationTwo.setFirefightersNumber(4);

        Firestation firestationThree = new Firestation();
        firestationThree.setId(3);
        firestationThree.setCity("Cracow");
        firestationThree.setFirefighters(new ArrayList<>());
        firestationThree.setFirefightersNumber(0);

        Firestation firestationFour = new Firestation();
        firestationFour.setId(4);
        firestationFour.setCity("Tokyo");
        firestationFour.setFirefighters(Arrays.asList(firefighters.get(1)));
        firestationFour.setFirefightersNumber(1);


        List<Firestation> mockedList = Arrays.asList(
                firestationOne,
                firestationTwo,
                firestationThree,
                firestationFour);

        return mockedList;
    }

    public static List<Fire> mockListOfFires() {

        Fire fireOne = new Fire();
        fireOne.setId(1);
        fireOne.setCity("Rome");
        fireOne.setActive(true);
        fireOne.setDangerLevel(8);
        fireOne.setFirefightersRequired(12);

        Fire fireTwo = new Fire();
        fireTwo.setId(2);
        fireTwo.setCity("Paris");
        fireTwo.setActive(true);
        fireTwo.setDangerLevel(6);
        fireTwo.setFirefightersRequired(4);

        Fire fireThree = new Fire();
        fireThree.setId(3);
        fireThree.setCity("London");
        fireThree.setActive(false);
        fireThree.setDangerLevel(2);
        fireThree.setFirefightersRequired(1);

        Fire fireFour = new Fire();
        fireFour.setId(4);
        fireFour.setCity("Tokyo");
        fireFour.setActive(true);
        fireFour.setDangerLevel(7);
        fireFour.setFirefightersRequired(6);

        List<Fire> mockedList = Arrays.asList(
                fireOne,
                fireTwo,
                fireThree,
                fireFour);

        return mockedList;
    }

}
