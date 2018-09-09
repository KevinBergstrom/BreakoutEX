package bounce;

import org.newdawn.slick.Image;

import jig.Entity;
import jig.ResourceManager;

/**
 * The Ball Trail class is an entity that simulates the trail
 * of the ball.
 * 
 */
 class BallTrail extends Entity {

	 
	final private float startingHealth = 2000f;
	
	private float health;
	private Image currentImage;

	public BallTrail(final float x, final float y) {
		super(x, y);
		health = startingHealth/3;
		
	}
	
	public void updateImage() {
		this.removeImage(currentImage);
		Image newImage = ResourceManager.getImage(BounceGame.BALL_BALLIMG_RSC).getScaledCopy(50,50);
		newImage.setFilter(Image.FILTER_NEAREST);
		newImage.setAlpha(health/startingHealth);
		addImage(newImage);
		currentImage = newImage;
	}
	
	public boolean isActive() {
		if(health<=0) {
			return false;
		}else {
			return true;
		}
	}
	
	public void update(final int delta) {
		health -= delta;
		updateImage();
		
	}
}
