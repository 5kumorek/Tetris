package com.tetris.main_classes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tetris.gui.Button;

public class MainMenuScreen implements Screen {
    private final MainController controller;
    private SpriteBatch batch = new SpriteBatch();
    private Button startButton;
    private Button optionsButton;
    private Button exitButton;

    MainMenuScreen(MainController controller) {
        this.controller = controller;
        startButton = new Button("start_button.png");
        optionsButton = new Button("options_button.png");
        exitButton = new Button("exit_button.png");
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(startButton.getButtonTexture(), 680 ,440, Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT);
        batch.draw(optionsButton.getButtonTexture(), 680 ,340, Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT);
        batch.draw(exitButton.getButtonTexture(), 680 ,240, Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT);
        controller.font.draw(batch, "Tetris", 680, 700);
        batch.end();
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
