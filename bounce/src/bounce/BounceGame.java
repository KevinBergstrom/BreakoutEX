package bounce;

import java.util.ArrayList;

import jig.Entity;
import jig.ResourceManager;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * A Slightly complex Game of Bounce.
 * 
 * The game has six states: Splash, StartUp, Playing, Results, Win and GameOver, the game
 * progresses through these states based on the user's input and the events that
 * occur. Each state is modestly different in terms of what is displayed and
 * what input is accepted.
 * 
 * In the playing state, our game displays a moving ball that
 * bounces off the sides of the game container, bricks, and the paddle.
 * If the ball hits the bottom of the screen, the player loses health.
 * 
 * The paddle is used to bounce the ball, collect PowerUps, and 
 * dodge projectiles.
 * 
 * When the ball bounces an explosion animation is played at the impact 
 * site to add a bit of eye-candy additionally, we play a short explosion 
 * sound effect when the game is actively being played.
 * 
 * The game generates patterns of bricks based on what level the player is
 * playing. Each level has different bricks. Each brick does a unique effect
 * when the ball hits it or it breaks.
 * 
 * At certain intervals PowerUps will spawn at the top of the screen and float
 * down for the player to collect. 
 * 
 * If the player runs out of health, they lose. The default amount of health
 * is 3.
 * 
 * The player's performance is evaluated and scored at the end of each level.
 * Completion time, number of PowerUps collected, and damage taken are tested.
 * 
 * If the player completes all of the levels, they will receive an overall rank of 
 * their performance.
 * 
 * Graphics resources courtesy of qubodup:
 * http://opengameart.org/content/bomb-explosion-animation
 * 
 * Sound resources courtesy of DJ Chronos:
 * http://www.freesound.org/people/DJ%20Chronos/sounds/123236/
 * 
 * 
 * @author wallaces and bergstromk
 * 
 */
public class BounceGame extends StateBasedGame {
	
	public static final int STARTUPSTATE = 0;
	public static final int PLAYINGSTATE = 1;
	public static final int GAMEOVERSTATE = 2;
	public static final int SPLASHSTATE = 3;
	public static final int RESULTSSCREENSTATE = 4;
	public static final int WINSTATE = 5;
	
