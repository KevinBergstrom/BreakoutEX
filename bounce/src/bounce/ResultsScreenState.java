package bounce;

import java.util.Iterator;

import jig.ResourceManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.HorizontalSplitTransition;


class ResultsScreenState extends BasicGameState {
	

	private int lastKnownBounces; // the user's score, to be displayed, but not updated.
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) {
	}

	public void setUserScore(int bounces) {
		lastKnownBounces = bounces;
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException {
		BounceGame bg = (BounceGame)game;
		if(bg.background!=null) {
			g.drawImage(bg.background, 0, 0);
		}
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
			//TODO render projshield
		}
		
		Image ResultsImage = ResourceManager.getImage(BounceGame.RESULTSIMG_RSC);
		ResultsImage.setFilter(Image.FILTER_NEAREST);
		g.drawImage(ResultsImage,
				0, 0, bg.ScreenWidth, bg.ScreenHeight,0, 0,400,300 );
		
		//draw results here
	}

	@Override
	public void update(GameContainer container, StateBasedGame game,
			int delta) throws SlickException {
		
		
		//set startup to load next level?
		//game.enterState(BounceGame.SPLASHSTATE, new EmptyTransition(), new HorizontalSplitTransition() );

	}

	@Override
	public int getID() {
		return BounceGame.RESULTSSCREENSTATE;
	}
	
}