package ca.masonx.minig;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ca.masonx.leek.Leek;
import ca.masonx.leek.core.gameElement.Entity;
import ca.masonx.leek.core.gameElement.Level;

public class MiniGame {
	public final Leek engine;
	private Player p;
	
	public static void main(String[] args) {
		MiniGame me = new MiniGame();
		me.start();
	}
	
	public MiniGame() {
		engine = new Leek();
		p = new Player(null);
	}
	
	protected void start() {
		engine.init("Gem Collector");
		try {
			Level l = new Level("Level 1", ImageIO.read(new File("resources/img/background.png")));
			l.add((Entity)p);
			CrystalSpawner cs = new CrystalSpawner(l);
			l.add((Entity)cs);
			engine.changeLevel(l);
			engine.enterMainLoop();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
}
