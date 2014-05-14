package com.tyr.game.screen.helper;

public class LabelNode {
	
	private final String text;
	private final FontNode font;
	
	public LabelNode(final String text, final FontNode font) {
		this.text = text;
		this.font = font;
	}

	public FontNode getFont() {
		return font;
	}

	public String getText() {
		return text;
	}
}
