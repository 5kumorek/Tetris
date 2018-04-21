package com.tetris.game_utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Square {
    final static int PIXEL_SIZE = 35;
    private Texture squareTexture;

    public Square() {
        createSquareTexture();
    }

    private void createSquareTexture() {
        Pixmap pixmap = new Pixmap(PIXEL_SIZE, PIXEL_SIZE, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.drawRectangle(0, 0, PIXEL_SIZE, PIXEL_SIZE);
        squareTexture = new Texture(pixmap);
    }
}
