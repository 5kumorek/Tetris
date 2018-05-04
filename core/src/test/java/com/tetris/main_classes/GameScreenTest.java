package com.tetris.main_classes;

import com.badlogic.gdx.Input;
import org.easymock.EasyMock;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameScreenTest {
    @Test
    public void test1() {
        MainController mc = EasyMock.createNiceMock(MainController.class);
        GameScreen gs = new GameScreen(mc,0);
        //gs.render(0.5f);
    }
}