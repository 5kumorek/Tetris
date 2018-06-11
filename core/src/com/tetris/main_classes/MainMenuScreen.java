package com.tetris.main_classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tetris.gui.Button;

/**
 * Class that represents main menu screen
 */
public class MainMenuScreen implements Screen {
    private final MainController controller;
    private SpriteBatch batch = new SpriteBatch();
    private Button startButton;
    private Button optionsButton;
    private Button topButton;
    private Button exitButton;
    static int boardNumber = 6;
    static String boardBackground = null;
    static int squareColor = Color.rgba8888(Color.RED);

    /**
     * Constructor of main menu screen
     * @param controller controller to set screen
     */
    public MainMenuScreen(MainController controller) {
        this.controller = controller;
        startButton = new Button("start_button", new SpriteBatch());
        optionsButton = new Button("options_button", new SpriteBatch());
        topButton = new Button("top_button", new SpriteBatch());
        exitButton = new Button("exit_button", new SpriteBatch());
    }

    @Override
    public void render(float delta) {
        int xButtonStart = Gdx.graphics.getWidth()/2 - Button.BUTTON_WIDTH/2;
        int yButtonStart = Gdx.graphics.getHeight()/2 - Button.BUTTON_HEIGHT/2;
        batch.begin();
        if(Gdx.input.getX() > xButtonStart && Gdx.input.getX() < xButtonStart + Button.BUTTON_WIDTH && Gdx.graphics.getHeight() - Gdx.input.getY() > yButtonStart+200 && Gdx.graphics.getHeight() - Gdx.input.getY() < yButtonStart+200+Button.BUTTON_HEIGHT) {
            batch.draw(startButton.getButtonActiveTexture(), xButtonStart, yButtonStart+200, Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT);
            if(Gdx.input.isTouched()){
                controller.setScreen(new GameScreen(controller, boardNumber, boardBackground, squareColor));
            }
        }
        else {
            batch.draw(startButton.getButtonTexture(), xButtonStart, yButtonStart+200, Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT);
        }
        if(Gdx.input.getX() > xButtonStart && Gdx.input.getX() < xButtonStart + Button.BUTTON_WIDTH && Gdx.graphics.getHeight() - Gdx.input.getY() > yButtonStart+100 && Gdx.graphics.getHeight() - Gdx.input.getY() < yButtonStart+100+Button.BUTTON_HEIGHT) {
            batch.draw(optionsButton.getButtonActiveTexture(), xButtonStart, yButtonStart+100, Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT);
            if(Gdx.input.isTouched()){
                controller.setScreen(new OptionsMenuScreen(controller));
            }
        }
        else {
            batch.draw(optionsButton.getButtonTexture(), xButtonStart, yButtonStart+100, Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT);
        }
        if(Gdx.input.getX() > xButtonStart && Gdx.input.getX() < xButtonStart + Button.BUTTON_WIDTH && Gdx.graphics.getHeight() - Gdx.input.getY() > yButtonStart && Gdx.graphics.getHeight() - Gdx.input.getY() < yButtonStart+Button.BUTTON_HEIGHT) {
            batch.draw(topButton.getButtonActiveTexture(), xButtonStart, yButtonStart, Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT);
            if(Gdx.input.isTouched()){
                controller.setScreen(new TopScoresScreen(controller));
            }
        }
        else {
            batch.draw(topButton.getButtonTexture(), xButtonStart, yButtonStart, Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT);
        }
        if(Gdx.input.getX() > xButtonStart && Gdx.input.getX() < xButtonStart + Button.BUTTON_WIDTH && Gdx.graphics.getHeight() - Gdx.input.getY() > yButtonStart-100 && Gdx.graphics.getHeight() - Gdx.input.getY() < yButtonStart-100+Button.BUTTON_HEIGHT) {
            batch.draw(exitButton.getButtonActiveTexture(), xButtonStart, yButtonStart - 100, Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT);
            if(Gdx.input.isTouched()){
                Gdx.app.exit();
            }
        }
        else {
            batch.draw(exitButton.getButtonTexture(), xButtonStart, yButtonStart - 100, Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT);
        }
        controller.font.draw(batch, "Tetris", Gdx.graphics.getWidth()/2-20, 690);
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
