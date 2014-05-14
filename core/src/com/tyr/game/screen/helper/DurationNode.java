package com.tyr.game.screen.helper;

public class DurationNode {
	/**
	 * The duration in milliseconds that this splash screen will be displayed
	 * (this does not include fade-in and fade-out time).
	 */
	private final float display;
	/**
	 * The duration in milliseconds of the transparent to opaque (and back)
	 * effect.
	 */
	private final float fade;
	
	public DurationNode(final float display, final float fade) {
		this.display = display;
		this.fade = fade;
	}

	public float getDisplay() {
		return display;
	}

	public float getFade() {
		return fade;
	}
}
