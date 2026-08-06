package com.example.interfaces;

import java.util.List;

import com.example.models.MaGoPopulation;
import com.example.models.Message;

/**
 * Defines a strategy used to select the next message.
 * Each strategy implements a different way of choosing
 * a message according to the current population of MaGos.
 */
public interface MessageStrategy {

    /**
     * Selects the next message according to the strategy rules.
     *
     * @param messagesList available valid messages
     * @param population current MaGo population
     * @return selected message
     */
    Message chooseMessage(List<Message> messagesList,MaGoPopulation population);
}