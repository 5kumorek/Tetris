package com.tetris.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tetris.main_classes.MainController;
import com.tetris.main_classes.MainMenuScreen;

/**
 * Class that encapsulates buttons on screens
 */
public class Button {
    public static final int BUTTON_WIDTH = Gdx.graphics.getWidth()/5;
    public static final int BUTTON_HEIGHT = Gdx.graphics.getHeight()/7;
    private Texture buttonTexture;
    private Texture buttonActiveTexture;
    private static final String EXTENSION = ".png";
    private MainController controller;
    private SpriteBatch batch;


    /**
     * Constructor of a button
     * @param path name of file with texture
     */
    public Button(String path, SpriteBatch spriteBatch)

    {
        buttonTexture = new Texture(path + EXTENSION);
        buttonActiveTexture = new Texture(path + "_active" + EXTENSION);
        batch = spriteBatch;
    }

    /**
     * Constructor of a button
     * @param path name of file with texture
     * @param controller controller to set screen
     */
    public Button(String path, MainController controller, SpriteBatch spriteBatch)
    {
        this.controller = controller;
        buttonTexture = new Texture(path + EXTENSION);
        buttonActiveTexture = new Texture(path + "_active" + EXTENSION);
        batch = spriteBatch;
    }

    /**
     * Draws back button
     * @param x x coordinate
     * @param y y coordinate
     * @param width width of button
     * @param height height of button
     */
    public void drawBackButton(int x, int y, int width, int height)
    {
        batch.begin();
        if(Gdx.input.getX() > x && Gdx.input.getX() < x + width && Gdx.graphics.getHeight() - Gdx.input.getY() > y && Gdx.graphics.getHeight() - Gdx.input.getY() < y+height) {
            batch.draw(buttonActiveTexture, x, y, width, height);
            if(Gdx.input.isTouched()){
                controller.setScreen(new MainMenuScreen(controller));
            }
        }
        else {
            batch.draw(buttonTexture, x, y, width, height);
        }
        batch.end();
    }

    /**
     * Getter of button texture
     * @return button texture
     */
    public Texture getButtonTexture()
    {
        return buttonTexture;
    }

    /**
     * Getter of button active texture
     * @return button active texture
     */
    public Texture getButtonActiveTexture() { return buttonActiveTexture; }
}