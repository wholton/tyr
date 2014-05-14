package com.tyr.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.tyr.game.OptionPreferences;
import com.tyr.game.Player;
import com.tyr.game.Tyr;
import com.tyr.game.screen.helper.ColorNode;
import com.tyr.game.screen.helper.FontNode;
import com.tyr.game.screen.helper.LabelNode;

public class OptionsScreen extends AbstractScreen {

	/**
	 * The spacing between the heading the options.
	 */
	protected final float TITLE_SPACE = 50;
	
	/**
	 * The spacing between the options.
	 */
	protected final float OPTION_SPACE = 20;
	
	private Stage stage;
	private Table table;
	private Slider masterVolume;
	private Slider gameVolume;
	private Slider musicVolume;
	private Label masterValue;
	private Label gameValue;
	private Label musicValue;

	public OptionsScreen() {
		super();
	}

	@Override
	public void show() {
		super.show();
		setupOptionsMenu();
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);

	}
	
	private void setupOptionsMenu() {
		
		final ScalingViewport scalingViewPort = new ScalingViewport(Scaling.stretch, SCREEN_WIDTH, SCREEN_HEIGHT);
		stage = new Stage(scalingViewPort, batch);
		Gdx.input.setInputProcessor(stage);
		
		table = new Table();
		table.setFillParent(true);
		stage.addActor(table);

		ColorNode colorNode = new ColorNode(0, 0, 0, 1);
		FontNode headerFontNode = new FontNode("CRAYON__.TTF", 80, colorNode);
		Label header = buildLabel(new LabelNode("Options", headerFontNode));
		table.add(header).spaceBottom(TITLE_SPACE);
		table.row();

		final OptionPreferences prefs = Tyr.getInstance().player.getPreferences();
		
		final FontNode optionsFontNode = new FontNode("CRAYON__.TTF", 80, colorNode);
		table.add(buildLabel(new LabelNode("Master Volume: ", optionsFontNode)));
		final SliderStyle sliderStyle = new SliderStyle();
		masterVolume = new Slider(0, 100, 5, false, sliderStyle);
		masterVolume.setValue(100 * prefs.getMasterVolume());

		// Add listener
		masterVolume.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				masterValue.setText((int) masterVolume.getValue() + "%");
				prefs.setMusicVolume((masterVolume.getValue() / 100)
						* (musicVolume.getValue() / 100));
				prefs.setSoundVolume((masterVolume.getValue() / 100)
						* (gameVolume.getValue() / 100));
			}
		});
		
		table.add(masterVolume);
		masterValue = buildLabel(new LabelNode((int) masterVolume.getValue() + "%", optionsFontNode));
		table.add(masterValue);
		table.row().spaceBottom(OPTION_SPACE);
		/*
		// Create gameVolume label
		table.add(buildLabel(new LabelNode("Game Volume: ", optionsFontNode)));
		gameVolume = new Slider(0, 100, 5, false, super.getSkin());
		gameVolume.setValue(100 * sounds.getSoundVolume());

		// Add listener
		gameVolume.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				gameValue.setText("" + (int) gameVolume.getValue() + "%");
				sounds.setSoundVolume((masterVolume.getValue() / 100)
						* (gameVolume.getValue() / 100));
			}
		});
		table.add(gameVolume);
		gameValue = new Label("" + (int) gameVolume.getValue() + "%",
				super.getSkin());
		table.add(gameValue);
		table.row();

		// Create musicVolume label
		table.add("Music Volume: ");
		musicVolume = new Slider(0, 100, 5, false, super.getSkin());
		musicVolume.setValue(100 * sounds.getMusicVolume());

		// Add listener
		musicVolume.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				musicValue.setText("" + (int) musicVolume.getValue() + "%");
				sounds.setMusicVolume((masterVolume.getValue() / 100)
						* (musicVolume.getValue() / 100));
			}
		});
		table.add(musicVolume);
		musicValue = new Label("" + (int) musicVolume.getValue() + "%",
				super.getSkin());
		table.add(musicValue);
		table.row();

		// back button
		TextButton backButton = new TextButton("Back", super.getSkin());
		backButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				FarmAdventure.getInstance().setScreen(new MainMenuScreen());
				return true;
			}
		});
		table.add(backButton).spaceTop(200).colspan(3).width(300);
		*/
	}	
	
}
