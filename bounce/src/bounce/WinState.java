package bounce;

import java.util.Iterator;

import jig.ResourceManager;
import jig.Vector;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.HorizontalSplitTransition;
import org.newdawn.slick.state.transition.VerticalSplitTransition;


class WinState extends BasicGameState {
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) {
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException {
		BounceGame bg = (BounceGame)game;
		
		Image WinImage = ResourceManager.getImage(BounceGame.WINIMG_RSC);
		WinImage.setFilter(Image.FILTER_NEAREST);
		g.drawImage(WinImage, 0, 0, bg.ScreenWidth, bg.ScreenHeight,0, 0,400,300 );	
	}

	@Override
	public void update(GameContainer container, StateBasedGame game,
			int delta) throws SlickException {
		
		Input input = container.getInput();
		BounceGame bg = (BounceGame)game;
		if (input.isKeyDown(Input.KEY_SPACE)) {
			bg.currentLevel = 0;
			game.enterState(BounceGame.SPLASHSTATE, new EmptyTransition(), new BlobbyTransition() );
		}

	}

	@Override
	public int getID() {
		return BounceGame.WINSTATE;
	}
	
}