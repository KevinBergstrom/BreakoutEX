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
	int health;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		health = 3;
		container.setSoundOn(true);
	}
	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException {
		BounceGame bg = (BounceGame)game;
		
		bg.ball.render(g);
		bg.paddle.render(g);
		//g.drawString("Bounces: " + bounces, 10, 30);
		for (Brick br : bg.bricks)
			br.render(g);
		for (Bang b : bg.explosions)
			b.render(g);
		for (Projectile p : bg.projectiles)
			p.render(g);
		
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
		
		boolean bounced = false;
		
		//ball and paddle collision
		if(bg.ball.collides(bg.paddle) != null) {
			int sideOfCol = bg.ball.sideOfCollision(bg.paddle);
			if(sideOfCol == 0) {
				bg.ball.setPosition(new Vector(bg.ball.getX(),bg.paddle.getCoarseGrainedMinY()-
						bg.ball.getCoarseGrainedHeight()/2));
				if(bg.ball.getVelocity().getY()>0) {
					bg.ball.bounce(0);
					bounced = true;
				}
			}else if(sideOfCol == 1) {
				bg.ball.setPosition(new Vector(bg.ball.getX(),bg.paddle.getCoarseGrainedMaxY()+
						bg.ball.getCoarseGrainedHeight()/2));
				if(bg.ball.getVelocity().getY()<0) {
					bg.ball.bounce(0);
					bounced = true;
				}
			}else if(sideOfCol == 2) {
				bg.ball.setPosition(new Vector(bg.paddle.getCoarseGrainedMinX()-
						bg.ball.getCoarseGrainedWidth()/2,bg.ball.getY()));
				if(bg.ball.getVelocity().getX()>0) {
					bg.ball.bounce(90);
					bounced = true;
				}
			}else if(sideOfCol == 3) {
				bg.ball.setPosition(new Vector(bg.paddle.getCoarseGrainedMaxX()+
						bg.ball.getCoarseGrainedWidth()/2,bg.ball.getY()));
				if(bg.ball.getVelocity().getX()<0) {
					bg.ball.bounce(90);
					bounced = true;
				}
			}
		}
		
		//ball and bricks collision
		
		// check bricks for collision and remove dead ones
		float nextBounce = -1;
		for (Iterator<Brick> i = bg.bricks.iterator(); i.hasNext();) {
			Brick nextBrick = i.next();
			if(nextBrick.isActive()) {
				if(bg.ball.collides(nextBrick) != null) {
					int sideOfCol = bg.ball.sideOfCollision(nextBrick);
					
					if(sideOfCol == 0) {
						if(bg.ball.getVelocity().getY()>0) {
							nextBrick.damageBrick(bg.ball.getDamage(), game);
							nextBounce = 0;
						}
					}else if(sideOfCol == 1) {
						if(bg.ball.getVelocity().getY()<0) {
							nextBrick.damageBrick(bg.ball.getDamage(), game);
							nextBounce = 0;
						}
					}else if(sideOfCol == 2) {
						if(bg.ball.getVelocity().getX()>0) {
							nextBrick.damageBrick(bg.ball.getDamage(), game);
							nextBounce = 90;
						}
					}else if(sideOfCol == 3) {
						if(bg.ball.getVelocity().getX()<0) {
							nextBrick.damageBrick(bg.ball.getDamage(), game);
							nextBounce = 90;
						}
					}
				}
			}
			if (!nextBrick.isActive()) {
				nextBrick.onDeath(bg);
				i.remove();
			}
		}
		if(nextBounce>-1) {
			bg.ball.bounce(nextBounce);
			bounced = true;
		}
		
		// bounce the ball...
		if ((bg.ball.getVelocity().getX()>0 && bg.ball.getCoarseGrainedMaxX() > bg.ScreenWidth)
				|| (bg.ball.getVelocity().getX()<0 && bg.ball.getCoarseGrainedMinX() < 0)) {
			bg.ball.bounce(90);
			bounced = true;
		} else if (bg.ball.getVelocity().getY()<0 && bg.ball.getCoarseGrainedMinY() < 0) {
			bg.ball.bounce(0);
			bounced = true;
		}else if(bg.ball.getCoarseGrainedMaxY() > bg.ScreenHeight) {
			bg.ball.reset();
			health--;
			bg.paddle.setHealth(health);
			
		}
		if (bounced) {
			bg.explosions.add(new Bang(bg.ball.getX(), bg.ball.getY()));
		}
		
		//update the projectiles
		for (Iterator<Projectile> i = bg.projectiles.iterator(); i.hasNext();) {
			Projectile nextProj = i.next();
			nextProj.update(delta);
			if (!nextProj.inRange(bg.ScreenWidth, bg.ScreenHeight)) {
				i.remove();
			}else if(bg.paddle.collides(nextProj) != null) {
				health = health - nextProj.getDamage();
				bg.paddle.setHealth(health);
				i.remove();
			}
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
		if (health<=0) {
			((GameOverState)game.getState(BounceGame.GAMEOVERSTATE)).setUserScore(0);
			game.enterState(BounceGame.GAMEOVERSTATE);
		}
	}

	@Override
	public int getID() {
		return BounceGame.PLAYINGSTATE;
	}
	
}