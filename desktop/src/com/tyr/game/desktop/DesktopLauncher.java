package com.tyr.game.desktop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tyr.game.Tyr;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Tyr.NAME + " - v" + Tyr.VERSION;
		config.width = 1920;
		config.height = 1080;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("Tyr.Preferences"));
			String preferences = reader.readLine();
			config.useGL30 = Boolean.valueOf(preferences);
			preferences = reader.readLine();
			config.vSyncEnabled = Boolean.valueOf(preferences); 
			preferences = reader.readLine();
			config.fullscreen = Boolean.valueOf(preferences);
			preferences = reader.readLine();
			config.resizable = Boolean.valueOf(preferences);
			reader.close();
		} catch(IOException exception) {
			exception.printStackTrace();
		}
		
		//config.addIcon(path, fileType);
		new LwjglApplication(Tyr.getInstance(), config); 
	}
}
