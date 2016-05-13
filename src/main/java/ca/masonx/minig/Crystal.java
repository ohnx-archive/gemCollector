package ca.masonx.minig;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ca.masonx.leek.core.gameElement.Level;
import ca.masonx.leek.core.gameElement.StaticEntity;
import ca.masonx.leek.core.render.PositionedImage;

public class Crystal extends StaticEntity {
	/**
	 * Make this serializable
	 */
	private static final long serialVersionUID = 1194922464514422553L;
	private transient BufferedImage img;

	public Crystal(Level parent, int px, int py, int pz) {
		super(parent);
		this.px = px;
		this.py = py;
		this.pz = pz;
		try {
			img = ImageIO.read(new File("resources/img/gem.png"));
			this.height = img.getHeight();
			this.width = img.getWidth();
		} catch (IOException e) {
			e.printStackTrace();
			img = null;
		}
	}

	public PositionedImage render() {
		PositionedImage pi = new PositionedImage(img, px, py, pz);
		return pi;
	}

}
