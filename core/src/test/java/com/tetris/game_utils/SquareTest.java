package com.tetris.game_utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import javax.xml.soap.Text;
import java.awt.*;

import static org.easymock.EasyMock.createMockBuilder;
import static org.easymock.EasyMock.createNiceMock;
import static org.junit.Assert.*;

public class SquareTest extends GameTestBase{

    private Square testSquare;
    private SpriteBatch testBatch;
    private Pixmap pixmap;

    @Before
    public void setUp() {
        //testBatch = new SpriteBatch();
        //testSquare = new Square(3, 3);
        //pixmap = new Pixmap(10, 10, Pixmap.Format.RGBA8888);
    }


   @Test
    public void textureCanBeDrawn()
    {
        SpriteBatch spriteBatch = createNiceMock(SpriteBatch.class);
        Pixmap pixmap = new Pixmap(10, 10, Pixmap.Format.RGBA8888);
        //Pixmap pixmap = createMockBuilder(Pixmap.class).withConstructor(int.class, int.class, Pixmap.Format.class).withArgs(10, 10, Pixmap.Format.RGBA8888).createMock();
        pixmap.setColor(Color.SKY);
        Texture texture = new Texture(pixmap);
        spriteBatch.begin();
        spriteBatch.draw(texture, 0f, 0f);
        spriteBatch.end();
    }
}