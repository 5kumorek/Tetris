package com.tetris.main_classes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MainController extends Game {
    BitmapFont font;

    public void create() {
        font = new BitmapFont();
        this.setScreen(new GameScreen(this, 3));
//      this method above is setting screen to GameScreen - at the end we want it first to set screen to MainMenuScreen
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
    }

    public void render() {
        clearScreen();
        super.render();
    }

    public void dispose() {
        font.dispose();
    }
}
