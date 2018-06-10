package com.tetris.main_classes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Class that allows an application to easily have multiple screens
 */
public class MainController extends Game {
    BitmapFont font;

    /**
     * Starts first screen
     */
    public void create() {
        font = new BitmapFont();
        Gdx.graphics.setTitle("Tetris");
        this.setScreen(new MainMenuScreen(this));
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

    /**
     * Clears screen
     */
    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
    }
}
