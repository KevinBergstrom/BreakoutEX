package bounce;

import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import jig.ResourceManager;

/**
 * The ProjectilePowerUp class is a PowerUp that moves downward. When it
 * collides with the paddle, the player will temporarily gain a shield
 * that grants immunity from projectile damage.
 * 
 */
public class ProjectilePowerUp extends PowerUp{
	
	public ProjectilePowerUp(float x, float y) {
		super(x, y, 0, 1f);
		Image newImage = ResourceManager.getImage(BounceGame.PROJ_POWERUPIMG_RSC).getScaledCopy(40, 40);
		newImage.setFilter(Image.FILTER_NEAREST);
		addImageWithBoundingBox(newImage);
	}
	@Override
	public void effect(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		bg.paddle.turnOnProjShield();
	}

}
