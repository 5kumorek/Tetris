package com.tetris.game_utils;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tetris.enums.FigureShape;
import org.easymock.EasyMock;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.xml.stream.FactoryConfigurationError;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static com.tetris.game_utils.Board.ARRAY_WIDTH;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;


public class BoardTest extends GameTestBase{

    @Test
    public void createSimpleBoard(){
        new Board(1, createNiceMock(SpriteBatch.class));
        //board.handleKeyPress(Input.Keys.LEFT);
    }

    @Test
    public void handlingLeftKeyPressedCorrectly() {
        Board b = new Board(1, createNiceMock(SpriteBatch.class));
        Class c = b.getClass();
        FigureFactory figureFactory = new FigureFactory();
        Figure f = figureFactory.getFigure(ARRAY_WIDTH / 2, 1, FigureShape.I);
        try {
            Field field = c.getDeclaredField("currentFigure");
            field.setAccessible(true);
            field.set(b,f);
        }catch (NoSuchFieldException | IllegalAccessException e){
            System.out.println(e.toString());
        }
        b.handleKeyPress(Input.Keys.LEFT);
        Square[] square = f.getSquareArray();
        assertEquals(square[0].getX(),ARRAY_WIDTH / 2 - 1);
    }

    @Test
    public void handlingRightKeyPressedCorrectly() {
        Board b = new Board(1, createNiceMock(SpriteBatch.class));
        Class c = b.getClass();
        FigureFactory figureFactory = new FigureFactory();
        Figure f = figureFactory.getFigure(ARRAY_WIDTH / 2, 1, FigureShape.I);
        try {
            Field field = c.getDeclaredField("currentFigure");
            field.setAccessible(true);
            field.set(b,f);
        }catch (NoSuchFieldException | IllegalAccessException e){
            System.out.println(e.toString());
        }
        b.handleKeyPress(Input.Keys.RIGHT);
        Square[] square = f.getSquareArray();
        assertEquals(square[0].getX(),ARRAY_WIDTH / 2 + 1);
    }

    @Test
    public void handlingSpaceKeyPressedCorrectly() {
        Board b = new Board(1, createNiceMock(SpriteBatch.class));
        FigureFactory figureFactory = new FigureFactory();
        Figure f = figureFactory.getFigure(ARRAY_WIDTH / 2, 1, FigureShape.I);
        try {
            Field field = Board.class.getDeclaredField("currentFigure");
            field.setAccessible(true);
            field.set(b,f);
        }catch (NoSuchFieldException | IllegalAccessException e){
            System.out.println(e.toString());
        }
        b.handleKeyPress(Input.Keys.SPACE);
        Square[] square = f.getSquareArray();
        assertEquals(square[0].getX(),ARRAY_WIDTH / 2 - 1);
        assertEquals(square[3].getX(),ARRAY_WIDTH / 2 + 2);
    }

    @Test
    public void currentFigureIsNotNull() {
        Board b = new Board(1, createNiceMock(SpriteBatch.class));
        b.update();
        try {
            Field field = Board.class.getDeclaredField("currentFigure");
            field.setAccessible(true);
            assertNotNull(field.get(b));
        }catch (NoSuchFieldException | IllegalAccessException e){
            fail("Exception was thrown");
            System.out.println(e.toString());
        }
    }

    @Test
    public void boardTextureIsNotNull() {
        Board b = new Board(1, createNiceMock(SpriteBatch.class));
        b.update();
        try {
            Field field = Board.class.getDeclaredField("boardTexture");
            field.setAccessible(true);
            assertNotNull(field.get(b));
        }catch (NoSuchFieldException | IllegalAccessException e){
            fail("Exception was thrown");
            System.out.println(e.toString());
        }
    }
    /*
    @Test
    public void brakPomysluJakZweryfikowacDraw() {
        Board b = new Board(1, createNiceMock(SpriteBatch.class));
        FigureFactory figureFactory = new FigureFactory();
        Figure f = figureFactory.getFigure(ARRAY_WIDTH / 2, 1, FigureShape.I);
        try {
            Field field = Board.class.getDeclaredField("currentFigure");
            field.setAccessible(true);
            field.set(b,f);
        }catch (NoSuchFieldException | IllegalAccessException e){
            System.out.println(e.toString());
        }
        b.draw();
        //TODO:Verify that the drawing was successful
    }
    */

    @Test
    public void figureShouldBeDecomposed() {
        Board b = new Board(1, createNiceMock(SpriteBatch.class));
        FigureFactory figureFactory = new FigureFactory();
        Figure f = figureFactory.getFigure(ARRAY_WIDTH / 2, 1, FigureShape.I);
        try {
            Field field = Board.class.getDeclaredField("currentFigure");
            field.setAccessible(true);
            field.set(b,f);
        }catch (NoSuchFieldException | IllegalAccessException e){
            System.out.println(e.toString());
        }
        b.update();
        try {
            Field field = Board.class.getDeclaredField("currentFigure");
            field.setAccessible(true);
            assertNull(field.get(b));
        }catch (NoSuchFieldException | IllegalAccessException e){
            fail("Exception was thrown");
            System.out.println(e.toString());
        }
    }

    @Test
    public void figureShouldNotBeDecomposed() {
        Board b = new Board(1, createNiceMock(SpriteBatch.class));
        FigureFactory figureFactory = new FigureFactory();
        Figure f = figureFactory.getFigure(ARRAY_WIDTH / 2, 2, FigureShape.I);
        try {
            Field field = Board.class.getDeclaredField("currentFigure");
            field.setAccessible(true);
            field.set(b,f);
        }catch (NoSuchFieldException | IllegalAccessException e){
            System.out.println(e.toString());
        }
        b.update();
        try {
            Field field = Board.class.getDeclaredField("currentFigure");
            field.setAccessible(true);
            assertNotNull(field.get(b));
        }catch (NoSuchFieldException | IllegalAccessException e){
            fail("Exception was thrown");
            System.out.println(e.toString());
        }
    }

    
}
