package com.exemple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.factory.MaGoFactory;
import com.example.factory.MaGoPopulationGenerator;
import com.example.models.MaGo;
import com.example.models.MaGoPopulation;


public class MaGoFactoryTest {

    private static final String CIRCLE = "Circle";
    private static final String SQUARE = "Square";
    private static final int MIN_MAGOS = 2;
    private static final int MAX_MAGOS = 8;

    private static final int FIXED_POPULATION_SIZE = 3;
    private static final long FIXED_TIMESTAMP = 1750257594L;
    
    private MaGoPopulationGenerator populationGenerator;
    private MaGoPopulation population;

    @BeforeEach
    public void setUp() {
        populationGenerator = new MaGoPopulationGenerator();
        population = new MaGoPopulation();
    }

    @Test
    public void shouldCreateCircleOrSquareMaGoWhenCall() {
        population = populationGenerator.createRandomPopulation();

        for (MaGo maGo : population.getMaGoList()) {
            boolean expected = true;
            boolean received = maGo.getType().equals(CIRCLE) || maGo.getType().equals(SQUARE);
            assertEquals(expected, received, "Expected Circle or Square, but received: " + maGo.getType());
        }
    }

    @Test
    public void shouldCreatePopulationContainingBetweenTwoAndEightMaGos() {
        population = populationGenerator.createRandomPopulation();

        boolean expected = true;
        boolean received = population.getNumberOfMaGos() >= MIN_MAGOS && population.getNumberOfMaGos() <= MAX_MAGOS;
        assertEquals(expected, received, "Expected a population containing between 2 and 8 MaGos, but received: " + population.getNumberOfMaGos());
    }

    @Test
    public void shouldNeverCreateAnEmptyPopulation() {
        population = populationGenerator.createRandomPopulation();

        boolean expected = true;
        boolean received = population.getNumberOfMaGos() > 0;
        assertEquals(expected, received, "Expected a non-empty population, but received: " + population.getNumberOfMaGos());
    }

    @Test
    public void shouldCreatePopulationWithRequestedNumberOfMaGos() {
        population = populationGenerator.createPopulation(FIXED_POPULATION_SIZE, FIXED_TIMESTAMP);

        int expected = FIXED_POPULATION_SIZE;
        int received = population.getNumberOfMaGos();
        assertEquals(expected, received, "Expected number of MaGo: " + expected + ", but received: " + received);
    }

    @Test
    public void shouldAssignTypesAccordingToTimestampParityExample() {
        population = populationGenerator.createPopulation(FIXED_POPULATION_SIZE, FIXED_TIMESTAMP);

        String expectedFirst = CIRCLE;
        String receivedFirst = population.getMaGoList().get(0).getType();
        assertEquals(expectedFirst, receivedFirst, "Expected first MaGo type: " + expectedFirst + ", but received: " + receivedFirst);

        String expectedSecond = SQUARE;
        String receivedSecond = population.getMaGoList().get(1).getType();
        assertEquals(expectedSecond, receivedSecond, "Expected second MaGo type: " + expectedSecond + ", but received: " + receivedSecond);

        String expectedThird = SQUARE;
        String receivedThird = population.getMaGoList().get(2).getType();
        assertEquals(expectedThird, receivedThird, "Expected third MaGo type: " + expectedThird + ", but received: " + receivedThird);
    }

}
