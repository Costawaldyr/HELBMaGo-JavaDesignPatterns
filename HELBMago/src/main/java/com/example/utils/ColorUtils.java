package com.example.utils;

import javafx.scene.paint.Color;

/**
 * Utility class used to convert a MaGo status into a color.
 * Positive status produce shades of red, while negative status produce shades of blue.
 */
public class ColorUtils {

    private static final int MAX_RGB = 255;
    private static final int ZERO = 0;

    /**
     * Returns the color associated with a MaGo status.
     * @param status the MaGo status
     * @return red if status is positive and blue if is negative 
    */
    public static Color getColor(int status) {

        if(status >= ZERO){
            int value = MAX_RGB - status * MAX_RGB / MyGlobals.MAX_STATUS;
            return Color.rgb(MAX_RGB, value, value);
        }
        int value = MAX_RGB - (-status) * MAX_RGB / MyGlobals.MAX_STATUS;
        return Color.rgb(value, value, MAX_RGB);
    }

}
