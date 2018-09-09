package bounce;

import org.newdawn.slick.Color;
import org.newdawn.slick.state.StateBasedGame;

/**
 * The ShootBrick class is a Brick that shoots
 * a Projectile at the paddle when hit by the ball.
 * 
 */
public class ShootBrick extends Brick{

	public ShootBrick(float x, float y, int bx, int by) {
		super(x, y, bx, by, new Color(200,0,0));
		
	}
	@Override
	public void onHit(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		Projectile proj = new Projectile(this.getX(),this.getY(),0,0);
		proj.setVelocity(proj.aimAt(bg.paddle));
		proj.defaultImage();
		bg.projectiles.add(proj);
	}

}
