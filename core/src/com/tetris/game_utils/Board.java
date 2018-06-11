package com.tetris.game_utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.tetris.enums.Direction;
import com.tetris.enums.FigureShape;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Class that encapsulates a single Board in which game takes place.
 */
public class Board {
    static final int ARRAY_WIDTH = 10;
    static final int ARRAY_HEIGHT = 20;
    public static final int PIXEL_WIDTH = ARRAY_WIDTH * Square.PIXEL_SIZE;
    private final int PIXEL_HEIGHT = ARRAY_HEIGHT * Square.PIXEL_SIZE;

    private Figure currentFigure;
    private Figure nextFigure;
    private Figure nextNextFigure;
    private Texture boardTexture;

    private Texture boardFrame;

    private int xCoordinateOfBoard;

    private SpriteBatch batch;
    private ArrayList<Square> squareArray = new ArrayList<>();
    private FigureFactory figureFactory;
    private Sound sound;
    private int points = 0;

    /**
     * Initializes sound, batch, figure factory for this board. Also moves batch to match board number.
     *
     * @param boardNumber     number of board (1-6)
     * @param boardBackground name of file with background
     * @param squareColor     color of squares in this board
     * @param spriteBatch     sprite batch to use
     */
    public Board(int boardNumber, String boardBackground, int squareColor, SpriteBatch spriteBatch) {
        createBoardTexture(boardBackground);
        batch = spriteBatch;
        sound = Gdx.audio.newSound(Gdx.files.internal("sound.mp3"));
        figureFactory = new FigureFactory(squareColor);
        xCoordinateOfBoard = boardNumber * PIXEL_WIDTH;
        batch.setTransformMatrix(new Matrix4(
                new Vector3(xCoordinateOfBoard, 0, 0),
                new Quaternion(),
                new Vector3(1, 1, 1))
        );
    }

