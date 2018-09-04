package bounce;

import java.util.Iterator;

import jig.ResourceManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This state is active prior to the Game starting. In this state, sound is
 * turned off, and the bounce counter shows '?'. The user can only interact with
 * the game by pressing the SPACE key which transitions to the Playing State.
 * Otherwise, all game objects are rendered and updated normally.
 * 
 * Transitions From (Initialization), GameOverState
 * 
 * Transitions To PlayingState
 */
class StartUpState extends BasicGameState {

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
	}
	
	//TODO temporary location
	public void clearLevel(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		bg.bricks.clear();
		bg.explosions.clear();
		bg.projectiles.clear();
		bg.powerups.clear();
		bg.ball.reset();
		bg.ball.setSpeed(1f);
		bg.paddle.reset();
		bg.paddle.setSpeed(0.2f);
		bg.paddle.setHealth(3);
	}
	
	public void loadLevel(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		clearLevel(game);
		/*for(int x = 0;x<14;x++) {
			for(int y = 0;y<6;y++) {
				bg.bricks.add(new RadioactiveBrick(120+(x*40), 40+(y*40), 1, 1));
			}
		}*/
		for(int x = 0;x<7;x++) {
			for(int y = 0;y<3;y++) {
				bg.bricks.add(new Brick(120+40+(x*2*40), 40+40+(y*2*40), 2, 2,new Color(255,255,255)));
			}
		}
		
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		container.setSoundOn(false);
		//loading level
		loadLevel(game);
		
	}


	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException {
		BounceGame bg = (BounceGame)game;
		
		bg.ball.render(g);
		bg.paddle.render(g);
		for (Brick br : bg.bricks)
			br.render(g);
		for (Bang b : bg.explosions)
			b.render(g);
		g.drawImage(ResourceManager.getImage(BounceGame.STARTUP_BANNER_RSC),
				225, 270);		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game,
			int delta) throws SlickException {

		Input input = container.getInput();
		BounceGame bg = (BounceGame)game;

		if (input.isKeyDown(Input.KEY_SPACE))
			bg.enterState(BounceGame.PLAYINGSTATE);	
		
		/*// bounce the ball...
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
		}
		bg.ball.update(delta);
		// check if there are any finished explosions, if so remove them
		for (Iterator<Bang> i = bg.explosions.iterator(); i.hasNext();) {
			if (!i.next().isActive()) {
				i.remove();
			}
		}*/

	}

	@Override
	public int getID() {
		return BounceGame.STARTUPSTATE;
	}
	
}