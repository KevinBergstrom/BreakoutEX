package bounce;

import java.util.Iterator;

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
 * This state is active when the Game is over. In this state, the ball is
 * neither drawn nor updated while everything else is drawn.
 * A gameover banner is displayed. A timer automatically transitions 
 * back to the Splash State. Space can be pressed to transition to Splash State
 * early.
 * 
 * Transitions From PlayingState
 * 
 * Transitions To SplashState
 */
class GameOverState extends BasicGameState {
	

	private float timer;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		timer = 10000f;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game,
			Graphics g) throws SlickException {
		BounceGame bg = (BounceGame)game;
		
		if(bg.background!=null) {
			g.drawImage(bg.background, 0, 0);
		}
		
		bg.paddle.render(g);
		
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
		
		Image GameOverImage = ResourceManager.getImage(BounceGame.GAMEOVER_BANNER_RSC);
		GameOverImage.setFilter(Image.FILTER_NEAREST);
		g.drawImage(GameOverImage,
				0, 0, bg.ScreenWidth, bg.ScreenHeight,0, 0,400,300 );	
	}

	@Override
	public void update(GameContainer container, StateBasedGame game,
			int delta) throws SlickException {
		
		Input input = container.getInput();
		BounceGame bg = (BounceGame)game;
		if (input.isKeyDown(Input.KEY_SPACE)) {
			timer = 0;
		}
		
		timer -= delta;
		if (timer <= 0) {
			//reset to first level
			bg.currentLevel = 0;
			//remove player performance data
			for(int i = 0;i<bg.ranks.length;i++) {
				bg.ranks[i] = 0;
			}
			game.enterState(BounceGame.SPLASHSTATE, new EmptyTransition(), new BlobbyTransition() );
		}
		// check if there are any finished explosions, if so remove them
		for (Iterator<Bang> i = ((BounceGame)game).explosions.iterator(); i.hasNext();) {
			if (!i.next().isActive()) {
				i.remove();
			}
		}

	}

	@Override
	public int getID() {
		return BounceGame.GAMEOVERSTATE;
	}
	
}