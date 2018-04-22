package com.tetris.game_utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tetris.enums.Direction;

class Figure {
    private Square[] squareArray;
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

    boolean canMove(Direction direction, Square[][] boardSquareArray) {
        for (Square square : squareArray) {
            if (!square.canMove(direction, boardSquareArray))
                return false;
        }
        return true;
    }

    Square[] getSquareArray() {
        return squareArray;
    }
}
