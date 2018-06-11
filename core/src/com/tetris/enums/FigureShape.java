package com.tetris.enums;

import java.awt.*;
import java.util.Arrays;

/**
 * Class used to encapsulate possible figure shapes.
 */
public enum FigureShape {
    I(new int[][]{{0, -1}, {0, 0}, {0, 1}, {0, 2}}),
    O(new int[][]{{0, 0}, {1, 0}, {1, 1}, {0, 1}}),
    T(new int[][]{{-1, 0}, {0, 0}, {1, 0}, {0, -1}}),
    S(new int[][]{{-1, -1}, {0, -1}, {0, 0}, {1, 0}}),
    Z(new int[][]{{1, -1}, {0, -1}, {0, 0}, {-1, 0}}),
    J(new int[][]{{-1, -1}, {0, -1}, {0, 0}, {0, 1}}),
    L(new int[][]{{1, -1}, {0, -1}, {0, 0}, {0, 1}});

    private final Point[] squareCoordinatesArray;

    /**
     * Constructor for example shapes.
     * @param intCoordinatesArray array of arrays of coordinates of blocks in figure
     */
    FigureShape(int[][] intCoordinatesArray) {
        squareCoordinatesArray = new Point[intCoordinatesArray.length];

        for (int i = 0; i < intCoordinatesArray.length; i++) {
            int[] coordinates = intCoordinatesArray[i];
            squareCoordinatesArray[i] = new Point(coordinates[0], coordinates[1]);
        }
    }

    /**
     * Gets array of arrays of coordinates of given figure
     * @return array of arrays of coordinates
     */
    public Point[] getSquareCoordinatesArray() {
        return Arrays.stream(squareCoordinatesArray).map(Point::new).toArray(Point[]::new);
    }
}