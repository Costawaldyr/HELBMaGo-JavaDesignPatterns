package com.example.strategy;

import java.util.List;
import java.util.Random;

import com.example.interfaces.MessageStrategy;
import com.example.models.MaGo;
import com.example.models.MaGoPopulation;
import com.example.models.Message;
import com.example.utils.MyGlobals;

/**
 * Chooses the best message for a randomly selected MaGo type.
 * The strategy randomly delegates the choice to either the
 * circle strategy or the square strategy.
 */
public class MaGoAnyStrategy implements MessageStrategy {

    private static final int TWO_TYPES = 2;
    private final MaGoCircleStrategy circleStrategy;
    private final MaGoSquareStrategy squareStrategy;
    private Random rand;

    public MaGoAnyStrategy() {
        this.rand = new Random();
        this.circleStrategy = new MaGoCircleStrategy();
        this.squareStrategy = new MaGoSquareStrategy();
    }

    /**
     * Selects a message using either the circle strategy
     * or the square strategy chosen at random.
     *
     * @param messagesList available messages
     * @param population current MaGo population
     * @return the selected message
     */
    @Override
    public Message chooseMessage(List<Message> messagesList, MaGoPopulation population) {

        int random = rand.nextInt(TWO_TYPES);

        if(random == MyGlobals.ZERO){
            return circleStrategy.chooseMessage(messagesList, population);
        }else{
            return squareStrategy.chooseMessage(messagesList, population);
        }
    }
}