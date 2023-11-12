package domain_model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class DatabaseTest {

    Database testDatabase = new Database(";", "testFile.csv");
    int emptyList;
    int expectedSize;
    int actualSize;

    ReturnMessage expectedReturnMessage;
    ReturnMessage actualReturnMessage;

    @BeforeEach
    void setUp() {

        // Hver test tilføjer tekst til csv filen. Og hver nye test læser fra csv filen. Her ryddes data som læses fra csv filen.
        testDatabase.getSuperheroList().clear();

        emptyList = testDatabase.getSuperheroList().size(); // = 0


    }

    @Test
    void addSuperhero() {
        Superhero testSuperhero = new Superhero("Name1", "RealName1", "Superpower1", 1111, true, 111);
        testDatabase.addSuperhero(testSuperhero);

        expectedSize = emptyList + 1; // = 1
        actualSize = testDatabase.getSuperheroList().size();
        assertEquals(expectedSize, actualSize, "Size of list should be " + emptyList + 1);

        Superhero expectedSuperhero = testSuperhero;
        Superhero actualSuperhero = testDatabase.getSuperheroList().get(0);
        assertEquals(expectedSuperhero, actualSuperhero, "Superhero mismatch");
    }


    @Test
    void removeSuperheroExitMenu() {
        expectedReturnMessage = ReturnMessage.EXIT;
        actualReturnMessage = testDatabase.removeSuperhero(0, 0);
        assertEquals(expectedReturnMessage, actualReturnMessage, "ReturnMessage mismatch");
    }

    @Test
    void removeSuperhero() {
        Superhero testSuperhero1 = new Superhero("Name1", "RealName1", "Superpower1", 1111, true, 111);
        Superhero testSuperhero2 = new Superhero("Name1", "RealName1", "Superpower1", 1111, true, 111);
        testDatabase.addSuperhero(testSuperhero1);
        testDatabase.addSuperhero(testSuperhero2);

        expectedReturnMessage = ReturnMessage.DELETED;
        actualReturnMessage = testDatabase.removeSuperhero(1, 0);
        expectedSize = 1;
        actualSize = testDatabase.getSuperheroList().size();
        Superhero expectedSuperheroRemaining = testSuperhero2;
        Superhero actualSuperheroRemaining = testDatabase.getSuperheroList().get(0);

        assertAll("Check multiple conditions",
                () -> assertEquals(expectedReturnMessage, actualReturnMessage, "ReturnMessage mismatch"),
                () -> assertEquals(expectedSize, actualSize, "Size of list should be " + expectedSize),
                () -> assertEquals(expectedSuperheroRemaining, actualSuperheroRemaining, "Wrong superhero removed")
        );
    }




    @Test
    void sortedDatabase() {
        Superhero testSuperhero0 = new Superhero("Name0", "Realname1", "Superpower1", 2000, true, 199);
        Superhero testSuperhero1 = new Superhero("Name1", "Realname2", "Superpower0", 2001, false, 399);
        Superhero testSuperhero2 = new Superhero("Name2", "Realname2", "Superpower0", 2003, false, 99);
        Superhero testSuperhero3 = new Superhero("Name0", "Realname0", "Superpower2", 2002, true, 299);
        testDatabase.addSuperhero(testSuperhero0);
        testDatabase.addSuperhero(testSuperhero1);
        testDatabase.addSuperhero(testSuperhero2);
        testDatabase.addSuperhero(testSuperhero3);
        int oneCriterion = 1;
        int twoCriteria = 2;
        int name = 1;
        int realName = 2;
        int superpower = 3;
        int yearCreated = 4;
        int isHuman = 5;
        int strengthLevel = 6;
        int asc = 1;
        int desc = 2;


        ArrayList<Superhero> superheroesByYearAsc = testDatabase.sortedSuperheroList(oneCriterion, yearCreated, asc, 0, 0);
        assertTrue(superheroesByYearAsc.get(0).getYearCreated() < superheroesByYearAsc.get(1).getYearCreated() &&
                superheroesByYearAsc.get(1).getYearCreated() < superheroesByYearAsc.get(2).getYearCreated() &&
                superheroesByYearAsc.get(2).getYearCreated() < superheroesByYearAsc.get(3).getYearCreated());

        ArrayList<Superhero> superheroesByRealNameAscThenStrengthDesc = testDatabase.sortedSuperheroList(twoCriteria,realName,asc,strengthLevel,desc);
        ArrayList<Superhero> expectedOrder = new ArrayList<>();
        expectedOrder.add(testSuperhero3);
        expectedOrder.add(testSuperhero0);
        expectedOrder.add(testSuperhero1);
        expectedOrder.add(testSuperhero2);
        assertEquals(expectedOrder,superheroesByRealNameAscThenStrengthDesc,"List order mismatch");
    }
}

