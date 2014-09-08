package com.tyr.game.audio;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Music;
import com.tyr.game.asset.AssetHelper;
import com.tyr.game.data.GamePreferences;

/**
 * A helper class that allows for music to be displayed despite screens
 * switching.
 * 
 * @author Bebop
 * @version 0.0.3.0
 */
public class AudioHelper {

	/**
	 * Represents music that will play despite screens switching.
	 */
	private static Music music;

	/**
	 * Represents the descriptor of the particular asset to be played which will
	 * be used to ask the asset manager for the concrete asset.
	 */
	private static AssetDescriptor<Music> musicDescriptor;

	/**
	 * Plays music that will continue between screens.
	 * 
	 * @param musicPath
	 *            The path to the track to be played.
	 * @param looping
	 *            Whether the music should loop.
	 * @param restart
	 *            Whether the music should restart if it was already playing the
	 *            given track.
	 */
	public static void playMusic(final AssetDescriptor<Music> musicDescriptor,
			boolean looping, boolean restart) {
		if (music != null) {
			if (AudioHelper.musicDescriptor.fileName
					.equals(musicDescriptor.fileName)) {
				// if already playing this track, just update it.
				if (restart) {
					music.stop();
					music.play();
				}
				music.setLooping(looping);
				return;
			}
			// if currently playing a different track, stop what's playing
			// before switching
			stopMusic();
		}
		AudioHelper.musicDescriptor = musicDescriptor;
		music = AssetHelper.MANAGER.get(AssetHelper.TRACK1);
		final GamePreferences preferences = GamePreferences.getInstance();
		music.setVolume(preferences.getMasterVolume()
				* preferences.getMusicVolume());
		music.play();
		music.setLooping(looping);
	}

	/**
	 * Sets the volume of the currently playing song to the given value.
	 * 
	 * @param volume
	 *            the volume the music will be set to
	 */
	public static void setVolume(final float volume) {
		if (music != null) {
			music.setVolume(volume);
		}
	}

	/**
	 * If music is being played through the Tyr instance, this method will stop
	 * it.
	 */
	public static void stopMusic() {
		if (music != null) {
			music.stop();
			music = null;
			musicDescriptor = null;
		}
	}

}
