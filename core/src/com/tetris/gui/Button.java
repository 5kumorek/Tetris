package com.tetris.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tetris.main_classes.MainController;
import com.tetris.main_classes.MainMenuScreen;

public class Button {
    public static final int BUTTON_WIDTH = Gdx.graphics.getWidth()/5;
    public static final int BUTTON_HEIGHT = Gdx.graphics.getHeight()/7;
    private Texture buttonTexture;
    private Texture buttonActiveTexture;
    private static final String EXTENSION = ".png";
    private MainController controller;
    private SpriteBatch batch;


    public Button(String path, SpriteBatch spriteBatch)
    {
        buttonTexture = new Texture(path + EXTENSION);
        buttonActiveTexture = new Texture(path + "_active" + EXTENSION);
        batch = spriteBatch;
    }

    public Button(String path, MainController controller, SpriteBatch spriteBatch)
    {
        this.controller = controller;
        buttonTexture = new Texture(path + EXTENSION);
        buttonActiveTexture = new Texture(path + "_active" + EXTENSION);
        batch = spriteBatch;
    }

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

    public Texture getButtonTexture()
    {
        return buttonTexture;
    }
    public Texture getButtonActiveTexture() { return buttonActiveTexture; }
}