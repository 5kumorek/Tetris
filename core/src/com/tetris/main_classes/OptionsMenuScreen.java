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
    private Button startButton;
    private Texture back1;
    private Texture back2;
    private Button backButton;

    OptionsMenuScreen(MainController controller){
        this.controller = controller;
        stage = new Stage(new ScreenViewport());
        startButton = new Button("start_button");
        back1 = new Texture("background1.png");
        back2 = new Texture("background2.png");
        backButton = new Button("back_button", controller);
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
        numberOfBoards.setValue(MainMenuScreen.boardNumber);
        numberOfBoards.addListener(event ->
        {
            MainMenuScreen.boardNumber = (int)numberOfBoards.getValue();
            return false;
        });


        final CheckBox background1 = new CheckBox(null, skin);
        final CheckBox background2 = new CheckBox(null, skin);

        if(MainMenuScreen.boardBackground != null){
            if (MainMenuScreen.boardBackground.equals("background1.png"))
                background1.setChecked(true);
            else if (MainMenuScreen.boardBackground.equals("background2.png"))
                background2.setChecked(true);
            else {
                background1.setChecked(false);
                background2.setChecked(false);
            }
        }

        background1.addListener(event ->
        {
            if(background1.isChecked() && background2.isChecked())
                background2.setChecked(false);
            if(background1.isChecked() && !background2.isChecked())
                MainMenuScreen.boardBackground = "background1.png";
            if(!background1.isChecked() && !background2.isChecked())
                MainMenuScreen.boardBackground = null;
            return false;
        });


        background2.addListener(event ->
        {
            if(background1.isChecked() && background2.isChecked())
                background1.setChecked(false);
            if(!background1.isChecked() && background2.isChecked())
                MainMenuScreen.boardBackground = "background2.png";
            if(!background1.isChecked() && !background2.isChecked())
                MainMenuScreen.boardBackground = null;
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
        backButton.drawBackButton(10, 10, 120, 50);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        int xButtonStart = Gdx.graphics.getWidth()/2 - Button.BUTTON_WIDTH/2;
        int yButtonStart = Gdx.graphics.getHeight()/2 - Button.BUTTON_HEIGHT/2;
        int xPixmap = Gdx.graphics.getWidth()/7;
        int yPixmap = 100;
        int pixmapWidth = 300;
        int pixmapHeight = 50;
        batch.begin();
        if(Gdx.input.getX() > xButtonStart && Gdx.input.getX() < xButtonStart + Button.BUTTON_WIDTH && Gdx.graphics.getHeight() - Gdx.input.getY() > yButtonStart+200 && Gdx.graphics.getHeight() - Gdx.input.getY() < yButtonStart+200+Button.BUTTON_HEIGHT) {
            batch.draw(startButton.getButtonActiveTexture(), xButtonStart, yButtonStart+200, Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT);
            if(Gdx.input.isTouched()){
                controller.setScreen(new GameScreen(controller, MainMenuScreen.boardNumber, MainMenuScreen.boardBackground, MainMenuScreen.squareColor));
            }
        }
        else {
            batch.draw(startButton.getButtonTexture(), xButtonStart, yButtonStart+200, Button.BUTTON_WIDTH, Button.BUTTON_HEIGHT);
        }
        controller.font.draw(batch, "Number of boards: " + MainMenuScreen.boardNumber, xButtonStart+50, 300);
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
        if(Gdx.input.getX() > xPixmap && Gdx.input.getX() < xPixmap + pixmapWidth && Gdx.graphics.getHeight() - Gdx.input.getY() > yPixmap && Gdx.graphics.getHeight() - Gdx.input.getY() < yPixmap+pixmapHeight) {
            if(Gdx.input.isTouched()){
                MainMenuScreen.squareColor = pixmap.getPixel(Gdx.input.getX() - xPixmap,Gdx.graphics.getHeight() - Gdx.input.getY() - yPixmap);
            }
        }
        Pixmap selectedColor = new Pixmap( pixmapWidth, pixmapHeight, Pixmap.Format.RGBA8888);
        selectedColor.setColor(MainMenuScreen.squareColor);
        selectedColor.fillRectangle(0, 0, 50, 50);
        Texture selectedColorTexture = new Texture(selectedColor);
        controller.font.draw(batch, "Background: ", 1200, 300);
        controller.font.draw(batch, "Selected color: ", xPixmap + pixmapWidth / 2 - 50, 300);
        batch.draw(selectedColorTexture,xPixmap + pixmapWidth / 2 - 25 , 200);
        batch.draw(back1, 860, 220, 50, 50);
        batch.draw(back2, 935, 220, 50, 50);
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
