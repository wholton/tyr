package com.tyr.game;

public class GameProgress {

	private static GameProgress Instance;

	public static GameProgress getInstance() {
		if (Instance == null) {
			Instance = new GameProgress();
		}
		return Instance;
	}

	private GameProgress() {
		// load if it is there
	}

}
