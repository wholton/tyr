package com.tyr.game.screen.helper;

public class FontNode {
	
	private final String name;
	private final int size;
	private final ColorNode color;
	
	public FontNode(final String name, final int size, final ColorNode color) {
		this.name = name;
		this.size = size;
		this.color = color;
	}

	public ColorNode getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	public int getSize() {
		return size;
	}
}
