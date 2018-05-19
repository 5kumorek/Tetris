package com.tetris.main_classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tetris.gui.Button;
import javafx.scene.control.ColorPicker;

public class OptionsMenuScreen implements Screen {

    private final MainController controller;
    private SpriteBatch batch = new SpriteBatch();
    private Stage stage;
    private int boardNumber;
    private String boardBackground;
    private Button startButton;

    OptionsMenuScreen(MainController controller){
        this.controller = controller;
        stage = new Stage(new ScreenViewport());
        boardNumber = 6;
        boardBackground = null;
        startButton = new Button("start_button");
    }

    @Override
    public void show()
    {
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("glassy-ui.json"));


        final Slider numberOfBoards = new Slider(1, 6, 1, false, skin);
        numberOfBoards.setValue(boardNumber);
        numberOfBoards.addListener(event ->
        {
            boardNumber = (int)numberOfBoards.getValue();
            return false;
        });


        final CheckBox background1 = new CheckBox(null, skin);
        final CheckBox background2 = new CheckBox(null, skin);

        background1.addListener(event ->
        {
            if(background1.isChecked() && background2.isChecked())
                background2.setChecked(false);
            if(background1.isChecked() && !background2.isChecked())
                boardBackground = "background1.png";
            return false;
        });


        background2.addListener(event ->
        {
            if(background1.isChecked() && background2.isChecked())
                background1.setChecked(false);
            if(!background1.isChecked() && background2.isChecked())
                boardBackground = "background2.png";
            return false;
        });

        table.add(numberOfBoards);
        table.row();
        table.add(background1);
        table.row();
        table.add(background2);

    }

    @Override
    public void render(float delta)
    {

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        int xButtonStart = 680;
        int yButtonStart = 240;
        batch.begin();
        if(Gdx.input.getX() > xButtonStart && Gdx.input.getX() < xButtonStart + Button.BUTTON_WIDTH && 720 - Gdx.input.getY() > yButtonStart+200 && 720 - Gdx.input.getY() < yButtonStart+200+Button.BUTTON_HEIGHT) {
            batch.draw(startButton.getButtonActiveTexture(), xButtonStart, yButtonStart+200, Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT);
            if(Gdx.input.isTouched()){
                controller.setScreen(new GameScreen(controller, boardNumber, boardBackground));
            }
        }
        else {
            batch.draw(startButton.getButtonTexture(), xButtonStart, yButtonStart+200, Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT);
        }
        controller.font.draw(batch, "Number of boards: " + boardNumber, xButtonStart+Button.BUTTON_WIDTH / 2, 300);
        batch.end();
    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose()
    {

    }
}
