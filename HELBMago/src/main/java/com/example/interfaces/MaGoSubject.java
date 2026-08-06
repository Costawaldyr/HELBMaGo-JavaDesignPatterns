package com.example.interfaces;

/**
 * Defines an object that can be observed by observers.
 * A subject maintains a list of observers and notifies them
 * whenever its state changes.
 */
public interface MaGoSubject {

    /**
     * Adds an observer to the subject.
     * @param observer observer to add
     */
    void attach(MaGoObserver observer);

    /**
     * Removes an observer from the subject.
     * @param observer observer to remove
     */
    void detach(MaGoObserver observer);

    /**
     * Notifies all registered observers about a change.
     */
    void notifyObservers();
}