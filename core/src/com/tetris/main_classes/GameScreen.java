package com.tetris.main_classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.tetris.game_utils.Board;

import java.util.ArrayList;

public class GameScreen implements Screen {
    private static final float TIME_BETWEEN_FRAMES = 0.5f;
    private MainController controller;
    private ArrayList<Board> boardArray = new ArrayList<>();
    private float timeSinceLastFrame = 0;

    GameScreen(MainController controller, int boardNumber) {
        this.controller = controller;
        for (int i = 0; i < boardNumber; i++) {
            boardArray.add(new Board());
        }
    }

    @Override
    public void render(float delta) {
        timeSinceLastFrame += delta;
        handleKeyboardPress();
        updateBoardsIfTimePassed();
        drawBoards();
    }

    @Override
    public void show() {
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

    private void handleKeyboardPress() {
        int pressedKey = 0;
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
            pressedKey = Input.Keys.LEFT;
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
            pressedKey = Input.Keys.RIGHT;
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            pressedKey = Input.Keys.SPACE;
        if (pressedKey != 0)
            for (Board board : boardArray)
                board.handleKeyPress(pressedKey);
    }

    private void updateBoardsIfTimePassed() {
        if (timeSinceLastFrame >= TIME_BETWEEN_FRAMES) {
            timeSinceLastFrame -= TIME_BETWEEN_FRAMES;
            for (Board board : boardArray)
                board.update();
        }
    }

    private void drawBoards() {
        for (Board board : boardArray) {
            board.draw(controller.batch);
        }
    }

}
