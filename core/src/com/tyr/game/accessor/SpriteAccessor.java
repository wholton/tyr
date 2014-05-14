package com.tyr.game.accessor;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteAccessor implements TweenAccessor<Sprite> {

	public static final int ALPHA = 0;
	public static final String ERROR_MESSAGE_TWEEN_TYPE = "Unknown tweenType.";

	@Override
	public int getValues(Sprite target, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case ALPHA:
			returnValues[0] = target.getColor().a;
			return 1; // return the number of return values in the array.
		default:
			throw new RuntimeException(ERROR_MESSAGE_TWEEN_TYPE);
		}
	}

	@Override
	public void setValues(Sprite target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case ALPHA:
			target.setColor(target.getColor().r, target.getColor().g,
					target.getColor().b, newValues[0]);
			break;
		default:
			throw new RuntimeException(ERROR_MESSAGE_TWEEN_TYPE);
		}
	}

}
