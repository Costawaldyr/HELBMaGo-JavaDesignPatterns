package com.exemple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.models.CircleMaGo;
import com.example.models.Message;

public class CircleMaGoTest {

    private CircleMaGo circleMaGo;

    @BeforeEach
    public void setUp() {
        circleMaGo = new CircleMaGo();
    }

    @Test
    public void shouldReturnCircleAsMaGoTypeString() {
        String expected = "Circle";
        String received = circleMaGo.getType();
        assertEquals(expected, received, "Expected type: " + expected + "but received: " + received);
    }

    @Test
    public void shouldIncreaseStatusFivePercentForEachPunctuationWhenIsFound() {
        Message msg = new Message("Nicolas Flamel est le seul à avoir fabriqué la pierre philosophale. – La quoi ?!");
        circleMaGo.reactToMessage(msg);
        int expected = 10;
        int received = circleMaGo.getStatus();
        assertEquals(expected, received, "Expected incrementation: " + expected + "but received: " + received);
    }

    @Test
    public void shouldDecreaseStatusLessTenPercentWhenPunctuationNotFound() {
        Message msg = new Message("Nicolas Flamel a fabriqué la pierre philosophale selon la légende");
        circleMaGo.reactToMessage(msg);
        int expected = -10;
        int received = circleMaGo.getStatus();
        assertEquals(expected, received, "Expected decrementation: " + expected + "but received: " + received);
    }

    @Test
    public void shouldIncreaseStatusByTwentyWhenFourPositivePunctuationsAreFound() {
        
    }
    
}
