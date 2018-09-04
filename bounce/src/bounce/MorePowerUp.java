package bounce;

import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import jig.ResourceManager;

public class MorePowerUp extends PowerUp{

	private float delayAmount;
	private float minDelayAmount;
	
	public MorePowerUp(float x, float y) {
		super(x, y, 0, 1f);
		Image newImage = ResourceManager.getImage(BounceGame.MORE_POWERUPIMG_RSC).getScaledCopy(40, 40);
		newImage.setFilter(Image.FILTER_NEAREST);
		addImageWithBoundingBox(newImage);
		delayAmount = 1000f;
		minDelayAmount = 3000f;
	}
	
	public void effect(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		bg.powerUpDelay = bg.powerUpDelay - delayAmount;
		bg.powerUpTimer = bg.powerUpTimer - delayAmount;
		if(bg.powerUpDelay<minDelayAmount) {
			bg.powerUpDelay = minDelayAmount;
		}
	}

}
