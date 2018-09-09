package bounce;

import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import jig.ResourceManager;

/**
 * The HealPowerUp class is a PowerUp that moves downward. When it
 * collides with the paddle, the player will gain health.
 * 
 */
public class HealPowerUp extends PowerUp{

	private int healAmount;
	
	public HealPowerUp(float x, float y) {
		super(x, y, 0, 1f);
		Image newImage = ResourceManager.getImage(BounceGame.HEAL_POWERUPIMG_RSC).getScaledCopy(40, 40);
		newImage.setFilter(Image.FILTER_NEAREST);
		addImageWithBoundingBox(newImage);
		healAmount = 1;
	}
	@Override
	public void effect(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		bg.health = bg.health + healAmount;
		if(bg.health>bg.maxHealth) {
			//too much health
			bg.health = bg.maxHealth;
		}
		bg.paddle.setHealth(bg.health);
	}

}
