package com.tyr.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tyr.game.OptionPreferences;
import com.tyr.game.Tyr;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		final Tyr tyr = Tyr.getInstance();
		OptionPreferences prefs = tyr.player.getPreferences();
		config.title = Tyr.NAME + " v" + Tyr.VERSION;
		config.useGL30 = prefs.useGL30();
		config.vSyncEnabled = prefs.useVSync(); 
		config.fullscreen = prefs.useFullscreen();
		config.resizable = prefs.useResizable();
		//config.addIcon(path, fileType);
		config.width = 1920;
		config.height = 1080;
		new LwjglApplication(tyr, config); //tyr.getInstance()
	}
}
