package com.example.models;

import com.example.utils.MyGlobals;


/**
 * Represents a square MaGo.
 * A square MaGo reacts according to the ratio between vowels and consonants.
 */
public class SquareMaGo extends MaGo{

    private static final double PIVOT_RATIO = 0.89;
    private static final int MAX_PERCENT = 10;
    private static final int NO_CONSONANT = 0;
    private static final String TYPE = "Square";

    public SquareMaGo(){
        super();
    }

    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * Updates the status according to the ratio between vowels and consonants.
     * @param msg received message
     */
    @Override
    public void reactToMessage(Message msg) {
       int variation = calculateStatusChange(msg);

       if(variation > MyGlobals.ZERO) {
           incrementStatus(variation);
       } else {
           decrementStatus(-variation);
       }
    }

    /**
     * Calculates the status variation caused by a message
     * without modifying the current status.
     *
     * @param msg message to analyse
     * @return positive or negative status variation
     */
    @Override
    public int calculateStatusChange(Message msg) {

        int vowels = msg.countVowels();
        int consonants = msg.countConsonants();
        int value = getStatusVariation();

        if (consonants == NO_CONSONANT) {
            return -value;
        }

        double ratio = (double) vowels / consonants;

        if (ratio > PIVOT_RATIO) {
            return value;
        }
        return -value;
    }

    /**
     * Returns the status variation corresponding to 10% of the maximum status.
     * @return status variation value
     */
    private int getStatusVariation() {
        return MyGlobals.MAX_STATUS * MAX_PERCENT / MyGlobals.DIVISION_PERCENT;
    }
}