package com.tetris.main_classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tetris.gui.Button;

public class OptionsMenuScreen implements Screen {

    private final MainController controller;
    private SpriteBatch batch = new SpriteBatch();
    private Stage stage;
    private int boardNumber;
    private String boardBackground;
    private Button startButton;
    private int squareColor;

    OptionsMenuScreen(MainController controller){
        this.controller = controller;
        stage = new Stage(new ScreenViewport());
        boardNumber = 6;
        boardBackground = null;
        squareColor = Color.rgba8888(Color.RED);
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
        Table table2 = new Table();
        table2.setFillParent(true);
        stage.addActor(table2);

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

        table.setPosition(0, -150);
        table.add(numberOfBoards);
        table2.setPosition(300, -150);
        table2.add(background1).padLeft(50);
        table2.add(background2).padLeft(50);

    }

    @Override
    public void render(float delta)
    {

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        int xButtonStart = 680;
        int yButtonStart = 240;
        int xPixmap = 420;
        int yPixmap = 100;
        int pixmapWidth = 300;
        int pixmapHeight = 50;
        batch.begin();
        if(Gdx.input.getX() > xButtonStart && Gdx.input.getX() < xButtonStart + Button.BUTTON_WIDTH && 720 - Gdx.input.getY() > yButtonStart+200 && 720 - Gdx.input.getY() < yButtonStart+200+Button.BUTTON_HEIGHT) {
            batch.draw(startButton.getButtonActiveTexture(), xButtonStart, yButtonStart+200, Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT);
            if(Gdx.input.isTouched()){
                controller.setScreen(new GameScreen(controller, boardNumber, boardBackground, squareColor));
            }
        }
        else {
            batch.draw(startButton.getButtonTexture(), xButtonStart, yButtonStart+200, Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT);
        }
        controller.font.draw(batch, "Number of boards: " + boardNumber, xButtonStart+Button.BUTTON_WIDTH / 2, 300);
        Pixmap pixmap = new Pixmap( pixmapWidth, pixmapHeight, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fillRectangle(0,0, 50, 50);
        pixmap.setColor(Color.YELLOW);
        pixmap.fillRectangle(50,0, 50, 50);
        pixmap.setColor(Color.GREEN);
        pixmap.fillRectangle(100,0, 50, 50);
        pixmap.setColor(Color.CYAN);
        pixmap.fillRectangle(150,0, 50, 50);
        pixmap.setColor(Color.BLUE);
        pixmap.fillRectangle(200,0, 50, 50);
        pixmap.setColor(Color.PURPLE);
        pixmap.fillRectangle(250,0, 50, 50);
        Texture pixTexture = new Texture( pixmap );
        batch.draw(pixTexture, xPixmap, yPixmap);
        if(Gdx.input.getX() > xPixmap && Gdx.input.getX() < xPixmap + pixmapWidth && 720 - Gdx.input.getY() > yPixmap && 720 - Gdx.input.getY() < yPixmap+pixmapHeight) {
            if(Gdx.input.isTouched()){
                squareColor = pixmap.getPixel(Gdx.input.getX() - xPixmap,720 - Gdx.input.getY() - yPixmap);
            }
        }
        Pixmap selectedColor = new Pixmap( pixmapWidth, pixmapHeight, Pixmap.Format.RGBA8888);
        selectedColor.setColor(squareColor);
        selectedColor.fillRectangle(0, 0, 50, 50);
        Texture selectedColorTexture = new Texture(selectedColor);
        controller.font.draw(batch, "Background: ", 1200, 300);
        controller.font.draw(batch, "Selected color: ", xPixmap + pixmapWidth / 2 - 50, 300);
        batch.draw(selectedColorTexture,xPixmap + pixmapWidth / 2 - 25 , 200);
        Texture back1 = new Texture("background1.png");
        Texture back2 = new Texture("background2.png");
        batch.draw(back1, 1160, 220, 50, 50);
        batch.draw(back2, 1235, 220, 50, 50);
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
