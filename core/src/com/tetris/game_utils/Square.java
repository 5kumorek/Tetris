package com.tetris.game_utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tetris.enums.Direction;

import java.awt.*;

class Square {
    final static int PIXEL_SIZE = 35;

    private Texture squareTexture;
    private Point coordinates;

    Square(int x, int y) {
        coordinates = new Point(x, y);
        createSquareTexture();
    }

    void draw(SpriteBatch batch) {
        batch.draw(squareTexture, PIXEL_SIZE * coordinates.x, PIXEL_SIZE * coordinates.y);
    }

    void move(Direction direction) {
        coordinates.translate(direction.getX(), direction.getY());
    }

    boolean canMove(Direction direction, Square[][] boardSquareArray) {
        int translatedX = coordinates.x + direction.getX();
        int translatedY = coordinates.y + direction.getY();
        return (!willCollide(translatedX, translatedY, boardSquareArray) &&
                !willMoveOutOfBoard(translatedX, translatedY, direction));
    }

    void rotate(Point figureCenter) {
        int deltaY = coordinates.y - figureCenter.y;
        int deltaX = coordinates.x - figureCenter.x;
        int translatedX = figureCenter.x + deltaY;
        int translatedY = figureCenter.y - deltaX;
        coordinates.move(translatedX, translatedY);
    }

    boolean canRotate(Point figureCenter, Square[][] boardSquareArray) {
        int deltaY = coordinates.y - figureCenter.y;
        int deltaX = coordinates.x - figureCenter.x;
        int translatedX = figureCenter.x + deltaY;
        int translatedY = figureCenter.y - deltaX;
        return (!willCollide(translatedX, translatedY, boardSquareArray) &&
                !willBeOutOfBoard(translatedX, translatedY));
    }

    int getY() {
        return coordinates.y;
    }

    int getX() {
        return coordinates.x;
    }

    private void createSquareTexture() {
        int offset = 2;
        Pixmap pixmap = new Pixmap(PIXEL_SIZE, PIXEL_SIZE, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fillRectangle(offset, offset, PIXEL_SIZE - offset, PIXEL_SIZE - offset);
        squareTexture = new Texture(pixmap);
    }

    private boolean willCollide(int translatedX, int translatedY, Square[][] boardSquareArray) {
        int limitedX = Helpers.limit(translatedX, 0, Board.ARRAY_WIDTH - 1);
        int limitedY = Helpers.limit(translatedY, 0, Board.ARRAY_HEIGHT - 1);
        return boardSquareArray[limitedX][limitedY] != null;
    }

    private boolean willMoveOutOfBoard(int translatedX, int translatedY, Direction direction) {
        switch (direction) {
            case UP:
                return translatedY >= Board.ARRAY_HEIGHT;
            case DOWN:
                return translatedY < 0;
            case LEFT:
                return translatedX < 0;
            case RIGHT:
                return translatedX >= Board.ARRAY_WIDTH;
            default:
                throw new IllegalStateException();
        }
    }

    private boolean willBeOutOfBoard(int translatedX, int translatedY) {
        return (translatedX < 0 ||
                translatedY < 0 ||
                translatedX >= Board.ARRAY_WIDTH ||
                translatedY >= Board.ARRAY_HEIGHT);
    }
}
