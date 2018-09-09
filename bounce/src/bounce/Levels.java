package bounce;

import org.newdawn.slick.Color;
import org.newdawn.slick.state.StateBasedGame;

import jig.ResourceManager;

/**
 * Contains data for generating all of the levels used in this game.
 */
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
				bg.bricks.add(new Brick(80+(x*4*40), 180+(y*40), 4, 1,new Color(200,200,200)));
			}
		}
	}
	
	private static void level3(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		//background
		bg.background = ResourceManager.getImage(BounceGame.MOUNTAIN_BGIMG_RSC);
		//load bricks
		int startX = 160;
		int startY = 20;
		float radius = 3.5f;
		int sizeX = 2;
		int sizeY = 1;
		
		for(int x = 0;x<2*radius;x++) {
			for(int y = 0;y<2*radius;y++) {
				float midX = radius-x-0.5f;
				float midY = radius-y-0.5f;
				if(Math.sqrt(midX*midX + midY*midY)<=radius) {
					if(y%2==0 || x%2==0) {
						bg.bricks.add(new Brick(startX+(x*(40*sizeX)), startY+(y*(40*sizeY)), sizeX, sizeY, new Color(0,255-(y*30),255-(y*20))));
					}else {
						bg.bricks.add(new RadioactiveBrick(startX+(x*(40*sizeX)), startY+(y*(40*sizeY)), sizeX, sizeY));
					}
				}
			}
		}
		
	}
	
	private static void level4(StateBasedGame game) {
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
		
		for(int x = 0;x<5;x++) {
			for(int y = 0;y<5;y++) {
				if(x == 0 || y == 0 || x == 4| y == 4) {
					Brick.spawnRandomBrick(120+(x*40), 80+(y*40), 1, 1, game);
				}
			}
		}
		bg.bricks.add(new PowerUpBrick(200, 160, 3, 3));
		
		for(int x = 0;x<5;x++) {
			for(int y = 0;y<5;y++) {
				if(x == 0 || y == 0 || x == 4| y == 4) {
					Brick.spawnRandomBrick(400+(x*40), 120+(y*40), 1, 1, game);
				}
			}
		}
		bg.bricks.add(new PowerUpBrick(480, 200, 3, 3));
		
		for(int x = 0;x<4;x++) {
			for(int y = 0;y<4;y++) {
				if(x == 0 || y == 0 || x == 3| y == 3) {
					Brick.spawnRandomBrick(600+(x*40), 40+(y*40), 1, 1, game);
				}
			}
		}
		bg.bricks.add(new PowerUpBrick(660, 100, 2, 2));
		
		for(int x = 0;x<4;x++) {
			for(int y = 0;y<4;y++) {
				if(x == 0 || y == 0 || x == 3| y == 3) {
					Brick.spawnRandomBrick(80+(x*40), 280+(y*40), 1, 1, game);
				}
			}
		}
		bg.bricks.add(new PowerUpBrick(140, 340, 2, 2));
		
		for(int x = 0;x<3;x++) {
			for(int y = 0;y<3;y++) {
				if(x == 0 || y == 0 || x == 2| y == 2) {
					Brick.spawnRandomBrick(320+(x*40), (y*40), 1, 1, game);
				}
			}
		}
		bg.bricks.add(new PowerUpBrick(360, 40, 1, 1));
		
		for(int x = 0;x<3;x++) {
			for(int y = 0;y<3;y++) {
				if(x == 0 || y == 0 || x == 2| y == 2) {
					Brick.spawnRandomBrick(600+(x*40), 240+(y*40), 1, 1, game);
				}
			}
		}
		bg.bricks.add(new PowerUpBrick(640, 280, 1, 1));
		
	}
	
	private static void level7(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		//background
		bg.background = ResourceManager.getImage(BounceGame.VOID_BGIMG_RSC);
		//load bricks
		for(int x = 0;x<5;x++) {
			for(int y = 0;y<2;y++) {
				bg.bricks.add(new PowerUpBrick(20+(x*4*40), 80+(y*4*40), 1, 1));
			}
		}
		for(int x = 0;x<20;x++) {
			for(int y = 0;y<6;y++) {
				Brick.spawnRandomBrick(20+(x*40), 40+(y*40), 1, 1, game);
			}
		}
		for(int x = 0;x<10;x++) {
			for(int y = 0;y<3;y++) {
				bg.bricks.add(new Brick(40+(x*2*40), 60+(y*2*40), 2, 2,new Color(0+(x*20)+(y*20),200+(x*10),200+(y*10))));
			}
		}
		
	}
	
}
