package com.tyr.game.screen;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tyr.game.Tyr;
import com.tyr.game.accessor.AbstractAccessor;
import com.tyr.game.accessor.SpriteAccessor;

/**
 * Displays a full-screen texture for a particular duration of time before
 * fading to a follow-up screen.
 * 
 * @author Bebop
 * 
 */
public class SplashScreen extends AbstractScreen {

	private final float fadeTime;
	private final float displayTime;

	/**
	 * The total duration in milliseconds that the splash screen will be
	 * displayed.
	 */
	private final float totalDuration;

	/**
	 * The next screen to be displayed.
	 */
	private final Screen transition;

	/**
	 * The sprite representing the splash image which will be drawn to the
	 * screen. Must dispose of the sprite's texture.
	 */
	private Sprite splash;
	private final String texturePath;

	/**
	 * Handles the splash fading effect.
	 */
	private TweenManager tweenManager;
	private static final int YOYO_COUNT = 2;

	private boolean disposed;

	public SplashScreen(final float fadeTime, final float displayTime,
			final Screen transition, final String texturePath) {
		super();
		this.fadeTime = fadeTime;
		this.displayTime = displayTime;
		this.totalDuration = displayTime + YOYO_COUNT * fadeTime;
		this.transition = transition;
		this.texturePath = texturePath;
	}

	@Override
	public void dispose() {
		splash.getTexture().dispose();
		super.dispose();
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		tweenManager.update(delta);

		if (!disposed) {
			batch.begin();
			splash.draw(batch);
			batch.end();
		}
	}

	@Override
	public void show() {
		super.show();

		disposed = false;

		final float totalDurationSeconds = totalDuration / 1000;
		Gdx.app.log(logName, "Transition in " + totalDurationSeconds
				+ " seconds");

		// Registers TYR's SpriteAccessor class to handle the Sprite tweening
		// effects.
		tweenManager = new TweenManager();
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());

		splash = new Sprite(new Texture(Gdx.files.internal(texturePath)));
		splash.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);

		// Sets the initial alpha value of the sprite such that it is
		// transparent.
		Tween.set(splash, AbstractAccessor.ALPHA).target(ALPHA_TRANSPARENT)
				.start(tweenManager);
		// Fades the alpha of the sprite such that it is opaque. The duration
		// must be in seconds.
		// Also fades the alpha of the sprite such that it is transparent after
		// a delay ("yoyos"). Duration and delay must be in seconds.
		// This also sets a call back which will transition the screen
		final float fadeDurationSeconds = fadeTime / 1000;
		final float displayDurationSeconds = displayTime / 1000;
		TweenCallback tweenCallback = new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				Tyr.getInstance().setScreen(transition);
				disposed = true;
			}
		};
		Tween.to(splash, AbstractAccessor.ALPHA, fadeDurationSeconds)
				.target(ALPHA_OPAQUE).repeatYoyo(1, displayDurationSeconds)
				.setCallback(tweenCallback).start(tweenManager);
	}
}
