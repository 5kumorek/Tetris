package com.tetris.game_utils;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tetris.enums.Direction;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class that encapsulates a single square in Figure
 */
class Square {
    final static int PIXEL_SIZE = 20;

    private Texture squareTexture;
    private Point coordinates;

    /**
     * Constructor for square
     * @param x x coordinate
     * @param y y coordinate
     * @param squareColor Color of square
     */
    Square(int x, int y, int squareColor) {
        coordinates = new Point(x, y);
        createSquareTexture(squareColor);
    }

    /**
     * Draws a square
     * @param batch batch to draw on
     */
    void draw(SpriteBatch batch) {
        batch.draw(squareTexture, PIXEL_SIZE * coordinates.x, PIXEL_SIZE * coordinates.y);
    }

    /**
     * Draws a square of next figure
     * @param batch batch to draw on
     * @param isFirstNext draw it as part of first next figure or second
     */
    void drawNext(SpriteBatch batch, boolean isFirstNext) {
        if(isFirstNext) {
            batch.draw(squareTexture, PIXEL_SIZE * coordinates.x, PIXEL_SIZE * coordinates.y + 60);
        }
        else {
            batch.draw(squareTexture, PIXEL_SIZE * coordinates.x, PIXEL_SIZE * coordinates.y + 180);
        }
    }

    /**
     * Moves a square in specified direction
     * @param direction direction to move square to
     */
    void move(Direction direction) {
        coordinates.translate(direction.getX(), direction.getY());
    }

    boolean canMove(Direction direction, ArrayList<Square> boardSquareArray) {
        Point translatedCoordinates = (Point) coordinates.clone();
        translatedCoordinates.translate(direction.getX(), direction.getY());

        return (!doCoordinatesCollide(translatedCoordinates, boardSquareArray) &&
                !willMoveOutOfBoard(translatedCoordinates, direction));
    }

    /**
     * Rotates a square
     * @param figureCenter coordinates of center of a figure
     */
    void rotate(Point figureCenter) {
        Point deltaCoordinates = (Point) coordinates.clone();
        deltaCoordinates.translate(-figureCenter.x, -figureCenter.y);

        Point translatedCoordinates = (Point) figureCenter.clone();
        translatedCoordinates.translate(deltaCoordinates.y, -deltaCoordinates.x);

        coordinates.setLocation(translatedCoordinates);
    }

    /**
     * Checks is it possible to rotate
     * @param figureCenter coordinates of center of a figure
     * @param boardSquareArray array of coordinates
     * @return is possible to rotate
     */
    boolean canRotate(Point figureCenter, ArrayList<Square> boardSquareArray) {
        Point deltaCoordinates = (Point) coordinates.clone();
        deltaCoordinates.translate(-figureCenter.x, -figureCenter.y);

        Point translatedCoordinates = (Point) figureCenter.clone();
        translatedCoordinates.translate(deltaCoordinates.y, -deltaCoordinates.x);

        return (!doCoordinatesCollide(translatedCoordinates, boardSquareArray) &&
                !willBeOutOfBoard(translatedCoordinates));
    }

    /**
     * Checks if this square overlaps with any block from block array
     * @param boardSquareArray array of coordinates
     * @return is this square overlapping with any blocks
     */
    boolean isOverlapping(ArrayList<Square> boardSquareArray) {
        return doCoordinatesCollide(coordinates, boardSquareArray);
    }

    /**
     * Getter for y coordinate
     * @return y coordinate
     */
    public int getY() {
        return coordinates.y;
    }

    /**
     * Getter for x coordinate
     * @return x coordinate
     */
    public int getX() {
        return coordinates.x;
    }

    /**
     * Creates square texture
     * @param squareColor color of a square
     */
    private void createSquareTexture(int squareColor) {
        int offset = 2;
        Pixmap pixmap = new Pixmap(PIXEL_SIZE, PIXEL_SIZE, Pixmap.Format.RGBA8888);
        pixmap.setColor(squareColor);
        pixmap.fillRectangle(offset, offset, PIXEL_SIZE - offset, PIXEL_SIZE - offset);
        squareTexture = new Texture(pixmap);
    }

    /**
     * Checks if coordinates collide
     * @param coordinatesToCheck coordinates to be checked
     * @param boardSquareArray array of coordinates
     * @return is coordinates collide
     */
    private boolean doCoordinatesCollide(Point coordinatesToCheck, ArrayList<Square> boardSquareArray) {
        return boardSquareArray.stream().anyMatch(square ->
                square.getX() == coordinatesToCheck.getX() && square.getY() == coordinatesToCheck.getY());
    }

    /**
     * Checks if square will be out of board after move
     * @param coordinatesToCheck coordinates to be checked
     * @param direction direction to move figure to
     * @return will square move out of board
     */
    private boolean willMoveOutOfBoard(Point coordinatesToCheck, Direction direction) {
        switch (direction) {
            case UP:
                return coordinatesToCheck.y >= Board.ARRAY_HEIGHT;
            case DOWN:
                return coordinatesToCheck.y < 0;
            case LEFT:
                return coordinatesToCheck.x < 0;
            case RIGHT:
                return coordinatesToCheck.x >= Board.ARRAY_WIDTH;
            default:
                throw new IllegalStateException();
        }
    }

    /**
     * Checks if square will be out of board after rotation
     * @param coordinatesToCheck coordinates to be checked
     * @return will square move out of board after rotation
     */
    private boolean willBeOutOfBoard(Point coordinatesToCheck) {
        return (coordinatesToCheck.x < 0 ||
                coordinatesToCheck.y < 0 ||
                coordinatesToCheck.x >= Board.ARRAY_WIDTH ||
                coordinatesToCheck.y >= Board.ARRAY_HEIGHT);
    }
}
