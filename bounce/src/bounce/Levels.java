package bounce;

import org.newdawn.slick.Color;
import org.newdawn.slick.state.StateBasedGame;

import jig.ResourceManager;

public class Levels {

	final public static int lastLevel = 7;
	
	public static void loadLevel(int level, StateBasedGame game) {
		if(level==1) {
			level1(game);
		}else if(level==2) {
			level2(game);
		}else if(level==3) {
			level3(game);
		}else if(level==4) {
			level4(game);
		}else if(level==5) {
			level5(game);
		}else if(level==6) {
			level6(game);
		}else if(level==7) {
			level7(game);
		}
	}
	
	private static void level1(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		//background
		bg.background = ResourceManager.getImage(BounceGame.CITY_BGIMG_RSC);
		//load bricks
		for(int x = 0;x<7;x++) {
			for(int y = 0;y<6;y++) {
				bg.bricks.add(new Brick(160+(x*2*40), 60+(y*40), 2, 1,new Color(0+(x*36),255-(x*20)-(y*20),0+(y*42))));
			}
		}
	}
	
	private static void level2(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		//background
		bg.background = ResourceManager.getImage(BounceGame.CAVE_BGIMG_RSC);
		//load bricks
		for(int x = 0;x<20;x++) {
			for(int y = 0;y<2;y++) {
				bg.bricks.add(new ShootBrick(20+(x*40), 100+(y*40), 1, 1));
			}
		}
		for(int x = 0;x<5;x++) {
			for(int y = 0;y<1;y++) {
				bg.bricks.add(new PowerUpBrick(80+(x*4*40), 60+(y*40), 4, 1));
			}
		}
		for(int x = 0;x<5;x++) {
			for(int y = 0;y<1;y++) {
				bg.bricks.add(new Brick(80+(x*4*40), 180+(y*40), 4, 1,new Color(255,255,255)));
			}
		}
	}
	
	private static void level3(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		//background
		bg.background = ResourceManager.getImage(BounceGame.ROAD_BGIMG_RSC);
		//load bricks
		for(int x = 0;x<5;x++) {
			for(int y = 0;y<5;y++) {
				bg.bricks.add(new InversionBrick(80+(x*4*40), 100+(y*40), 1, 1));
			}
		}
		for(int x = 0;x<3;x++) {
			for(int y = 0;y<5;y++) {
				bg.bricks.add(new SpeedBrick(120+(x*8*40), 100+(y*40), 1, 1));
			}
		}
		for(int x = 0;x<2;x++) {
			for(int y = 0;y<5;y++) {
				bg.bricks.add(new PowerUpBrick(280+(x*8*40), 100+(y*40), 1, 1));
			}
		}
	}
	
	private static void level4(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		//background
		bg.background = ResourceManager.getImage(BounceGame.MOUNTAIN_BGIMG_RSC);
		//load bricks
		for(int x = 0;x<6;x++) {
				bg.bricks.add(new ShootBrick(180+(x*40), 20+(x*40), 1, 1));
		}
		for(int x = 0;x<5;x++) {
			bg.bricks.add(new PowerUpBrick(220+(x*40), 20+(x*40), 1, 1));
		}
		for(int x = 0;x<4;x++) {
			bg.bricks.add(new RadioactiveBrick(260+(x*40), 20+(x*40), 1, 1));
		}
		for(int x = 0;x<3;x++) {
			bg.bricks.add(new SpeedBrick(300+(x*40), 20+(x*40), 1, 1));
		}
		bg.bricks.add(new InversionBrick(400, 20, 4, 1));
		bg.bricks.add(new InversionBrick(400, 60, 2, 1));
		for(int x = 0;x<6;x++) {
			bg.bricks.add(new SpeedBrick(620-(x*40), 20+(x*40), 1, 1));
		}
		for(int x = 0;x<5;x++) {
			bg.bricks.add(new RadioactiveBrick(580-(x*40), 20+(x*40), 1, 1));
		}
		for(int x = 0;x<4;x++) {
			bg.bricks.add(new PowerUpBrick(540-(x*40), 20+(x*40), 1, 1));
		}
		for(int x = 0;x<3;x++) {
			bg.bricks.add(new ShootBrick(500-(x*40), 20+(x*40), 1, 1));
		}
		
	}
	
	private static void level5(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		//background
		bg.background = ResourceManager.getImage(BounceGame.PIPES_BGIMG_RSC);
		//load bricks
		
		for(int x = 0;x<2;x++) {
			for(int y = 0;y<4;y++) {
				bg.bricks.add(new RadioactiveBrick(380+(x*40), 40+(y*80), 1, 2));
			}
		}
		for(int x = 0;x<6;x++) {
			for(int y = 0;y<6;y++) {
				if(x<=y) {
					bg.bricks.add(new ShootBrick(20+(x*40), 60+(y*40), 1, 1));
				}
			}
		}
		for(int x = 0;x<6;x++) {
			for(int y = 0;y<6;y++) {
				if(x<=y) {
					bg.bricks.add(new ShootBrick(780-(x*40), 60+(y*40), 1, 1));
				}
			}
		}
		for(int x = 0;x<3;x++) {
			bg.bricks.add(new SpeedBrick(60+(x*3*40), 20, 3, 1));
		}
		for(int x = 0;x<3;x++) {
			bg.bricks.add(new SpeedBrick(500+(x*3*40), 20, 3, 1));
		}
		for(int x = 0;x<2;x++) {
			bg.bricks.add(new PowerUpBrick(60+(x*3*40), 300, 3, 1));
		}
		for(int x = 0;x<2;x++) {
			bg.bricks.add(new PowerUpBrick(620+(x*3*40), 300, 3, 1));
		}
	}
	
	private static void level6(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		//background
		bg.background = ResourceManager.getImage(BounceGame.STAR_BGIMG_RSC);
		//load bricks
		bg.bricks.add(new Brick(bg.ScreenWidth/2, bg.ScreenHeight/2, 1, 1,new Color(255,255,255)));
	}
	
	private static void level7(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		//background
		bg.background = ResourceManager.getImage(BounceGame.VOID_BGIMG_RSC);
		//load bricks
		bg.bricks.add(new Brick(bg.ScreenWidth/2, bg.ScreenHeight/2, 1, 1,new Color(255,255,255)));
	}
	
}
