package com.tetris.main_classes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenuScreen implements Screen {
    private final MainController controller;
    private SpriteBatch batch = new SpriteBatch();

    MainMenuScreen(MainController controller) {
        this.controller = controller;
    }

    @Override
    public void render(float delta) {
        controller.font.draw(batch, "Some example text!", 0, 480);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }
}
