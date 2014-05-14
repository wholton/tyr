package com.tyr.game.screen;

import java.util.ArrayList;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.tyr.game.OptionPreferences;
import com.tyr.game.Tyr;
import com.tyr.game.accessor.AbstractAccessor;
import com.tyr.game.accessor.ActorAccessor;
import com.tyr.game.screen.helper.LabelNode;
import com.tyr.game.screen.helper.TextureNode;
import com.tyr.game.screen.helper.TransitionButtonNode;

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
	 * The heading which will display at the top of the screen. The display's font must be disposed of.
	 */
	protected Label heading;
	private final LabelNode headingNode;
	
	/**
	 * The spacing between the heading the transition buttons.
	 */
	protected final float TITLE_SPACE = 50;
	
	/**
	 * The spacing between the transition buttons.
	 */
	protected final float BUTTON_SPACE = 20;
	
	/**
	 * A list of buttons to be displayed vertically in order below the heading. These buttons transition the screen.
	 * Each button's font must be disposed of.
	 */
	protected ArrayList<TextButton> transitionButtons;
	private final ArrayList<TransitionButtonNode> transitionButtonNodes;
	
	/**
	 * The table will hold all of the buttons and be placed on the stage. This
	 * will make it easier to align things.
	 */
	protected Table table;
	
	/**
	 * The image that will fill the background. This must be disposed of.
	 */
	protected Sprite background;
	private final TextureNode backgroundTextureNode;
	
	protected Sound music;
	
	protected TweenManager tweenManager;
	protected final float fadeDuration = .75f;
	//TODO: perhaps if button alpha != OPAQUE, no actions?
	public MenuScreen(final LabelNode headingNode, final TextureNode backgroundTextureNode, final ArrayList<TransitionButtonNode> transitionButtonNodes) {
		super();
		this.headingNode = headingNode;
		this.backgroundTextureNode = backgroundTextureNode;
		this.transitionButtonNodes = transitionButtonNodes;
	}
	
	@Override
	public void dispose() {
		music.dispose();
		stage.dispose();
		background.getTexture().dispose();
		heading.getStyle().font.dispose();
		for(final TextButton button : transitionButtons) {
			button.getStyle().font.dispose();
		}
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
		
		music = Gdx.audio.newSound(Gdx.files.internal("audio/track1.mp3"));
		final OptionPreferences prefs = Tyr.getInstance().player.getPreferences();
		music.loop(prefs.getMusicVolume() * prefs.getMasterVolume());
		
		background = new Sprite(buildTexture(backgroundTextureNode));
		
		final ScalingViewport scalingViewPort = new ScalingViewport(Scaling.stretch, SCREEN_WIDTH, SCREEN_HEIGHT);
		stage = new Stage(scalingViewPort, batch);
		Gdx.input.setInputProcessor(stage);

		table = new Table();
		table.setFillParent(true);
		
		heading = buildLabel(headingNode);
		table.add(heading).spaceBottom(TITLE_SPACE);
		table.row();

		transitionButtons = new ArrayList<TextButton>();
		transitionButtons.addAll(buildTransitionButtons(transitionButtonNodes));
		
		for(final TextButton button : transitionButtons) {
			table.add(button).spaceBottom(BUTTON_SPACE);
			table.row();
		}
		
		stage.addActor(table);
		
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class, new ActorAccessor());
		// Makes a timeline of events, that runs sequentially, and pushes a tween effect that changes the alpha
		// of the header and buttons from transparent to opaque over the fade time.
		// Start the buttons out as transparent
		final Timeline timeline = Timeline.createSequence().beginSequence();
		//TODO: should not be able to click buttons if they're not loaded in yet.
		for(TextButton button : transitionButtons) {
			timeline.push(Timeline.createSequence().beginSequence()
					.push(Tween.set(button, AbstractAccessor.ALPHA).target(ALPHA_TRANSPARENT)).end());
		} 

		// Fade in the heading
		timeline.push(Timeline.createSequence().beginSequence()
				.push(Tween.from(heading, AbstractAccessor.ALPHA, 10).target(ALPHA_TRANSPARENT)).end());
		//TODO use asset manager and load screen so I don't have to use this value
		// Fade in the buttons 
		for(TextButton button : transitionButtons) {
			timeline.push(Timeline.createSequence().beginSequence()
					.push(Tween.to(button, AbstractAccessor.ALPHA, fadeDuration).target(ALPHA_OPAQUE)).end());
		}
		
		//start the timeline
		timeline.end().start(tweenManager);
	}
}
