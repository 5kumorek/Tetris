package com.tetris.game_utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tetris.other_classes.IBoard;

public class Board implements IBoard {
    private final int WIDTH = 500;
    private final int HEIGHT = 720;
//    width and height are placeholder values for now - they can be changed if this class will be implemented more
    private Texture texture;
    private ShapeRenderer shapeRenderer;
    private Square block;

    public Board() {
        texture = new Texture("badlogic.jpg");
//      this texture is only for testing purpouse, to see that this Board does anything
        shapeRenderer = new ShapeRenderer();
        block = new Square();
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, 0, 0);

        //testing only
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 1, 0, 1);
        for(int i = 0; i < 4; i++)
        {
            shapeRenderer.rect(100 + 50*block.getX(i), 100 + 50*block.getY(i), 50, 50);
        }
        shapeRenderer.end();
    }
}
