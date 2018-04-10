package com.tetris.game_utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tetris.other_classes.IBoard;

public class Board implements IBoard {
    private final int WIDTH = 500;
    private final int HEIGHT = 720;
    private Texture texture;

    public Board() {
        texture = new Texture("badlogic.jpg");
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, 0, 0);
    }
}
