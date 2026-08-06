package com.example.strategy;

import java.util.List;

import com.example.interfaces.MessageStrategy;
import com.example.models.MaGo;
import com.example.models.MaGoPopulation;
import com.example.models.Message;
import com.example.utils.MyGlobals;

/**
 * Chooses the message that maximizes the total status
 * variation of all Square MaGos.
 */
public class MaGoSquareStrategy implements MessageStrategy {
    private static final String SQUARE = "Square";

    /**
     * Returns the message that gives the highest total score
     * for all Square MaGos.
     *
     * @param messagesList available messages
     * @param population current MaGo population
     * @return the best message for Square MaGos
     * @throws IllegalStateException if the message list is empty
     */
    @Override
    public Message chooseMessage(List<Message> messagesList, MaGoPopulation population) {
        
        if(messagesList.isEmpty()) {
            throw new IllegalStateException("No messages available");
        }

        Message bestMessage = messagesList.get(MyGlobals.ZERO);
        int bestScore = Integer.MIN_VALUE;

        for(Message message : messagesList) {
            int score = MyGlobals.ZERO;

            for(MaGo maGo : population.getMaGoList()) {
                if(maGo.getType().equals(SQUARE)){
                    score += maGo.calculateStatusChange(message);
                }
            }

            if(score > bestScore) {
                bestScore = score;
                bestMessage = message;
            }
        }
        return bestMessage;
    }
}