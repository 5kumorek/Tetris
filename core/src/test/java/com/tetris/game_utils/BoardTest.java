package com.tetris.game_utils;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tetris.enums.FigureShape;
import org.junit.Test;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.rules.ExpectedException;

import java.io.EOFException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import static com.tetris.game_utils.Board.ARRAY_HEIGHT;
import static com.tetris.game_utils.Board.ARRAY_WIDTH;
import static java.lang.System.out;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.willAnswer;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


public class BoardTest extends GameTestBase{

    @Test
    public void createSimpleBoard(){
        new Board(1, createNiceMock(SpriteBatch.class));
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
            out.println(e.toString());
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
            out.println(e.toString());
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
            out.println(e.toString());
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
            out.println(e.toString());
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
            out.println(e.toString());
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
            out.println(e.toString());
        }
        b.update();
        try {
            Field field = Board.class.getDeclaredField("currentFigure");
            field.setAccessible(true);
            assertNull(field.get(b));
        }catch (NoSuchFieldException | IllegalAccessException e){
            fail("Exception was thrown");
            out.println(e.toString());
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
            out.println(e.toString());
        }
        b.update();
        try {
            Field field = Board.class.getDeclaredField("currentFigure");
            field.setAccessible(true);
            assertNotNull(field.get(b));
        }catch (NoSuchFieldException | IllegalAccessException e){
            fail("Exception was thrown");
            out.println(e.toString());
        }
    }

    /*
    @Test // Niestety System.exit(0) przerywa działanie testu
    public void loseGameShouldBeCalled(){
        Board d = new Board(1, createNiceMock(SpriteBatch.class));
        Board b = spy(d);
        FigureFactory figureFactory = new FigureFactory();
        Figure f = figureFactory.getFigure(ARRAY_WIDTH / 2, ARRAY_HEIGHT - 1, FigureShape.I);
        try {
            Square[] figureSquareArray = f.getSquareArray();
            ArrayList<Square> squareArray = new ArrayList<>();
            squareArray.addAll(Arrays.asList(figureSquareArray));
            Field field = Board.class.getDeclaredField("squareArray");
            field.setAccessible(true);
            field.set(b,squareArray);
            Method method = Board.class.getDeclaredMethod("loseGame");
            method.setAccessible(true);
            //when(Board.class.getDeclaredMethod("createSquareTextre")).thenThrow(new RuntimeException());
            when(Board.class.getDeclaredMethod("loseGame")).thenThrow(new RuntimeException());
        }catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException e){
            out.println(e.toString());
        }
            b.update();
    }
    */

    @Test//Unchecked Cast can be avoided because of type erasure
    public @SuppressWarnings("unchecked") void shouldDeleteFilledRows(){
        Board b = new Board(1, createNiceMock(SpriteBatch.class));
        try {
            ArrayList<Square> squareArray = new ArrayList<>();
            for(int i = 0; i < ARRAY_WIDTH; i++){
                squareArray.add(new Square(i,0));
            }
            Field field = Board.class.getDeclaredField("squareArray");
            field.setAccessible(true);
            field.set(b,squareArray);
            Method method = Board.class.getDeclaredMethod("deleteFilledRows");
            method.setAccessible(true);
            method.invoke(b);
            ArrayList<Square> s = (ArrayList<Square>) field.get(b);
            assertTrue(s.isEmpty());
        }catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e){
            out.println(e.toString());
        }
        b.update();
    }

    @Test //Unchecked Cast can be avoided because of type erasure
    public @SuppressWarnings("unchecked") void shouldDeleteFilledRowAndMoveDown(){
        Board b = new Board(1, createNiceMock(SpriteBatch.class));
        try {
            ArrayList<Square> squareArray = new ArrayList<>();
            for(int i = 0; i < ARRAY_WIDTH; i++){
                squareArray.add(new Square(i,0));
            }
            squareArray.add(new Square(ARRAY_WIDTH/2,ARRAY_HEIGHT/2));
            Field field = Board.class.getDeclaredField("squareArray");
            field.setAccessible(true);
            field.set(b,squareArray);
            Method method = Board.class.getDeclaredMethod("deleteFilledRows");
            method.setAccessible(true);
            method.invoke(b);
            ArrayList<Square> s = (ArrayList<Square>) field.get(b);
            assertEquals(s.get(0).getY(),ARRAY_HEIGHT / 2 - 1);
            assertEquals(s.get(0).getX(), ARRAY_WIDTH / 2);
        }catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e){
            out.println(e.toString());
        }
        b.update();
    }

}
