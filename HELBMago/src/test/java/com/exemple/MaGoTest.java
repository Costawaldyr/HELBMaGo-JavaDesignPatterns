package com.exemple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.models.CircleMaGo;
import com.example.models.SquareMaGo;


public class MaGoTest {

    private SquareMaGo squareMaGo;
    private CircleMaGo circleMaGo;

    @BeforeEach
    public void setUp() {
        squareMaGo = new SquareMaGo();
        circleMaGo = new CircleMaGo();
    }

    @Test
    public void shouldReturnZeroAsInitialStatusWhenMaGoIsNewlyCreated() {
        int expected = 0;
        int received = circleMaGo.getStatus();
        assertEquals(expected,received,"Expected status: " + expected + ", but received: " + received);
    }

    @Test
    public void shouldIncrementStatusByTwentyWhenIncrementStatusIsCalledOnce() {

        squareMaGo.incrementStatus(20);
        int expected = 20;
        int received = squareMaGo.getStatus();
        assertEquals(expected,received, "Expected status: " + expected + ", but received: " + received);
    }

    @Test
    public void shouldDecrementStatusWhenDecrementStatusIsCalledOnce() {

        squareMaGo.decrementStatus(15);
        int expected = -15;
        int received = squareMaGo.getStatus();
        assertEquals(expected, received, "Expected status: " + expected + ", but received: " + received);
    }

    @Test
    public void shouldNeverLeaveStatusGoAboveMaximumStatus() {

        squareMaGo.incrementStatus(500);
        int expected = 100;
        int received = squareMaGo.getStatus();
        assertEquals(expected, received, "Expected status: " + expected + ", but received: " + received);
    }

    @Test
    public void shouldNeverLeaveStatusGoBelowMinimumStatus() {

        squareMaGo.decrementStatus(500);
        int expected = -100;
        int received = squareMaGo.getStatus();
        assertEquals(expected,received, "Expected status: " + expected + ", but received: " + received);
    }

}