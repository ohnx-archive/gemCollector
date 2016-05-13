package ca.masonx.minig;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ca.masonx.leek.core.annotations.LeekEventHandler;
import ca.masonx.leek.core.events.CollisionEvent;
import ca.masonx.leek.core.events.HandlerType;
import ca.masonx.leek.core.gameElement.Level;
import ca.masonx.leek.core.gameElement.MovableEntity;
import ca.masonx.leek.core.render.PositionedImage;

public class Player extends MovableEntity implements KeyListener {
	/**
	 * Make this serializable
	 */
	private static final long serialVersionUID = 66916044368560750L;
	
	private boolean[] keysPressed = {false, false, false, false};
	private BufferedImage[] images = new BufferedImage[4];
	private int currPos = 0;
	
	@LeekEventHandler(handles = HandlerType.COLLISION)
	public void collided(CollisionEvent e) {
		
	}
	
	public Player(Level l) {
		super(l);
		try {
			images[0] = ImageIO.read(new File("resources/img/player0.png"));
			images[1] = ImageIO.read(new File("resources/img/player1.png"));
			images[2] = ImageIO.read(new File("resources/img/player2.png"));
			images[3] = ImageIO.read(new File("resources/img/player3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update(double time) {
		if (keysPressed[0]) {
			currPos = 0;
			py -= time;
		} else if (keysPressed[1]) {
			currPos = 1;
			px += time;
		} else if (keysPressed[2]) {
			currPos = 2;
			py += time;
		} else if (keysPressed[3]) {
			currPos = 3;
			px -= time;
		}
	}
	
	public PositionedImage render() {
		return new PositionedImage(images[currPos], px, py, 5);
	}
	
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			keysPressed[0] = true;
			break;
		case KeyEvent.VK_DOWN:
			keysPressed[1] = true;
			break;
		case KeyEvent.VK_LEFT:
			keysPressed[2] = true;
			break;
		case KeyEvent.VK_RIGHT:
			keysPressed[3] = true;
			break;
		default:
			return;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			keysPressed[0] = false;
			break;
		case KeyEvent.VK_DOWN:
			keysPressed[1] = false;
			break;
		case KeyEvent.VK_LEFT:
			keysPressed[2] = false;
			break;
		case KeyEvent.VK_RIGHT:
			keysPressed[3] = false;
			break;
		default:
			return;
		}
	}
	/* uneeded for the player class */
	public void keyTyped(KeyEvent e) {}
}
