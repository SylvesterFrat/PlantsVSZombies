package com.omstead.gamebasics.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.SideScrollAssignment;
import com.omstead.gamebasics.*;
import com.omstead.gamebasics.Basic2DTest;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Side Scroller";
		config.height = 480;
		config.width = 800;
		new LwjglApplication(new FlappyBirdAssignment(), config);
	}
}
