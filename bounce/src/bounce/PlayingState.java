package bounce;

import java.util.Iterator;
import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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
	
	private float timeTaken;
	private int powerUpsGot;
	private int damageTaken;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		container.setSoundOn(true);
		timeTaken = 0f;
		powerUpsGot = 0;
		damageTaken = 0;
	}
	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException {
		BounceGame bg = (BounceGame)game;
		
		if(bg.background!=null) {
			g.drawImage(bg.background, 0, 0);
		}
		
		bg.ball.render(g);
		bg.paddle.render(g);

		for (Brick br : bg.bricks)
			br.render(g);
		for (Bang b : bg.explosions)
			b.render(g);
		for (Projectile p : bg.projectiles)
			p.render(g);
		for (PowerUp pu : bg.powerups)
			pu.render(g);
		
		if(bg.invincibility) {
			g.drawString("Invinciblility: On", 10, 30);
		}
		
		if(bg.paddle.getProjShield()) {
			bg.paddle.renderProjShield(g);
		}
		
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game,
			int delta) throws SlickException {
		
		Input input = container.getInput();
		BounceGame bg = (BounceGame)game;
		
		bg.paddle.setVelocity(new Vector(0, 0));
		timeTaken += delta;
		
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
		if (input.isKeyDown(Input.KEY_INSERT))
			bg.invincibility = true;
		//cheats
		if (input.isKeyDown(Input.KEY_L)) {
			if(input.isKeyDown(Input.KEY_1)) {
				bg.currentLevel = 0;
				bg.enterState(BounceGame.STARTUPSTATE);	
			}else if(input.isKeyDown(Input.KEY_2)) {
				bg.currentLevel = 1;
				bg.enterState(BounceGame.STARTUPSTATE);	
			}else if(input.isKeyDown(Input.KEY_3)) {
				bg.currentLevel = 2;
				bg.enterState(BounceGame.STARTUPSTATE);	
			}else if(input.isKeyDown(Input.KEY_4)) {
				bg.currentLevel = 3;
				bg.enterState(BounceGame.STARTUPSTATE);	
			}else if(input.isKeyDown(Input.KEY_5)) {
				bg.currentLevel = 4;
				bg.enterState(BounceGame.STARTUPSTATE);	
			}else if(input.isKeyDown(Input.KEY_6)) {
				bg.currentLevel = 5;
				bg.enterState(BounceGame.STARTUPSTATE);	
			}else if(input.isKeyDown(Input.KEY_7)) {
				bg.currentLevel = 6;
				bg.enterState(BounceGame.STARTUPSTATE);	
			}
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
					nextBrick.onHit(bg);
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
		//TODO have one bounce for every frame?
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
			bg.health = bg.health -1;
			damageTaken += 1;
			if(bg.health<0) {
				bg.health = 0;
			}
			bg.paddle.setHealth(bg.health);
			
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
				
				if(!bg.paddle.getProjShield()) {
					bg.health -= nextProj.getDamage();
					damageTaken += nextProj.getDamage();
					if(bg.health<0) {
						bg.health = 0;
					}
					bg.paddle.setHealth(bg.health);
				}
				i.remove();
			}
		}
		
		//power up spawning
		 bg.powerUpTimer =  bg.powerUpTimer - delta;
		 if(bg.powerUpTimer<0) {
			 bg.powerUpTimer = bg.powerUpDelay;
			 PowerUp newPU = PowerUp.spawnRandomPowerUp(bg.ScreenWidth-20);
			 if(newPU!=null) {
				 bg.powerups.add(newPU);
			 }
		 }
		
		//update the powerups
		for (Iterator<PowerUp> i = bg.powerups.iterator(); i.hasNext();) {
			PowerUp nextPU = i.next();
			nextPU.update(delta);
			if (!nextPU.inRange(bg.ScreenWidth, bg.ScreenHeight)) {
				i.remove();
			}else if(bg.paddle.collides(nextPU) != null) {
				powerUpsGot++;
				nextPU.effect(game);
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

		//check if the player has lost
		if (bg.health<=0 && !bg.invincibility) {
			game.enterState(BounceGame.GAMEOVERSTATE);
		}else if(bg.bricks.size()==0) {
			((ResultsScreenState)game.getState(BounceGame.RESULTSSCREENSTATE)).setUserScore(timeTaken,powerUpsGot,damageTaken);
			game.enterState(BounceGame.RESULTSSCREENSTATE);
		}
	}

	@Override
	public int getID() {
		return BounceGame.PLAYINGSTATE;
	}
	
}