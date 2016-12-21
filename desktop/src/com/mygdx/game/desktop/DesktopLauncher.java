package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.RaceIt;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.width = RaceIt.WIDTH;
                config.height = RaceIt.HEIGHT;
                config.title = "Race It!";
//                config.fullscreen = true;
		new LwjglApplication(new RaceIt(), config);
	}
}
