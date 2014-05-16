package com.tyr.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tyr.game.GamePreferences;

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
	
	protected final boolean useGL30 = GamePreferences.getInstance().useGL30();
	
	public AbstractScreen() {
		logName = this.getClass().getSimpleName();
		Gdx.app.log(logName, "Constructor called");
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		Gdx.app.log(logName, "Dispose called");
	}
	
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		Gdx.app.log(logName, "Hide called");
	}
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		Gdx.app.log(logName, "Pause called");
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		//TODO: Unsure how needed this is.
		if(useGL30)
			Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		else 
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		Gdx.app.log(logName, "Resize called");
	}
	
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		Gdx.app.log(logName, "Resume called");
	}
	
	@Override
	public void show() {
		Gdx.app.log(logName, "Show called");
		batch = new SpriteBatch();
	}
}
