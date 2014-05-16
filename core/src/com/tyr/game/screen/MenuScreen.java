package com.tyr.game.screen;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.tyr.game.GamePreferences;
import com.tyr.game.Tyr;
import com.tyr.game.accessor.AbstractAccessor;
import com.tyr.game.accessor.ActorAccessor;

/**
 * Allows the user to pick between multiple options.
 * 
 * @author Bebop
 * 
 */
public class MenuScreen extends AbstractScreen {

	/**
	 * The stage on which we will draw the table and its buttons. This must be disposed of.
	 */
	protected Stage stage;
	
	/**
	 * The spacing between the heading the transition buttons.
	 */
	protected final float TITLE_SPACE = 60;
	
	/**
	 * The spacing between the transition buttons.
	 */
	protected final float BUTTON_SPACE = 20;
	
	/**
	 * The table will hold all of the buttons and be placed on the stage. This
	 * will make it easier to align things.
	 */
	protected Table table;
	
	/**
	 * The image that will fill the background. This must be disposed of.
	 */
	protected Sprite background;
	protected static final String BACKGROUND_TEXTURE_PATH = "texture/main-menu-background1.png";
	
	protected Sound music;
	protected static final String MUSIC_PATH = "audio/track1.mp3";
	
	protected BitmapFont headingFont;
	protected BitmapFont buttonFont;
	protected static final int HEADING_FONT_SIZE = 80;
	protected static final int BUTTON_FONT_SIZE = 40;
	protected static final String FONT_PATH = "font/CRAYON__.TTF";
	
	protected TextButton newGameButton, continueButton, optionsButton, exitButton;
	
	protected TweenManager tweenManager;
	protected final float fadeDuration = .75f;
	
	@Override
	public void dispose() {
		music.dispose();
		stage.dispose();
		background.getTexture().dispose();
		headingFont.dispose();
		buttonFont.dispose();
		super.dispose();
	}
	
	@Override
	public void render(final float delta) {
		super.render(delta);
		
		tweenManager.update(delta);
		
		batch.begin();
		background.draw(batch);
		batch.end();		
		
		stage.act(delta);
		stage.draw();
	}
	
	@Override
	public void resize(final int width, final int height) {
		super.resize(width, height);
		stage.getViewport().update(width, height);
		table.invalidateHierarchy();
		table.setSize(width,  height);
	}
	
	@Override
	public void show() { 
		super.show();
		
		// Setup music
		music = Gdx.audio.newSound(Gdx.files.internal(MUSIC_PATH));
		final GamePreferences preferences = GamePreferences.getInstance();
		music.loop(preferences.getMusicVolume() * preferences.getMasterVolume());
		
		// Setup background texture
		background = new Sprite(new Texture(Gdx.files.internal(BACKGROUND_TEXTURE_PATH)));
		background.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		
		// Setup stage
		final ScalingViewport scalingViewPort = new ScalingViewport(Scaling.stretch, SCREEN_WIDTH, SCREEN_HEIGHT);
		stage = new Stage(scalingViewPort, batch);
		Gdx.input.setInputProcessor(stage);

		// Setup table to align elements
		table = new Table();
		table.setFillParent(true);
		
		// Setup font
		final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_PATH));
		final FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = HEADING_FONT_SIZE;
		headingFont = generator.generateFont(parameter);
		parameter.size = BUTTON_FONT_SIZE;
		buttonFont = generator.generateFont(parameter);
		buttonFont.setColor(Color.GRAY);
		generator.dispose();
		
		// Setup styles
		final LabelStyle headingLabelStyle = new LabelStyle();
		headingLabelStyle.font = headingFont;
		final TextButtonStyle style = new TextButtonStyle();
		style.font = buttonFont;

		// Setup heading
		final Label heading = new Label("Thank you, Robutcus", headingLabelStyle);
		table.add(heading).spaceBottom(TITLE_SPACE);
		table.row();

		// Setup new game button
		newGameButton = new TextButton("New Game", style);
		newGameButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Tyr.getInstance().setScreen(new GameScreen());
				dispose();
				return true;
			}
		});
		table.add(newGameButton).spaceBottom(BUTTON_SPACE);
		table.row();

		// Setup continue game button
		continueButton = new TextButton("Continue", style);
		continueButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Tyr.getInstance().setScreen(new GameScreen());
				dispose();
				return true;
			}
		});
		table.add(continueButton).spaceBottom(BUTTON_SPACE);
		table.row();
		
		// Setup options button
		optionsButton = new TextButton("Option", style);
		optionsButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Tyr.getInstance().setScreen(new OptionsScreen());
				dispose();
				return true;
			}
		});
		table.add(optionsButton).spaceBottom(BUTTON_SPACE);
		table.row();
		
		// Setup exit button
		exitButton = new TextButton("Exit", style);
		exitButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Tyr.getInstance().setScreen(new EndScreen());
				dispose();
				return true;
			}
		});
		table.add(exitButton).spaceBottom(BUTTON_SPACE);
		table.row();
		
		stage.addActor(table);
		
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class, new ActorAccessor());
		
		// Start the game with the buttons disabled
		disableGameButtons(true);
		
		// Setup a callback for when the sequence ends to enable buttons
		TweenCallback tweenCallback = new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				disableGameButtons(false);
			}
		};
		
		// TODO: setting heading fade time to 8 to make up for the lack of a load screen. Use asset manager to fix this
		final int headingFadeTime = GamePreferences.getInstance().useIntro() ? 8 : 2;
		
		// Makes a timeline of events, that runs sequentially, and pushes a tween effect that changes the alpha
		// of the header and buttons from transparent to opaque over the fade time.
		Timeline.createSequence().beginSequence()
			.push(Tween.set(newGameButton, AbstractAccessor.ALPHA).target(ALPHA_TRANSPARENT))
			.push(Tween.set(continueButton, AbstractAccessor.ALPHA).target(ALPHA_TRANSPARENT))
			.push(Tween.set(optionsButton, AbstractAccessor.ALPHA).target(ALPHA_TRANSPARENT))
			.push(Tween.set(exitButton, AbstractAccessor.ALPHA).target(ALPHA_TRANSPARENT))
			.push(Tween.from(heading, AbstractAccessor.ALPHA, headingFadeTime).target(ALPHA_TRANSPARENT)) 
			.push(Tween.to(newGameButton, AbstractAccessor.ALPHA, fadeDuration).target(ALPHA_OPAQUE))
			.push(Tween.to(continueButton, AbstractAccessor.ALPHA, fadeDuration).target(ALPHA_OPAQUE))
			.push(Tween.to(optionsButton, AbstractAccessor.ALPHA, fadeDuration).target(ALPHA_OPAQUE))
			.push(Tween.to(exitButton, AbstractAccessor.ALPHA, fadeDuration).target(ALPHA_OPAQUE))
			.end().setCallback(tweenCallback).start(tweenManager);
	}
	
	protected void disableGameButtons(boolean bool) {
		newGameButton.setDisabled(bool);
		continueButton.setDisabled(bool);
		optionsButton.setDisabled(bool);
		exitButton.setDisabled(bool);
	}
}
