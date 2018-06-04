package com.tetris.game_utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.tetris.enums.Direction;
import org.junit.Test;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static com.tetris.game_utils.Board.ARRAY_HEIGHT;
import static com.tetris.game_utils.Board.ARRAY_WIDTH;
import static java.lang.System.out;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;


public class SquareTest extends GameTestBase{


    @Test
    public void squareCanBeDrawn()
    {
        SpriteBatch spriteBatch = new SpriteBatch(10, createNiceMock(ShaderProgram.class));
        spriteBatch.begin();
        Square testSquare = new Square(0,0, Color.rgba8888(Color.RED));
        testSquare.draw(spriteBatch);
        try {
            Field field = SpriteBatch.class.getDeclaredField("lastTexture");
            field.setAccessible(true);
            assertNotNull(field.get(spriteBatch));
        }catch (NoSuchFieldException | IllegalAccessException e){
            out.println(e.toString());
            fail("Exception was thrown");
        }
    }

    @Test(expected = IllegalStateException.class)
    public void squareShouldNotBeDrawn()
    {
        SpriteBatch spriteBatch = new SpriteBatch(10, createNiceMock(ShaderProgram.class));
        Square testSquare = new Square(0,0, Color.rgba8888(Color.RED));
        //there is no spriteBatch.begin();
        testSquare.draw(spriteBatch);
        try {
            Field field = SpriteBatch.class.getDeclaredField("lastTexture");
            field.setAccessible(true);
            assertNull(field.get(spriteBatch));
        }catch (NoSuchFieldException | IllegalAccessException e){
            out.println(e.toString());
            fail("Exception was thrown");
        }
    }

    @Test
    public void nextSquareCanBeDrawn()
    {
        SpriteBatch spriteBatch = new SpriteBatch(10, createNiceMock(ShaderProgram.class));
        spriteBatch.begin();
        Square testSquare = new Square(0,0, Color.rgba8888(Color.RED));
        testSquare.drawNext(spriteBatch, true);
        try {
            Field field = SpriteBatch.class.getDeclaredField("lastTexture");
            field.setAccessible(true);
            assertNotNull(field.get(spriteBatch));
        }catch (NoSuchFieldException | IllegalAccessException e){
            out.println(e.toString());
            fail("Exception was thrown");
        }
    }

    @Test(expected = IllegalStateException.class)
    public void nextSquareShouldNotBeDrawn()
    {
        SpriteBatch spriteBatch = new SpriteBatch(10, createNiceMock(ShaderProgram.class));
        Square testSquare = new Square(0,0, Color.rgba8888(Color.RED));
        //there is no spriteBatch.begin();
        testSquare.drawNext(spriteBatch, true);
        try {
            Field field = SpriteBatch.class.getDeclaredField("lastTexture");
            field.setAccessible(true);
            assertNull(field.get(spriteBatch));
        }catch (NoSuchFieldException | IllegalAccessException e){
            out.println(e.toString());
            fail("Exception was thrown");
        }
    }

    @Test
    public void squareShouldNotBeAbleToMoveOutOfBoardFromLeft() {
        Square testSquare = new Square(0, 0, Color.rgba8888(Color.RED));
        assertFalse(testSquare.canMove(Direction.LEFT, new ArrayList<>()));
    }

    @Test
    public void squareShouldNotBeAbleToMoveOutOfBoardFromRight() {
        Square testSquare = new Square(ARRAY_WIDTH,0, Color.rgba8888(Color.RED));
        assertFalse(testSquare.canMove(Direction.RIGHT, new ArrayList<>()));
    }

    @Test
    public void squareShouldNotBeAbleToMoveOutOfBoardFromTop() {
        Square testSquare = new Square(0,ARRAY_HEIGHT, Color.rgba8888(Color.RED));
        assertFalse(testSquare.canMove(Direction.UP, new ArrayList<>()));
    }

    @Test
    public void squareShouldNotBeAbleToMoveOutOfBoardFromBottom() {
        Square testSquare = new Square(0,0, Color.rgba8888(Color.RED));
        assertFalse(testSquare.canMove(Direction.DOWN, new ArrayList<>()));
    }

    @Test
    public void squareShouldBeAbleToMoveLeftOneTime() {
        Square testSquare = new Square(1, 0, Color.rgba8888(Color.RED));
        assertTrue(testSquare.canMove(Direction.LEFT, new ArrayList<>()));
        testSquare.move(Direction.LEFT);
        assertFalse(testSquare.canMove(Direction.LEFT, new ArrayList<>()));
    }

    @Test
    public void squareShouldBeAbleToMoveRightOneTime() {
        Square testSquare = new Square(ARRAY_WIDTH - 2,0, Color.rgba8888(Color.RED));
        assertTrue(testSquare.canMove(Direction.RIGHT, new ArrayList<>()));
        testSquare.move(Direction.RIGHT);
        assertFalse(testSquare.canMove(Direction.RIGHT, new ArrayList<>()));
    }

