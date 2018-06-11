package com.tetris.game_utils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mockito.Mockito;

import static org.easymock.EasyMock.createNiceMock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class GameTestBase {
    private static Application application;

    @BeforeClass
    public static void init() {
        application = new HeadlessApplication(new ApplicationListener() {
            @Override public void create() {}
            @Override public void resize(int width, int height) {}
            @Override public void render() {}
            @Override public void pause() {}
            @Override public void resume() {}
            @Override public void dispose() {}
        });

        Gdx.gl20 = Mockito.mock(GL20.class);
        Gdx.gl = Gdx.gl20;

        Gdx.graphics = Mockito.mock(Graphics.class);
        when(Gdx.graphics.getWidth()).thenReturn(100);
        when(Gdx.graphics.getHeight()).thenReturn(100);
    }

    @AfterClass
    public static void cleanUp() {
        application.exit();
        application = null;
    }
}
