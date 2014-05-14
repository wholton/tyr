package com.tyr.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.tyr.game.audio.Audio;
import com.tyr.game.screen.helper.ScreenHelper;

public class Tyr extends Game {

	/**
	 * The name this object will be registered as inside the logger.
	 */
	private static final String LOG_NAME = "TYR";

	/**
	 * The string to be displayed at the top of the application
	 */
	public static final String NAME = "\"Thank you, Robutcus.\"";

	/**
	 * The string representing the current version number of the game, which is
	 * displayed with the game's name at the top of the application.
	 * 
	 * First digit is the overall version which changes based the level of
	 * revision (major update or expansion) Second digit represents incremental
	 * changes to a particular version (medium updates) Third digit represents
	 * the development stage of an update (0 is alpha, 1 is beta, 2 is release
	 * candidate, 3 is release) Fourth digit represents the level of completion
	 * of the development stage.
	 * 
	 * EXAMPLES: 1.2.0.1 instead of 1.2-a1 1.2.1.2, instead of 1.2-b2 (beta with
	 * some bug fixes), 1.2.2.3 instead of 1.2-rc3 (release candidate), 1.2.3.0
	 * instead of 1.2-r (commercial distribution), 1.2.3.5 instead of 1.2-r5
	 * (commercial distribution with many bug fixes)
	 */
	// Version 0, unreleased. Working on update 0 (screen support). Currently in
	// alpha (developing, untested, needs java docs).
	// Half-way done: Splash and Menu screen support developed, currently have 
	// two splashes and a main menu. Options and game screens to go.
	public static final String VERSION = "0.0.0.5";

	private static final FPSLogger FPS_LOGGER = new FPSLogger();
	
	private static final String DEFAULT_STARTING_SCREEN = "company";
	private static final String SKIP_INTRO_STARTING_SCREEN = "main-menu";
	private static boolean skipIntro;
	
	private static Tyr Instance = null;

	public final Player player = Player.getInstance();

	public static Tyr getInstance() {
		if (Instance == null) {
			Instance = new Tyr();
		}
		return Instance;
	}

	/**
	 * This class is singleton, and thus its constructor is private.
	 */
	private Tyr() {
		super();
		skipIntro = false;
	}

	@Override
	public void create() {
		Gdx.app.log(LOG_NAME, "Creating");
		final String STARTING_SCREEN;
		if(skipIntro) {
			STARTING_SCREEN = SKIP_INTRO_STARTING_SCREEN;
		} else {
			STARTING_SCREEN = DEFAULT_STARTING_SCREEN;
		}
			
		setScreen(ScreenHelper.buildScreen(STARTING_SCREEN));
		// TODO: Load game data.
	}

	@Override
	public void dispose() {
		Gdx.app.log(LOG_NAME, "Disposing");
		super.dispose();
		// TODO: Save game data.
	}

	@Override
	public void render() {
		super.render();
		// FPS_LOGGER.log();
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log(LOG_NAME, "Resizing game to: " + width + " x " + height);
		super.resize(width, height);
	}

	@Override
	public void resume() {
		Gdx.app.log(LOG_NAME, "Resuming");
		super.resume();
	}

	@Override
	public void setScreen(Screen screen) {
		Gdx.app.log(LOG_NAME, "Setting screen: "
				+ screen.getClass().getSimpleName());
		super.setScreen(screen);
	}
	
}
