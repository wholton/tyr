package com.tyr.game.screen.helper;

public class ColorNode {
	
	private final float red;
	private final float green;
	private final float blue;
	private final float alpha;
	
	public ColorNode(final float red, final float green, final float blue, final float alpha) {
		this.red = red;
		this. green = green;
		this. blue = blue;
		this.alpha = alpha;
	}

	public float getAlpha() {
		return alpha;
	}

	public float getBlue() {
		return blue;
	}

	public float getGreen() {
		return green;
	}

	public float getRed() {
		return red;
	}
}
