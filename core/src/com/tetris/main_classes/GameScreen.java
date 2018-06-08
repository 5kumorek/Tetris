package com.tetris.main_classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tetris.game_utils.Board;
import com.tetris.gui.Button;

import java.util.ArrayList;

public class GameScreen implements Screen {
    private static float TIME_BETWEEN_FRAMES = 0.5f;

    private MainController controller;
    private ArrayList<Board> boardArray = new ArrayList<>();
    private float timeSinceLastFrame = 0;
    private SpriteBatch batch;
    private int boardNumber;
    private Button backButton;

    GameScreen(MainController controller, int boardCount, String boardBackground, int squareColor) {
        this.controller = controller;
        batch = new SpriteBatch();
        TIME_BETWEEN_FRAMES = 0.5f;
        boardNumber = boardCount - 1;
        for (int i = 0; i < boardCount; i++) {
            boardArray.add(new Board(i, boardBackground, squareColor, new SpriteBatch()));
        }
        backButton = new Button("back_button", controller);
    }

    @Override
    public void render(float delta) {
        timeSinceLastFrame += delta;
        handleKeyboardPress();
        updateBoardsIfTimePassed();
        if(TIME_BETWEEN_FRAMES > 0.2f)
            TIME_BETWEEN_FRAMES = 0.5f - sumPoints()*0.05f;
        batch.begin();
        controller.font.draw(batch, "Points: " + sumPoints(), Board.PIXEL_WIDTH * 6 / 2 - 30, 690);
        batch.end();
        drawBoards();
        backButton.drawBackButton(10, Gdx.graphics.getHeight() - 60, 120, 50);

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
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
            pressedKey = Input.Keys.DOWN;
        if (pressedKey != 0)
            for (Board board : boardArray)
                board.handleKeyPress(pressedKey);
    }

    private void updateBoardsIfTimePassed() {
        if (timeSinceLastFrame >= TIME_BETWEEN_FRAMES) {
            timeSinceLastFrame -= TIME_BETWEEN_FRAMES;
            for (Board board : boardArray)
                if(board.update(sumPoints(), boardNumber))
                    controller.setScreen(new MainMenuScreen(controller));
        }
    }

    private void drawBoards() {
        for (Board board : boardArray)
            board.draw();
    }

    private int sumPoints(){
        int sum = 0;
        for (Board board : boardArray){
            sum += board.getPoints();
        }
        return sum;
    }
}
