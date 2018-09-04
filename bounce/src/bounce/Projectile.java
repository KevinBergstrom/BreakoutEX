package bounce;

import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

/**
 * The Ball class is an Entity that has a velocity (since it's moving). When
 * the Ball bounces off a surface, it temporarily displays a image with
 * cracks for a nice visual effect.
 * 
 */
 class Projectile extends Entity {

	private Vector velocity;
	private float speed;
	private int damage;

	public Projectile(final float x, final float y, final float vx, final float vy) {
		super(x, y);
		Image newImage = ResourceManager.getImage(BounceGame.PROJECTILEIMG_RSC).getScaledCopy(40, 40);
		newImage.setFilter(Image.FILTER_NEAREST);
		addImageWithBoundingBox(newImage);
		velocity = new Vector(vx, vy);
		damage = 1;
		speed = 0.2f;
	}

	public void setVelocity(final Vector v) {
		velocity = v;
	}
	
	public void setSpeed(float s) {
		speed = s;
	}
	
	public void setDamage(int d) {
		damage = d;
	}

	public Vector getVelocity() {
		return velocity;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public Vector aimAt(Entity target) {
		float xDist = target.getX()-this.getX();
		float yDist = target.getY()-this.getY();
		float targetDist = (float) Math.sqrt(xDist*xDist + yDist*yDist);
		Vector aimVector = new Vector(xDist/targetDist,yDist/targetDist);
		return aimVector;
	}
	
	public boolean inRange(float ScreenWidth, float ScreenHeight) {
		if(this.getCoarseGrainedMaxX()<0) {
			return false;
		}else if(this.getCoarseGrainedMinX()>ScreenWidth) {
			return false;
		}else if(this.getCoarseGrainedMinY()<0) {
			return false;
		}else if(this.getCoarseGrainedMaxY()>ScreenHeight) {
			return false;
		}else {
			return true;
		}
	}

	public void update(final int delta) {
		translate(velocity.scale(delta*speed));
	}
}
