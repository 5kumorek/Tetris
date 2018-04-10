package com.tetris.game_utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tetris.other_classes.IBoard;

public class Board implements IBoard {
    private final int WIDTH = 500;
    private final int HEIGHT = 720;
//    width and height are placeholder values for now - they can be changed if this class will be implemented more
    private Texture texture;

    public Board() {
        texture = new Texture("badlogic.jpg");
//      this texture is only for testing purpouse, to see that this Board does anything
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, 0, 0);
    }
}
