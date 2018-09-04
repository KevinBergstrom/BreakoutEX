package bounce;

import org.newdawn.slick.Color;
import org.newdawn.slick.state.StateBasedGame;

public class ShootBrick extends Brick{

	public ShootBrick(float x, float y, int bx, int by) {
		super(x, y, bx, by, new Color(255,0,0));
		
	}
	
	public void onDeath(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		Projectile proj = new Projectile(this.getX(),this.getY(),0,0);
		proj.setVelocity(proj.aimAt(bg.paddle));
		proj.defaultImage();
		bg.projectiles.add(proj);
	}

}
