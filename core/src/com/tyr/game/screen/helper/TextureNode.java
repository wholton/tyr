package com.tyr.game.screen.helper;
/**
 * The texture that will be used for the splash art, located inside of "assets/texture/splash".
 */
public class TextureNode {

	private final String name;
	private final int count;
	private final String type;
	
	public TextureNode(final String name, final int count, final String type) {
		this.name = name;
		this.count = count;
		this.type = type;
	}

	public int getCount() {
		return count;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}
	
}
