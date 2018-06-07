package com.tetris.main_classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TopScoresScreen implements Screen
{
    private final MainController controller;
    private SpriteBatch batch = new SpriteBatch();
    private int topScores [][];
    private int height;
    private int width;

    public TopScoresScreen(MainController controller) {
        this.controller = controller;
        topScores = new int[6][10];
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
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
        for(int i = 0; i < 6; i++) {
            drawScore(i);
        }
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

    public void drawScore(int boardNumber)  {
        batch.begin();
        controller.font.draw(batch, "Number of boards: " + (boardNumber+1), width/6+boardNumber*(width/9), height-height/11);
        for(int i = 0; i < 10; i++)
        {
            controller.font.draw(batch, (i+1) + ".  " + topScores[boardNumber][i], width/6+width/30+boardNumber*(width/9), height-height/8-i*height/12);
        }
        batch.end();
    }
}