	public static final String BALL_BALLIMG_RSC = "bounce/resource/ballFinal.png";
	public static final String GAMEOVER_BANNER_RSC = "bounce/resource/gameOverBG.png";
	public static final String STARTUP_BANNER_RSC = "bounce/resource/pressSpace.png";
	public static final String BANG_EXPLOSIONIMG_RSC = "bounce/resource/explosion.png";
	public static final String BANG_EXPLOSIONSND_RSC = "bounce/resource/explosion.wav";
	public static final String SPLASH_IMG = "bounce/resource/splashScreen.png";
	public static final String SPLASH_SCROLL_IMG = "bounce/resource/smallOrangeBG.png";
	public static final String FULL_PADDLEIMG_RSC = "bounce/resource/fullPaddle.png";
	public static final String HALF_PADDLEIMG_RSC = "bounce/resource/halfPaddle.png";
	public static final String LOW_PADDLEIMG_RSC = "bounce/resource/lowPaddle.png";
	public static final String DEAD_PADDLEIMG_RSC = "bounce/resource/deadPaddle.png";
	public static final String PROJECTILEIMG_RSC = "bounce/resource/projectile.png";
	public static final String SLIMDRIPIMG_RSC = "bounce/resource/slimeDrip.png";
	public static final String RESULTSIMG_RSC = "bounce/resource/resultsScreen.png";
	public static final String WINIMG_RSC = "bounce/resource/winnerBG.png";
	//bricks
	public static final String BRICK_1X1_RSC = "bounce/resource/gray1x1.png";
	public static final String BRICK_1X2_RSC = "bounce/resource/gray1x2.png";
	public static final String BRICK_1X3_RSC = "bounce/resource/gray1x3.png";
	public static final String BRICK_1X4_RSC = "bounce/resource/gray1x4.png";
	public static final String BRICK_2X1_RSC = "bounce/resource/gray2x1.png";
	public static final String BRICK_3X1_RSC = "bounce/resource/gray3x1.png";
	public static final String BRICK_4X1_RSC = "bounce/resource/gray4x1.png";
	public static final String BRICK_2X2_RSC = "bounce/resource/gray2x2.png";
	public static final String BRICK_3X3_RSC = "bounce/resource/gray3x3.png";
	//powerUps
	public static final String ACCEL_POWERUPIMG_RSC = "bounce/resource/acceleratePowerup.png";
	public static final String HEAL_POWERUPIMG_RSC = "bounce/resource/healPowerup.png";
	public static final String MORE_POWERUPIMG_RSC = "bounce/resource/morePowerup.png";
	public static final String PROJ_POWERUPIMG_RSC = "bounce/resource/projPowerup.png";
	public static final String SLOW_POWERUPIMG_RSC = "bounce/resource/slowPowerup.png";
	public static final String X_POWERUPIMG_RSC = "bounce/resource/xPowerup.png";
	public static final String PROJ_SHIELDIMG_RSC = "bounce/resource/projShield.png";
	//backgrounds
	public static final String CAVE_BGIMG_RSC = "bounce/resource/caveBG.png";
	public static final String CITY_BGIMG_RSC = "bounce/resource/cityScapeBG.png";
	public static final String ROAD_BGIMG_RSC = "bounce/resource/midnightRoadBG.png";
	public static final String MOUNTAIN_BGIMG_RSC = "bounce/resource/mountainBG.png";
	public static final String PIPES_BGIMG_RSC = "bounce/resource/pipesBG.png";
	public static final String STAR_BGIMG_RSC = "bounce/resource/starfieldBG.png";
	public static final String VOID_BGIMG_RSC = "bounce/resource/voidBG.png";
	//ranks
	public static final String S_RANKIMG_RSC = "bounce/resource/sRank.png";
	public static final String A_RANKIMG_RSC = "bounce/resource/aRank.png";
	public static final String B_RANKIMG_RSC = "bounce/resource/bRank.png";
	public static final String C_RANKIMG_RSC = "bounce/resource/cRank.png";
	

	public final int ScreenWidth;
	public final int ScreenHeight;

	Ball ball;
	Paddle paddle;
	ArrayList<Bang> explosions;
	ArrayList<Brick> bricks;
	ArrayList<Projectile> projectiles;
	ArrayList<PowerUp> powerups;
	ArrayList<BallTrail> trails;
	
	public int health;
	public int maxHealth;
	public float powerUpDelay;//time in between PowerUp spawns
	public float powerUpTimer;
	public float trailDelay;//time in between BallTrail spawns
	public float trailTimer;
	public int currentLevel;
	public int[] ranks = {0,0,0,0};//stores the ranks received for win state
	
	public boolean invincibility;
	
	public Image background;

