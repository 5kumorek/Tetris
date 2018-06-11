package com.tetris.game_utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tetris.enums.Direction;

import java.awt.*;
import java.util.ArrayList;

/**
 * Encapsulates single moving figure.
 */
class Figure {
    private Square[] squareArray;
    private Point center;

    /**
     * Initializes this figure's position and shape.
     *
     * @param centerX     x of center of figure
     * @param centerY     y of center of figure
     * @param squareArray array of blocks for this figure
     */
    Figure(int centerX, int centerY, Square[] squareArray) {
        if (centerX < 0 || centerX >= Board.ARRAY_WIDTH || centerY < 0)
            throw new IllegalArgumentException();
        center = new Point(centerX, centerY);
        this.squareArray = squareArray;
    }

    /**
     * Draws this figure on given batch.
     *
     * @param spriteBatch batch to draw on
     */
    void draw(SpriteBatch spriteBatch) {
        for (Square square : squareArray)
            square.draw(spriteBatch);
    }

    /**
     * Draws figure as next one above the board.
     *
     * @param spriteBatch batch to draw on
     * @param isFirstNext draw it as first figure or second
     */
    void drawNext(SpriteBatch spriteBatch, boolean isFirstNext) {
        for (Square square : squareArray)
            square.drawNext(spriteBatch, isFirstNext);
    }

    /**
     * Moves figure one block in given direction.
     *
     * @param direction direction to move figure to
     */
    void move(Direction direction) {
        center.translate(direction.getX(), direction.getY());
        for (Square square : squareArray)
            square.move(direction);
    }

    /**
     * Checks if figure can move in given direction.
     *
     * @param direction        direction to check for move
     * @param boardSquareArray board of blocks to check against
     * @return does figure can move
     */
    boolean canMove(Direction direction, ArrayList<Square> boardSquareArray) {
        for (Square square : squareArray)
            if (!square.canMove(direction, boardSquareArray))
                return false;
        return true;
    }

    /**
     * Rotate this figure clockwise.
     */
    void rotate() {
        for (Square square : squareArray)
            square.rotate(center);
    }

    /**
     * Check if this figure can rotate.
     *
     * @param boardSquareArray array of blocks to check against
     * @return does this figure can rotate
     */
    boolean canRotate(ArrayList<Square> boardSquareArray) {
        for (Square square : squareArray)
            if (!square.canRotate(center, boardSquareArray))
                return false;
        return true;
    }

    /**
     * Check if this figure overlaps with any block from block array.
     * @param boardSquareArray array of blocks to check against
     * @return is this figure overlapping with any blocks
     */
    boolean isOverlapping(ArrayList<Square> boardSquareArray) {
        for (Square square : squareArray)
            if (square.isOverlapping(boardSquareArray))
                return true;
        return false;
    }

    /**
     * Gets array of blocks of this figure.
     * @return array of blocks of this figure
     */
    Square[] getSquareArray() {
        return squareArray;
    }
}
