package com.omstead.gamebasics.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.omstead.gamebasics.Basic2DTest;

public class Basic2DTestLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Terraria";
        config.height = 720;
        config.width = 1280;
        new LwjglApplication(new Basic2DTest(), config);
    }
}
