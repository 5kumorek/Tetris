package com.tetris.game_utils;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.tetris.enums.FigureShape;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.support.membermodification.MemberMatcher.method;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Board.class})
public class BoardTest extends GameTestBase{

    @Test
    public void createSimpleBoard(){
        new Board(1, null, Color.rgba8888(Color.RED), createNiceMock(SpriteBatch.class));
    }

    @Test
    public void handlingLeftKeyPressedCorrectly() {
        Board d = new Board(1, null , Color.rgba8888(Color.RED), createNiceMock(SpriteBatch.class));
        Board b = spy(d);
        Class c = d.getClass();
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure f = figureFactory.getFigure(ARRAY_WIDTH / 2, 1, FigureShape.I);
        try {
            String name = "currentFigure";
            Field field = c.getDeclaredField(name);
            field.setAccessible(true);
            field.set(b,f);
        }catch (NoSuchFieldException | IllegalAccessException e){
            out.println(e.toString());
        }
        try {
            org.powermock.api.mockito.PowerMockito.when(b, "isMouseInsideBoard").thenReturn(true);
        }catch (Exception e){
            System.out.println(e.toString());
        }
        b.handleKeyPress(Input.Keys.LEFT);
        Square[] square = f.getSquareArray();
        assertEquals(square[0].getX(),ARRAY_WIDTH / 2 - 1);
    }

    @Test
    public void handlingRightKeyPressedCorrectly() {
        Board d = new Board(1, null , Color.rgba8888(Color.RED), mock(SpriteBatch.class));
        Board b = spy(d);
        Class c = d.getClass();
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure f = figureFactory.getFigure(ARRAY_WIDTH / 2, 1, FigureShape.I);
        try {
            Field field = c.getDeclaredField("currentFigure");
            field.setAccessible(true);
            field.set(b,f);
        }catch (NoSuchFieldException | IllegalAccessException e){
            out.println(e.toString());
        }
        try {
            org.powermock.api.mockito.PowerMockito.when(b, "isMouseInsideBoard").thenReturn(true);
        }catch (Exception e){
            System.out.println(e.toString());
        }
        b.handleKeyPress(Input.Keys.RIGHT);
        Square[] square = f.getSquareArray();
        assertEquals(square[0].getX(),ARRAY_WIDTH / 2 + 1);
    }


    @Test
    public void handlingSpaceKeyPressedCorrectly() {
        Board d = new Board(1, null , Color.rgba8888(Color.RED), createNiceMock(SpriteBatch.class));
        Class c = d.getClass();
        Board b = spy(d);
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure f = figureFactory.getFigure(ARRAY_WIDTH / 2, 1, FigureShape.I);
        try {
            Field field = c.getDeclaredField("currentFigure");
            field.setAccessible(true);
            field.set(b,f);
        }catch (NoSuchFieldException | IllegalAccessException e){
            out.println(e.toString());
        }

        try {
            org.powermock.api.mockito.PowerMockito.when(b, method(Board.class, "isMouseInsideBoard")).withNoArguments().thenReturn(true);
        }catch (Exception e){
            System.out.println(e.toString());
        }
        b.handleKeyPress(Input.Keys.SPACE);
        Square[] square = f.getSquareArray();
        assertEquals(square[0].getX(),ARRAY_WIDTH / 2 - 1);
        assertEquals(square[3].getX(),ARRAY_WIDTH / 2 + 2);
    }

    @Test
    public void handlingDownKeyPressedCorrectly() {
        Board d = new Board(1, null , Color.rgba8888(Color.RED), mock(SpriteBatch.class));
        Board b = spy(d);
        Class c = d.getClass();
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure f = figureFactory.getFigure(ARRAY_WIDTH / 2, 2, FigureShape.I);
        try {
            Field field = c.getDeclaredField("currentFigure");
            field.setAccessible(true);
            field.set(b,f);
        }catch (NoSuchFieldException | IllegalAccessException e){
            out.println(e.toString());
        }
        try {
            org.powermock.api.mockito.PowerMockito.when(b, "isMouseInsideBoard").thenReturn(true);
        }catch (Exception e){
            System.out.println(e.toString());
        }
        b.handleKeyPress(Input.Keys.DOWN);
        Square[] square = f.getSquareArray();
        assertEquals(square[0].getY(), 0);
    }

