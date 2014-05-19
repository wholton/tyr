package com.tyr.game.accessor;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Represents an accessor for the Actor class, which is used by the tween manager to 
 * access and manipulate its variables. 
 * 
 * @author Bebop
 * @version 0.0.3.0
 */
public class ActorAccessor extends AbstractAccessor implements
		TweenAccessor<Actor> {

	/**
	 * Gets the values of the target corresponding to the given tween type and places them
	 * within the return values array then returns the number of values retrieved.
	 * 
	 * @param target	the target of the tween which will supply the return values
	 * @param tweenType	the type of tween to be performed which will decide which values are retrieved
	 * @param returnValues	the set of values retrieved from the target decided by the tween type
	 * 
	 * @return	the number of values placed into the return value array
	 */
	@Override
	public int getValues(final Actor target, final int tweenType, final float[] returnValues) {
		switch (tweenType) {
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

	/**
	 * Sets the values of the target corresponding to the given tween type to the values within the
	 * new values array.
	 * 
	 * @param target	the target of the tween which will have its variables updated
	 * @param tweenType	the type of tween to be performed which will decide which values are updated
	 * @param returnValues	the set of values to be updated decided by the tween type
	 */
	@Override
	public void setValues(final Actor target, final int tweenType, final float[] newValues) {
		switch (tweenType) {
		case ALPHA:
			target.setColor(target.getColor().r, target.getColor().g,
					target.getColor().b, newValues[0]);
			break;
		case RGB:
			target.setColor(newValues[0], newValues[1], newValues[3],
					target.getColor().a);
			break;
		case Y:
			target.setY(newValues[0]);
			break;
		default:
			throw new RuntimeException(ERROR_MESSAGE_TWEEN_TYPE);
		}
	}

}
