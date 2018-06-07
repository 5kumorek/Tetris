package com.tetris.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Button {
    public static final int BUTTON_WIDTH = Gdx.graphics.getWidth()/5;
    public static final int BUTTON_HEIGHT = Gdx.graphics.getHeight()/7;
    private Texture buttonTexture;
    private Texture buttonActiveTexture;
    private static final String EXTENSION = ".png";

    public Button(String path)
    {
        buttonTexture = new Texture(path + EXTENSION);
        buttonActiveTexture = new Texture(path + "_active" + EXTENSION);
    }

    public Texture getButtonTexture()
    {
        return buttonTexture;
    }
    public Texture getButtonActiveTexture() { return buttonActiveTexture; }
}
//TODO: zrobic ta klase buttona (to ma byc button ktory bedzie znajdowal w menu glownym)