package com.tetris.game_utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tetris.enums.Direction;

import java.awt.*;
import java.util.ArrayList;

class Square {
    final static int PIXEL_SIZE = 30;

    private Texture squareTexture;
    private Point coordinates;

    Square(int x, int y, int squareColor) {
        coordinates = new Point(x, y);
        createSquareTexture(squareColor);
    }

    void draw(SpriteBatch batch) {
        batch.draw(squareTexture, PIXEL_SIZE * coordinates.x, PIXEL_SIZE * coordinates.y);
    }

    void move(Direction direction) {
        coordinates.translate(direction.getX(), direction.getY());
    }

    boolean canMove(Direction direction, ArrayList<Square> boardSquareArray) {
        Point translatedCoordinates = (Point) coordinates.clone();
        translatedCoordinates.translate(direction.getX(), direction.getY());

        return (!doCoordinatesCollide(translatedCoordinates, boardSquareArray) &&
                !willMoveOutOfBoard(translatedCoordinates, direction));
    }

    void rotate(Point figureCenter) {
        Point deltaCoordinates = (Point) coordinates.clone();
        deltaCoordinates.translate(-figureCenter.x, -figureCenter.y);

        Point translatedCoordinates = (Point) figureCenter.clone();
        translatedCoordinates.translate(deltaCoordinates.y, -deltaCoordinates.x);

        coordinates.setLocation(translatedCoordinates);
    }

    boolean canRotate(Point figureCenter, ArrayList<Square> boardSquareArray) {
        Point deltaCoordinates = (Point) coordinates.clone();
        deltaCoordinates.translate(-figureCenter.x, -figureCenter.y);

        Point translatedCoordinates = (Point) figureCenter.clone();
        translatedCoordinates.translate(deltaCoordinates.y, -deltaCoordinates.x);

        return (!doCoordinatesCollide(translatedCoordinates, boardSquareArray) &&
                !willBeOutOfBoard(translatedCoordinates));
    }

    boolean isOverlapping(ArrayList<Square> boardSquareArray) {
        return doCoordinatesCollide(coordinates, boardSquareArray);
    }

    int getY() {
        return coordinates.y;
    }

    private int getX() {
        return coordinates.x;
    }

    private void createSquareTexture(int squareColor) {
        int offset = 2;
        Pixmap pixmap = new Pixmap(PIXEL_SIZE, PIXEL_SIZE, Pixmap.Format.RGBA8888);
        pixmap.setColor(squareColor);
        pixmap.fillRectangle(offset, offset, PIXEL_SIZE - offset, PIXEL_SIZE - offset);
        squareTexture = new Texture(pixmap);
    }

    private boolean doCoordinatesCollide(Point coordinatesToCheck, ArrayList<Square> boardSquareArray) {
        return boardSquareArray.stream().anyMatch(square ->
                square.getX() == coordinatesToCheck.getX() && square.getY() == coordinatesToCheck.getY());
    }

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

    private boolean willBeOutOfBoard(Point coordinatesToCheck) {
        return (coordinatesToCheck.x < 0 ||
                coordinatesToCheck.y < 0 ||
                coordinatesToCheck.x >= Board.ARRAY_WIDTH ||
                coordinatesToCheck.y >= Board.ARRAY_HEIGHT);
    }
}
