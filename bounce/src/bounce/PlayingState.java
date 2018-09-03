package bounce;

import java.util.Iterator;
import jig.Entity;
import jig.Vector;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


/**
 * This state is active when the Game is being played. In this state, sound is
 * turned on, the bounce counter begins at 0 and increases until 10 at which
 * point a transition to the Game Over state is initiated. The user can also
 * control the ball using the WAS & D keys.
 * 
 * Transitions From StartUpState
 * 
 * Transitions To GameOverState
 */
class PlayingState extends BasicGameState {
	int bounces;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		bounces = 0;
		container.setSoundOn(true);
	}
	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException {
		BounceGame bg = (BounceGame)game;
		
		bg.ball.render(g);
		bg.paddle.render(g);
		g.drawString("Bounces: " + bounces, 10, 30);
		for (Bang b : bg.explosions)
			b.render(g);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game,
			int delta) throws SlickException {

		Input input = container.getInput();
		BounceGame bg = (BounceGame)game;
		bg.paddle.setVelocity(new Vector(0, 0));
		if (input.isKeyDown(Input.KEY_W)) {
			bg.paddle.setVelocity(bg.paddle.getVelocity().add(new Vector(0f, -1.0f)));
		}
		if (input.isKeyDown(Input.KEY_S)) {
			bg.paddle.setVelocity(bg.paddle.getVelocity().add(new Vector(0f, +1.0f)));
		}
		if (input.isKeyDown(Input.KEY_A)) {
			bg.paddle.setVelocity(bg.paddle.getVelocity().add(new Vector(-1.0f, 0)));
		}
		if (input.isKeyDown(Input.KEY_D)) {
			bg.paddle.setVelocity(bg.paddle.getVelocity().add(new Vector(+1.0f, 0f)));
		}
		
		//ball and paddle collision
		if(bg.ball.collides(bg.paddle) != null) {
			
			if(bg.ball.getCoarseGrainedMinX()<bg.paddle.getCoarseGrainedMaxX()-delta &&
				bg.ball.getCoarseGrainedMaxX()>bg.paddle.getCoarseGrainedMinX()+delta	) {
				if(bg.ball.getY()<=bg.paddle.getY()) {
					//hit top of paddle
					bg.ball.setPosition(new Vector(bg.ball.getX(),bg.paddle.getCoarseGrainedMinY()-
							bg.ball.getCoarseGrainedHeight()/2));
					if(bg.ball.getVelocity().getY()>0) {
						bg.ball.bounce(0);
					}
				}else {
					//hit bottom of paddle
					bg.ball.setPosition(new Vector(bg.ball.getX(),bg.paddle.getCoarseGrainedMaxY()+
							bg.ball.getCoarseGrainedHeight()/2));
					if(bg.ball.getVelocity().getY()<0) {
						bg.ball.bounce(0);
					}
				}
				
			}else {
				if(bg.ball.getX()<=bg.paddle.getX()) {
					//left side of paddle
					bg.ball.setPosition(new Vector(bg.paddle.getCoarseGrainedMinX()-
							bg.ball.getCoarseGrainedWidth()/2,bg.ball.getY()));
					if(bg.ball.getVelocity().getX()>0) {
						bg.ball.bounce(90);
					}
					
				}else{
					//left side of paddle
					bg.ball.setPosition(new Vector(bg.paddle.getCoarseGrainedMaxX()+
							bg.ball.getCoarseGrainedWidth()/2,bg.ball.getY()));
					if(bg.ball.getVelocity().getY()>0) {
						bg.ball.bounce(90);
					}
				}
			}

			
		}
		
		// bounce the ball...
		boolean bounced = false;
		if ((bg.ball.getVelocity().getX()>0 && bg.ball.getCoarseGrainedMaxX() > bg.ScreenWidth)
				|| (bg.ball.getVelocity().getX()<0 && bg.ball.getCoarseGrainedMinX() < 0)) {
			bg.ball.bounce(90);
			bounced = true;
		} else if ((bg.ball.getVelocity().getY()>0 && bg.ball.getCoarseGrainedMaxY() > bg.ScreenHeight)
				|| (bg.ball.getVelocity().getY()<0 && bg.ball.getCoarseGrainedMinY() < 0)) {
			bg.ball.bounce(0);
			bounced = true;
		}
		if (bounced) {
			bg.explosions.add(new Bang(bg.ball.getX(), bg.ball.getY()));
			bounces++;
		}
		bg.ball.update(delta);
		bg.paddle.update(delta);
		
		// check if there are any finished explosions, if so remove them
		for (Iterator<Bang> i = bg.explosions.iterator(); i.hasNext();) {
			if (!i.next().isActive()) {
				i.remove();
			}
		}

		//TODO change later
		if (bounces >= 10) {
			((GameOverState)game.getState(BounceGame.GAMEOVERSTATE)).setUserScore(bounces);
			game.enterState(BounceGame.GAMEOVERSTATE);
		}
	}

	@Override
	public int getID() {
		return BounceGame.PLAYINGSTATE;
	}
	
}