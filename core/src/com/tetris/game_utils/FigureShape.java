package com.tetris.game_utils;

enum FigureShape {
    I(new int[][]{{0, -1}, {0, 0}, {0, 1}, {0, 2}}),
    O(new int[][]{{0, 0}, {1, 0}, {1, 1}, {0, 1}}),
    T(new int[][]{{-1, 0}, {0, 0}, {1, 0}, {0, -1}}),
    S(new int[][]{{-1, -1}, {0, -1}, {0, 0}, {1, 0}}),
    Z(new int[][]{{1, -1}, {0, -1}, {0, 0}, {-1, 0}}),
    J(new int[][]{{-1, -1}, {0, -1}, {0, 0}, {0, 1}}),
    L(new int[][]{{1, -1}, {0, -1}, {0, 0}, {0, 1}});

    private final Square[] squareArray;

    FigureShape(int[][] intArrayShape) {
        squareArray = new Square[intArrayShape.length];

        for (int i = 0; i < intArrayShape.length; i++) {
            int coordinates[] = intArrayShape[i];
            squareArray[i] = new Square(coordinates[0], coordinates[1]);
        }
    }

    Square[] getSquareArray() {
        return squareArray;
    }
}