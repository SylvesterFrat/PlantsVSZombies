package com.omstead.gamebasics.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.omstead.gamebasics.Basic2DTest;
import com.omstead.gamebasics.PvZ.GameClass;

public class PvZLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "PvZ";
        config.width = 1020;
        config.height = 600;
        new LwjglApplication(new GameClass(), config);
    }
}
