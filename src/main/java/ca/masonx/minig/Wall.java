package ca.masonx.minig;

import ca.masonx.leek.core.render.PositionedImage;
import ca.masonx.leek.core.world.Block;
import ca.masonx.leek.core.world.Level;

public class Wall extends Block {
	private static final long serialVersionUID = 8288230553638000678L;
	private final MiniGame daddy;

	public Wall(Level parent, MiniGame mg) {
		super(parent);
		daddy = mg;
	}

	public PositionedImage render() {
		return daddy.wallPic;
	}
}
