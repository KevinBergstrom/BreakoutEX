package bounce;

import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import jig.ResourceManager;

public class AcceleratePowerUp extends PowerUp{

	private float addSpeed;
	
	public AcceleratePowerUp(float x, float y) {
		super(x, y, 0, 1f);
		Image newImage = ResourceManager.getImage(BounceGame.ACCEL_POWERUPIMG_RSC).getScaledCopy(40, 40);
		newImage.setFilter(Image.FILTER_NEAREST);
		addImageWithBoundingBox(newImage);
		addSpeed = 0.05f;
	}
	
	public void effect(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		bg.paddle.addSpeed(addSpeed);
	}

}
