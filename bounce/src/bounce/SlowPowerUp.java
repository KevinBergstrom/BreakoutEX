package bounce;

import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import jig.ResourceManager;

public class SlowPowerUp extends PowerUp{

	private float loseSpeed;
	
	public SlowPowerUp(float x, float y) {
		super(x, y, 0, 1f);
		Image newImage = ResourceManager.getImage(BounceGame.SLOW_POWERUPIMG_RSC).getScaledCopy(40, 40);
		newImage.setFilter(Image.FILTER_NEAREST);
		addImageWithBoundingBox(newImage);
		loseSpeed = -0.1f;
	}
	@Override
	public void effect(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		bg.ball.addSpeed(loseSpeed);
	}

}
