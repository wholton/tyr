package com.tyr.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.tyr.game.AssetHelper;
import com.tyr.game.GamePreferences;
import com.tyr.game.Tyr;

public class OptionsScreen extends AbstractScreen {

	/**
	 * The spacing between the heading the options.
	 */
	protected final float TITLE_SPACE = 64;

	/**
	 * The spacing between the options.
	 */
	protected final float OPTION_SPACE = 16;

	private Stage stage;
	private Table table;

	private Sprite background;

	private BitmapFont optionFont;
	private BitmapFont headingFont;
	private BitmapFont buttonFont;
	private static final int OPTION_FONT_SIZE = 32;
	private static final int HEADING_FONT_SIZE = 64;
	private static final int BUTTON_FONT_SIZE = 64;
	private static final String FONT_PATH = "font/CRAYON__.TTF";

	private Skin skin;

	@Override
	public void dispose() {
		optionFont.dispose();
		headingFont.dispose();
		stage.dispose();
		super.dispose();
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		batch.begin();
		background.draw(batch);
		batch.end();

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void show() {
		super.show();

		// Setup stage
		final ScalingViewport scalingViewPort = new ScalingViewport(
				Scaling.stretch, SCREEN_WIDTH, SCREEN_HEIGHT);
		stage = new Stage(scalingViewPort, batch);
		Gdx.input.setInputProcessor(stage);

		// Setup background sprite
		background = new Sprite(
				AssetHelper.MANAGER.get(AssetHelper.OPTIONS_BACKGROUND1));

		// Setup table to align items
		table = new Table();
		table.setFillParent(true);

		// Setup skin
		skin = AssetHelper.MANAGER.get(AssetHelper.SKIN);

		// Setup fonts
		final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
				Gdx.files.internal(FONT_PATH));
		final FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = HEADING_FONT_SIZE;
		headingFont = generator.generateFont(parameter);
		parameter.size = OPTION_FONT_SIZE;
		optionFont = generator.generateFont(parameter);
		optionFont.setColor(Color.GRAY);
		parameter.size = BUTTON_FONT_SIZE;
		buttonFont = generator.generateFont(parameter);
		generator.dispose();

		// Setup label styles
		final LabelStyle optionLabelStyle = new LabelStyle();
		optionLabelStyle.font = optionFont;
		final LabelStyle headingLabelStyle = new LabelStyle();
		headingLabelStyle.font = headingFont;
		final TextButtonStyle buttonStyle = new TextButtonStyle();
		buttonStyle.font = buttonFont;

		// Setup heading
		table.add(new Label("Options", headingLabelStyle))
				.spaceBottom(TITLE_SPACE).colspan(2);
		table.row();

		// Setup game preferences that the sliders + checkboxes will be editing
		final GamePreferences gamePreferences = GamePreferences.getInstance();

		// Setup master volume option
		table.add(new Label("Master Volume", optionLabelStyle))
				.spaceBottom(OPTION_SPACE).spaceRight(OPTION_SPACE);
		final Slider masterVolumeSlider = new Slider(0, 1, .01f, false, skin);
		masterVolumeSlider.setValue(gamePreferences.getMasterVolume());
		masterVolumeSlider.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				gamePreferences.setMasterVolume(masterVolumeSlider.getValue());
			}
		});
		table.add(masterVolumeSlider).spaceBottom(OPTION_SPACE);
		table.row();

		// Setup sound volume option
		table.add(new Label("Sound Effect Volume", optionLabelStyle))
				.spaceBottom(OPTION_SPACE).spaceRight(OPTION_SPACE);
		final Slider soundVolumeSlider = new Slider(0, 1, .01f, false, skin);
		soundVolumeSlider.setValue(gamePreferences.getSoundVolume());
		soundVolumeSlider.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				gamePreferences.setSoundVolume(soundVolumeSlider.getValue());
			}
		});
		table.add(soundVolumeSlider).spaceBottom(OPTION_SPACE);
		table.row();

		// Setup music volume option
		table.add(new Label("Music Volume", optionLabelStyle))
				.spaceBottom(OPTION_SPACE).spaceRight(OPTION_SPACE);
		final Slider musicVolumeSlider = new Slider(0, 1, .01f, false, skin);
		musicVolumeSlider.setValue(gamePreferences.getMusicVolume());
		musicVolumeSlider.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				gamePreferences.setMusicVolume(musicVolumeSlider.getValue());
			}
		});
		table.add(musicVolumeSlider).spaceBottom(OPTION_SPACE);
		table.row();

		// Setup fullscreen option
		table.add(new Label("Fullscreen", optionLabelStyle))
				.spaceBottom(OPTION_SPACE).spaceRight(OPTION_SPACE);
		final CheckBox fullscreenCheckBox = new CheckBox("", skin);
		fullscreenCheckBox.setChecked(gamePreferences.isWindowed());
		fullscreenCheckBox.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				gamePreferences.setWindowed(fullscreenCheckBox.isChecked());
			}
		});
		table.add(fullscreenCheckBox).spaceBottom(OPTION_SPACE);
		table.row();

		// Setup vsync option
		table.add(new Label("VSync", optionLabelStyle))
				.spaceBottom(OPTION_SPACE).spaceRight(OPTION_SPACE);
		final CheckBox vsyncCheckBox = new CheckBox("", skin);
		vsyncCheckBox.setChecked(gamePreferences.isVSync());
		vsyncCheckBox.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				gamePreferences.setVSync(vsyncCheckBox.isChecked());
			}
		});
		table.add(vsyncCheckBox).spaceBottom(OPTION_SPACE);
		table.row();

		// Setup intro option
		table.add(new Label("Skip Intro", optionLabelStyle))
				.spaceBottom(TITLE_SPACE).spaceRight(OPTION_SPACE);
		final CheckBox introCheckBox = new CheckBox("", skin);
		introCheckBox.setChecked(gamePreferences.isSkipIntro());
		introCheckBox.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				gamePreferences.setSkipIntro(introCheckBox.isChecked());
			}
		});
		table.add(introCheckBox).spaceBottom(TITLE_SPACE);
		table.row();

		// Setup the submit button
		final TextButton submitButton = new TextButton("Submit", buttonStyle);
		submitButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				gamePreferences.saveData();
				Tyr.getInstance().setScreen(new MenuScreen());
				return true;
			}
		});
		table.add(submitButton);

		// Setup the cancel button
		final TextButton cancelButton = new TextButton("Cancel", buttonStyle);
		cancelButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				gamePreferences.loadData();
				Tyr.getInstance().setScreen(new MenuScreen());
				return true;
			}
		});
		table.add(cancelButton);
		table.row();

		stage.addActor(table);
	}

}
