package bounce;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;


 class Paddle extends Entity {

	private Vector velocity;
	private int countdown;
	private float speed;
	private float minSpeed;
	private float maxSpeed;
	private int minBoundX;
	private int maxBoundX;
	private int minBoundY;
	private int maxBoundY;
	private float defaultX;
	private float defaultY;
	private boolean projShield;
	private float projShieldDelay;
	private float projShieldTimer;
	

	public Paddle(final float x, final float y, final float vx, final float vy) {
		super(x, y);
		Image newImage = ResourceManager.getImage(BounceGame.FULL_PADDLEIMG_RSC).getScaledCopy(160, 40);
		newImage.setFilter(Image.FILTER_NEAREST);
		addImageWithBoundingBox(newImage);
		
		velocity = new Vector(vx, vy);
		speed = 0.2f;
		minSpeed = 0.1f;
		maxSpeed = 0.5f;
		countdown = 0;
		minBoundX = (int) x;
		maxBoundX = (int) x;
		minBoundY = (int) y;
		maxBoundY = (int) y;
		defaultX = x;
		defaultY = y;
		projShield = false;
		projShieldDelay = 6000f;
		projShieldTimer = 6000f;
	}

	public void setVelocity(final Vector v) {
		velocity = v;
	}
	
	public void setMoveBounds(int minX, int maxX, int minY, int maxY) {
		minBoundX = minX;
		maxBoundX = maxX;
		minBoundY = minY;
		maxBoundY = maxY;
		
	}
	
	public void reverseControls() {
		speed = speed * -1;
	}
	
	public void reset() {
		this.setPosition(defaultX, defaultY);
		speed = Math.abs(speed);
		projShield = false;
		projShieldTimer = projShieldDelay;
	}
	
	public void setHealth(int health) {
		//TODO remove image?
		if(health <= 0) {
			Image image = ResourceManager.getImage(BounceGame.DEAD_PADDLEIMG_RSC);
			Image newImage = image.getScaledCopy(160, 40);
			newImage.setFilter(Image.FILTER_NEAREST);
			addImageWithBoundingBox(newImage);
		}else if(health == 1) {
			Image image = ResourceManager.getImage(BounceGame.LOW_PADDLEIMG_RSC);
			Image newImage = image.getScaledCopy(160, 40);
			newImage.setFilter(Image.FILTER_NEAREST);
			addImageWithBoundingBox(newImage);
		}else if(health == 2) {
			Image image = ResourceManager.getImage(BounceGame.HALF_PADDLEIMG_RSC);
			Image newImage = image.getScaledCopy(160, 40);
			newImage.setFilter(Image.FILTER_NEAREST);
			addImageWithBoundingBox(newImage);
		}else{
			Image image = ResourceManager.getImage(BounceGame.FULL_PADDLEIMG_RSC);
			Image newImage = image.getScaledCopy(160, 40);
			newImage.setFilter(Image.FILTER_NEAREST);
			addImageWithBoundingBox(newImage);
		}
	}

	public Vector getVelocity() {
		return velocity;
	}
	
	public void addSpeed(float s) {
		if(speed<0) {
			reverseControls();
			speed = speed + s;
			if(speed>maxSpeed) {
				speed = maxSpeed;
			}else if(speed<minSpeed) {
				speed = minSpeed;
			}
			reverseControls();
		}else {
			speed = speed + s;
			if(speed>maxSpeed) {
				speed = maxSpeed;
			}else if(speed<minSpeed) {
				speed = minSpeed;
			}
		}
	}
	
	public void setSpeed(float s) {
		speed = s;
		if(speed>maxSpeed) {
			speed = maxSpeed;
		}else if(speed<minSpeed) {
			speed = minSpeed;
		}
	}
	
	public boolean getProjShield() {
		return projShield;
	}
	
	public void turnOnProjShield() {
		projShield = true;
		projShieldTimer = projShieldDelay;
	}
	
	public void renderProjShield(Graphics g) {
		Image ShieldImage = ResourceManager.getImage(BounceGame.PROJ_SHIELDIMG_RSC);
		ShieldImage.setFilter(Image.FILTER_NEAREST);
		g.drawImage(ShieldImage,
				this.getCoarseGrainedMinX()-8*5,this.getCoarseGrainedMinY()-4*5, 
				this.getCoarseGrainedMaxX()+8*5, this.getCoarseGrainedMaxY()+4*5,0, 0,40,16 );
	}

	public void update(final int delta) {
		translate(velocity.scale(delta*speed));
		//keep paddle within bounds
		if(this.getX()>maxBoundX) {
			this.setX(maxBoundX);
		}else if(this.getX()<minBoundX) {
			this.setX(minBoundX);
		}
		if(this.getY()>maxBoundY) {
			this.setY(maxBoundY);
		}else if(this.getY()<minBoundY) {
			this.setY(minBoundY);
		}

		if(projShield) {
			projShieldTimer -= delta;
			if(projShieldTimer<0) {
				projShieldTimer = projShieldDelay;
				projShield = false;
			}
		}
	}
}
