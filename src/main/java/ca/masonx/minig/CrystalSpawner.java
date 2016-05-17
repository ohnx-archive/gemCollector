package ca.masonx.minig;

import java.util.Random;

import ca.masonx.leek.core.physics.Updateable;
import ca.masonx.leek.core.render.PositionedImage;
import ca.masonx.leek.core.world.Entity;
import ca.masonx.leek.core.world.Level;

public class CrystalSpawner extends Entity implements Updateable {
	/**
	 * Make this serializable
	 */
	private static final long serialVersionUID = 6337195758662980150L;
	Random randomGenerator = new Random();
	private double timeToSpawn = 0;
	private final MiniGame mommy;

	public CrystalSpawner(Level parent, MiniGame mg) {
		super(parent);
		recalcTimeToSpawn();
		this.mommy = mg;
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
			int score = mommy.getScore();
			int randx = randomGenerator.nextInt(parent.width - 60) + 30;
			int randy = randomGenerator.nextInt(parent.height - 60) + 30;
			if (score > 8) {
				if (randomGenerator.nextInt(score*2) > score) {
					EvilCrystal c = new EvilCrystal(parent, randx, randy, 3);
					parent.add(c);
					return;
				}
			}
			Crystal c = new Crystal(parent, randx, randy, 3);
			parent.add(c);
		}
	}

}
