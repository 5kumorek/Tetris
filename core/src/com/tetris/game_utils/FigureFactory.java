package com.tetris.game_utils;

import com.tetris.enums.FigureShape;

import java.awt.*;

class FigureFactory {
    Figure getFigure(int center_x, int center_y, FigureShape figureShape) {
        Point pointArray[] = figureShape.getSquareCoordinatesArray();
        pointArray = translatePoints(center_x, center_y, pointArray);
        Square squareArray[] = convertPointsToSquares(pointArray);
        return new Figure(center_x, center_y, squareArray);
    }

    private Point[] translatePoints(int x_translation, int y_translation, final Point[] pointArray) {
        for (Point point : pointArray)
            point.translate(x_translation, y_translation);
        return pointArray;
    }

    private Square[] convertPointsToSquares(Point[] pointArray) {
        Square squareArray[] = new Square[pointArray.length];

        for (int i = 0; i < pointArray.length; i++) {
            Point coordinates = pointArray[i];
            squareArray[i] = new Square(coordinates.x, coordinates.y);
        }

        return squareArray;
    }
}
