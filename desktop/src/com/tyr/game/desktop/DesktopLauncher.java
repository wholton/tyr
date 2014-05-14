package com.tyr.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tyr.game.tyr;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = tyr.NAME + " v" + tyr.VERSION;
		//config.vSyncEnabled = true;
		//config.useGL30 = true;
		//config.fullscreen = true;
		//config.resizable = false;
		config.width = 1920;
		config.height = 1080;
		new LwjglApplication(new tyr(), config); //tyr.getInstance()
	}
}
