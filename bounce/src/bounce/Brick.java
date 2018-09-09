package bounce;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import jig.Entity;
import jig.ResourceManager;

/**
 * The Brick class is an Entity that is stationary. It can take
 * a number of hits before it breaks. When damaged it will turn
 * transparent to symbolize how much damage it has taken.
 * 
 */
 class Brick extends Entity {

	private Image brickImg;
	private int bricksX;
	private int bricksY;
	private Color brickColor;
	private int maxHealth;
	private int health;

	/**
	 * bx and by respresent how many base brick lengths long or wide it is (1 to 4)x
	 */
	public Brick(final float x, final float y, final int bx, final int by, Color col) {
		super(x, y);
		//get the correct brick image
		Image image = getBrickImageFromSize(bx, by);
		//scale and color the image
		brickImg = image.getScaledCopy(bx*40, by*40);
		brickImg.setFilter(Image.FILTER_NEAREST);
		brickImg.setImageColor(col.r, col.g, col.b);
		addImageWithBoundingBox(brickImg);
		
		maxHealth = bx*by;
		health = bx*by;
		
		bricksX = bx;
		bricksY = by;
		brickColor = col;
	}
	
	public Image getBrickImageFromSize(int bx, int by) {
		
		if(bx==1 && by == 1) {
			return ResourceManager.getImage(BounceGame.BRICK_1X1_RSC);
		}else if(bx==1 && by == 2) {
			return ResourceManager.getImage(BounceGame.BRICK_2X1_RSC);
		}else if(bx==1 && by == 3) {
			return ResourceManager.getImage(BounceGame.BRICK_3X1_RSC);
		}else if(bx==1 && by == 4) {
			return ResourceManager.getImage(BounceGame.BRICK_4X1_RSC);
		}else if(bx==2 && by == 1) {
			return ResourceManager.getImage(BounceGame.BRICK_1X2_RSC);
		}else if(bx==3 && by == 1) {
			return ResourceManager.getImage(BounceGame.BRICK_1X3_RSC);
		}else if(bx==4 && by == 1) {
			return ResourceManager.getImage(BounceGame.BRICK_1X4_RSC);
		}else if(bx==2 && by == 2) {
			return ResourceManager.getImage(BounceGame.BRICK_2X2_RSC);
		}else if(bx==3 && by == 3) {
			return ResourceManager.getImage(BounceGame.BRICK_3X3_RSC);
		}else {
			//wrong size
			return null;
		}
	}

	public void damageBrick(int damage, StateBasedGame game) {
		health = health - damage;
		this.removeImage(brickImg);
		if(health<0) {
			health = 0;
		}else {
			//change the bricks transparency based on health remaining
			float brickAlpha = (float)health/(float)maxHealth;
			Image image = getBrickImageFromSize(bricksX, bricksY);
			brickImg = image.getScaledCopy(bricksX*40, bricksY*40);
			brickImg.setFilter(Image.FILTER_NEAREST);
			brickImg.setImageColor(brickColor.r, brickColor.g, brickColor.b,brickAlpha);
			//remake the bounding box
			addImageWithBoundingBox(brickImg);
		}
		
	}
	
	public void onHit(StateBasedGame game) {
		//Will trigger when hit by the ball
	}
	
	public void onDeath(StateBasedGame game) {
		//Will trigger when health fully depleted
	}
	
	public boolean isActive() {
		if(health<=0) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * Spawns one of the Brick types chosen at random
	 */
	public static void spawnRandomBrick(final float x, final float y, final int bx, final int by,StateBasedGame game) {
		BounceGame bg = (BounceGame)game;
		Random rand = new Random();
		int BNum = rand.nextInt(6);
		
		if(BNum==0) {
			bg.bricks.add(new PowerUpBrick(x, y, bx, by));
		}else if(BNum==1) {
			bg.bricks.add(new ShootBrick(x, y, bx, by));
		}else if(BNum==2) {
			bg.bricks.add(new SpeedBrick(x, y, bx, by));
		}else if(BNum==3) {
			bg.bricks.add(new RadioactiveBrick(x, y, bx, by));
		}else if(BNum==4) {
			bg.bricks.add(new InversionBrick(x, y, bx, by));
		}else {
			bg.bricks.add(new Brick(x, y, bx, by,new Color(190,190,190)));
		}
	}

}
