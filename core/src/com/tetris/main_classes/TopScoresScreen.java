package com.tetris.main_classes;

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

    public TopScoresScreen(MainController controller) {
        this.controller = controller;
        topScores = new int[6][10];
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
        controller.font.draw(batch, "Number of boards: " + (boardNumber+1), 300+boardNumber*200, 600);
        for(int i = 0; i < 10; i++)
        {
            controller.font.draw(batch, (i+1) + ".  " + topScores[boardNumber][i], 350+boardNumber*200, 550-i*50);
        }
        batch.end();
    }
}
