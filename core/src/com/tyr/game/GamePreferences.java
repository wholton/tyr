package com.tyr.game;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GamePreferences {

	private static final String PREFERENCES_NAME = "Tyr.Preferences";

	private float musicVolume;
	private static final String MUSIC_VOLUME_PREFERENCES_NAME = "MUSICVOLUME";

	private float soundVolume;
	private static final String SOUND_VOLUME_PREFERENCES_NAME = "SOUNDVOLUME";

	private float masterVolume;
	private static final String MASTER_VOLUME_PREFERENCES_NAME = "MASTERVOLUME";

	private boolean useGL30;
	private static final String USE_GL30_PREFERENCES_NAME = "USEGL30";

	private boolean useVSync;
	private static final String USE_VSYNC_PREFERENCES_NAME = "USEVSYNC";

	private boolean useResizable;
	private static final String USE_RESIZABLE_PREFERENCES_NAME = "USERESIZABLE";

	private boolean useFullscreen;
	private static final String USE_FULLSCREEN_PREFERENCES_NAME = "USEFULLSCREEN";

	private boolean useSkipIntro;
	private static final String USE_INTRO_PREFERENCES_NAME = "SKIPINTRO";

	private static GamePreferences Instance;

	public static GamePreferences getInstance() {
		if (Instance == null) {
			Instance = new GamePreferences();
		}
		return Instance;
	}

	private GamePreferences() {
		loadData();
	}

	public float getMasterVolume() {
		return masterVolume;
	}

	public float getMusicVolume() {
		return musicVolume;
	}

	public float getSoundVolume() {
		return soundVolume;
	}

	public void loadData() {
		Preferences preferences = Gdx.app.getPreferences(PREFERENCES_NAME);

		masterVolume = preferences.getFloat(MASTER_VOLUME_PREFERENCES_NAME, 1);
		soundVolume = preferences.getFloat(SOUND_VOLUME_PREFERENCES_NAME, 1);
		musicVolume = preferences.getFloat(MUSIC_VOLUME_PREFERENCES_NAME, 1);
		useGL30 = preferences.getBoolean(USE_GL30_PREFERENCES_NAME, true);
		useVSync = preferences.getBoolean(USE_VSYNC_PREFERENCES_NAME, false);
		useResizable = preferences.getBoolean(USE_RESIZABLE_PREFERENCES_NAME,
				false);
		useFullscreen = preferences.getBoolean(USE_FULLSCREEN_PREFERENCES_NAME,
				true);
		useSkipIntro = preferences.getBoolean(USE_INTRO_PREFERENCES_NAME, true);
	}

	public void saveData() {

		com.badlogic.gdx.Preferences prefs = Gdx.app
				.getPreferences(PREFERENCES_NAME);

		prefs.putFloat(MASTER_VOLUME_PREFERENCES_NAME, masterVolume);
		prefs.putFloat(MUSIC_VOLUME_PREFERENCES_NAME, musicVolume);
		prefs.putFloat(SOUND_VOLUME_PREFERENCES_NAME, soundVolume);
		prefs.putBoolean(USE_GL30_PREFERENCES_NAME, useGL30);
		prefs.putBoolean(USE_VSYNC_PREFERENCES_NAME, useVSync);
		prefs.putBoolean(USE_RESIZABLE_PREFERENCES_NAME, useResizable);
		prefs.putBoolean(USE_FULLSCREEN_PREFERENCES_NAME, useFullscreen);
		prefs.putBoolean(USE_INTRO_PREFERENCES_NAME, useSkipIntro);

		prefs.flush();

		// This is for the pre-app configuation file.
		try {
			// TODO: May want to switch over to XML to make it easier to expand
			// this.
			// Also if users want to change their own prefs files, it's more
			// readable.
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					"Tyr.Preferences"));
			writer.write(String.valueOf(useGL30) + "\n");
			writer.write(String.valueOf(useVSync) + "\n");
			writer.write(String.valueOf(useFullscreen) + "\n");
			writer.write(String.valueOf(useResizable));
			writer.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}

	}

	public void setMasterVolume(float masterVolume) {
		this.masterVolume = masterVolume;
	}

	public void setMusicVolume(float musicVolume) {
		this.musicVolume = musicVolume;
	}

	public void setSoundVolume(float soundVolume) {
		this.soundVolume = soundVolume;
	}

	public void setUseFullscreen(boolean useFullscreen) {
		this.useFullscreen = useFullscreen;
	}

	public void setUseGL30(boolean useGL30) {
		this.useGL30 = useGL30;
	}

	public void setUseSkipIntro(boolean useSkipIntro) {
		this.useSkipIntro = useSkipIntro;
	}

	public void setUseResizable(boolean useResizable) {
		this.useResizable = useResizable;
	}

	public void setUseVSync(boolean useVSync) {
		this.useVSync = useVSync;
	}

	public boolean useFullscreen() {
		return useFullscreen;
	}

	public boolean useGL30() {
		return useGL30;
	}

	public boolean useSkipIntro() {
		return useSkipIntro;
	}

	public boolean useResizable() {
		return useResizable;
	}

	public boolean useVSync() {
		return useVSync;
	}

}
