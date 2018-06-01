package com.tetris.game_utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.tetris.enums.Direction;
import com.tetris.enums.FigureShape;
import org.easymock.EasyMock;
import org.easymock.internal.matchers.Null;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.api.easymock.annotation.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.xml.soap.Text;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.tetris.game_utils.Board.ARRAY_HEIGHT;
import static com.tetris.game_utils.Board.ARRAY_WIDTH;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Direction.class)
public class SquareTest extends GameTestBase{

    @Before
    public void setUp() {
        //testBatch = new SpriteBatch();
        //testSquare = new Square(3, 3);
        //pixmap = new Pixmap(10, 10, Pixmap.Format.RGBA8888);
    }

    @Test
    public void squareCanBeDrawn()
    {
        SpriteBatch spriteBatch = new SpriteBatch(10, createNiceMock(ShaderProgram.class));
        spriteBatch.begin();
        Square testSquare = new Square(0,0);
        testSquare.draw(spriteBatch);
    }

    @Test(expected = IllegalStateException.class)
    public void squareShouldNotBeDrawn()
    {
        SpriteBatch spriteBatch = new SpriteBatch(10, createNiceMock(ShaderProgram.class));
        Square testSquare = new Square(0,0);
        //there is no spriteBatch.begin();
        testSquare.draw(spriteBatch);
    }

    @Test
    public void squareShouldNotBeAbleToMoveOutOfBoardFromLeft() {
        Square testSquare = new Square(0, 0);
        assertFalse(testSquare.canMove(Direction.LEFT, new ArrayList<>()));
    }

    @Test
    public void squareShouldNotBeAbleToMoveOutOfBoardFromRight() {
        Square testSquare = new Square(ARRAY_WIDTH,0);
        assertFalse(testSquare.canMove(Direction.RIGHT, new ArrayList<>()));
    }

    @Test
    public void squareShouldNotBeAbleToMoveOutOfBoardFromTop() {
        Square testSquare = new Square(0,ARRAY_HEIGHT);
        assertFalse(testSquare.canMove(Direction.UP, new ArrayList<>()));
    }

    @Test
    public void squareShouldNotBeAbleToMoveOutOfBoardFromBottom() {
        Square testSquare = new Square(0,0);
        assertFalse(testSquare.canMove(Direction.DOWN, new ArrayList<>()));
    }

    @Test
    public void squareShouldBeAbleToMoveLeftOneTime() {
        Square testSquare = new Square(1, 0);
        assertTrue(testSquare.canMove(Direction.LEFT, new ArrayList<>()));
        testSquare.move(Direction.LEFT);
        assertFalse(testSquare.canMove(Direction.LEFT, new ArrayList<>()));
    }

    @Test
    public void squareShouldBeAbleToMoveRightOneTime() {
        Square testSquare = new Square(ARRAY_WIDTH - 2,0);
        assertTrue(testSquare.canMove(Direction.RIGHT, new ArrayList<>()));
        testSquare.move(Direction.RIGHT);
        assertFalse(testSquare.canMove(Direction.RIGHT, new ArrayList<>()));
    }

    @Test
    public void squareShouldBeAbleToMoveUpOneTime() {
        Square testSquare = new Square(0, ARRAY_HEIGHT - 2);
        assertTrue(testSquare.canMove(Direction.UP, new ArrayList<>()));
        testSquare.move(Direction.UP);
        assertFalse(testSquare.canMove(Direction.UP, new ArrayList<>()));
    }

    @Test
    public void squareShouldBeAbleToMoveDownOneTime() {
        Square testSquare = new Square(0, 1);
        assertTrue(testSquare.canMove(Direction.DOWN, new ArrayList<>()));
        testSquare.move(Direction.DOWN);
        assertFalse(testSquare.canMove(Direction.DOWN, new ArrayList<>()));
    }


    @Test
    public void squareCannotBeRotatedCloseToLeftEndOfBoard() {
        Point figureCenter = new Point(0,1);
        Square testSquare = new Square(0,0);
        assertFalse(testSquare.canRotate(figureCenter,new ArrayList<>()));
    }

    @Test
    public void squareCannotBeRotatedCloseToRightEndOfBoard() {
        Point figureCenter = new Point(ARRAY_WIDTH,1);
        Square testSquare = new Square(ARRAY_WIDTH,1);
        assertFalse(testSquare.canRotate(figureCenter,new ArrayList<>()));
    }

    @Test
    public void squareCanBeRotatedCloseToLeftEndOfBoard() {
        Point figureCenter = new Point(0,1);
        Square testSquare = new Square(0,2);
        assertTrue(testSquare.canRotate(figureCenter,new ArrayList<>()));
    }

    @Test
    public void squareCanBeRotatedCloseCloseToRightEndOfBoard() {
        Point figureCenter = new Point(ARRAY_WIDTH,1);
        Square testSquare = new Square(ARRAY_WIDTH,0);
        assertTrue(testSquare.canRotate(figureCenter,new ArrayList<>()));
    }

    @Test
    public void squareCannotBeRotatedIntoAnotherSquare() {
        ArrayList<Square> squareArray = new ArrayList<>();
        squareArray.add(new Square(0,2));
        Point figureCenter = new Point(1,1);
        Square testSquare = new Square(0,0);
        assertFalse(testSquare.canRotate(figureCenter, squareArray));
    }

    @Test
    public void squareShouldBeAbleToRotateOnce() {
        ArrayList<Square> squareArray = new ArrayList<>();
        squareArray.add(new Square(2,2));
        Point figureCenter = new Point(1,1);
        Square testSquare = new Square(0,0);
        assertTrue(testSquare.canRotate(figureCenter, squareArray));
        testSquare.rotate(figureCenter);
        assertFalse(testSquare.canRotate(figureCenter, squareArray));
    }

    @Test
    public void squareShouldBeAbleToRotateTwice() {
        ArrayList<Square> squareArray = new ArrayList<>();
        squareArray.add(new Square(2,0));
        Point figureCenter = new Point(1,1);
        Square testSquare = new Square(0,0);
        assertTrue(testSquare.canRotate(figureCenter, squareArray));
        testSquare.rotate(figureCenter);
        assertTrue(testSquare.canRotate(figureCenter, squareArray));
        testSquare.rotate(figureCenter);
        assertFalse(testSquare.canRotate(figureCenter, squareArray));
    }

    @Test
    public void squaresShouldOverlap() {
        ArrayList<Square> squareArray = new ArrayList<>();
        Square testSquare = new Square(1, 1);
        squareArray.add(new Square(0, 0));
        squareArray.add(new Square(1, 1));
        assertTrue(testSquare.isOverlapping(squareArray));
    }

    @Test
    public void squaresShouldNotOverlap() {
        ArrayList<Square> squareArray = new ArrayList<>();
        Square testSquare = new Square(1, 1);
        squareArray.add(new Square(0, 0));
        assertFalse(testSquare.isOverlapping(squareArray));
    }
}