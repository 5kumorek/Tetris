package com.tetris.game_utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tetris.other_classes.IBoard;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Board implements IBoard {
    private final int WIDTH = 300;
    private final int HEIGHT = 600;
//    width and height are placeholder values for now - they can be changed if this class will be implemented more
    private Texture texture;
    private ShapeRenderer shapeRenderer;
    private Square currentBlock;
    private int currentX = 1280/2-30;
    private int currentY = 540;
    private int step = 30;
    private boolean isDown = false;


    public Board() {
        texture = new Texture("badlogic.jpg");
//      this texture is only for testing purpouse, to see that this Board does anything
        shapeRenderer = new ShapeRenderer();
        currentBlock = new Square();
    }

    public void render(SpriteBatch batch) {
        //batch.draw(texture, 0, 0);
        drawGrid();
        if(isDown)
        {
            currentBlock = new Square();
            isDown = false;
            currentX = 1280/2-30;
            currentY = 540;
        }
        try
        {
            Thread.sleep(400);
        }
        catch(InterruptedException e)
        {
            return;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
        {
            moveBlock(currentX + step, currentY + step);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
        {
            moveBlock(currentX - step, currentY + step);
        }
        drawBlock(currentBlock, currentX, currentY);
        moveBlock(currentX, currentY - step);
    }

    public void drawBlock(Square sq, int x, int y)
    {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 0, 0, 1);
        for(int i = 0; i < 4; i++)
        {
            shapeRenderer.rect(x + step*sq.getX(i), y + step*sq.getY(i), step, step);
        }
        shapeRenderer.end();
    }

    public void drawGrid()
    {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 0, 1);
        for(int i = 1280/2 - WIDTH/2; i < 1280/2 + WIDTH/2; i+=step)
        {
            for(int j = 0; j < HEIGHT; j+=step)
                shapeRenderer.rect(i, j, step, step);
        }
        shapeRenderer.end();
    }

    public void moveBlock(int x, int y)
    {
        if(x >= 1280/2 - WIDTH/2 && x <= 1280/2 + WIDTH/2)
            currentX = x;
        if(y >= step)
            currentY = y;
        else isDown = true;
    }
}
