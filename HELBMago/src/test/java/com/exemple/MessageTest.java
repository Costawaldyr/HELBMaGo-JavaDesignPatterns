package com.exemple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.models.Message;


public class MessageTest {
    private Message msg;

    @BeforeEach
    public void setUp() {
        msg = new Message("Hello");
    }

    @Test
    public void shouldReturnTextGivenInConstructor() {
        String expected = "Hello";
        String received = msg.getText();
        assertEquals(expected,received,"Expected text: " + expected + ", but received: " + received);
    }

    @Test
    public void shouldReturnTrueWhenMessageIsValid(){
        boolean expected = true;
        boolean received = msg.isValid();
        assertEquals(expected,received, "Expected validity: " + expected + ", but received: " + received);
    }

    @Test
    public void shouldReturnFalseWhenMessageContainsOnlyNumbers() {
        Message msg = new Message("123456789");
        boolean expected = false;
        boolean received = msg.isValid();
        assertEquals(expected,received, "Expected validity: " + expected + ", but received: " + received);
    }

    @Test
    public void shouldCountVowelsCorrectly() {
        Message msg = new Message("Méssage");
        int expected = 3;
        int received = msg.countVowels();
        assertEquals(expected, received,"Expected vowels: " + expected + ", but received: " + received);
    }

    @Test
    public void shouldCountConsonantsCorrectly() {
        Message msg = new Message("Consonants");
        int expected = 7;
        int received = msg.countConsonants();
        assertEquals(expected, received,"Expected consonants: " + expected + ", but received: " + received);
    }

    @Test
    public void shouldCountPositivePunctuationCorrectly() {
        Message msg = new Message("Hello?!?!");
        int expected = 4;
        int received = msg.countPositivePunctuation();
        assertEquals(expected, received,"Expected puntuation: " + expected + ", but received: " + received);
    }
}