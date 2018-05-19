package com.tetris.main_classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
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
        startButton = new Button("start_button");
        optionsButton = new Button("options_button");
        exitButton = new Button("exit_button");
    }

    @Override
    public void render(float delta) {
        int xButtonStart = 680;
        int yButtonStart = 240;
        batch.begin();
        if(Gdx.input.getX() > xButtonStart && Gdx.input.getX() < xButtonStart + Button.BUTTON_WIDTH && 720 - Gdx.input.getY() > yButtonStart+200 && 720 - Gdx.input.getY() < yButtonStart+200+Button.BUTTON_HEIGHT) {
            batch.draw(startButton.getButtonActiveTexture(), xButtonStart, yButtonStart+200, Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT);
            if(Gdx.input.isTouched()){
                controller.setScreen(new GameScreen(controller, 6, null));
            }
        }
        else {
            batch.draw(startButton.getButtonTexture(), xButtonStart, yButtonStart+200, Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT);
        }
        if(Gdx.input.getX() > xButtonStart && Gdx.input.getX() < xButtonStart + Button.BUTTON_WIDTH && 720 - Gdx.input.getY() > yButtonStart+100 && 720 - Gdx.input.getY() < yButtonStart+100+Button.BUTTON_HEIGHT) {
            batch.draw(optionsButton.getButtonActiveTexture(), xButtonStart, yButtonStart+100, Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT);
            if(Gdx.input.isTouched()){
                controller.setScreen(new OptionsMenuScreen(controller));
            }
        }
        else {
            batch.draw(optionsButton.getButtonTexture(), xButtonStart, yButtonStart+100, Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT);
        }
        if(Gdx.input.getX() > xButtonStart && Gdx.input.getX() < xButtonStart + Button.BUTTON_WIDTH && 720 - Gdx.input.getY() > yButtonStart && 720 - Gdx.input.getY() < yButtonStart+Button.BUTTON_HEIGHT) {
            batch.draw(exitButton.getButtonActiveTexture(), xButtonStart, yButtonStart, Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT);
            if(Gdx.input.isTouched()){
                Gdx.app.exit();
            }
        }
        else {
            batch.draw(exitButton.getButtonTexture(), xButtonStart, yButtonStart, Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT);
        }
        controller.font.draw(batch, "Tetris", xButtonStart, 700);
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
