package com.tetris.game_utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Square {
    final static int PIXEL_SIZE = 35;

    private Texture squareTexture;
    private int x, y;

    Square(int x, int y) {
        this.x = x;
        this.y = y;
        createSquareTexture();
    }

    void draw(SpriteBatch batch) {
        batch.draw(squareTexture, PIXEL_SIZE * x, PIXEL_SIZE * y);
    }

    void translate(int x, int y) {
        this.x += x;
        this.y += y;
    }

    private void createSquareTexture() {
        Pixmap pixmap = new Pixmap(PIXEL_SIZE, PIXEL_SIZE, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.drawRectangle(0, 0, PIXEL_SIZE, PIXEL_SIZE);
        squareTexture = new Texture(pixmap);
    }
}
