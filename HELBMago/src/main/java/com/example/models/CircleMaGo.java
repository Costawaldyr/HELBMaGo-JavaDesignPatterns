package com.example.models;

import com.example.utils.MyGlobals;


/**
 * Represents a circle MaGo.
 * A circle MaGo reacts according to the number of positive punctuation marks.
*/
public class CircleMaGo extends MaGo{

    private static final int MAX_PERCENT_GAIN = 5;
    private static final int MAX_PERCENT_LOSS = 10;
    private static final int NO_PUNCTUATION = 0;
    private static final String TYPE = "Circle";

    public CircleMaGo() {
        super();
    }

    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * Updates the status according to the number of positive punctuation marks.
     * @param msg received message
     */
    @Override
    public void reactToMessage(Message msg) {
        int statusChange = calculateStatusChange(msg);

        if (statusChange > MyGlobals.ZERO) {
            incrementStatus(statusChange);
        } else {
            decrementStatus(-statusChange);
        }
    }

    /**
     * Calculates the status variation without modifying the MaGo.
     *
     * @param msg message to evaluate
     * @return predicted status variation
     */
    @Override
    public int calculateStatusChange(Message msg) {
        int punctuation = msg.countPositivePunctuation();

        if (punctuation == NO_PUNCTUATION) {
            return -getTenPercentStatus();
        }
        return punctuation * getFivePercentStatus();
    }

    /**
     * Returns 5% of the maximum status.
     * @return status value corresponding to 5%
     */
    private int getFivePercentStatus() {
        return MyGlobals.MAX_STATUS * MAX_PERCENT_GAIN / MyGlobals.DIVISION_PERCENT;
    }

    /**
     * Returns 10% of the maximum status.
     * @return status value corresponding to 10%
     */
    private int getTenPercentStatus() {
        return MyGlobals.MAX_STATUS * MAX_PERCENT_LOSS / MyGlobals.DIVISION_PERCENT;
    }

}