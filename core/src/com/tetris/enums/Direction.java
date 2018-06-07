package com.tetris.enums;

/**
 * Class used to encapsulate directions in which figures can move.
 */
public enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int x, y;

    /**
     * Constructor for given enum example.
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for x coordinate.
     *
     * @return x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for y coordinate.
     *
     * @return y coordinate
     */
    public int getY() {
        return y;
    }
}