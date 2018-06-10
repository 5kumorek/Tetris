/*
package com.tetris.main_classes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.tetris.game_utils.GameTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


import static org.easymock.EasyMock.createNiceMock;
import static org.powermock.api.easymock.PowerMock.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SpriteBatch.class,ShaderProgram.class,GameScreen.class,MainController.class})
public class GameScreenTest extends GameTestBase {

    @Test
    public void test1() {

        try {
            //MainController mc = createNiceMockAndExpectNew(MainController.class);
            MainController mc = new MainController();
            ShaderProgram sp = createNiceMockAndExpectNew(ShaderProgram.class);
            SpriteBatch sb  = createNiceMock(SpriteBatch.class);
            expectNew(SpriteBatch.class).andReturn(sb).anyTimes();
            replayAll();
            //verifyAll();
            GameScreen gs = new GameScreen(mc,1, "assets/background1.png", Color.rgba8888(Color.RED));
            //Field field = gs.getClass().getDeclaredField("controller");
            //field.setAccessible(true);
            gs.render(0.1f);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
*/
