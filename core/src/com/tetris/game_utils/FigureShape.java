package com.tetris.game_utils;

import java.awt.*;

enum FigureShape {
    I(new int[][]{{0, -1}, {0, 0}, {0, 1}, {0, 2}}),
    O(new int[][]{{0, 0}, {1, 0}, {1, 1}, {0, 1}}),
    T(new int[][]{{-1, 0}, {0, 0}, {1, 0}, {0, -1}}),
    S(new int[][]{{-1, -1}, {0, -1}, {0, 0}, {1, 0}}),
    Z(new int[][]{{1, -1}, {0, -1}, {0, 0}, {-1, 0}}),
    J(new int[][]{{-1, -1}, {0, -1}, {0, 0}, {0, 1}}),
    L(new int[][]{{1, -1}, {0, -1}, {0, 0}, {0, 1}});

    private final Point[] squareCoordinatesArray;

    FigureShape(int[][] intCoordinatesArray) {
        squareCoordinatesArray = new Point[intCoordinatesArray.length];

        for (int i = 0; i < intCoordinatesArray.length; i++) {
            int coordinates[] = intCoordinatesArray[i];
            squareCoordinatesArray[i] = new Point(coordinates[0], coordinates[1]);
        }
    }

    Point[] getSquareCoordinatesArray() {
        return squareCoordinatesArray;
    }
}