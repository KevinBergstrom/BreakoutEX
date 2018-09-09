package bounce;

import org.newdawn.slick.Image;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

/**
 * The Ball class is an Entity that has a velocity (since it's moving). When it
 * collides with something is bounces off at an opposite angle. 
 * 
 */
 class Ball extends Entity {

	 
	final private float maxSpeed = 1.8f;
	final private float minSpeed = 0.4f;
	final private int maxDamage = 4;
	 
	private Vector velocity;
	private float speed;
	private int damage;
	private float defaultX;
	private float defaultY;
	private Vector defaultV;

	public Ball(final float x, final float y, final float vx, final float vy) {
		super(x, y);
		Image newImage = ResourceManager.getImage(BounceGame.BALL_BALLIMG_RSC).getScaledCopy(50, 50);
		newImage.setFilter(Image.FILTER_NEAREST);
		addImageWithBoundingBox(newImage);
		
		velocity = new Vector(vx, vy);
		defaultV = new Vector(vx, vy);
		defaultX = x;
		defaultY = y;
		damage = 1;
		speed = 1.1f;
	}

	public void setVelocity(final Vector v) {
		velocity = v;
	}
	
	public void setSpeed(float s) {
		speed = s;
		if(speed>maxSpeed) {
			//too much speed
			speed = maxSpeed;
		}else if(speed<minSpeed) {
			//too little speed
			speed = minSpeed;
		}
	}
	
	public void addSpeed(float s) {
		speed = speed += s;
		if(speed>maxSpeed) {
			//too much speed
			speed = maxSpeed;
		}else if(speed<minSpeed) {
			//too little speed
			speed = minSpeed;
		}
	}
	
	public void setDamage(int d) {
		damage = d;
		if(damage>maxDamage) {
			//too much damage
			damage = maxDamage;
		}
	}

	public Vector getVelocity() {
		return velocity;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void reset() {
		//return the ball to its default position and speed
		this.setPosition(defaultX, defaultY);
		this.setVelocity(defaultV);
	}
	
	/**
	 *Check which side the ball has collided with a rectangle on:
	 * 0 = top, 1 = bottom, 2 = left, 3 = right
	 * @param rect
	 */
	public int sideOfCollision(Entity rect) {
		int sideLeeway = 3;// so the ball wont always collide with top or bottom of rect
		
		if(this.getCoarseGrainedMinX()<rect.getCoarseGrainedMaxX()-sideLeeway &&
				this.getCoarseGrainedMaxX()>rect.getCoarseGrainedMinX()+sideLeeway) {
				if(this.getY()<=rect.getY()) {
					//hit top of rect
					return 0;
				}else {
					//hit bottom of rect
					return 1;
				}
			}else {
				if(this.getX()<=rect.getX()) {
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
	}
}
