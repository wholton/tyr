package com.tyr.game.audio;

public class Audio {
	/**
	 * Represents the volume level of game sounds, such as a "click"
	 */
	protected float soundVolume;

	protected float musicVolume;
	
	/**
	 * Scales the volume of the game.
	 */
	protected float masterVolume;

	private static Audio Instance;

	/**
	 * Creates a Audio instance if it doesn't exist, or uses an already
	 * existing one.
	 * 
	 * @return a Singleton instance of the Audio class
	 */
	public static Audio getInstance() {
		if (Instance == null) {
			Instance = new Audio();
		}
		return Instance;
	}

	private Audio() {
		soundVolume = 1;
		masterVolume = 1;
		musicVolume = 1;
	}

	/**
	 * Returns the master volume of the game.
	 * 
	 * @return masterVolume
	 */
	public float getMasterVolume() {
		return masterVolume;
	}

	public float getMusicVolume() {
		return musicVolume;
	}

	/**
	 * Returns the volume of sounds within the game.
	 * 
	 * @return soundVolume
	 */
	public float getSoundVolume() {
		return soundVolume;
	}
	
	/**
	 * Sets the volume of the master volume
	 * 
	 * @param volume
	 *            a float in the range [0,1]. 0 is the lowest volume and 1 is
	 *            the highest.
	 */
	public void setMasterVolume(float masterVolume) {
		this.masterVolume = masterVolume;
	}

	/**
	 * Sets the volume of the music.
	 * 
	 * @param vol
	 *            a float in the range [0,1]. 0 is the lowest volume and 1 is
	 *            the highest.
	 */
	public void setMusicVolume(float musicVolume) {
		this.musicVolume = musicVolume;
	}

	/**
	 * Sets the volume of the sounds.
	 * 
	 * @param vol
	 *            a float in the range [0,1]. 0 is the lowest volume and 1 is
	 *            the highest.
	 */
	public void setSoundVolume(float soundVolume) {
		this.soundVolume = soundVolume;
	}
}
