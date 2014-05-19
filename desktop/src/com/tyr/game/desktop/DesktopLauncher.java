package com.tyr.game.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tyr.game.Tyr;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Tyr.NAME + " - v" + Tyr.VERSION;
		config.width = 1920;
		config.height = 1080;
		config.useGL30 = true;
		config.resizable = false;
		config.addIcon("icon/icon-128.png", FileType.Internal);
		config.addIcon("icon/icon-32.png", FileType.Internal);
		config.addIcon("icon/icon-16.png", FileType.Internal);
		new LwjglApplication(Tyr.getInstance(), config); 
	}
}
