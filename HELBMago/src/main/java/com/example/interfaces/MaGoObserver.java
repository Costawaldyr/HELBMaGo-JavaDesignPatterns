package com.example.interfaces;


/**
 * Defines an observer that receives updates from a subject.
 */
public interface MaGoObserver {

    /**
     * Updates the observer when the observed object changes.
     * @param source object that triggered the update
     */
    void update(Object source);
}

