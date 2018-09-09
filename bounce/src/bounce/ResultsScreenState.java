package bounce;

import jig.ResourceManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.VerticalSplitTransition;

/**
 * This comes after the PlayingState when a player has completed a level.
 * This state shows how well the player performed during the level.
 * Exiting this state will start loading the next level or go on to
 * the win screen.
 * 
 * Transitions From PlayingState
 * 
 * Transitions To StartUpState, WinState
 */
class ResultsScreenState extends BasicGameState {
	
	
	private float timeTaken;
	private int powerUpsGot;
	private int damageTaken;
	
	private int timeRank;
	private int powerUpRank;
	private int damageRank;
	private int rankScore;
	
	//Determines the value to beat to get {S,A,B,C}
	final private int[] timeRankTiers = {140000,200000,260000};
	final private int[] powerUpRankTiers = {15,10,5};
	final private int[] damageRankTiers = {0,2,5};
	
	private boolean readyToProgress;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		readyToProgress = false;
	}

	public void setUserScore(float t, int p, int d) {
		timeTaken = t;
		powerUpsGot = p;
		damageTaken = d;
		//calculate the player's scores
		timeRank = getRank((int)timeTaken, timeRankTiers, false);
		powerUpRank = getRank(powerUpsGot, powerUpRankTiers, true);
		damageRank = getRank(damageTaken, damageRankTiers, false);
		rankScore = (int)((timeRank + powerUpRank + damageRank)/3);
	}
	
	public int getRank(int value, int[] tierList, boolean greaterThan) {
		//check which rank value falls into
		for(int i = 0;i<tierList.length;i++) {
			if(greaterThan && value>=tierList[i]) {
					return i;
			}else if(!greaterThan && value<=tierList[i]) {
					return i;
			}
		}
		return tierList.length;
	}
	
	public void drawUserScores(Graphics g) {
		drawRankLetter(g, timeRank, -10, 86);
		drawRankLetter(g, powerUpRank, 57, 202);
		drawRankLetter(g, damageRank, 130, 332);
		drawRankLetter(g, rankScore, 207, 466);
	}
	
	public static void drawRankLetter(Graphics g, int rank, float x, float y) {
		Image rankImage;
		if(rank == 0) {
			//S rank
			rankImage = ResourceManager.getImage(BounceGame.S_RANKIMG_RSC);
		}else if(rank == 1) {
			//A rank
			rankImage = ResourceManager.getImage(BounceGame.A_RANKIMG_RSC);
		}else if(rank == 2) {
			//B rank
			rankImage = ResourceManager.getImage(BounceGame.B_RANKIMG_RSC);
		}else {
			//C rank
			rankImage = ResourceManager.getImage(BounceGame.C_RANKIMG_RSC);
		}
		rankImage.setFilter(Image.FILTER_NEAREST);
		g.drawImage(rankImage, x, y, x + 128, y+ 128,0, 0,64,64 );
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException {
		BounceGame bg = (BounceGame)game;
		if(bg.background!=null) {
			g.drawImage(bg.background, 0, 0);
		}
		for (BallTrail t : bg.trails)
			t.render(g);
		bg.paddle.render(g);
		bg.ball.render(g);
		for (Brick br : bg.bricks)
			br.render(g);
		for (Bang b : bg.explosions)
			b.render(g);
		for (Projectile p : bg.projectiles)
			p.render(g);
		for (PowerUp pu : bg.powerups)
			pu.render(g);
		
		if(bg.paddle.getProjShield()) {
			bg.paddle.renderProjShield(g);
		}
		
		Image ResultsImage = ResourceManager.getImage(BounceGame.RESULTSIMG_RSC);
		ResultsImage.setFilter(Image.FILTER_NEAREST);
		g.drawImage(ResultsImage,
				0, 0, bg.ScreenWidth, bg.ScreenHeight,0, 0,400,300 );
		
		drawUserScores(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game,
			int delta) throws SlickException {
		
		Input input = container.getInput();
		BounceGame bg = (BounceGame)game;
		if (input.isKeyDown(Input.KEY_SPACE)) {
			if(readyToProgress) {
				//update the players score
				bg.ranks[rankScore] = bg.ranks[rankScore] + 1;
				if(bg.currentLevel==Levels.lastLevel) {
					//completed last level
					game.enterState(BounceGame.WINSTATE, new EmptyTransition(), new VerticalSplitTransition() );
				}else {
					//there are still more levels
					game.enterState(BounceGame.STARTUPSTATE, new EmptyTransition(), new VerticalSplitTransition() );	
				}
			}
		}else {
			readyToProgress = true;
		}
		//level warp cheats
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
		return BounceGame.RESULTSSCREENSTATE;
	}
	
}