package com.tetris.gui;

import com.badlogic.gdx.graphics.Texture;

public class Button {
    public static final int BUTTON_WIDTH = 300;
    public static final int BUTTON_HEIGHT = 100;
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