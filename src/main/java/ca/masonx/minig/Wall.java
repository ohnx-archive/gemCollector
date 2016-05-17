package ca.masonx.minig;

import java.awt.image.BufferedImage;

import ca.masonx.leek.core.render.PositionedImage;
import ca.masonx.leek.core.world.Block;
import ca.masonx.leek.core.world.Level;

public class Wall extends Block {
	private static final long serialVersionUID = 8288230553638000678L;
	private PositionedImage image;
	
	public Wall(Level parent, BufferedImage pic, int px, int py) {
		super(parent);
		this.image = new PositionedImage(pic, px, py, 10);
		this.height = pic.getHeight();
		this.width = pic.getWidth();
		this.px = px;
		this.py = py;
	}

	public PositionedImage render() {
		return image;
	}
}
