package com.tetris.game_utils;

import com.tetris.enums.FigureShape;
import org.junit.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

import static com.tetris.game_utils.Board.ARRAY_HEIGHT;
import static com.tetris.game_utils.Board.ARRAY_WIDTH;
import static org.easymock.EasyMock.createNiceMock;
import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;

/**
 * TODO:Testy wymagają rzucania wyjątku IllegalArgumentException przez FigureFactory
 * przy próbie stworzenia Figury poza planszą
 */

public class FigureFactoryTest extends GameTestBase {

    @Test(expected = IllegalArgumentException.class)
    public void getFigureOutBoardFromLeft() {
        FigureFactory figureFactory = new FigureFactory();
        figureFactory.getFigure(0, ARRAY_HEIGHT - 1, FigureShape.T);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getFigureOutOfBoardFromRight() {
        FigureFactory figureFactory = new FigureFactory();
        figureFactory.getFigure(ARRAY_WIDTH, ARRAY_HEIGHT - 1, FigureShape.T);
    }

/*    @Test(expected = IllegalArgumentException.class)
    public void getFigureOutOfBoardFromTop() {
        FigureFactory figureFactory = new FigureFactory();
        figureFactory.getFigure(ARRAY_WIDTH / 2, ARRAY_HEIGHT + 1, FigureShape.T);
    }*/

    @Test(expected = IllegalArgumentException.class)
    public void getFigureOutOfBoardFromBottom() {
        FigureFactory figureFactory = new FigureFactory();
        figureFactory.getFigure(ARRAY_WIDTH / 2, 0, FigureShape.I);
    }

    @Test
    public void factoryPlacesPointsCorrectly() {
        int notTheCenter = 10;
        FigureFactory figureFactory = new FigureFactory();
        FigureShape[] figureShapes = FigureShape.values();
        for (FigureShape actualShape : figureShapes) {
            Point[] figureShapePoints = actualShape.getSquareCoordinatesArray();
            Figure factoredFigure = figureFactory.getFigure(notTheCenter, notTheCenter, actualShape);
            Square[] s = factoredFigure.getSquareArray();
            for (int j = 0; j < s.length; j++) {
                assertTrue(s[j].getX()==figureShapePoints[j].getX() + notTheCenter
                && s[j].getY()==figureShapePoints[j].getY() + notTheCenter);
            }
        }
    }
}