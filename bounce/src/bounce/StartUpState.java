package bounce;

import java.util.Iterator;

import jig.ResourceManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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
		bg.ball.setDamage(1);
		bg.paddle.reset();
		bg.paddle.setSpeed(0.2f);
		bg.paddle.setHealth(3);
	}
	
	public void loadLevel(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		clearLevel(game);
		bg.currentLevel = bg.currentLevel+1;
		Levels.loadLevel(bg.currentLevel, game);
		
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		container.setSoundOn(false);
		//loading level
		BounceGame bg = (BounceGame)game;
		bg.health = 3;
		bg.maxHealth = 3;
		bg.powerUpDelay = 6000f;
		bg.powerUpTimer = 6000f;
		loadLevel(game);
		
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
		
		Image SplashImage = ResourceManager.getImage(BounceGame.STARTUP_BANNER_RSC);
		SplashImage.setFilter(Image.FILTER_NEAREST);
		g.drawImage(SplashImage,
				bg.ScreenWidth/2 -127, bg.ScreenHeight/2 - 21, bg.ScreenWidth/2 + 127, bg.ScreenHeight/2 + 21,0, 0,127,21 );
		
		if(bg.invincibility) {
			g.drawString("Invinciblility: On", 10, 30);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game,
			int delta) throws SlickException {

		Input input = container.getInput();
		BounceGame bg = (BounceGame)game;

		if (input.isKeyDown(Input.KEY_SPACE))
			bg.enterState(BounceGame.PLAYINGSTATE);	
		
		if (input.isKeyDown(Input.KEY_INSERT))
			bg.invincibility = true;

	}

	@Override
	public int getID() {
		return BounceGame.STARTUPSTATE;
	}
	
}