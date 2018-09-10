package bounce;

import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import jig.ResourceManager;

/**
 * The SlowPowerUp class is a PowerUp that moves downward. When it
 * collides with the paddle, the ball will lose some speed.
 * 
 */
public class SlowPowerUp extends PowerUp{

	final private float loseSpeed = -0.1f;
	
	public SlowPowerUp(float x, float y) {
		super(x, y, 0, 1f);
		Image newImage = ResourceManager.getImage(BounceGame.SLOW_POWERUPIMG_RSC).getScaledCopy(40, 40);
		newImage.setFilter(Image.FILTER_NEAREST);
		addImageWithBoundingBox(newImage);
	}
	@Override
	public void effect(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		bg.ball.addSpeed(loseSpeed);
	}

}
