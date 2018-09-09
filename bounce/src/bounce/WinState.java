package bounce;

import jig.ResourceManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
import org.newdawn.slick.state.transition.EmptyTransition;

/**
 * This state shows up after the last level is completed. It displays
 * the player's final performance grade and a win message.
 * Exiting will restart the game.
 * 
 * Transitions From ResultsScreenState
 * 
 * Transitions To SplashState
 */
class WinState extends BasicGameState {
	
	private int finalRank;
	private boolean readyToProgress;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		readyToProgress = false;
		finalRank = 0;
		int ranksSize = 0;
		BounceGame bg = (BounceGame)game;
		//calculate the player's final rank
		for(int i = 0;i<bg.ranks.length;i++) {
			if(bg.ranks[i]>ranksSize) {
				finalRank = i;
				ranksSize = bg.ranks[i];
			}
		}
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException {
		BounceGame bg = (BounceGame)game;
		
		Image WinImage = ResourceManager.getImage(BounceGame.WINIMG_RSC);
		WinImage.setFilter(Image.FILTER_NEAREST);
		g.drawImage(WinImage, 0, 0, bg.ScreenWidth, bg.ScreenHeight,0, 0,400,300 );
		
		ResultsScreenState.drawRankLetter(g, finalRank, 460, 380);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game,
			int delta) throws SlickException {
		
		Input input = container.getInput();
		BounceGame bg = (BounceGame)game;
		
		if (input.isKeyDown(Input.KEY_SPACE)) {
			if(readyToProgress) {
				//reset to the first level
				bg.currentLevel = 0;
				//reset player stats
				for(int i = 0;i<bg.ranks.length;i++) {
					bg.ranks[i] = 0;
				}
				game.enterState(BounceGame.SPLASHSTATE, new EmptyTransition(), new BlobbyTransition() );
			}
		}else {
			readyToProgress = true;
		}

	}

	@Override
	public int getID() {
		return BounceGame.WINSTATE;
	}
	
}