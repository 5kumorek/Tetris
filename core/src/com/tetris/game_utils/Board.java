package com.tetris.game_utils;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tetris.enums.Direction;
import com.tetris.enums.FigureShape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class Board {
    static final int ARRAY_WIDTH = 10;
    static final int ARRAY_HEIGHT = 20;
    private final int PIXEL_WIDTH = ARRAY_WIDTH * Square.PIXEL_SIZE;
    private final int PIXEL_HEIGHT = ARRAY_HEIGHT * Square.PIXEL_SIZE;

    private Texture boardTexture;
    private ArrayList<Square> squareArray = new ArrayList<>();
    private Figure currentFigure;
    private FigureFactory figureFactory = new FigureFactory();

    public Board() {
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
            case Input.Keys.SPACE:
                if (currentFigure != null && currentFigure.canRotate(squareArray))
                    currentFigure.rotate();
                break;
        }
    }

    public void update() {
        if (currentFigure == null) {
            createRandomFigure();
            if (currentFigure.isOverlapping(squareArray))
                loseGame();
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
        FigureShape[] figureShapeValues = FigureShape.values();
        int randomNumber = new Random().nextInt(figureShapeValues.length);
        FigureShape randomFigureShape = figureShapeValues[randomNumber];
        currentFigure = figureFactory.getFigure(ARRAY_WIDTH / 2, ARRAY_HEIGHT - 1, randomFigureShape);
    }

    private void decomposeCurrentFigure() {
        Square[] figureSquareArray = currentFigure.getSquareArray();
        squareArray.addAll(Arrays.asList(figureSquareArray));
        currentFigure = null;
    }

    private void deleteFilledRows() {
        for (int row = ARRAY_HEIGHT - 1; row >= 0; row--)
            if (isRowFull(row)) {
                clearRow(row);
                moveRowsDown(row + 1);
            }
    }

    private boolean isRowFull(int rowIndex) {
        int squaresInRow = squareArray.stream()
                .filter(square -> square.getY() == rowIndex)
                .collect(Collectors.toCollection(ArrayList::new))
                .size();
        return squaresInRow == ARRAY_WIDTH;
    }

    private void clearRow(int rowIndex) {
        squareArray = squareArray.stream()
                .filter(square -> square.getY() != rowIndex)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private void moveRowsDown(int startingRow) {
        squareArray.stream()
                .filter(square -> square.getY() >= startingRow)
                .forEach(square -> square.move(Direction.DOWN));
    }

    private void drawSquareArray(SpriteBatch batch) {
        squareArray.forEach(square -> square.draw(batch));
    }

    private void loseGame() {
        System.out.println("PRZEGRANA");
        System.exit(0);
    }
}
