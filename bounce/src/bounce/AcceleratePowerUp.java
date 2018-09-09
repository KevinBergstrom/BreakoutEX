package bounce;

import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import jig.ResourceManager;

/**
 * The AcceleratePowerUp class is an Entity that moves downward. When it
 * collides with the paddle, the paddle will gain speed.
 * 
 */
public class AcceleratePowerUp extends PowerUp{

	final private float addSpeed = 0.05f;
	
	public AcceleratePowerUp(float x, float y) {
		super(x, y, 0, 1f);
		Image newImage = ResourceManager.getImage(BounceGame.ACCEL_POWERUPIMG_RSC).getScaledCopy(40, 40);
		newImage.setFilter(Image.FILTER_NEAREST);
		addImageWithBoundingBox(newImage);
	}
	@Override
	public void effect(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		bg.paddle.addSpeed(addSpeed);
	}

}
