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
		// the CrystalSpawner is invisible
		return null;
	}

	public void update(double time) {
		// called every time this gets updated
		timeToSpawn -= time;
		// check if the time to spawn is negative
		if (timeToSpawn < 0) {
			recalcTimeToSpawn();
			int score = mommy.getScore();
			int randx = randomGenerator.nextInt(parent.width - 60) + 30;
			int randy = randomGenerator.nextInt(parent.height - 60) + 30;
			
			// 1/4 chance of bad crystal
			if (randomGenerator.nextInt(80) < Math.min(score, 20)) {
				EvilCrystal c = new EvilCrystal(parent, randx, randy, 3);
				parent.add(c);
				return;
			}
			
			// 1/5 chance good crystal
			if (randomGenerator.nextInt(50) < Math.min(score, 10)) {
				GoodCrystal c = new GoodCrystal(parent, randx, randy, 3);
				parent.add(c);
				return;
			}
		
			// remaining chance is the normal crystal
			Crystal c = new Crystal(parent, randx, randy, 3);
			parent.add(c);
		}
	}

}
