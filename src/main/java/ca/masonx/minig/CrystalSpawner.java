package ca.masonx.minig;

import java.util.Random;

import ca.masonx.leek.core.gameElement.Entity;
import ca.masonx.leek.core.gameElement.Level;
import ca.masonx.leek.core.physics.Updateable;
import ca.masonx.leek.core.render.PositionedImage;

public class CrystalSpawner extends Entity implements Updateable {
	/**
	 * Make this serializable
	 */
	private static final long serialVersionUID = 6337195758662980150L;
	Random randomGenerator = new Random();
	private double timeToSpawn = 0;

	public CrystalSpawner(Level parent) {
		super(parent);
		recalcTimeToSpawn();
	}
	
	private void recalcTimeToSpawn() {
		timeToSpawn = randomGenerator.nextInt(5000) + 1000;
	}

	public PositionedImage render() {
		return null;
	}

	public void update(double time) {
		timeToSpawn -= time;
		if (timeToSpawn < 0) {
			recalcTimeToSpawn();
			int randx = randomGenerator.nextInt(parent.height);
			int randy = randomGenerator.nextInt(parent.width);
			Crystal c = new Crystal(parent, randx, randy, 3);
			parent.add(c);
		}
	}

}
