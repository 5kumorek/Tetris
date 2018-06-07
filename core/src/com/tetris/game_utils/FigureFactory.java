package com.tetris.game_utils;

import com.tetris.enums.FigureShape;

import java.awt.*;

class FigureFactory {
    private int squareColor;

    FigureFactory(int squareColor){
        this.squareColor = squareColor;
    }

    Figure getFigure(int centerX, int centerY, FigureShape figureShape) {
        if (centerX < 0 || centerX >= Board.ARRAY_WIDTH || centerY < 0 || centerY > Board.ARRAY_HEIGHT)
            throw new IllegalArgumentException();
        Point[] pointArray = figureShape.getSquareCoordinatesArray();
        pointArray = translatePoints(centerX, centerY, pointArray);
        Square[] squareArray = convertPointsToSquares(pointArray);
        return new Figure(centerX, centerY, squareArray);
    }

    private Point[] translatePoints(int xTranslation, int yTranslation, final Point[] pointArray) {
        for (Point point : pointArray)
            point.translate(xTranslation, yTranslation);
        return pointArray;
    }

    private Square[] convertPointsToSquares(Point[] pointArray) {
        Square[] squareArray = new Square[pointArray.length];

        for (int i = 0; i < pointArray.length; i++) {
            Point coordinates = pointArray[i];
            squareArray[i] = new Square(coordinates.x, coordinates.y, squareColor);
        }

        return squareArray;
    }
}
