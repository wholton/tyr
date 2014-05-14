package com.tyr.game.accessor;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorAccessor extends AbstractAccessor implements TweenAccessor<Actor> {

	@Override
	public int getValues(Actor target, int tweenType, float[] returnValues) {
		switch(tweenType) {
			case ALPHA:
				returnValues[0] = target.getColor().a;
				return 1;
			case RGB:
				returnValues[0] = target.getColor().r;
				returnValues[0] = target.getColor().g;
				returnValues[0] = target.getColor().b;
				return 3;
			case Y:
				returnValues[0] = target.getY();
				return 1;
			default:
				throw new RuntimeException(ERROR_MESSAGE_TWEEN_TYPE);
		}
	}

	@Override
	public void setValues(Actor target, int tweenType, float[] newValues) {
		switch (tweenType) {
			case ALPHA:
				target.setColor(target.getColor().r, target.getColor().g,
						target.getColor().b, newValues[0]);
				break;
			case RGB:
				target.setColor(newValues[0], newValues[1], newValues[3], target.getColor().a);
				break;
			case Y:
				target.setY(newValues[0]);
				break;
			default:
				throw new RuntimeException(ERROR_MESSAGE_TWEEN_TYPE);
		}
	}

}
