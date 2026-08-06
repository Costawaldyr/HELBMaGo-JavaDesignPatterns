package com.example.models;

import com.example.utils.MyGlobals;

/**
 * This class represents a MaGo.
 * A MaGo owns a status and reacts to messages.
 */
public abstract class MaGo {

    private static final int INITIAL_STATUS = (MyGlobals.MIN_STATUS + MyGlobals.MAX_STATUS) / 2;

    private int status;

    public MaGo(){
        this.status = INITIAL_STATUS;
    }

    //GETTER
    public int getStatus() { return status; }

    /**
     * SETTER
     * Keeps the status inside the allowed interval.
    */
    public void setStatus(int newValue) {

        if (newValue > MyGlobals.MAX_STATUS) {
            newValue = MyGlobals.MAX_STATUS;
        }

        if (newValue < MyGlobals.MIN_STATUS) {
            newValue = MyGlobals.MIN_STATUS;
        }
        this.status = newValue;
    }

    /**
     * Increases the status.
     * @param value value to add
    */
    public void incrementStatus(int value) {
        setStatus(getStatus() + value);
    }

    /**
     * Decreases the status.
     * @param value value to subtract
    */
    public void decrementStatus(int value) {
        setStatus(getStatus() - value);
    }

    /**
     * Return a type of MaGo (cicle or square)
     * each subclasses shoud implemented this abstract function
    */
    public abstract String getType();

    /**
     * Reacts to a message.
     * @param message received message
     * each subclasses shoud implemented this abstract function
    */
    public abstract void reactToMessage(Message msg);

    /**
     * Calculates the status variation caused by a message
     * without modifying the current status.
     *
     * @param msg message to analyse
     * @return predicted status variation
     */
    public abstract int calculateStatusChange(Message msg);
}
