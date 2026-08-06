package com.example.controller;

import com.example.models.MaGo;
import com.example.models.MaGoPopulation;
import com.example.view.MaGoControlView;
import com.example.utils.MyGlobals;


/**
 * Controls the MaGo control window.
 * Allows the user to modify the status of one MaGo.
 */
public class MaGoControlController {

    private MaGoControlView view;
    private MaGoPopulation population;
    private MaGo maGo;

    /**
     * Creates the controller of the MaGo control window.
     * Registers all user actions.
     *
     * @param view control window
     * @param maGo selected MaGo
     * @param population MaGo population
     */
    public MaGoControlController(MaGoControlView view,MaGo maGo,MaGoPopulation population) {
        this.view = view;
        this.maGo = maGo;
        this.population = population;

        setActions();
    }

    /**
     * Registers all actions of the control window.
     */
    private void setActions() {
        view.getTextFieldNewValue().setOnKeyReleased(e -> checkValue());
        view.geButtonConfirm().setOnAction(e -> handleConfirm());
    }

    /**
     * Checks whether the entered value is valid.
     * Enables the Confirm button only if the value is between the allowed status bounds.
     */
    private void checkValue() {
        try {

            int value = Integer.parseInt(view.getNewValue());
            if (value >= MyGlobals.MIN_STATUS && value <= MyGlobals.MAX_STATUS) {
                view.geButtonConfirm().setDisable(false);
            } else {
                view.geButtonConfirm().setDisable(true);
            }

        } catch (NumberFormatException e) {
            view.geButtonConfirm().setDisable(true);
        }
    }

    /**
     * Updates the MaGo status with the entered value
     * and closes the control window.
     */
    private void handleConfirm() {
        int value = Integer.parseInt(view.getNewValue());
        population.updateMaGoStatus(maGo, value);
        view.close();
    }

}