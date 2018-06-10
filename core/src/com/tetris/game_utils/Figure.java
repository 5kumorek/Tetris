package com.tetris.game_utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tetris.enums.Direction;

import java.awt.*;
import java.util.ArrayList;

class Figure {
    private Square[] squareArray;
    private Point center;

    Figure(int centerX, int centerY, Square[] squareArray) {
        if (centerX < 0 || centerX >= Board.ARRAY_WIDTH || centerY < 0)
            throw new IllegalArgumentException();
        center = new Point(centerX, centerY);
        this.squareArray = squareArray;
    }

    void draw(SpriteBatch spriteBatch) {
        for (Square square : squareArray)
            square.draw(spriteBatch);
    }

    void drawNext(SpriteBatch spriteBatch, boolean isFirstNext) {
        for (Square square : squareArray)
            square.drawNext(spriteBatch, isFirstNext);
    }

    void move(Direction direction) {
        center.translate(direction.getX(), direction.getY());
        for (Square square : squareArray)
            square.move(direction);
    }

    boolean canMove(Direction direction, ArrayList<Square> boardSquareArray) {
        for (Square square : squareArray)
            if (!square.canMove(direction, boardSquareArray))
                return false;
        return true;
    }

    void rotate() {
        for (Square square : squareArray)
            square.rotate(center);
    }

    boolean canRotate(ArrayList<Square> boardSquareArray) {
        for (Square square : squareArray)
            if (!square.canRotate(center, boardSquareArray))
                return false;
        return true;
    }

    boolean isOverlapping(ArrayList<Square> boardSquareArray) {
        for (Square square : squareArray)
            if (square.isOverlapping(boardSquareArray))
                return true;
        return false;
    }

    Square[] getSquareArray() {
        return squareArray;
    }
}
