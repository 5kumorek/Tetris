package com.tetris.game_utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tetris.enums.Direction;

import java.awt.*;

class Figure {
    private Square[] squareArray;
    private Point center;

    Figure(int center_x, int center_y, Square[] squareArray) {
        center = new Point(center_x, center_y);
        this.squareArray = squareArray;
    }

    void draw(SpriteBatch spriteBatch) {
        for (Square square : squareArray)
            square.draw(spriteBatch);
    }

    void move(Direction direction) {
        center.translate(direction.getX(), direction.getY());
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

    boolean canRotate(Square[][] boardSquareArray) {
        for (Square square : squareArray) {
            if (!square.canRotate(center, boardSquareArray))
                return false;
        }
        return true;
    }

    Square[] getSquareArray() {
        return squareArray;
    }
}
