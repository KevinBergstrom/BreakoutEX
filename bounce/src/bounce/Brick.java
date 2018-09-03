package bounce;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import jig.Entity;
import jig.ResourceManager;


 class Brick extends Entity {

	private Image brickImg;
	private int bricksX;
	private int bricksY;
	private Color brickColor;
	private int maxHealth;
	private int health;

	public Brick(final float x, final float y, final int bx, final int by, Color col) {
		super(x, y);
		//bx and by respresent how many base bricks long or wide it is (1 to 4)
		//addImageWithBoundingBox(ResourceManager.getImage(BounceGame.BALL_BALLIMG_RSC));
		Image image = getBrickImageFromSize(bx, by);
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
			float brickAlpha = (float)health/(float)maxHealth;
			Image image = getBrickImageFromSize(bricksX, bricksY);
			brickImg = image.getScaledCopy(bricksX*40, bricksY*40);
			brickImg.setFilter(Image.FILTER_NEAREST);
			brickImg.setImageColor(brickColor.r, brickColor.g, brickColor.b,brickAlpha);
			addImageWithBoundingBox(brickImg);
			onHit(game);
		}
		
	}
	
	public void onHit(StateBasedGame game) {
		
	}
	
	public void onDeath(StateBasedGame game) {
		
	}
	
	public boolean isActive() {
		if(health<=0) {
			return false;
		}else {
			return true;
		}
	}

	/*public void update(final int delta) {
		
	}*/
}
