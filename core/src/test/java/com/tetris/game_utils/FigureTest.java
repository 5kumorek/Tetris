package com.tetris.game_utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.tetris.enums.Direction;
import com.tetris.enums.FigureShape;
import org.junit.Test;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static com.tetris.game_utils.Board.ARRAY_HEIGHT;
import static com.tetris.game_utils.Board.ARRAY_WIDTH;
import static java.lang.System.out;
import static org.easymock.EasyMock.createNiceMock;
import static org.junit.Assert.*;



public class FigureTest extends GameTestBase{

    @Test
    public void figureCanBeDrawn()
    {
        Square[] squareArray = new Square[1];
        squareArray[0] = new Square(2,2, Color.rgba8888(Color.RED));
        SpriteBatch spriteBatch = new SpriteBatch(10, createNiceMock(ShaderProgram.class));
        spriteBatch.begin();
        Figure testFigure = new Figure(2,2, squareArray);
        testFigure.draw(spriteBatch);
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
    public void figureShouldNotBeDrawn()
    {
        Square[] squareArray = new Square[1];
        squareArray[0] = new Square(2,2, Color.rgba8888(Color.RED));
        SpriteBatch spriteBatch = new SpriteBatch(10, createNiceMock(ShaderProgram.class));
        Figure testFigure = new Figure(2,2, squareArray);
        //there is no spriteBatch.begin();
        testFigure.draw(spriteBatch);
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
    public void nextFigureCanBeDrawn()
    {
        Square[] squareArray = new Square[1];
        squareArray[0] = new Square(2,2, Color.rgba8888(Color.RED));
        SpriteBatch spriteBatch = new SpriteBatch(10, createNiceMock(ShaderProgram.class));
        spriteBatch.begin();
        Figure testFigure = new Figure(2,2, squareArray);
        testFigure.drawNext(spriteBatch, false);
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
    public void nextFigureShouldNotBeDrawn()
    {
        Square[] squareArray = new Square[1];
        squareArray[0] = new Square(2,2, Color.rgba8888(Color.RED));
        SpriteBatch spriteBatch = new SpriteBatch(10, createNiceMock(ShaderProgram.class));
        Figure testFigure = new Figure(2,2, squareArray);
        //there is no spriteBatch.begin();
        testFigure.drawNext(spriteBatch, false);
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
    public void shouldCreateSimpleFigure() {
        Point[] pointArray = FigureShape.I.getSquareCoordinatesArray();
        Square[] squareArray = new Square[pointArray.length];
        for (int i = 0; i < pointArray.length; i++) {
            Point coordinates = pointArray[i];
            squareArray[i] = new Square(coordinates.x, coordinates.y, Color.rgba8888(Color.RED));
        }
        new Figure(2,2, squareArray);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateFigureOutOfBoardFromLeft() {
        Point[] pointArray = FigureShape.I.getSquareCoordinatesArray();
        Square[] squareArray = new Square[pointArray.length];
        for (int i = 0; i < pointArray.length; i++) {
            Point coordinates = pointArray[i];
            squareArray[i] = new Square(coordinates.x, coordinates.y, Color.rgba8888(Color.RED));
        }
        new Figure(-1,2, squareArray);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateFigureOutOfBoardFromRight() {
        Point[] pointArray = FigureShape.I.getSquareCoordinatesArray();
        Square[] squareArray = new Square[pointArray.length];
        for (int i = 0; i < pointArray.length; i++) {
            Point coordinates = pointArray[i];
            squareArray[i] = new Square(coordinates.x, coordinates.y, Color.rgba8888(Color.RED));
        }
        new Figure(ARRAY_WIDTH,2, squareArray);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateFigureOutOfBoardFromBottom() {
        Point[] pointArray = FigureShape.I.getSquareCoordinatesArray();
        Square[] squareArray = new Square[pointArray.length];
        for (int i = 0; i < pointArray.length; i++) {
            Point coordinates = pointArray[i];
            squareArray[i] = new Square(coordinates.x, coordinates.y, Color.rgba8888(Color.RED));
        }
        new Figure(2,-1, squareArray);
    }

    @Test
    public void figureShouldNotBeAbleToMoveOutOfBoardFromLeft() {
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure testFigure = figureFactory.getFigure(0, 1, FigureShape.I);
        assertFalse(testFigure.canMove(Direction.LEFT, new ArrayList<>()));
    }

    @Test
    public void figureShouldBeAbleToMoveLeft() {
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure testFigure = figureFactory.getFigure(1, 1, FigureShape.I);
        assertTrue(testFigure.canMove(Direction.LEFT, new ArrayList<>()));
    }

    @Test
    public void figureShouldBeAbleToMoveLeftOnlyOnce() {
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure testFigure = figureFactory.getFigure(1, 1, FigureShape.I);
        assertTrue(testFigure.canMove(Direction.LEFT, new ArrayList<>()));
        testFigure.move(Direction.LEFT);
        assertFalse(testFigure.canMove(Direction.LEFT, new ArrayList<>()));
    }

    @Test
    public void figureShouldNotBeAbleToMoveOutOfBoardFromRight() {
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure testFigure = figureFactory.getFigure(ARRAY_WIDTH-1, 1, FigureShape.I);
        assertFalse(testFigure.canMove(Direction.RIGHT, new ArrayList<>()));
    }

    @Test
    public void figureShouldBeAbleToMoveRight() {
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure testFigure = figureFactory.getFigure(ARRAY_WIDTH-2, 1, FigureShape.I);
        assertTrue(testFigure.canMove(Direction.RIGHT, new ArrayList<>()));
    }

    @Test
    public void figureShouldBeAbleToMoveRightOnlyOnce() {
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure testFigure = figureFactory.getFigure(ARRAY_WIDTH-2, 1, FigureShape.I);
        assertTrue(testFigure.canMove(Direction.RIGHT, new ArrayList<>()));
        testFigure.move(Direction.RIGHT);
        assertFalse(testFigure.canMove(Direction.RIGHT, new ArrayList<>()));
    }

    @Test
    public void figureShouldNotBeAbleToMoveOutOfBoardFromBottom() {
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure testFigure = figureFactory.getFigure(1, 1, FigureShape.I);
        assertFalse(testFigure.canMove(Direction.DOWN, new ArrayList<>()));
    }

    @Test
    public void figureShouldBeAbleToMoveBottom() {
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure testFigure = figureFactory.getFigure(1, 2, FigureShape.I);
        assertTrue(testFigure.canMove(Direction.DOWN, new ArrayList<>()));
    }

    @Test
    public void figureShouldBeAbleToMoveBottomOnlyOnce() {
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure testFigure = figureFactory.getFigure(1, 2, FigureShape.I);
        assertTrue(testFigure.canMove(Direction.DOWN, new ArrayList<>()));
        testFigure.move(Direction.DOWN);
        assertFalse(testFigure.canMove(Direction.DOWN, new ArrayList<>()));
    }

    @Test
    public void figureShouldNotBeAbleToMoveOutOfBoardFromTop() {
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure testFigure = figureFactory.getFigure(1, ARRAY_HEIGHT-3, FigureShape.I);
        assertFalse(testFigure.canMove(Direction.UP, new ArrayList<>()));
    }

    @Test
    public void figureShouldBeAbleToMoveTop() {
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure testFigure = figureFactory.getFigure(1, ARRAY_HEIGHT-4, FigureShape.I);
        assertTrue(testFigure.canMove(Direction.UP, new ArrayList<>()));
    }

    @Test
    public void figureShouldBeAbleToMoveTopOnlyOnce() {
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure testFigure = figureFactory.getFigure(1, ARRAY_HEIGHT-4, FigureShape.I);
        assertTrue(testFigure.canMove(Direction.UP, new ArrayList<>()));
        testFigure.move(Direction.UP);
        assertFalse(testFigure.canMove(Direction.UP, new ArrayList<>()));
    }

    @Test
    public void figureCannotBeRotatedCloseToLeftEndOfBoard() {
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure testFigure = figureFactory.getFigure(0, 2, FigureShape.I);
        assertFalse(testFigure.canRotate(new ArrayList<>()));
    }

    @Test
    public void figureCanBeRotatedCloseToLeftEndOfBoard() {
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure testFigure = figureFactory.getFigure(1, 2, FigureShape.I);
        assertTrue(testFigure.canRotate(new ArrayList<>()));
    }

    @Test
    public void figureCannotBeRotatedCloseToRightEndOfBoard() {
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure testFigure = figureFactory.getFigure(ARRAY_WIDTH-1, 2, FigureShape.I);
        assertFalse(testFigure.canRotate(new ArrayList<>()));
    }

    @Test
    public void figureCanBeRotatedCloseToRightEndOfBoard() {
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure testFigure = figureFactory.getFigure(ARRAY_WIDTH-3, 2, FigureShape.I);
        assertTrue(testFigure.canRotate(new ArrayList<>()));
    }

    @Test
    public void figureCannotBeRotatedCloseToBottomEndOfBoard() {
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure testFigure = figureFactory.getFigure(2, 0, FigureShape.O);
        assertFalse(testFigure.canRotate(new ArrayList<>()));
    }

    @Test
    public void figureCanBeRotatedCloseToBottomEndOfBoard() {
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure testFigure = figureFactory.getFigure(2, 1, FigureShape.O);
        assertTrue(testFigure.canRotate(new ArrayList<>()));
    }

    @Test
    public void rotationShouldWorkCorrectly(){
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure testFigure = figureFactory.getFigure(2, 2, FigureShape.I);
        testFigure.rotate();
        Square[] rotated = testFigure.getSquareArray();
        Square[] expected = new Square[FigureShape.I.getSquareCoordinatesArray().length];
        expected[0] = new Square(1,2, Color.rgba8888(Color.RED)); //(-1,0)+(2,2)
        expected[1] = new Square(2,2, Color.rgba8888(Color.RED)); //(0,0)+(2,2)
        expected[2] = new Square(3,2, Color.rgba8888(Color.RED)); //(1,0)+(2,2)
        expected[3] = new Square(4,2, Color.rgba8888(Color.RED)); //(2,0)+(2,2)
        for(int i = 0; i < 4; i++) {
            assertEquals(rotated[i].getX(), expected[i].getX());
            assertEquals(rotated[i].getY(), expected[i].getY());
        }
    }

    @Test
    public void figuresShouldOverlap() {
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure testFigure = figureFactory.getFigure(2, 2, FigureShape.I); // (2,1),(2,2),(2,3),(2,4)
        ArrayList<Square> squareArray = new ArrayList<>();
        squareArray.add(new Square(2, 1, Color.rgba8888(Color.RED)));
        assertTrue(testFigure.isOverlapping(squareArray));
    }

    @Test
    public void figuresShouldNotOverlap() {
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        Figure testFigure = figureFactory.getFigure(2, 2, FigureShape.I); // (2,1),(2,2),(2,3),(2,4)
        ArrayList<Square> squareArray = new ArrayList<>();
        squareArray.add(new Square(1, 2, Color.rgba8888(Color.RED)));
        assertFalse(testFigure.isOverlapping(squareArray));
    }

}
