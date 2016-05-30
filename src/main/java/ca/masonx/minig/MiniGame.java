package ca.masonx.minig;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import ca.masonx.leek.Leek;
import ca.masonx.leek.core.gui.WindowLocation;
import ca.masonx.leek.core.render.Text;
import ca.masonx.leek.core.world.Level;
import ca.masonx.leek.misc.JukeBox;

public class MiniGame {
	public final Leek engine;
	private Player p;
	private CrystalSpawner cs;
	private Text score;
	private Text health;
	private boolean isFirstTime = true;
	
	public static void main(String[] args) {
		MiniGame me = new MiniGame();
		me.mainMenu();
	}
	
	public MiniGame() {
		engine = new Leek();
		try {
			engine.init("Gem Collector", WindowLocation.CENTER, ImageIO.read(new File("resources/img/hat.png")));
			int handler = JukeBox.play("resources/sound/fade.wav");
			JukeBox.setLoopMode(handler, Clip.LOOP_CONTINUOUSLY);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	protected void mainMenu() {
		try {
			Level l = new Level("Dead", ImageIO.read(new File("resources/img/mainmenu.png")));
			DeathListener dl = new DeathListener(l, this);
			l.add(dl);
			engine.requestChangeLevel(l);
			if (isFirstTime) {
				isFirstTime = false;
				engine.enterMainLoop();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void mainLevel() {
		try {
			Level l = new Level("Level 1", ImageIO.read(new File("resources/img/background.png")));
			p = new Player(l, this);
			cs = new CrystalSpawner(l, this);
			score = new Text(l, "Score: 0", 5, l.height-6);
			health = new Text(l, "Health: 3", 5, l.height-18);
			
			l.add(p);
			l.add(cs);
			l.add(score);
			l.add(health);

			Wall wl = new Wall(l, ImageIO.read(new File("resources/img/wall-vert.png")), 0, 0);
			Wall wt = new Wall(l, ImageIO.read(new File("resources/img/wall-horiz.png")), 0, 0);
			Wall wr = new Wall(l, ImageIO.read(new File("resources/img/wall-vert.png")), 625, 0);
			Wall wb = new Wall(l, ImageIO.read(new File("resources/img/wall-horiz.png")), 0, 465);
			
			l.add(wl);
			l.add(wt);
			l.add(wr);
			l.add(wb);

			engine.requestChangeLevel(l);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	
	protected void updateScore() {
		score.text = "Score: " + Integer.toString(p.score);
	}
	
	protected int getScore() {
		return p.score;
	}
	
	protected void updateHealth() {
		health.text = "Health: " + Integer.toString(p.health);
	}
}
