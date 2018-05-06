package com.tetris.game_utils;

import com.tetris.enums.FigureShape;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.*;
import java.awt.*;
import org.powermock.modules.junit4.*;

import static org.junit.Assert.*;

/*

public class FigureTest extends GameTestBase{

    @Test
    public void test() {
        FigureShape[] figureShapeValues = FigureShape.values();
        FigureShape figureShape = figureShapeValues[1];
        Point[] pointArray = figureShape.getSquareCoordinatesArray();
        Square[] squareArray = new Square[pointArray.length];
        for (int i = 0; i < pointArray.length; i++) {
            Point coordinates = pointArray[i];
            squareArray[i] = new Square(coordinates.x, coordinates.y);
        }
        Figure f = new Figure(1,1, squareArray);

        @Test
        FigureFactory figureFactory = new FigureFactory();
        Figure currentFigure = figureFactory.getFigure( 0, 1, FigureShape.I); //(0.0 , 0.1, 0.2, 0.3)
        ArrayList<Square> squareArray = new ArrayList<>(Arrays.asList(currentFigure.getSquareArray()));
        //Square[] s = currentFigure.getSquareArray();
        }
    }

}*/