    @Test
    public void squareShouldBeAbleToMoveUpOneTime() {
        Square testSquare = new Square(0, ARRAY_HEIGHT - 2, Color.rgba8888(Color.RED));
        assertTrue(testSquare.canMove(Direction.UP, new ArrayList<>()));
        testSquare.move(Direction.UP);
        assertFalse(testSquare.canMove(Direction.UP, new ArrayList<>()));
    }

    @Test
    public void squareShouldBeAbleToMoveDownOneTime() {
        Square testSquare = new Square(0, 1, Color.rgba8888(Color.RED));
        assertTrue(testSquare.canMove(Direction.DOWN, new ArrayList<>()));
        testSquare.move(Direction.DOWN);
        assertFalse(testSquare.canMove(Direction.DOWN, new ArrayList<>()));
    }


    @Test
    public void squareCannotBeRotatedCloseToLeftEndOfBoard() {
        Point figureCenter = new Point(0,1);
        Square testSquare = new Square(0,0, Color.rgba8888(Color.RED));
        assertFalse(testSquare.canRotate(figureCenter,new ArrayList<>()));
    }

    @Test
    public void squareCannotBeRotatedCloseToRightEndOfBoard() {
        Point figureCenter = new Point(ARRAY_WIDTH,1);
        Square testSquare = new Square(ARRAY_WIDTH,1, Color.rgba8888(Color.RED));
        assertFalse(testSquare.canRotate(figureCenter,new ArrayList<>()));
    }

    @Test
    public void squareCanBeRotatedCloseToLeftEndOfBoard() {
        Point figureCenter = new Point(0,1);
        Square testSquare = new Square(0,2, Color.rgba8888(Color.RED));
        assertTrue(testSquare.canRotate(figureCenter,new ArrayList<>()));
    }

    @Test
    public void squareCanBeRotatedCloseCloseToRightEndOfBoard() {
        Point figureCenter = new Point(ARRAY_WIDTH,1);
        Square testSquare = new Square(ARRAY_WIDTH,0, Color.rgba8888(Color.RED));
        assertTrue(testSquare.canRotate(figureCenter,new ArrayList<>()));
    }

    @Test
    public void squareCannotBeRotatedIntoAnotherSquare() {
        ArrayList<Square> squareArray = new ArrayList<>();
        squareArray.add(new Square(0,2, Color.rgba8888(Color.RED)));
        Point figureCenter = new Point(1,1);
        Square testSquare = new Square(0,0, Color.rgba8888(Color.RED));
        assertFalse(testSquare.canRotate(figureCenter, squareArray));
    }

    @Test
    public void squareShouldBeAbleToRotateOnce() {
        ArrayList<Square> squareArray = new ArrayList<>();
        squareArray.add(new Square(2,2, Color.rgba8888(Color.RED)));
        Point figureCenter = new Point(1,1);
        Square testSquare = new Square(0,0, Color.rgba8888(Color.RED));
        assertTrue(testSquare.canRotate(figureCenter, squareArray));
        testSquare.rotate(figureCenter);
        assertFalse(testSquare.canRotate(figureCenter, squareArray));
    }

    @Test
    public void squareShouldBeAbleToRotateTwice() {
        ArrayList<Square> squareArray = new ArrayList<>();
        squareArray.add(new Square(2,0, Color.rgba8888(Color.RED)));
        Point figureCenter = new Point(1,1);
        Square testSquare = new Square(0,0, Color.rgba8888(Color.RED));
        assertTrue(testSquare.canRotate(figureCenter, squareArray));
        testSquare.rotate(figureCenter);
        assertTrue(testSquare.canRotate(figureCenter, squareArray));
        testSquare.rotate(figureCenter);
        assertFalse(testSquare.canRotate(figureCenter, squareArray));
    }

    @Test
    public void squaresShouldOverlap() {
        ArrayList<Square> squareArray = new ArrayList<>();
        Square testSquare = new Square(1, 1, Color.rgba8888(Color.RED));
        squareArray.add(new Square(0, 0, Color.rgba8888(Color.RED)));
        squareArray.add(new Square(1, 1, Color.rgba8888(Color.RED)));
        assertTrue(testSquare.isOverlapping(squareArray));
    }

    @Test
    public void squaresShouldNotOverlap() {
        ArrayList<Square> squareArray = new ArrayList<>();
        Square testSquare = new Square(1, 1, Color.rgba8888(Color.RED));
        squareArray.add(new Square(0, 0, Color.rgba8888(Color.RED)));
        assertFalse(testSquare.isOverlapping(squareArray));
    }
}
