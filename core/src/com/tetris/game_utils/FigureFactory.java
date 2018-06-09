package com.tetris.game_utils;

import com.tetris.enums.FigureShape;
import com.tetris.game_utils.Board;

import java.awt.*;

/**
 * Factory of figures.
 */
class FigureFactory {
    private int squareColor;

    /**
     * Initializes color of created figures.
     * @param squareColor color of figures to create
     */
    FigureFactory(int squareColor){
        this.squareColor = squareColor;
    }

    /**
     * Creates new figure.
     * @param centerX center x coordinate of new figure
     * @param centerY center y coordinate of new figure
     * @param figureShape shape of new figure
     * @return new figure
     */
    Figure getFigure(int centerX, int centerY, FigureShape figureShape) {
        if (centerX < 0 || centerX > Board.ARRAY_WIDTH || centerY < 0 || centerY > Board.ARRAY_HEIGHT)
            throw new IllegalArgumentException();
        Point[] pointArray = figureShape.getSquareCoordinatesArray();
        pointArray = translatePoints(centerX, centerY, pointArray);
        Square[] squareArray = convertPointsToSquares(pointArray);
        return new Figure(centerX, centerY, squareArray);
    }

    /**
     * Translates points of given array by given translation.
     * @param xTranslation x translation
     * @param yTranslation y translation
     * @param pointArray array from which to translate points
     * @return array of translated points
     */
    private Point[] translatePoints(int xTranslation, int yTranslation, final Point[] pointArray) {
        for (Point point : pointArray)
            point.translate(xTranslation, yTranslation);
        return pointArray;
    }

    /**
     * Converts array of points to array of squares.
     * @param pointArray array of points to convert
     * @return array of squares converted from points
     */
    private Square[] convertPointsToSquares(Point[] pointArray) {
        Square[] squareArray = new Square[pointArray.length];

        for (int i = 0; i < pointArray.length; i++) {
            Point coordinates = pointArray[i];
            squareArray[i] = new Square(coordinates.x, coordinates.y, squareColor);
        }

        return squareArray;
    }
}