    @Test
    public void nextNextFigureFigureIsNotNull() {
        Board b = new Board(1, null , Color.rgba8888(Color.RED), createNiceMock(SpriteBatch.class));
        b.update(0,0);
        try {
            Field field = Board.class.getDeclaredField("nextNextFigure");
            field.setAccessible(true);
            assertNotNull(field.get(b));
        }catch (NoSuchFieldException | IllegalAccessException e){
            fail("Exception was thrown");
            out.println(e.toString());
        }
    }

    /*
    @Test
    public void boardTextureIsNotNull() {
        Board b = new Board(1, "background1.png", Color.rgba8888(Color.RED), createNiceMock(SpriteBatch.class));
        b.update(0,0);
        try {
            Field field = Board.class.getDeclaredField("boardTexture");
            field.setAccessible(true);
            assertNotNull(field.get(b));
        }catch (NoSuchFieldException | IllegalAccessException e){
           out.println(e.toString());
           fail("Exception was thrown");
        }
    }
    TODO: trzeba jakoś załadować ten plik (background1.png)
    */

    @Test
    public void boardTextureIsNull() {
        Board b = new Board(1, null, Color.rgba8888(Color.RED), createNiceMock(SpriteBatch.class));
        b.update(0,0);
        try {
            Field field = Board.class.getDeclaredField("boardTexture");
            field.setAccessible(true);
            assertNull(field.get(b));
        }catch (NoSuchFieldException | IllegalAccessException e){
            out.println(e.toString());
            fail("Exception was thrown");
        }
    }


