package com.tyr.game.screen;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tyr.game.Tyr;
import com.tyr.game.accessor.SpriteAccessor;
import com.tyr.game.screen.helper.DurationNode;
import com.tyr.game.screen.helper.ScreenHelper;
import com.tyr.game.screen.helper.TextureNode;

/**
 * Displays a full-screen texture for a particular duration of time before
 * fading to a follow-up screen.
 * 
 * @author Bebop
 * 
 */
public class SplashScreen extends AbstractScreen {

	private final DurationNode durationNode;

	/**
	 * The total duration in milliseconds that the splash screen will be
	 * displayed.
	 */
	private final float totalDuration;

	/**
	 * The next screen to be displayed.
	 */
	private final String transition;

	/**
	 * The sprite representing the splash image which will be drawn to the
	 * screen. Must dispose of the sprite's texture.
	 */
	private Sprite splash;
	private final TextureNode textureNode;
	
	/**
	 * Handles the splash fading effect.
	 */
	private TweenManager tweenManager;
	private static final int YOYO_COUNT = 2;

	public SplashScreen(final DurationNode durationNode, final String transition, final TextureNode textureNode) {
		super();
		this.durationNode = durationNode;
		this.totalDuration = durationNode.getDisplay() + YOYO_COUNT * durationNode.getFade();
		this.transition = transition;
		this.textureNode = textureNode;
	}

	@Override
	public void dispose() {
		super.dispose();
		splash.getTexture().dispose();
	}

	@Override
	public void render(float delta) {
			super.render(delta);
	
			tweenManager.update(delta);
	
			batch.begin();
			splash.draw(batch);
			batch.end();
	}

	@Override
	public void show() {
		super.show();
		final float totalDurationSeconds = totalDuration / 1000;
		Gdx.app.log(logName, "Transition in "
				+ totalDurationSeconds + " seconds");
		
		// Registers TYR's SpriteAccessor class to handle the Sprite tweening
		// effects.
		tweenManager = new TweenManager();
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());

		splash = new Sprite(buildTexture(textureNode));
		splash.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);

		// Sets the initial alpha value of the sprite such that it is
		// transparent.
		Tween.set(splash, SpriteAccessor.ALPHA).target(ALPHA_TRANSPARENT)
				.start(tweenManager);
		// Fades the alpha of the sprite such that it is opaque. The duration
		// must be in seconds.
		// Also fades the alpha of the sprite such that it is transparent after
		// a delay ("yoyos"). Duration and delay must be in seconds.
		// This also sets a call back which will transition the screen
		final float fadeDurationSeconds = durationNode.getFade() / 1000;
		final float displayDurationSeconds = durationNode.getDisplay() / 1000;
		TweenCallback tweenCallback = new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				//tyr.getInstance().setScreen(ScreenHelper.buildScreen(transition));
				Tyr.getInstance().setScreen(ScreenHelper.buildScreen(transition));
				dispose();
			}
		};
		Tween.to(splash, SpriteAccessor.ALPHA, fadeDurationSeconds)
				.target(ALPHA_OPAQUE).repeatYoyo(1, displayDurationSeconds)
				.setCallback(tweenCallback).start(tweenManager);
	}
}
