package bounce;

import org.newdawn.slick.Image;

import jig.ResourceManager;
import jig.Vector;

/**
 * The GravityProjectile class is an Entity that moves downward.
 * It has an constant acceleration to give a gravity falling effect.
 *  When it collides with the paddle, the player will take damage. 
 * 
 */
public class GravityProjectile extends Projectile{

	private float acc = 0.2f;
	
	public GravityProjectile(float x, float y) {
		super(x, y, 0,0.1f);
		Image newImage = ResourceManager.getImage(BounceGame.SLIMDRIPIMG_RSC).getScaledCopy(30, 60);
		newImage.setFilter(Image.FILTER_NEAREST);
		addImageWithBoundingBox(newImage);
	}
	@Override
	public void update(final int delta) {
		translate(velocity.scale(delta*speed));
		setVelocity(new Vector(0,this.getVelocity().getY()+acc));
	}
	
}
