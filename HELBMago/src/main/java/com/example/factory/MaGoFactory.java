package com.example.factory;

import com.example.models.CircleMaGo;
import com.example.models.MaGo;
import com.example.models.SquareMaGo;

/**
 * Creates a single MaGo instance.
 * Centralizes MaGo instantiation logic so it is not duplicated
 * elsewhere, and to keep the class responsible only for creation.
 */
public class MaGoFactory {

    private static final int PARITY_DIVISOR = 2;
    private static final int EVEN_REMAINDER = 0;

    public MaGoFactory() {
    }

    /**
     * Creates a MaGo according to the given digit.
     * Even digits create a Circle MaGo, odd digits create a Square MaGo.
     *
     * @param digit digit used to determine the MaGo type
     * @return the created MaGo
     */
    public static MaGo createMaGo(int digit) {
        if (digit % PARITY_DIVISOR == EVEN_REMAINDER) {
            return new CircleMaGo();
        }else {
            return new SquareMaGo();
        }
    }
}