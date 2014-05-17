package com.tyr.game.screen;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tyr.game.accessor.AbstractAccessor;
import com.tyr.game.accessor.SpriteAccessor;

public class EndScreen extends AbstractScreen {

	/**
	 * The image to be splashed at the end of the game.
	 */
	private Sprite splash;
	
	/**
	 * The path to the image to be splashed at the end of the game.
	 */
	protected static final String TEXTURE_PATH = "texture/end-splash.png";

	/**
	 * Handles the splash fading effect.
	 */
	private TweenManager tweenManager;
	
	private boolean disposed;

	@Override
	public void dispose() {
		splash.getTexture().dispose();
		super.dispose();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
		tweenManager.update(delta);
		
		if(!disposed) {
			batch.begin();
			splash.draw(batch);
			batch.end();
		}
	}

	@Override
	public void show() {
		super.show();
		
		disposed = false;
		
		// TODO: Render credits.
		tweenManager = new TweenManager();
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());

		splash = new Sprite(new Texture(Gdx.files.internal(TEXTURE_PATH)));
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
		final float FADE_DURATION_SECONDS = 1;
		final float DISPLAY_DURATION_SECONDS = 1;
		TweenCallback tweenCallback = new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				disposed = true;
				Gdx.app.exit();
			}
		};
		Tween.to(splash, AbstractAccessor.ALPHA, FADE_DURATION_SECONDS)
				.target(ALPHA_OPAQUE).repeatYoyo(1, DISPLAY_DURATION_SECONDS)
				.setCallback(tweenCallback).start(tweenManager);
	}
}
