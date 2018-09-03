package bounce;

import org.newdawn.slick.Image;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;


 class Paddle extends Entity {

	private Vector velocity;
	private int countdown;
	private int reverseControls;

	public Paddle(final float x, final float y, final float vx, final float vy) {
		super(x, y);
		Image image = ResourceManager.getImage(BounceGame.FULL_PADDLEIMG_RSC);
		Image newImage = image.getScaledCopy(160, 40);
		newImage.setFilter(Image.FILTER_NEAREST);
		
		addImageWithBoundingBox(newImage);
		velocity = new Vector(vx, vy);
		reverseControls = 1;
		countdown = 0;
	}

	public void setVelocity(final Vector v) {
		velocity = v;
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

	public void update(final int delta) {
		translate(velocity.scale(delta*reverseControls));
				//addImageWithBoundingBox(ResourceManager
				//		.getImage(BounceGame.BALL_BALLIMG_RSC));
				//removeImage(ResourceManager
				//		.getImage(BounceGame.BALL_BROKENIMG_RSC));
	}
}
