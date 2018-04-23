package com.tetris.main_classes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MainController extends Game {
    BitmapFont font;

    public void create() {
        font = new BitmapFont();
        this.setScreen(new GameScreen(this, 6));
//      this method above is setting screen to GameScreen - at the end we want it first to set screen to MainMenuScreen
    }

    @Override
    public void render() {
        clearScreen();
        super.render();
    }

    @Override
    public void dispose() {
        font.dispose();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
    }
}
