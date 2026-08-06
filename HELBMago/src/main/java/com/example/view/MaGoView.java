package com.example.view;

import javafx.scene.shape.Shape;

import com.example.models.MaGo;
import com.example.utils.ColorUtils;

/**
 * Represents the graphical view of a MaGo.
 * Keeps the displayed shape synchronized with the model.
 */
public class MaGoView {

    private MaGo maGo;
    private Shape shape;

    /**
     * Creates the view associated with a MaGo.
     *
     * @param maGo associated model
     * @param shape graphical representation
     */
    public MaGoView(MaGo maGo, Shape shape) {
        this.maGo = maGo;
        this.shape = shape;
    }

    /**
     * Refreshes the shape color according to the MaGo status.
     */
    public void refresh() {
        shape.setFill(ColorUtils.getColor(maGo.getStatus()));
    }
}
