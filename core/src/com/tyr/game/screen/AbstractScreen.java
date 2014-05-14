package com.tyr.game.screen;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.tyr.game.Tyr;
import com.tyr.game.screen.helper.ColorNode;
import com.tyr.game.screen.helper.FontNode;
import com.tyr.game.screen.helper.LabelNode;
import com.tyr.game.screen.helper.ScreenHelper;
import com.tyr.game.screen.helper.TextureNode;
import com.tyr.game.screen.helper.TransitionButtonNode;

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

	protected BitmapFont buildBitmapFont(final FontNode node) {
		final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/" + node.getName()));
		final FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = node.getSize();
		final BitmapFont font = generator.generateFont(parameter);
		font.setColor(buildColor(node.getColor()));
		generator.dispose();
		
		return font;
	}

	protected Color buildColor(final ColorNode node) {
		return new Color(node.getRed(), node.getGreen(), node.getBlue(), node.getAlpha());
	}

	protected Label buildLabel(final LabelNode node) {
		final LabelStyle style = new LabelStyle();
		style.font = buildBitmapFont(node.getFont());
		
		return new Label(node.getText(), style);
	}

	protected Texture buildTexture(final TextureNode textureNode) {
		// Combine the texture fields to form a string such as "texture2.jpg".
		// The count is incremented because texture
		// indexes start at 1.
		final String fullTextureName = textureNode.getName() + ((new Random()).nextInt(textureNode.getCount()) + 1) + textureNode.getType();
		
		return new Texture(Gdx.files.internal("texture/" + fullTextureName));
	}

	protected TextButton buildTransitionButton(final TransitionButtonNode node) {
		final TextButtonStyle style = new TextButtonStyle();
		style.font = buildBitmapFont(node.getFont());
		final TextButton button = new TextButton(node.getText(), style);
		button.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Tyr.getInstance().setScreen(ScreenHelper.buildScreen(node.getTransition()));
				dispose();
				return true;
			}
		});
		
		return button;
	}

	protected ArrayList<TextButton> buildTransitionButtons(final ArrayList<TransitionButtonNode> nodes) {
		ArrayList<TextButton> transitionButtons = new ArrayList<TextButton>();
		for(TransitionButtonNode node : nodes) {
			transitionButtons.add(buildTransitionButton(node));
		}
		
		return transitionButtons;
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
