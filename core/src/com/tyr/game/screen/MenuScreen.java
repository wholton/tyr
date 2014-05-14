package com.tyr.game.screen;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
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
	
	public MenuScreen(final LabelNode headingNode, final TextureNode backgroundTextureNode, final ArrayList<TransitionButtonNode> transitionButtonNodes) {
		super();
		this.headingNode = headingNode;
		this.backgroundTextureNode = backgroundTextureNode;
		this.transitionButtonNodes = transitionButtonNodes;

	}
	
	@Override
	public void dispose() {
		stage.dispose();
		background.getTexture().dispose();
		heading.getStyle().font.dispose();
		for(final TextButton button : transitionButtons) {
			button.getStyle().font.dispose();
		}
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
		
		background = new Sprite(buildTexture(backgroundTextureNode));
		
		ScalingViewport scalingViewPort = new ScalingViewport(Scaling.stretch, SCREEN_WIDTH, SCREEN_HEIGHT, camera);
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
			table.add(button);
			table.row();
		}
		
		stage.addActor(table);
	}	
}
