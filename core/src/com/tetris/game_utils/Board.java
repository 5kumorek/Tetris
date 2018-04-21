package com.tetris.game_utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tetris.other_classes.IBoard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.Arrays;

public class Board implements IBoard {
    private final int WIDTH = 300;
    private final int HEIGHT = 660;
    //    width and height are placeholder values for now - they can be changed if this class will be implemented more
    private Texture texture;
    private ShapeRenderer shapeRenderer;
    private Figure currentBlock;
    private int currentX = 0;
    private int currentY = 0;
    private int SIZE_X = 10;
    private int SIZE_Y = 22;
    private int step = 30;
    private boolean isDown = false;
    private boolean filledGrid[][];


    public Board() {
        texture = new Texture("badlogic.jpg");
//      this texture is only for testing purpouse, to see that this Board does anything
        shapeRenderer = new ShapeRenderer();
        currentBlock = new Figure();
        filledGrid = new boolean[SIZE_X][SIZE_Y];

        currentX = SIZE_X / 2 + 1;
        currentY = SIZE_Y - 1 + currentBlock.minY();
    }

    //TODO fix array -1 and Z/S shapes not falling correctly
    public void render(SpriteBatch batch) {
        //batch.draw(texture, 0, 0);
        drawGrid();
        if (isDown) {
            isDown = false;
            int removeLine = -1;
            for (int i = 0; i < 4; i++) {
                int x = currentX + currentBlock.getX(i);
                int y = currentY + currentBlock.getY(i);
                filledGrid[x - 1][y - currentBlock.minY()] = true;
            }
            int counter[] = new int[SIZE_Y];
            for (int i = 0; i < SIZE_X; i++) {
                for (int j = 0; j < SIZE_Y; j++) {
                    if (filledGrid[i][j]) {
                        counter[j]++;
                    }
                    if (counter[j] == 10)
                        removeLine = j;
                }
            }
            if (removeLine >= 0) {
                for (int i = 0; i < SIZE_X; i++) {
                    filledGrid[i][removeLine] = false;
                }
                for (int i = 0; i < SIZE_X; i++) {
                    for (int j = removeLine; j < SIZE_Y - 1; j++) {
                        filledGrid[i][j] = filledGrid[i][j + 1];
                    }
                }
                removeLine = -1;
                drawFilledGrid();
            }
            System.out.println(Arrays.toString(counter));
            currentBlock = new Figure();
            currentX = SIZE_X / 2 + 1;
            currentY = SIZE_Y - 1 + currentBlock.minY();
        }
        drawFilledGrid();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            return;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            moveBlock(currentX + 1, currentY + 1);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            moveBlock(currentX - 1, currentY + 1);
        }
        drawBlock(currentBlock, currentX, currentY);
        moveBlock(currentX, currentY - 1);
    }

    public void drawBlock(Figure sq, float x, float y) {
        x = ((x - 1) / 10 * WIDTH) + 490;
        y = (y - currentBlock.minY()) / 22 * HEIGHT;
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 0, 0, 1);
        for (int i = 0; i < 4; i++) {
            shapeRenderer.rect(x + step * sq.getX(i), y + step * sq.getY(i), step, step);
        }
        shapeRenderer.end();
    }

    public void drawGrid() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 0, 1);
        for (int i = 1280 / 2 - WIDTH / 2; i < 1280 / 2 + WIDTH / 2; i += step) {
            for (int j = 0; j < HEIGHT; j += step)
                shapeRenderer.rect(i, j, step, step);
        }
        shapeRenderer.end();
    }

    public void moveBlock(int x, int y) {
        if (x > 0 && x < SIZE_X)
            currentX = x;
        for (int i = 0; i < SIZE_Y; i++) {
            if (filledGrid[x - 1][i] && y <= i) {
                isDown = true;
                return;
            }
        }
        if (y >= 0)
            currentY = y;
        else isDown = true;
    }

    public void drawFilledGrid() {
        boolean removeLine = false;
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 1, 1);
        for (int i = 1280 / 2 - WIDTH / 2, k = 0; i < 1280 / 2 + WIDTH / 2; i += step, k++) {
            for (int j = 0, l = 0; j < HEIGHT; j += step, l++) {
                if (filledGrid[k][l]) {
                    shapeRenderer.rect(i, j, step, step);
                }
            }
        }
        shapeRenderer.end();
    }
}
