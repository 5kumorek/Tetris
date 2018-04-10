package com.tetris.main_classes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainController extends Game {
    public SpriteBatch batch;
    public BitmapFont font;
    public OrthographicCamera camera;

    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        this.setScreen(new GameScreen(this, 1));
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
    }

    private void updateCameraAndBatch() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    public void render() {
        clearScreen();
        updateCameraAndBatch();

        batch.begin();
        super.render();
        batch.end();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
