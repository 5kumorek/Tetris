package com.tetris.main_classes;

import com.badlogic.gdx.Screen;

public class MainMenuScreen implements Screen {
    private final MainController controller;

    MainMenuScreen(MainController controller) {
        this.controller = controller;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        controller.font.draw(controller.batch, "Some example text!", 0, 480);
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

    @Override
    public void dispose() {

    }
}
