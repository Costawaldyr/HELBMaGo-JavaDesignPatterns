package com.exemple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.models.Message;
import com.example.models.SquareMaGo;

public class SquareMaGoTest {

    private SquareMaGo squareMaGo;

    @BeforeEach
    public void setUp() {
        squareMaGo = new SquareMaGo();
    }

    @Test
    public void shouldReturnSquareAsMaGoTypeString(){
        String expected = "Square";
        String received = squareMaGo.getType();
        assertEquals(expected, received, "Expected type: " + expected + "but received: " + received);
    }

    @Test
    public void shouldIncreaseStatusWhenVowelConsonantRatioIsAbovePivot() {
        Message msg = new Message(" Nicolas Flamel est le seul à avoir fabriqué la pierre philosophale. – La quoi ?! ");
        squareMaGo.reactToMessage(msg);
        int expected = 10;
        int received = squareMaGo.getStatus();
        assertEquals(expected, received, "Expected status: " + expected + ", but received: " + received);
    }

    @Test
    public void shouldDecreaseStatusWhenVowelConsonantRatioIsBelowPivot() {
        Message msg = new Message("Welcome to Belgium");
        squareMaGo.reactToMessage(msg);
        int expected = -10;
        int received = squareMaGo.getStatus();
        assertEquals(expected, received, "Expected status: " + expected + ", but received: " + received);
    }

    @Test
    public void shouldDecreaseStatusWhenThereIsNoConsonant() {
        
    }
}
