package ca.masonx.minig;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ca.masonx.leek.Leek;
import ca.masonx.leek.Leek.WindowLocation;
import ca.masonx.leek.core.render.Text;
import ca.masonx.leek.core.world.Entity;
import ca.masonx.leek.core.world.Level;

public class MiniGame {
	public final Leek engine;
	private Player p;
	private CrystalSpawner cs;
	private Text t;
	public final Image wallPic;
	
	public static void main(String[] args) {
		MiniGame me = new MiniGame();
		me.start();
	}
	
	public MiniGame() {
		engine = new Leek();
		Image img = null;
		try {
			img = ImageIO.read(new File("resources/img/wall.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		wallPic = img;
	}
	
	protected void start() {
		try {
			engine.init("Gem Collector", WindowLocation.CENTER, ImageIO.read(new File("resources/img/hat.png")));
			Level l = new Level("Level 1", ImageIO.read(new File("resources/img/background.png")));
			p = new Player(l, this);
			cs = new CrystalSpawner(l);
			t = new Text(l, "Score: 0", 5, l.height-6);
			
			l.add((Entity)p);
			l.add((Entity)cs);
			l.add(t);
			
			engine.changeLevel(l);
			engine.enterMainLoop();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	
	protected void updateScore(int score) {
		t.text = "Score: " + Integer.toString(score);
	}
}