    /**
     * Handles key press.
     *
     * @param pressedKey number of pressed key
     */
    public void handleKeyPress(int pressedKey) {
        if (!isMouseInsideBoard())
            return;

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
            case Input.Keys.DOWN:
                if (currentFigure != null && currentFigure.canMove(Direction.DOWN, squareArray))
                    currentFigure.move(Direction.DOWN);
                break;
        }
    }

    /**
     * Main loop of game.
     *
     * @param pointsSum   sum of points
     * @param boardNumber number of board
     *
     * @return is game ended
     */
    public boolean update(int pointsSum, int boardNumber) {
        if (nextNextFigure == null) {
            createRandomFigure();
            nextFigure = nextNextFigure;
            createRandomFigure();
        } else if (currentFigure == null) {
            currentFigure = nextFigure;
            nextFigure = nextNextFigure;
            createRandomFigure();
            if (currentFigure.isOverlapping(squareArray)) {
                loseGame(pointsSum, boardNumber);
                return true;
            }
        } else {
            if (currentFigure.canMove(Direction.DOWN, squareArray)) {
                currentFigure.move(Direction.DOWN);
            } else {
                sound.play();
                decomposeCurrentFigure();
                deleteFilledRows();
            }
        }
        return false;
    }


    /**
     * Checks if mouse inside board.
     *
     * @return is mouse inside board
     */
    @SuppressWarnings("WeakerAccess")
    boolean isMouseInsideBoard() {
        int x = Gdx.input.getX();
        int y = Gdx.graphics.getHeight() - Gdx.input.getY();
        return x >= xCoordinateOfBoard
                && x < xCoordinateOfBoard + PIXEL_WIDTH
                && y >= 0
                && y < PIXEL_HEIGHT;
    }

    /**
     * Draws all things on this board.
     */
    public void draw() {
        batch.begin();
        if (boardTexture != null)
            batch.draw(boardTexture, 0, 0, PIXEL_WIDTH, PIXEL_HEIGHT);
        batch.draw(boardFrame, 0, 0);
        drawSquareArray(batch);
        if (currentFigure != null)
            currentFigure.draw(batch);
        if (nextFigure != null)
            nextFigure.drawNext(batch, true);
        if (nextNextFigure != null)
            nextNextFigure.drawNext(batch, false);
        batch.end();
    }

    /**
     * Creates board texture given by provided background.
     *
     * @param boardBackground background from which create texture
     */
    private void createBoardTexture(String boardBackground) {
        Pixmap pixmap = new Pixmap(PIXEL_WIDTH, PIXEL_HEIGHT, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.SKY);
        pixmap.drawRectangle(0, 0, PIXEL_WIDTH, PIXEL_HEIGHT);
        boardFrame = new Texture(pixmap);
        if (boardBackground != null)
            boardTexture = new Texture(boardBackground);
        else
            boardTexture = null;
    }

    /**
     * Creates random figure.
     */
    private void createRandomFigure() {
        FigureShape[] figureShapeValues = FigureShape.values();
        int randomNumber = new Random().nextInt(figureShapeValues.length);
        FigureShape randomFigureShape = figureShapeValues[randomNumber];
        nextNextFigure = figureFactory.getFigure(ARRAY_WIDTH / 2, ARRAY_HEIGHT - 1, randomFigureShape);
    }

    /**
     * Decomposes current moving figure to blocks that will stand still.
     */
    private void decomposeCurrentFigure() {
        Square[] figureSquareArray = currentFigure.getSquareArray();
        squareArray.addAll(Arrays.asList(figureSquareArray));
        currentFigure = null;
    }

    /**
     * Deletes all filled rows.
     */
    private void deleteFilledRows() {
        for (int row = ARRAY_HEIGHT - 1; row >= 0; row--)
            if (isRowFull(row)) {
                clearRow(row);
                points++;
                moveRowsDown(row + 1);
            }
    }

    /**
     * Checks if row is full
     *
     * @param rowIndex index of row to check
     * @return is row full
     */
    private boolean isRowFull(int rowIndex) {
        int squaresInRow = squareArray.stream()
                .filter(square -> square.getY() == rowIndex)
                .collect(Collectors.toCollection(ArrayList::new))
                .size();
        return squaresInRow == ARRAY_WIDTH;
    }

    /**
     * Deletes all block in given row.
     *
     * @param rowIndex index of row to clear
     */
    private void clearRow(int rowIndex) {
        squareArray = squareArray.stream()
                .filter(square -> square.getY() != rowIndex)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Moves all blocks in starting row and higher 1 block below.
     *
     * @param startingRow row form which start moving blocks
     */
    private void moveRowsDown(int startingRow) {
        squareArray.stream()
                .filter(square -> square.getY() >= startingRow)
                .forEach(square -> square.move(Direction.DOWN));
    }

    /**
     * Draws all blocks.
     *
     * @param batch batch to draw on
     */
    private void drawSquareArray(SpriteBatch batch) {
        squareArray.forEach(square -> square.draw(batch));
    }

    /**
     * Saves score in file and exits game.
     * @param pointsSum gained points
     * @param boardNumber number of board
     */
    private void loseGame(int pointsSum, int boardNumber) {
        int topScores[][] = new int[6][10];
        try {
            Scanner s = new Scanner(new File("TopScores.txt"));
            for (int i = 0; i < 6; i++)
                for (int j = 0; j < 10; j++)
                    topScores[i][j] = s.nextInt();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find a file");
        }

        int ind;
        boolean change = false;
        for (ind = 0; ind < 10; ind++)
            if (topScores[boardNumber][ind] < pointsSum) {
                change = true;
                break;
            }

        if (change) {
            int tmp = topScores[boardNumber][ind];
            topScores[boardNumber][ind] = pointsSum;
            for (int i = ind; i < topScores[boardNumber].length - 1; i++) {
                int tmp2 = topScores[boardNumber][i + 1];
                topScores[boardNumber][i + 1] = tmp;
                tmp = tmp2;
            }
        }

        BufferedWriter outputWriter;
        try {
            outputWriter = new BufferedWriter(new FileWriter("TopScores.txt"));
            for (int i = 0; i < 6; i++)
                for (int j = 0; j < 10; j++)
                    outputWriter.write(topScores[i][j] + "\n");
            outputWriter.flush();
            outputWriter.close();
        } catch (IOException e) {
            System.out.println("Unable to save score");
        }
    }

    /**
     * Getter for points.
     * @return points
     */

    public int getPoints() {
        return points;
    }
}
