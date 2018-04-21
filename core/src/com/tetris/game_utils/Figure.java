package com.tetris.game_utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tetris.enums.Direction;

class Figure implements Shape {
    private Square squareArray[];
    private int center_x, center_y;

    Figure(int center_x, int center_y, Square[] squareArray) {
        this.center_x = center_x;
        this.center_y = center_y;
        this.squareArray = squareArray;
    }

    void draw(SpriteBatch spriteBatch) {
        for (Square square : squareArray)
            square.draw(spriteBatch);
    }

    void move(Direction direction) {
        for (Square square : squareArray) {
            square.move(direction);
        }
    }

    boolean canMove(Direction direction, Square boardSquareArray[][]) {
        for (Square square : squareArray) {
            if (!square.canMove(direction, boardSquareArray))
                return false;
        }
        return true;
    }

    boolean reachedBottom() {
        for (Square square : squareArray) {
            if (square.getY() == 0)
                return true;
        }
        return false;
    }

    Square[] getSquareArray() {
        return squareArray;
    }


//    private int position[][];
//
//    public Figure() {
//        position = new int[4][2];
//        int[][][] coordinates = new int[][][]{
//                {{0, -1}, {0, 0}, {0, 1}, {0, 2}}, //I
//                {{0, 0}, {0, 1}, {1, 0}, {1, 1}}, //O
//                {{0, -1}, {0, 0}, {0, 1}, {1, 0}}, //T
//                {{0, -1}, {0, 0}, {1, 0}, {1, 1}}, //S
//                {{-1, 1}, {0, -1}, {0, 0}, {-1, 0}}, //Z
//                {{1, -1}, {0, -1}, {0, 0}, {0, 1}}, //J
//                {{-1, -1}, {0, -1}, {0, 0}, {0, 1}} //L
//        };
//
//        Random rand = new Random();
//        int randomPiece = Math.abs(rand.nextInt()) % 7;
//
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 2; j++) {
//                position[i][j] = coordinates[randomPiece][i][j];
//            }
//        }
//    }
//
//    public int getX(int index) {
//        return position[index][0];
//    }
//
//    public int getY(int index) {
//        return position[index][1];
//    }
//
//    public int minX() {
//        int min = position[0][0];
//        for (int i = 0; i < 4; i++) {
//            if (position[i][0] < min)
//                min = position[i][0];
//        }
//        return min;
//    }
//
//    public int minY() {
//        int min = position[0][1];
//        for (int i = 0; i < 4; i++) {
//            if (position[i][1] < min)
//                min = position[i][1];
//        }
//        return min;
//    }

}
