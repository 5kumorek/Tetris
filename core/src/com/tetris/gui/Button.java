package com.tetris.gui;

import com.badlogic.gdx.graphics.Texture;

public class Button {
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 100;
    private Texture buttonTexture;

    public Button(String path)
    {
        buttonTexture = new Texture(path);
    }

    public static int getButtonHeight()
    {
        return BUTTON_HEIGHT;
    }

    public static int getButtonWidth()
    {
        return BUTTON_WIDTH;
    }

    public Texture getButtonTexture()
    {
        return buttonTexture;
    }
}
//TODO: zrobic ta klase buttona (to ma byc button ktory bedzie znajdowal w menu glownym)