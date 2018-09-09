package bounce;

import org.newdawn.slick.Color;
import org.newdawn.slick.state.StateBasedGame;

public class PowerUpBrick extends Brick{

	public PowerUpBrick(float x, float y, int bx, int by) {
		super(x, y, bx, by, new Color(200,50,180));
		
	}
	@Override
	public void onHit(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		//generate a random powerup
		PowerUp newPU = PowerUp.spawnRandomPowerUp(bg.ScreenWidth);
		
		 if(newPU!=null) {
			 //spawn in the powerup at a random position
			 newPU.setPosition(this.getX(),this.getY());
			 bg.powerups.add(newPU);
		 }
	}

}
