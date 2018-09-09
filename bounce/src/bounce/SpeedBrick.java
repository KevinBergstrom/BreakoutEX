package bounce;

import org.newdawn.slick.Color;
import org.newdawn.slick.state.StateBasedGame;

public class SpeedBrick extends Brick{

	private float addSpeed;
	
	public SpeedBrick(float x, float y, int bx, int by) {
		super(x, y, bx, by, new Color(225,120,0));
		addSpeed = 0.08f;
	}
	
	public void onHit(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		bg.ball.setSpeed(bg.ball.getSpeed()+addSpeed);
	}

}
