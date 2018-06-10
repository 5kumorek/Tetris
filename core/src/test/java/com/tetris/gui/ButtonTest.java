package com.tetris.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tetris.game_utils.GameTestBase;
import org.junit.Test;
import com.tetris.gui.Button;

import java.awt.*;
import java.lang.reflect.Field;

import static java.lang.System.out;
import static org.easymock.EasyMock.createNiceMock;
import static org.junit.Assert.*;

public class ButtonTest extends GameTestBase {

    @Test
    public void getButtonTextureTest() {
        Button button = new Button("assets/start_button", createNiceMock(SpriteBatch.class));
        try {
            Field field = Button.class.getDeclaredField("buttonTexture");
            field.setAccessible(true);
            assertEquals(field.get(button), button.getButtonTexture());
        } catch (NoSuchFieldException | IllegalAccessException | NullPointerException e) {
            out.println(e.toString());
            fail("Exception was thrown");
        }
    }

    @Test
    public void getButtonActiveTextureTest() {
        Button button = new Button("assets/start_button", createNiceMock(SpriteBatch.class));
        try {
            Field field = Button.class.getDeclaredField("buttonActiveTexture");
            field.setAccessible(true);
            assertEquals(field.get(button), button.getButtonActiveTexture());
        } catch (NoSuchFieldException | IllegalAccessException | NullPointerException e) {
            out.println(e.toString());
            fail("Exception was thrown");
        }
    }

}