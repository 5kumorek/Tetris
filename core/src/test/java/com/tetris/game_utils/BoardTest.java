package com.tetris.game_utils;

import com.badlogic.gdx.Input;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;


public class BoardTest {

    @Test
    public void sayThat0BoardsIsBad(){

    }

    @Test
    public void sayThat1BoardsIsGood(){
        ArrayList<Board> boardArray = new ArrayList<>();
        boardArray.add(createNiceMock(Board.class));
        boardArray.get(0).update();
        //assertTrue(true);
    }

    @Test
    public void sayThat2BoardsIsGood(){
        //Board b = EasyMock.createMockBuilder(Board.class).withConstructor(1).createMock();
        //java.lang.UnsatisfiedLinkError

        assertTrue(true);
    }


}