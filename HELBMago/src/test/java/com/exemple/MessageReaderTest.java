package com.exemple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.utils.MessageReader;

public class MessageReaderTest {

    private MessageReader myReader;

    @BeforeEach
    public void setUp() {
        myReader = new MessageReader("mess.msg");
    }

    @Test
    public void shouldReadTwentyEightValidMessages() {
        int expected = 28;
        int received = myReader.getNumberOfMessages();
        assertEquals(expected, received,"Expected number of valid messages: " + expected + ", but received: " + received);
    }

    @Test
    public void shouldIgnoreElevenInvalidMessages() {
        int totalLines = 39;
        int expected = 11;
        int received = totalLines - myReader.getNumberOfMessages();
        assertEquals(expected, received,"Expected number of invalid messages: " + expected + ", but received: " + received);
    }
}
