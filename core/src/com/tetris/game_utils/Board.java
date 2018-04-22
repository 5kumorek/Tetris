package com.tetris.game_utils;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tetris.enums.Direction;
import com.tetris.enums.FigureShape;

import java.util.Random;

public class Board {
    static final int ARRAY_WIDTH = 10;
    static final int ARRAY_HEIGHT = 20;
    private final int PIXEL_WIDTH = ARRAY_WIDTH * Square.PIXEL_SIZE;
    private final int PIXEL_HEIGHT = ARRAY_HEIGHT * Square.PIXEL_SIZE;

    private Texture boardTexture;
    private Square squareArray[][] = new Square[ARRAY_WIDTH][ARRAY_HEIGHT];
    private Figure currentFigure;
    private FigureFactory figureFactory;

    public Board() {
        figureFactory = new FigureFactory();
        createBoardTexture();
    }

    public void handleKeyPress(int pressedKey) {
        switch (pressedKey) {
            case Input.Keys.LEFT:
                if (currentFigure != null && currentFigure.canMove(Direction.LEFT, squareArray))
                    currentFigure.move(Direction.LEFT);
                break;
            case Input.Keys.RIGHT:
                if (currentFigure != null && currentFigure.canMove(Direction.RIGHT, squareArray))
                    currentFigure.move(Direction.RIGHT);
                break;
        }
    }

    public void update() {
        if (currentFigure == null) {
            createRandomFigure();
        } else {
            if (currentFigure.canMove(Direction.DOWN, squareArray)) {
                currentFigure.move(Direction.DOWN);
            } else {
                decomposeCurrentFigure();
                deleteFilledRows();
            }
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(boardTexture, 0, 0);
        drawSquareArray(batch);
        if (currentFigure != null)
            currentFigure.draw(batch);
    }

    private void createBoardTexture() {
        Pixmap pixmap = new Pixmap(PIXEL_WIDTH, PIXEL_HEIGHT, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.SKY);
        pixmap.drawRectangle(0, 0, PIXEL_WIDTH, PIXEL_HEIGHT);
        boardTexture = new Texture(pixmap);
    }

    private void createRandomFigure() {
        FigureShape figureShapeValues[] = FigureShape.values();
        int randomNumber = new Random().nextInt(figureShapeValues.length);
        FigureShape randomFigureShape = figureShapeValues[randomNumber];
        currentFigure = figureFactory.getFigure(ARRAY_WIDTH / 2, ARRAY_HEIGHT - 1, randomFigureShape);
    }

    private void decomposeCurrentFigure() {
        Square figureSquareArray[] = currentFigure.getSquareArray();
        for (Square square : figureSquareArray) {
            squareArray[square.getX()][square.getY()] = square;
        }
        currentFigure = null;
    }

    private void deleteFilledRows() {
        for (int row = ARRAY_HEIGHT - 1; row >= 0; row--) {
            if (isRowFull(row)) {
                clearRow(row);
                moveRowsDown(row);
            }
        }
    }

    private boolean isRowFull(int rowIndex) {
        for (int i = 0; i < ARRAY_WIDTH; i++) {
            if (squareArray[i][rowIndex] == null)
                return false;
        }
        return true;
    }

    private void clearRow(int rowIndex) {
        for (int column = 0; column < ARRAY_WIDTH; column++) {
            squareArray[column][rowIndex] = null;
        }
    }

    private void moveRowsDown(int startingRow) {
        for (int column = 0; column < ARRAY_WIDTH; column++) {
            for (int row = startingRow; row < ARRAY_HEIGHT; row++) {
                Square currentSquare = squareArray[column][row];
                if (currentSquare != null)
                    currentSquare.move(Direction.DOWN);
            }
        }
    }

    private void drawSquareArray(SpriteBatch batch) {
        for (Square[] squareRow : squareArray)
            for (Square square : squareRow)
                if (square != null)
                    square.draw(batch);
    }

}
