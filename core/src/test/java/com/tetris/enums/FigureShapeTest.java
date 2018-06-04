package com.tetris.enums;

import org.junit.Test;
import java.awt.*;

import static org.junit.Assert.*;

public class FigureShapeTest {

    @Test
    public void testFigureShapeI() {
        Point[] p = new Point[4];
        p[0] = new Point(0,-1);
        p[1] = new Point(0,0);
        p[2] = new Point(0,1);
        p[3] = new Point(0,2);
        assertArrayEquals(FigureShape.I.getSquareCoordinatesArray(),p);
    }

    @Test(expected = java.lang.AssertionError.class)
    public void testFigureShapeO() {
        Point[] p = new Point[4];
        p[0] = new Point(0,0);
        p[1] = new Point(1,0);
        p[2] = new Point(1,1);
        p[3] = new Point(0,0); //correct is (0,1)
        assertArrayEquals(FigureShape.O.getSquareCoordinatesArray(),p);
    }
}