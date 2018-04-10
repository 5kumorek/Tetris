package com.tetris.other_classes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Board implements IBoard {
    private Texture texture;

    public Board(int width, int height) {
        texture = new Texture("badlogic.jpg");
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, 0, 0);
    }
}
