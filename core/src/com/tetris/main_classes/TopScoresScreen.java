package com.tetris.main_classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tetris.gui.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class that represents top scores screen
 */
public class TopScoresScreen implements Screen
{
    private final MainController controller;
    private SpriteBatch batch = new SpriteBatch();
    private int topScores [][];
    private int height;
    private int width;
    private Button backButton;

    /**
     * Constructor of top scores screen
     * @param controller controller to set screen
     */
    TopScoresScreen(MainController controller) {
        this.controller = controller;
        topScores = new int[6][10];
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
        backButton = new Button("back_button", controller);
        loadScores();
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        for(int i = 0; i < 6; i++) {
            drawScore(i);
        }
        backButton.drawBackButton(10, 10, 120, 50);
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

    /**
     * Draws table with scores
     * @param boardNumber number of boards
     */
    private void drawScore(int boardNumber)  {
        batch.begin();
        controller.font.draw(batch, "Number of boards: " + (boardNumber+1), width/10+boardNumber*(width/7), height-height/11);
        for(int i = 0; i < 10; i++)
        {
            controller.font.draw(batch, (i+1) + ".  " + topScores[boardNumber][i], width/10+width/30+boardNumber*(width/7), height-height/8-i*height/12);
        }
        batch.end();
    }

    /**
     * Loads scores from file
     */
    private void loadScores(){
        try
        {
            Scanner s = new Scanner(new File("TopScores.txt"));
            for (int i = 0; i < 6; i++)
                for(int j = 0; j < 10; j++)
                    topScores[i][j] = s.nextInt();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Unable to find a file");
        }
    }
}
