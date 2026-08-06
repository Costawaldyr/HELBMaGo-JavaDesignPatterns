package com.example.models;

import java.util.ArrayList;
import java.util.List;

import com.example.interfaces.MaGoObserver;
import com.example.interfaces.MaGoSubject;

/**
 * Represents a population of MaGo.
 * Stores all MaGo and notifies observers
 * whenever the population changes.
 */
public class MaGoPopulation implements MaGoSubject {

    private static final int INITIAL_TOTAL = 0;

    private List<MaGo> maGoList;
    private List<MaGoObserver> observerList;

    public MaGoPopulation() {
        maGoList = new ArrayList<>();
        observerList = new ArrayList<>();
    }

    //Getter
    public List<MaGo> getMaGoList(){ return maGoList; }
    public int getNumberOfMaGos(){ return maGoList.size(); }

    /**
     * Updates the status of a MaGo and notifies all registered observers.
     * This ensures that every view displaying the population is refreshed
     * after the status modification.
     *
     * @param maGo MaGo whose status has to be updated
     * @param newStatus new status value to apply
     */
    public void updateMaGoStatus(MaGo maGo, int newStatus) {
        maGo.setStatus(newStatus);
        notifyObservers();
    }

    /**
     * Computes the average status of all MaGo.
     * @return average status of the population
     */
    public int getAverageStatus() {
        if (maGoList.isEmpty()) {  
            throw new IllegalStateException("List is empty"); 
        }

        int totalStatus = INITIAL_TOTAL;

        for (MaGo maGo : maGoList ) {
            totalStatus += maGo.getStatus();
        }
        return totalStatus / maGoList.size();
    }

    /**
     * Adds a MaGo to the population.
     * @param maGo MaGo to add
     */
    public void addMaGo(MaGo maGo){ 
        maGoList.add(maGo); 
        notifyObservers();
    }

    /**
     * removes a MaGo to the population.
     * @param maGo MaGo to remove
     */
    public void removeMaGo(MaGo maGo){ 
        maGoList.remove(maGo); 
        notifyObservers();
    }

    /**
     * Sends a message to every MaGo.
     * @param message message to process
     */
    public void reactToMessage(Message msg){
        for (MaGo maGo : maGoList) {
            maGo.reactToMessage(msg);
        }
        notifyObservers();
    }

    /**
     * Registers an observer.
     * @param observer observer to add
     */
    @Override
    public void attach(MaGoObserver observer) {
        observerList.add(observer);
    }

    /**
     * Removes an observer.
     * @param observer observer to remove
     */
    @Override
    public void detach(MaGoObserver observer) {
        observerList.remove(observer);
    }

    /**
     * Notifies all registered observers.
     */
    @Override
    public void notifyObservers() {
        for (MaGoObserver observer : observerList) {
            observer.update(this);
        }
    }
}
