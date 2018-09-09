package bounce;

import jig.ResourceManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This state is used to load everything in the level before the player
 * can interact with it. This state comes whenever the player is starting
 * a level.
 * 
 * Transitions From SplashState, ResultsScreenState
 * 
 * Transitions To PlayingState
 */
class StartUpState extends BasicGameState {

	private boolean readyToProgress;
	
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
		bg.trails.clear();
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
		readyToProgress = false;
		bg.health = 3;
		bg.maxHealth = 3;
		bg.powerUpDelay = 6000f;
		bg.powerUpTimer = bg.powerUpDelay;
		bg.trailDelay = 100f;
		bg.trailTimer = bg.trailDelay;
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
		
		Image SplashImage = ResourceManager.getImage(BounceGame.STARTUP_BANNER_RSC);
		SplashImage.setFilter(Image.FILTER_NEAREST);
		g.drawImage(SplashImage,
				bg.ScreenWidth/2 -127, bg.ScreenHeight/2 - 21, bg.ScreenWidth/2 + 127, bg.ScreenHeight/2 + 21,0, 0,127,21 );
		
		g.drawString("Level: " + bg.currentLevel, 10, 30);
		
		if(bg.invincibility) {
			g.drawString("Invinciblility: On", 10, 50);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game,
			int delta) throws SlickException {

		Input input = container.getInput();
		BounceGame bg = (BounceGame)game;

		if (input.isKeyDown(Input.KEY_SPACE)) {
			if(readyToProgress) {
				bg.enterState(BounceGame.PLAYINGSTATE);
			}
		}else {
			readyToProgress = true;
		}
		
		if (input.isKeyDown(Input.KEY_INSERT))
			bg.invincibility = true;
		
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

	}

	@Override
	public int getID() {
		return BounceGame.STARTUPSTATE;
	}
	
}