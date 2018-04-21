package com.tetris.game_utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tetris.other_classes.IBoard;

import java.util.Random;

public class Board implements IBoard {
    private final int ARRAY_WIDTH = 10;
    private final int ARRAY_HEIGHT = 20;
    private final int PIXEL_WIDTH = ARRAY_WIDTH * Square.PIXEL_SIZE;
    private final int PIXEL_HEIGHT = ARRAY_HEIGHT * Square.PIXEL_SIZE;

    private Texture boardTexture;
    private Square squareArray[][] = new Square[ARRAY_WIDTH][ARRAY_HEIGHT];
    private Figure currentFigure;
    private FigureFactory figureFactory;

    private Texture texture;
    private ShapeRenderer shapeRenderer;
    private int currentX = 0;
    private int currentY = 0;
    private int step = 30;
    private boolean isDown = false;
    private boolean filledGrid[][];


    public Board() {
        figureFactory = new FigureFactory();
        createBoardTexture();
        // TODO: delete these example squares later
        squareArray[0][0] = new Square(0, 0);
        squareArray[9][0] = new Square(9, 0);
        squareArray[4][5] = new Square(4, 5);
        squareArray[1][1] = new Square(1, 1);
        squareArray[0][2] = new Square(1, 2);
        squareArray[3][3] = new Square(3, 3);

//        shapeRenderer = new ShapeRenderer();
//        currentBlock = new Figure();
//        filledGrid = new boolean[ARRAY_WIDTH][ARRAY_HEIGHT];

//        currentX = ARRAY_WIDTH / 2 + 1;
//        currentY = ARRAY_HEIGHT - 1 + currentBlock.minY();
    }

    public void update() {
        if (currentFigure == null)
            createRandomFigure();
        currentFigure.moveDown();
    }

    //TODO fix array -1 and Z/S shapes not falling correctly
    public void draw(SpriteBatch batch) {
        //batch.draw(texture, 0, 0);
        batch.draw(boardTexture, 0, 0);
        drawSquareArray(batch);
        currentFigure.draw(batch);
//        drawGrid();
//        if (isDown) {
//            isDown = false;
//            int removeLine = -1;
//            for (int i = 0; i < 4; i++) {
//                int x = currentX + currentBlock.getX(i);
//                int y = currentY + currentBlock.getY(i);
//                filledGrid[x - 1][y - currentBlock.minY()] = true;
//            }
//            int counter[] = new int[ARRAY_HEIGHT];
//            for (int i = 0; i < ARRAY_WIDTH; i++) {
//                for (int j = 0; j < ARRAY_HEIGHT; j++) {
//                    if (filledGrid[i][j]) {
//                        counter[j]++;
//                    }
//                    if (counter[j] == 10)
//                        removeLine = j;
//                }
//            }
//            if (removeLine >= 0) {
//                for (int i = 0; i < ARRAY_WIDTH; i++) {
//                    filledGrid[i][removeLine] = false;
//                }
//                for (int i = 0; i < ARRAY_WIDTH; i++) {
//                    for (int j = removeLine; j < ARRAY_HEIGHT - 1; j++) {
//                        filledGrid[i][j] = filledGrid[i][j + 1];
//                    }
//                }
//                removeLine = -1;
//                drawFilledGrid();
//            }
//            System.out.println(Arrays.toString(counter));
//            currentBlock = new Figure();
//            currentX = ARRAY_WIDTH / 2 + 1;
//            currentY = ARRAY_HEIGHT - 1 + currentBlock.minY();
//        }
//        drawFilledGrid();
//        try {
//            Thread.sleep(300);
//        } catch (InterruptedException e) {
//            return;
//        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
//            moveBlock(currentX + 1, currentY + 1);
//        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
//            moveBlock(currentX - 1, currentY + 1);
//        }
//        drawBlock(currentBlock, currentX, currentY);
//        moveBlock(currentX, currentY - 1);
    }

//    public void drawBlock(Figure sq, float x, float y) {
//        x = ((x - 1) / 10 * PIXEL_WIDTH) + 490;
//        y = (y - currentBlock.minY()) / 22 * PIXEL_HEIGHT;
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.setColor(1, 0, 0, 1);
//        for (int i = 0; i < 4; i++) {
//            shapeRenderer.rect(x + step * sq.getX(i), y + step * sq.getY(i), step, step);
//        }
//        shapeRenderer.end();
//    }
//
//    public void drawGrid() {
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        shapeRenderer.setColor(1, 1, 0, 1);
//        for (int i = 1280 / 2 - PIXEL_WIDTH / 2; i < 1280 / 2 + PIXEL_WIDTH / 2; i += step) {
//            for (int j = 0; j < PIXEL_HEIGHT; j += step)
//                shapeRenderer.rect(i, j, step, step);
//        }
//        shapeRenderer.end();
//    }
//
//    public void moveBlock(int x, int y) {
//        if (x > 0 && x < ARRAY_WIDTH)
//            currentX = x;
//        for (int i = 0; i < ARRAY_HEIGHT; i++) {
//            if (filledGrid[x - 1][i] && y <= i) {
//                isDown = true;
//                return;
//            }
//        }
//        if (y >= 0)
//            currentY = y;
//        else isDown = true;
//    }
//
//    public void drawFilledGrid() {
//        boolean removeLine = false;
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.setColor(0, 0, 1, 1);
//        for (int i = 1280 / 2 - PIXEL_WIDTH / 2, k = 0; i < 1280 / 2 + PIXEL_WIDTH / 2; i += step, k++) {
//            for (int j = 0, l = 0; j < PIXEL_HEIGHT; j += step, l++) {
//                if (filledGrid[k][l]) {
//                    shapeRenderer.rect(i, j, step, step);
//                }
//            }
//        }
//        shapeRenderer.end();
//    }


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

    private void drawSquareArray(SpriteBatch batch) {
        for (Square[] squareRow : squareArray)
            for (Square square : squareRow)
                if (square != null)
                    square.draw(batch);
    }

}