	/**
	 * Create the BounceGame frame, saving the width and height for later use.
	 * 
	 * @param title
	 *            the window's title
	 * @param width
	 *            the window's width
	 * @param height
	 *            the window's height
	 */
	public BounceGame(String title, int width, int height) {
		super(title);
		ScreenHeight = height;
		ScreenWidth = width;

		Entity.setCoarseGrainedCollisionBoundary(Entity.AABB);
		explosions = new ArrayList<Bang>(10);
		bricks = new ArrayList<Brick>();
		projectiles = new ArrayList<Projectile>();
		powerups = new ArrayList<PowerUp>();
		trails = new ArrayList<BallTrail>();
		background = null;
				
	}


	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new SplashState());
		addState(new StartUpState());
		addState(new GameOverState());
		addState(new PlayingState());
		addState(new ResultsScreenState());
		addState(new WinState());
		
		// the sound resource takes a particularly long time to load,
		// we preload it here to (1) reduce latency when we first play it
		// and (2) because loading it will load the audio libraries and
		// unless that is done now, we can't *disable* sound as we
		// attempt to do in the startUp() method.
		ResourceManager.loadSound(BANG_EXPLOSIONSND_RSC);	

		// preload all the resources to avoid warnings & minimize latency...
		//TODO perhaps a better automated way to do this?
		ResourceManager.loadImage(BALL_BALLIMG_RSC);
		ResourceManager.loadImage(GAMEOVER_BANNER_RSC);
		ResourceManager.loadImage(STARTUP_BANNER_RSC);
		ResourceManager.loadImage(BANG_EXPLOSIONIMG_RSC);
		ResourceManager.loadImage(SPLASH_IMG);
		ResourceManager.loadImage(SPLASH_SCROLL_IMG);
		ResourceManager.loadImage(FULL_PADDLEIMG_RSC);
		ResourceManager.loadImage(HALF_PADDLEIMG_RSC);
		ResourceManager.loadImage(LOW_PADDLEIMG_RSC);
		ResourceManager.loadImage(DEAD_PADDLEIMG_RSC);
		ResourceManager.loadImage(PROJECTILEIMG_RSC);
		ResourceManager.loadImage(SLIMDRIPIMG_RSC);
		ResourceManager.loadImage(RESULTSIMG_RSC);
		ResourceManager.loadImage(WINIMG_RSC);
		//bricks
		ResourceManager.loadImage(BRICK_1X1_RSC);
		ResourceManager.loadImage(BRICK_1X2_RSC);
		ResourceManager.loadImage(BRICK_1X3_RSC);
		ResourceManager.loadImage(BRICK_1X4_RSC);
		ResourceManager.loadImage(BRICK_2X1_RSC);
		ResourceManager.loadImage(BRICK_3X1_RSC);
		ResourceManager.loadImage(BRICK_4X1_RSC);
		ResourceManager.loadImage(BRICK_2X2_RSC);
		ResourceManager.loadImage(BRICK_3X3_RSC);
		//powerups
		ResourceManager.loadImage(ACCEL_POWERUPIMG_RSC);
		ResourceManager.loadImage(HEAL_POWERUPIMG_RSC);
		ResourceManager.loadImage(MORE_POWERUPIMG_RSC);
		ResourceManager.loadImage(PROJ_POWERUPIMG_RSC);
		ResourceManager.loadImage(SLOW_POWERUPIMG_RSC);
		ResourceManager.loadImage(X_POWERUPIMG_RSC);
		ResourceManager.loadImage(PROJ_SHIELDIMG_RSC);
		//backgrounds
		ResourceManager.loadImage(CAVE_BGIMG_RSC);
		ResourceManager.loadImage(CITY_BGIMG_RSC);
		ResourceManager.loadImage(ROAD_BGIMG_RSC);
		ResourceManager.loadImage(MOUNTAIN_BGIMG_RSC);
		ResourceManager.loadImage(PIPES_BGIMG_RSC);
		ResourceManager.loadImage(STAR_BGIMG_RSC);
		ResourceManager.loadImage(VOID_BGIMG_RSC);
		//ranks
		ResourceManager.loadImage(S_RANKIMG_RSC);
		ResourceManager.loadImage(A_RANKIMG_RSC);
		ResourceManager.loadImage(B_RANKIMG_RSC);
		ResourceManager.loadImage(C_RANKIMG_RSC);
		
		ball = new Ball(ScreenWidth/2, ScreenHeight - 200, .1f, -.2f);
		paddle = new Paddle(ScreenWidth/2, ScreenHeight-60, .0f, .0f);
		paddle.setMoveBounds(80, ScreenWidth-80, ScreenHeight-100, ScreenHeight-20);
		
		health = 3;
		maxHealth = 3;
		powerUpDelay = 6000f;
		trailDelay = 100f;
		powerUpTimer = powerUpDelay;
		trailTimer = trailDelay;
		currentLevel = 0;
		
		invincibility = false;

	}
	
	public static void main(String[] args) {
		AppGameContainer app;
		try {
			app = new AppGameContainer(new BounceGame("BreakoutEX", 800, 600));
			app.setDisplayMode(800, 600, false);
			app.setVSync(true);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	
}
