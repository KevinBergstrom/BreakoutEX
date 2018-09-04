package bounce;

import jig.ResourceManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


class SplashState extends BasicGameState {

	int ScrollPos = 0;
	float ScrollSpeed = 0.1f;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		//container.setSoundOn(false);
	}


	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException {
		BounceGame bg = (BounceGame)game;
		
		/*bg.ball.render(g);
		g.drawString("Bounces: ?", 10, 30);
		for (Bang b : bg.explosions)
			b.render(g);*/
		
		Image ScrollImage = ResourceManager.getImage(BounceGame.SPLASH_SCROLL_IMG);
		ScrollImage.setFilter(Image.FILTER_NEAREST);
		//first part of scroll
		g.drawImage(ScrollImage,
				-(ScrollSpeed*ScrollPos), 0, bg.ScreenWidth - (ScrollSpeed*ScrollPos), bg.ScreenHeight,0, 0,400,300 );
		//falloff part of scroll
		g.drawImage(ScrollImage,
				bg.ScreenWidth-(ScrollSpeed*ScrollPos), 0, bg.ScreenWidth*2 - (ScrollSpeed*ScrollPos), bg.ScreenHeight,0, 0,400,300 );
		
		Image SplashImage = ResourceManager.getImage(BounceGame.SPLASH_IMG);
		SplashImage.setFilter(Image.FILTER_NEAREST);
		g.drawImage(SplashImage,
				0, 0, bg.ScreenWidth, bg.ScreenHeight,0, 0,400,300 );		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game,
			int delta) throws SlickException {

		Input input = container.getInput();
		BounceGame bg = (BounceGame)game;
		
		ScrollPos = ScrollPos + delta;
		if(ScrollPos*ScrollSpeed>bg.ScreenWidth) {
			ScrollPos = 0;
		}

		if (input.isKeyDown(Input.KEY_ENTER))//TODO how do I do any input?
			bg.enterState(BounceGame.STARTUPSTATE);	
		
		
		//bg.ball.update(delta);


	}

	@Override
	public int getID() {
		return BounceGame.SPLASHSTATE;
	}
	
}