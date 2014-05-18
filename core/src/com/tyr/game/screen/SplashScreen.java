package com.tyr.game.screen;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tyr.game.AssetHelper;
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
	 * The next screen to be displayed.
	 */
	private final Screen transition;

	/**
	 * The sprite representing the splash image which will be drawn to the
	 * screen. Must dispose of the sprite's texture.
	 */
	private Sprite splash;
	private final AssetDescriptor<Texture> textureDescriptor;

	/**
	 * Handles the splash fading effect.
	 */
	private TweenManager tweenManager;

	public SplashScreen(final float fadeTime, final float displayTime,
			final Screen transition,
			final AssetDescriptor<Texture> textureDescriptor) {
		super();
		this.fadeTime = fadeTime;
		this.displayTime = displayTime;
		this.transition = transition;
		this.textureDescriptor = textureDescriptor;
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		batch.begin();
		splash.draw(batch);
		batch.end();

		tweenManager.update(delta);
	}

	@Override
	public void show() {
		super.show();

		// 2 is the number of times the effect will happen.
		Gdx.app.log(logName, "Transition in " + displayTime + 2 * fadeTime
				+ " seconds");

		splash = new Sprite(AssetHelper.MANAGER.get(textureDescriptor));
		splash.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);

		// Registers TYR's SpriteAccessor class to handle the Sprite tweening
		// effects.
		tweenManager = new TweenManager();
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());

		// Sets the initial alpha value of the sprite such that it is
		// transparent.
		Tween.set(splash, AbstractAccessor.ALPHA).target(ALPHA_TRANSPARENT)
				.start(tweenManager);

		// Fades the alpha of the sprite such that it is opaque. The duration
		// must be in seconds.
		// Also fades the alpha of the sprite such that it is transparent after
		// a delay ("yoyos"). Duration and delay must be in seconds.
		// This also sets a call back which will transition the screen
		TweenCallback tweenCallback = new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				Tyr.getInstance().setScreen(transition);
			}
		};

		Tween.to(splash, AbstractAccessor.ALPHA, fadeTime).target(ALPHA_OPAQUE)
				.repeatYoyo(1, displayTime).setCallback(tweenCallback)
				.start(tweenManager);

		// This is to get rid of the flicker caused by drawing with the batch
		// then
		// updating the tween in render.
		tweenManager.update(Float.MIN_VALUE);
	}
}
