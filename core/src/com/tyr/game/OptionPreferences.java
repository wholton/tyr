package com.tyr.game;

public class OptionPreferences {

	private float musicVolume;
	private float soundVolume;
	private float masterVolume;
	
	private boolean useGL30;
	private boolean useVSync;
	private boolean useResizable;
	private boolean useFullscreen;
	
	private static OptionPreferences Instance;
	
	public static OptionPreferences getInstance() {
		if (Instance == null) {
			Instance = new OptionPreferences();
		}
		return Instance;
	}
	
	private OptionPreferences() {
		musicVolume = 1;
		soundVolume = 1;
		masterVolume = 1;
		useGL30 = true;
		useVSync = false;
		useResizable = false;
		useFullscreen = true;
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
	public boolean useResizable() {
		return useResizable;
	}
	public boolean useVSync() {
		return useVSync;
	}
	
}
