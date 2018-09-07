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
		bg.bricks.add(new Brick(bg.ScreenWidth/2, bg.ScreenHeight/2, 1, 1,new Color(255,255,255)));
	}
	
	private static void level2(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		//background
		bg.background = ResourceManager.getImage(BounceGame.ROAD_BGIMG_RSC);
		//load bricks
		bg.bricks.add(new Brick(bg.ScreenWidth/2, bg.ScreenHeight/2, 1, 1,new Color(255,255,255)));
	}
	
	private static void level3(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		//background
		bg.background = ResourceManager.getImage(BounceGame.CAVE_BGIMG_RSC);
		//load bricks
		bg.bricks.add(new Brick(bg.ScreenWidth/2, bg.ScreenHeight/2, 1, 1,new Color(255,255,255)));
	}
	
	private static void level4(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		//background
		bg.background = ResourceManager.getImage(BounceGame.MOUNTAIN_BGIMG_RSC);
		//load bricks
		bg.bricks.add(new Brick(bg.ScreenWidth/2, bg.ScreenHeight/2, 1, 1,new Color(255,255,255)));
	}
	
	private static void level5(StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		//background
		bg.background = ResourceManager.getImage(BounceGame.PIPES_BGIMG_RSC);
		//load bricks
		bg.bricks.add(new Brick(bg.ScreenWidth/2, bg.ScreenHeight/2, 1, 1,new Color(255,255,255)));
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
