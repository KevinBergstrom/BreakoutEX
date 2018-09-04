package bounce;

import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import jig.ResourceManager;

public class ProjectilePowerUp extends PowerUp{
	
	public ProjectilePowerUp(float x, float y) {
		super(x, y, 0, 1f);
		Image newImage = ResourceManager.getImage(BounceGame.PROJ_POWERUPIMG_RSC).getScaledCopy(40, 40);
		newImage.setFilter(Image.FILTER_NEAREST);
		addImageWithBoundingBox(newImage);
	}
	
	public void effect(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		bg.projectiles.clear();
	}

}
