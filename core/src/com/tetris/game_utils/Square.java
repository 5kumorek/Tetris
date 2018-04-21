package com.tetris.game_utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tetris.enums.Direction;

class Square {
    final static int PIXEL_SIZE = 35;

    private Texture squareTexture;
    private int x, y;

    Square(int x, int y) {
        this.x = x;
        this.y = y;
        createSquareTexture();
    }

    void draw(SpriteBatch batch) {
        batch.draw(squareTexture, PIXEL_SIZE * x, PIXEL_SIZE * y);
    }

    void move(Direction direction) {
        x += direction.getX();
        y += direction.getY();
    }

    boolean canMove(Direction direction, Square boardSquareArray[][]) {
        int translatedX = x + direction.getX();
        int translatedY = y + direction.getY();
        return (!isColliding(translatedX, translatedY, boardSquareArray) &&
                !isOutOfBoard(translatedX, translatedY, direction));
    }

    private boolean isColliding(int translatedX, int translatedY, Square boardSquareArray[][]) {
        int limitedX = Helpers.limit(translatedX, 0, Board.ARRAY_WIDTH - 1);
        int limitedY = Helpers.limit(translatedY, 0, Board.ARRAY_HEIGHT - 1);
        return boardSquareArray[limitedX][limitedY] != null;
    }

    private boolean isOutOfBoard(int translatedX, int translatedY, Direction direction) {
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

    int getY() {
        return y;
    }

    int getX() {
        return x;
    }

    private void createSquareTexture() {
        Pixmap pixmap = new Pixmap(PIXEL_SIZE, PIXEL_SIZE, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.drawRectangle(0, 0, PIXEL_SIZE, PIXEL_SIZE);
        squareTexture = new Texture(pixmap);
    }
}
