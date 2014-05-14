package com.tyr.game;

public class Player {
	
	private static OptionPreferences Prefs = OptionPreferences.getInstance();
	private static Player Instance;
	
	public static Player getInstance() {
		if (Instance == null) {
			Instance = new Player();
		}
		return Instance;
	}
	
	private Player() {
		//load if it is there
	}

	public OptionPreferences getPreferences() {
		return Prefs;
	}
	
}
