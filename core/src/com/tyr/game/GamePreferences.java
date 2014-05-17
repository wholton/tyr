package com.tyr.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Preferences;
import com.tyr.game.audio.AudioHelper;

public class GamePreferences {

	private static final String PREFERENCES_NAME = "Tyr.Preferences";

	private float musicVolume;
	private static final String MUSIC_VOLUME_PREFERENCES_NAME = "MUSICVOLUME";

	private float soundVolume;
	private static final String SOUND_VOLUME_PREFERENCES_NAME = "SOUNDVOLUME";

	private float masterVolume;
	private static final String MASTER_VOLUME_PREFERENCES_NAME = "MASTERVOLUME";

	private boolean vsync;
	private static final String USE_VSYNC_PREFERENCES_NAME = "VSYNC";

	private boolean windowed;
	private static final String USE_FULLSCREEN_PREFERENCES_NAME = "WINDOWED";

	private boolean skipIntro;
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

	private void applyEffects() {
		// Apply audio effects
		AudioHelper.setVolume(getMusicVolume() * getMasterVolume());
		// Apply video effects
		final Graphics graphics = Gdx.graphics;
		// Apply vsync effect
		graphics.setVSync(vsync);
		// Apply windowed effect
		graphics.setDisplayMode(graphics.getWidth(), graphics.getHeight(),
				windowed);
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

	public boolean isSkipIntro() {
		return skipIntro;
	}

	public boolean isVSync() {
		return vsync;
	}

	public boolean isWindowed() {
		return windowed;

	}

	public void loadData() {
		Preferences preferences = Gdx.app.getPreferences(PREFERENCES_NAME);

		masterVolume = preferences.getFloat(MASTER_VOLUME_PREFERENCES_NAME, 1);
		soundVolume = preferences.getFloat(SOUND_VOLUME_PREFERENCES_NAME, 1);
		musicVolume = preferences.getFloat(MUSIC_VOLUME_PREFERENCES_NAME, 1);
		vsync = preferences.getBoolean(USE_VSYNC_PREFERENCES_NAME, false);
		windowed = preferences
				.getBoolean(USE_FULLSCREEN_PREFERENCES_NAME, true);
		skipIntro = preferences.getBoolean(USE_INTRO_PREFERENCES_NAME, true);

		applyEffects();
	}

	public void saveData() {
		Preferences prefs = Gdx.app.getPreferences(PREFERENCES_NAME);

		prefs.putFloat(MASTER_VOLUME_PREFERENCES_NAME, masterVolume);
		prefs.putFloat(MUSIC_VOLUME_PREFERENCES_NAME, musicVolume);
		prefs.putFloat(SOUND_VOLUME_PREFERENCES_NAME, soundVolume);
		prefs.putBoolean(USE_VSYNC_PREFERENCES_NAME, vsync);
		prefs.putBoolean(USE_FULLSCREEN_PREFERENCES_NAME, windowed);
		prefs.putBoolean(USE_INTRO_PREFERENCES_NAME, skipIntro);

		prefs.flush();

		applyEffects();
	}

	public void setMasterVolume(float masterVolume) {
		this.masterVolume = masterVolume;
	}

	public void setMusicVolume(float musicVolume) {
		this.musicVolume = musicVolume;
	}

	public void setSkipIntro(boolean skipIntro) {
		this.skipIntro = skipIntro;
	}

	public void setSoundVolume(float soundVolume) {
		this.soundVolume = soundVolume;
	}

	public void setVSync(boolean vsync) {
		this.vsync = vsync;
	}

	public void setWindowed(boolean windowed) {
		this.windowed = windowed;
	}
}
