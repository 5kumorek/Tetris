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
    private final int HEIGHT = 720;
//    width and height are placeholder values for now - they can be changed if this class will be implemented more
    private Texture texture;
    private ShapeRenderer shapeRenderer;
    private Square currentBlock;
    private int currentX = 150;
    private int currentY = 500;
    private boolean isDown = false;


    public Board() {
        texture = new Texture("badlogic.jpg");
//      this texture is only for testing purpouse, to see that this Board does anything
        shapeRenderer = new ShapeRenderer();
        currentBlock = new Square();
    }

    public void render(SpriteBatch batch) {
        //batch.draw(texture, 0, 0);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 0, 1);
        for(int i = 0; i < 1280; i+=50)
        {
            for(int j = 0; j < 720; j+=50)
            shapeRenderer.rect(i, j, 50, 50);
        }
        shapeRenderer.end();
        drawBlock(currentBlock, currentX, currentY);
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
        {
            currentX += 50;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
        {
            currentX -= 50;
        }

    }

    public void drawBlock(Square sq, int x, int y)
    {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 0, 0, 1);
        for(int i = 0; i < 4; i++)
        {
            shapeRenderer.rect(x + 50*sq.getX(i), y + 50*sq.getY(i), 50, 50);
        }
        shapeRenderer.end();
    }
}
