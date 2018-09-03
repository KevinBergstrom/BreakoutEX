package bounce;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

/**
 * The Ball class is an Entity that has a velocity (since it's moving). When
 * the Ball bounces off a surface, it temporarily displays a image with
 * cracks for a nice visual effect.
 * 
 */
 class Ball extends Entity {

	private Vector velocity;
	private float speed;
	private int countdown;
	private int damage;

	public Ball(final float x, final float y, final float vx, final float vy) {
		super(x, y);
		addImageWithBoundingBox(ResourceManager.getImage(BounceGame.BALL_BALLIMG_RSC));
		velocity = new Vector(vx, vy);
		countdown = 0;
		damage = 1;
		speed = 1f;
	}

	public void setVelocity(final Vector v) {
		velocity = v;
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
	
	public int sideOfCollision(Entity other) {
		//check which side the ball has collided with a rectangle on
		//	_0_
		//2[___]3
		//	 1
		
		int sideLeeway = 10;// so the ball wont always collide with top or bottom of rect
		
		if(this.getCoarseGrainedMinX()<other.getCoarseGrainedMaxX()-sideLeeway &&
				this.getCoarseGrainedMaxX()>other.getCoarseGrainedMinX()+sideLeeway) {
				if(this.getY()<=other.getY()) {
					//hit top of rect
					return 0;
				}else {
					//hit bottom of rect
					return 1;
				}
			}else {
				if(this.getX()<=other.getX()) {
					//left side of rect
					return 2;
					
				}else{
					//left side of rect
					return 3;
				}
			}
	}

	/**
	 * Bounce the ball off a surface. This simple implementation, combined
	 * with the test used when calling this method can cause "issues" in
	 * some situations. Can you see where/when? If so, it should be easy to
	 * fix!
	 * 
	 * @param surfaceTangent
	 */
	public void bounce(float surfaceTangent) {
		removeImage(ResourceManager.getImage(BounceGame.BALL_BALLIMG_RSC));
		addImageWithBoundingBox(ResourceManager
				.getImage(BounceGame.BALL_BROKENIMG_RSC));
		countdown = 500;
		velocity = velocity.bounce(surfaceTangent);
	}

	/**
	 * Update the Ball based on how much time has passed...
	 * 
	 * @param delta
	 *            the number of milliseconds since the last update
	 */
	public void update(final int delta) {
		translate(velocity.scale(delta*speed));
		if (countdown > 0) {
			countdown -= delta;
			if (countdown <= 0) {
				addImageWithBoundingBox(ResourceManager
						.getImage(BounceGame.BALL_BALLIMG_RSC));
				removeImage(ResourceManager
						.getImage(BounceGame.BALL_BROKENIMG_RSC));
			}
		}
	}
}
