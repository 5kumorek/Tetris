package com.tetris.game_utils;

import com.badlogic.gdx.graphics.Color;
import com.tetris.enums.FigureShape;
import org.junit.Test;
import java.awt.*;

import static com.tetris.game_utils.Board.ARRAY_HEIGHT;
import static com.tetris.game_utils.Board.ARRAY_WIDTH;
import static org.junit.Assert.*;

public class FigureFactoryTest extends GameTestBase {

    @Test(expected = IllegalArgumentException.class)
    public void getFigureOutBoardFromLeft() {
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        figureFactory.getFigure(0, ARRAY_HEIGHT - 2, FigureShape.T);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getFigureOutOfBoardFromRight() {
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        figureFactory.getFigure(ARRAY_WIDTH + 1, ARRAY_HEIGHT - 2, FigureShape.T);
    }



    @Test(expected = IllegalArgumentException.class)
    public void getFigureOutOfBoardFromTop() {
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        figureFactory.getFigure(ARRAY_WIDTH / 2, ARRAY_HEIGHT + 1, FigureShape.T);
    }


    @Test(expected = IllegalArgumentException.class)
    public void getFigureOutOfBoardFromBottom() {
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
        figureFactory.getFigure(ARRAY_WIDTH / 2, 0, FigureShape.I);
    }

    @Test
    public void factoryPlacesPointsCorrectly() {
        int notTheCenter = 5;
        FigureFactory figureFactory = new FigureFactory(Color.rgba8888(Color.RED));
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

