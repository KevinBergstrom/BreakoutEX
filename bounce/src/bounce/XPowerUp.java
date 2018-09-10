package bounce;

import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;
import jig.ResourceManager;

/**
 * The XPowerUp class is a PowerUp that moves downward. When it
 * collides with the paddle, the ball will gain damage.
 * 
 */
public class XPowerUp extends PowerUp{

	final private int damage = 1;
	
	public XPowerUp(float x, float y) {
		super(x, y, 0, 1f);
		Image newImage = ResourceManager.getImage(BounceGame.X_POWERUPIMG_RSC).getScaledCopy(40, 40);
		newImage.setFilter(Image.FILTER_NEAREST);
		addImageWithBoundingBox(newImage);
	}
	
	@Override
	public void effect(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		bg.ball.setDamage(bg.ball.getDamage()+damage);
	}

}
