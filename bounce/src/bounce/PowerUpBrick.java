package bounce;

import org.newdawn.slick.Color;
import org.newdawn.slick.state.StateBasedGame;

public class PowerUpBrick extends Brick{

	public PowerUpBrick(float x, float y, int bx, int by) {
		super(x, y, bx, by, new Color(200,50,180));
		
	}
	
	public void onHit(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		
		PowerUp newPU = PowerUp.spawnRandomPowerUp(bg.ScreenWidth);
		 if(newPU!=null) {
			 newPU.setPosition(this.getX(),this.getY());
			 bg.powerups.add(newPU);
		 }
	}

}
