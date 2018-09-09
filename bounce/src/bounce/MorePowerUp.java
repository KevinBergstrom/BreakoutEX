package bounce;

import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import jig.ResourceManager;

/**
 * The MorePowerUp class is a PowerUp that moves downward. When it
 * collides with the paddle, the time between PowerUp spawns will
 * decrease.
 * 
 */
public class MorePowerUp extends PowerUp{

	private float delayAmount;
	private float minDelayAmount;
	
	public MorePowerUp(float x, float y) {
		super(x, y, 0, 1f);
		Image newImage = ResourceManager.getImage(BounceGame.MORE_POWERUPIMG_RSC).getScaledCopy(40, 40);
		newImage.setFilter(Image.FILTER_NEAREST);
		addImageWithBoundingBox(newImage);
		delayAmount = 500f;
		minDelayAmount = 1000f;
	}
	@Override
	public void effect(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		bg.powerUpDelay = bg.powerUpDelay - delayAmount;
		bg.powerUpTimer = bg.powerUpTimer - delayAmount;
		if(bg.powerUpDelay<minDelayAmount) {
			//too little delay
			bg.powerUpDelay = minDelayAmount;
		}
	}

}
