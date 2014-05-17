package com.tyr.game.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.tyr.game.GamePreferences;

public class AudioHelper {

	/**
	 * Represents music that will play despite screens switching.
	 */
	private static Music music;
	private static String musicPath;

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
	public static void playMusic(final String musicPath, boolean looping,
			boolean restart) {
		if (music != null) {
			if (musicPath.equals(AudioHelper.musicPath)) {
				// if we're already playing this track, just update it.
				if (restart) {
					music.stop();
					music.play();
				}
				music.setLooping(looping);
				return;
			}
			// if we're playing a different track, dispose of what's playing
			stopMusic();
		}
		AudioHelper.musicPath = musicPath;
		music = Gdx.audio.newMusic(Gdx.files.internal(musicPath));
		final GamePreferences preferences = GamePreferences.getInstance();
		music.setVolume(preferences.getMasterVolume()
				* preferences.getMusicVolume());
		music.play();
		music.setLooping(looping);
	}

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
			music.dispose();
			music = null;
			musicPath = null;
		}
	}

}
