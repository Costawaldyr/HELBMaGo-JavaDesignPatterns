package com.example.strategy;

import com.example.interfaces.MessageStrategy;
import com.example.models.MaGo;
import com.example.models.MaGoPopulation;
import com.example.models.Message;

import java.util.List;
import java.util.Random;

/**
 * Chooses a random message from the available messages.
 */
public class MaGoRandomStrategy implements MessageStrategy {

    private Random rand;

    public MaGoRandomStrategy() {
        this.rand = new Random();
    }

    /**
     * Returns a randomly selected message.
     *
     * @param messagesList available messages
     * @param population current MaGo population (not used)
     * @return a random message
     * @throws IllegalStateException if the message list is empty
     */
    @Override
     public Message chooseMessage(List<Message> messagesList, MaGoPopulation population) {

        if(messagesList.isEmpty()) {
            throw new IllegalStateException("No messages available");
        }
        
        int index = rand.nextInt(messagesList.size());
        return messagesList.get(index);
    }
}