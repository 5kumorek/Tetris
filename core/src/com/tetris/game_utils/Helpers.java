package com.tetris.game_utils;

public class Helpers {
    public static int limit(int numberToLimit, int lowerBound, int upperBound) {
        if (lowerBound > upperBound)
            throw new IllegalArgumentException();
        if (numberToLimit < lowerBound)
            return lowerBound;
        else if (numberToLimit > upperBound)
            return upperBound;
        else
            return numberToLimit;
    }
}
