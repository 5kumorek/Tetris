package com.tetris.game_utils;

class FigureFactory {
    Figure getFigure(int center_x, int center_y, FigureShape figureShape) {
        return new Figure(center_x, center_y, figureShape.getSquareArray());
    }
}
