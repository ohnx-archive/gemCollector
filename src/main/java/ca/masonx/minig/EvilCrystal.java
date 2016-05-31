package ca.masonx.minig;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ca.masonx.leek.core.render.PositionedImage;
import ca.masonx.leek.core.world.Level;
import ca.masonx.leek.core.world.StaticEntity;

public class EvilCrystal extends StaticEntity {
	/**
	 * Make this serializable
	 */
	private static final long serialVersionUID = 1194922464514422553L;
	private transient BufferedImage img;
	private PositionedImage pi;

	public EvilCrystal(Level parent, int px, int py, int pz) {
		super(parent);
		// place it where it should be
		this.px = px;
		this.py = py;
		this.pz = pz;
		try {
			// load the image
			img = ImageIO.read(new File("resources/img/evilgem.png"));
			this.height = img.getHeight();
			this.width = img.getWidth();
			// create the positioned image
			// we can use the same one since nothing ever changes
			pi = new PositionedImage(img, px, py, pz);
		} catch (IOException e) {
			e.printStackTrace();
			img = null;
		}
	}

	public PositionedImage render() {
		// return pre-rendered stuff
		return pi;
	}

	public void collect() {
		// when the gem gets collected, remove itself
		parent.removeEntity(this);
	}
}
