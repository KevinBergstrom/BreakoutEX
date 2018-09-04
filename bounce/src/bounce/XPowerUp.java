package bounce;

import java.util.Iterator;

import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import jig.ResourceManager;

public class XPowerUp extends PowerUp{

	private int damage;
	
	public XPowerUp(float x, float y) {
		super(x, y, 0, 1f);
		Image newImage = ResourceManager.getImage(BounceGame.X_POWERUPIMG_RSC).getScaledCopy(40, 40);
		newImage.setFilter(Image.FILTER_NEAREST);
		addImageWithBoundingBox(newImage);
		damage = 1;
	}
	
	public void effect(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		bg.ball.setDamage(bg.ball.getDamage()+damage);
	}

}
