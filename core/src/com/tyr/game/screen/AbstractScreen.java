package com.tyr.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AbstractScreen implements Screen {

	/**
	 * The name this object will be registered as inside the logger.
	 */
	protected final String logName;

	protected static final int SCREEN_WIDTH = Gdx.graphics.getWidth();
	protected static final int SCREEN_HEIGHT = Gdx.graphics.getHeight();

	public static final float ALPHA_OPAQUE = 1;
	public static final float ALPHA_TRANSPARENT = 0;

	protected SpriteBatch batch;

	public AbstractScreen() {
		logName = this.getClass().getSimpleName();
		Gdx.app.log(logName, "Constructor called");
	}

	@Override
	public void dispose() {
		Gdx.app.log(logName, "Dispose called");
		batch.dispose();
	}

	@Override
	public void hide() {
		Gdx.app.log(logName, "Hide called");
		dispose();
	}

	@Override
	public void pause() {
		Gdx.app.log(logName, "Pause called");
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log(logName, "Resize called");
	}

	@Override
	public void resume() {
		Gdx.app.log(logName, "Resume called");
	}

	@Override
	public void show() {
		Gdx.app.log(logName, "Show called");
		batch = new SpriteBatch();
	}
}
