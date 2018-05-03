package com.tetris.game_utils;

import com.tetris.enums.FigureShape;
import org.junit.Test;

import java.util.Random;

import static com.tetris.game_utils.Board.ARRAY_HEIGHT;
import static com.tetris.game_utils.Board.ARRAY_WIDTH;
import static org.easymock.EasyMock.createNiceMock;
import static org.junit.Assert.*;

public class FigureFactoryTest {

    @Test
    public void testGetFigure() {
        FigureShape[] figureShapeValues = FigureShape.values();
        int randomNumber = new Random().nextInt(figureShapeValues.length);
        FigureShape randomFigureShape = figureShapeValues[randomNumber];
        FigureFactory figureFactory = createNiceMock(FigureFactory.class);
        Figure currentFigure = figureFactory.getFigure(ARRAY_WIDTH / 2, ARRAY_HEIGHT - 1, randomFigureShape);
    }
}