    @Test
    public void drawIsExecutedCorrectly() {
        ShaderProgram sp = createNiceMock(ShaderProgram.class);
        SpriteBatch spriteBatch = new SpriteBatch(10, sp);
        replay(sp);
        Board b = new Board(1, null , Color.rgba8888(Color.RED), spriteBatch);
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.BLUE));
        Figure f = figureFactory.getFigure(ARRAY_WIDTH / 2, 1, FigureShape.I);
        try {
            Field field = Board.class.getDeclaredField("currentFigure");
            field.setAccessible(true);
            field.set(b,f);
            Field field2 = Board.class.getDeclaredField("boardTexture");
            field2.setAccessible(true);
            field2.set(b, new Texture(new Pixmap(1, 1, Pixmap.Format.RGBA8888)));
        }catch (NoSuchFieldException | IllegalAccessException e){
            System.out.println(e.toString());
        }
        b.draw();
        try {
            Field sb = Board.class.getDeclaredField("batch");
            sb.setAccessible(true);
            Field field = SpriteBatch.class.getDeclaredField("totalRenderCalls");
            field.setAccessible(true);
            assertNotEquals((int)field.get(sb.get(b)),0);
        }catch (NoSuchFieldException | IllegalAccessException e){
            out.println(e.toString());
            fail("Exception was thrown");
        }
    }



    @Test
    public void figureShouldBeDecomposed() {
        Board b = new Board(1, null , Color.rgba8888(Color.RED), createNiceMock(SpriteBatch.class));
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure f = figureFactory.getFigure(ARRAY_WIDTH / 2, 1, FigureShape.I);
        try {
            Field field = Board.class.getDeclaredField("currentFigure");
            field.setAccessible(true);
            field.set(b,f);
            Field field2 = Board.class.getDeclaredField("nextNextFigure");
            field2.setAccessible(true);
            field2.set(b,f);
        }catch (NoSuchFieldException | IllegalAccessException e){
            out.println(e.toString());
        }
        b.update(0,0);
        try {
            Field field = Board.class.getDeclaredField("currentFigure");
            field.setAccessible(true);
            assertNull(field.get(b));
        }catch (NoSuchFieldException | IllegalAccessException e){
            out.println(e.toString());
            fail("Exception was thrown");
        }
    }

    @Test
    public void figureShouldNotBeDecomposed() {
        Board b = new Board(1, null , Color.rgba8888(Color.RED), createNiceMock(SpriteBatch.class));
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure f = figureFactory.getFigure(ARRAY_WIDTH / 2, 2, FigureShape.I);
        try {
            Field field = Board.class.getDeclaredField("currentFigure");
            field.setAccessible(true);
            field.set(b,f);
        }catch (NoSuchFieldException | IllegalAccessException e){
            out.println(e.toString());
        }
        b.update(0,0);
        try {
            Field field = Board.class.getDeclaredField("currentFigure");
            field.setAccessible(true);
            assertNotNull(field.get(b));
        }catch (NoSuchFieldException | IllegalAccessException e){
            out.println(e.toString());
            fail("Exception was thrown");
        }
    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();
    @Test
    public void loseGameShouldBeCalled(){
        Board b = new Board(1, null , Color.rgba8888(Color.RED), createNiceMock(SpriteBatch.class));
        //Board b = spy(d);
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure f = figureFactory.getFigure(ARRAY_WIDTH / 2, ARRAY_HEIGHT - 1, FigureShape.I);
        try {
            Square[] figureSquareArray = f.getSquareArray();
            ArrayList<Square> squareArray = new ArrayList<>(Arrays.asList(figureSquareArray));
            Field field = Board.class.getDeclaredField("squareArray");
            field.setAccessible(true);
            field.set(b,squareArray);
            Field figureField = Board.class.getDeclaredField("nextNextFigure");
            figureField.setAccessible(true);
            figureField.set(b,f);
            Field figureField2 = Board.class.getDeclaredField("nextFigure");
            figureField2.setAccessible(true);
            figureField2.set(b,f);
            exit.expectSystemExit();
        }catch (NoSuchFieldException | IllegalAccessException e){
            out.println(e.toString());
        }
            b.update(0,0);
    }



    @Test//Unchecked Cast can be avoided because of type erasure
    public @SuppressWarnings("unchecked") void shouldDeleteFilledRows(){
        Board b = new Board(1, null , Color.rgba8888(Color.RED), createNiceMock(SpriteBatch.class));
        try {
            ArrayList<Square> squareArray = new ArrayList<>();
            for(int i = 0; i < ARRAY_WIDTH; i++){
                squareArray.add(new Square(i,0, Color.rgba8888(Color.RED)));
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
        b.update(0,0);
    }

    @Test //Unchecked Cast can be avoided because of type erasure
    public @SuppressWarnings("unchecked") void shouldDeleteFilledRowAndMoveDown(){
        Board b = new Board(1, null , Color.rgba8888(Color.RED), createNiceMock(SpriteBatch.class));
        try {
            ArrayList<Square> squareArray = new ArrayList<>();
            for(int i = 0; i < ARRAY_WIDTH; i++){
                squareArray.add(new Square(i,0, Color.rgba8888(Color.RED)));
            }
            squareArray.add(new Square(ARRAY_WIDTH/2,ARRAY_HEIGHT/2, Color.rgba8888(Color.RED)));
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
        b.update(0,0);
    }

    @Test
    public void getPointsTest() {
        Board b = new Board(1, null , Color.rgba8888(Color.RED), createNiceMock(SpriteBatch.class));
        try {
            Field field = Board.class.getDeclaredField("points");
            field.setAccessible(true);
            assertEquals(field.get(b),b.getPoints());
        }catch (NoSuchFieldException | IllegalAccessException e){
            out.println(e.toString());
            fail("Exception was thrown");
        }
    }
}
