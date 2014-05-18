package com.tyr.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tyr.game.Tyr;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Tyr.NAME + " - v" + Tyr.VERSION;
		config.width = 1920;
		config.height = 1080;
		//config.resizable = false;
		//config.addIcon(path, fileType);
		new LwjglApplication(Tyr.getInstance(), config); 
	}
}
