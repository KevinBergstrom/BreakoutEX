package bounce;

import org.newdawn.slick.Color;
import org.newdawn.slick.state.StateBasedGame;

public class RadioactiveBrick extends Brick{

	public RadioactiveBrick(float x, float y, int bx, int by) {
		super(x, y, bx, by, new Color(0,225,0));
		
	}
	
	public void onHit(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		Projectile proj = new GravityProjectile(this.getX(),this.getY());
		bg.projectiles.add(proj);
	}

}
