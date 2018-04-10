package com.tetris.main_classes;

import com.badlogic.gdx.Screen;
import com.tetris.game_utils.Board;

import java.util.ArrayList;

public class GameScreen implements Screen {
    private final MainController controller;
    private ArrayList<Board> boardArray = new ArrayList<Board>();

    GameScreen(MainController controller, int boardNumber) {
        this.controller = controller;
        for (int i = 0; i < boardNumber; i++) {
            boardArray.add(new Board(500, 720));
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        for (Board board : boardArray) {
            board.render(controller.batch);
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
