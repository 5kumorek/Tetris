package com.tetris.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tetris.game_utils.Board;
import com.tetris.main_classes.MainController;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Board.PIXEL_WIDTH * 6;
        config.height = 720;
        config.resizable = false;
        new LwjglApplication(new MainController(), config);
    }
}
