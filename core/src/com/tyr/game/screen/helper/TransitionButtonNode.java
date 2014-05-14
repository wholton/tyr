package com.tyr.game.screen.helper;

public class TransitionButtonNode {
	
	private final String text;
	private final String transition;
	private final FontNode font;
	
	public TransitionButtonNode(final String text, final String transition, final FontNode font) {
		this.text = text;
		this.transition = transition;
		this.font = font;
	}

	public FontNode getFont() {
		return font;
	}

	public String getText() {
		return text;
	}

	public String getTransition() {
		return transition;
	}
}
