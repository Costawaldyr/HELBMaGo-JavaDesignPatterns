package com.exemple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.models.CircleMaGo;
import com.example.models.MaGoPopulation;
import com.example.models.Message;
import com.example.models.SquareMaGo;

public class MaGoPopulationTest {

    private MaGoPopulation population;

    @BeforeEach
    public void setUp() {
        population = new MaGoPopulation();
    }
    
    @Test
    public void shouldCreateEmptyPopulation() {
        int expected = 0;
        int received = population.getNumberOfMaGos();
        assertEquals(expected, received, "Expected number of MaGo: " + expected + ", but received: " + received);
    }

    @Test
    public void shouldAddOneMaGo() {
        CircleMaGo circleGo = new CircleMaGo();
        population.addMaGo(circleGo);
        int expected = 1;
        int received = population.getNumberOfMaGos();
        assertEquals(expected,received, "Expected number of MaGo: " + expected + ", but received: " + received);
    }

    @Test
    public void shouldRemoveOneMaGo() {
        SquareMaGo squareGo = new SquareMaGo();
        population.addMaGo(squareGo);
        population.removeMaGo(squareGo);
        int expected = 0;
        int received = population.getNumberOfMaGos();
        assertEquals(expected, received, "Expected number of MaGo: " + expected + ", but received: " + received);
    }

    @Test
    public void shouldReactAllMaGosWhenMessageIsReceived() {

        CircleMaGo circle = new CircleMaGo();
        SquareMaGo square = new SquareMaGo();

        population.addMaGo(circle);
        population.addMaGo(square);

        Message message = new Message("Nicolas Flamel est le seul à avoir fabriqué la pierre philosophale. – La quoi ?!");
        population.reactToMessage(message);

        int expected = 10;
        int receivedForCircle = circle.getStatus();
        int receivedForSquare = square.getStatus();

        assertEquals(expected, receivedForCircle,"Expected CircleMaGo status: " + expected + "but received: " + receivedForCircle);
        assertEquals(expected, receivedForSquare,"Expected SquareMaGo status: " + expected + "but received: " + receivedForSquare);
    }
}
