package bounce;

import org.newdawn.slick.state.StateBasedGame;

import jig.Entity;
import jig.Vector;

/**
 * The Ball class is an Entity that has a velocity (since it's moving). When
 * the Ball bounces off a surface, it temporarily displays a image with
 * cracks for a nice visual effect.
 * 
 */
 class PowerUp extends Entity {

	protected Vector velocity;
	protected float speed;

	public PowerUp(final float x, final float y, final float vx, final float vy) {
		super(x, y);
		velocity = new Vector(vx, vy);
		speed = 0.2f;
	}
	
	public void setVelocity(final Vector v) {
		velocity = v;
	}
	
	public void setSpeed(float s) {
		speed = s;
	}

	public Vector getVelocity() {
		return velocity;
	}
	
	public boolean inRange(float ScreenWidth, float ScreenHeight) {
		if(this.getCoarseGrainedMaxX()<0) {
			return false;
		}else if(this.getCoarseGrainedMinX()>ScreenWidth) {
			return false;
		}else if(this.getCoarseGrainedMaxY()<0) {
			return false;
		}else if(this.getCoarseGrainedMinY()>ScreenHeight) {
			return false;
		}else {
			return true;
		}
	}
	
	public void effect(StateBasedGame game) {
		
	}

	public void update(final int delta) {
		translate(velocity.scale(delta*speed));
	}
}
