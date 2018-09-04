package bounce;

import java.util.Random;

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
	
	public static PowerUp spawnRandomPowerUp(float ScreenWidth) {
		Random rand = new Random();
		int PUNum = rand.nextInt(5);
		float PUPosX = (float)rand.nextInt((int)ScreenWidth);
		float PUPosY = 20;
		
		if(PUNum==0) {
			return new AcceleratePowerUp(PUPosX,PUPosY);
		}else if(PUNum==1) {
			return new HealPowerUp(PUPosX,PUPosY);
		}else if(PUNum==2) {
			return new MorePowerUp(PUPosX,PUPosY);
		}else if(PUNum==3) {
			return new ProjectilePowerUp(PUPosX,PUPosY);
		}else if(PUNum==4) {
			return new SlowPowerUp(PUPosX,PUPosY);
		}else {
			
		}
		return null;
	}
}
