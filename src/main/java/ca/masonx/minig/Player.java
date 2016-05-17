package ca.masonx.minig;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ca.masonx.leek.core.annotations.LeekEventHandler;
import ca.masonx.leek.core.events.CollisionListener;
import ca.masonx.leek.core.events.EventHandlerRegister;
import ca.masonx.leek.core.physics.CollisionChecker;
import ca.masonx.leek.core.render.PositionedImage;
import ca.masonx.leek.core.world.GameElement;
import ca.masonx.leek.core.world.Level;
import ca.masonx.leek.core.world.MovableEntity;

@LeekEventHandler
public class Player extends MovableEntity implements KeyListener, CollisionListener {
	/* Make this serializable */
	private static final long serialVersionUID = 66916044368560750L;
	
	private final MiniGame mommy;
	
	private boolean[] keysPressed = {false, false, false, false};
	private BufferedImage[] images = new BufferedImage[4];
	private int currPos = 0;
	private float speed = 0.1f;
	private double closex;
	private double closey;
	private double tempx;
	private double tempy;
	int score = 0;
	int health = 3;
	
	/* constructor for Player */
	public Player(Level l, MiniGame parent) {
		super(l);
		
		mommy = parent;
		
		/* load all the images (up, down, left, right) */
		try {
			images[0] = ImageIO.read(new File("resources/img/player0.png"));
			images[3] = ImageIO.read(new File("resources/img/player1.png"));
			images[1] = ImageIO.read(new File("resources/img/player2.png"));
			images[2] = ImageIO.read(new File("resources/img/player3.png"));
			height = images[0].getHeight();
			width = images[0].getWidth();
			px = 30;
			py = 30;
			closex = 30;
			closey = 30;
		} catch (IOException e) {
			e.printStackTrace();
		}
		/* register events */
		EventHandlerRegister.registerEventHandlers(l, this);
	}
	
	/* update the Player */
	public void update(double time) {
		/* backup the old values in case we can't move here */
		tempx = closex;
		tempy = closey;
		/* check if any of the keys were pressed and
		 * change the current graphic to the right one
		 * and change the player position based on the speed */
		if (keysPressed[0]) {
			currPos = 0;
			closey -= time*speed;
		} else if (keysPressed[1]) {
			currPos = 1;
			closey += time*speed;
		} else if (keysPressed[2]) {
			currPos = 2;
			closex -= time*speed;
		} else if (keysPressed[3]) {
			currPos = 3;
			closex += time*speed;
		} else {
			return;
		}
		/* Check if the player can walk there */
		if (CollisionChecker.canMoveHere(parent, (int) Math.round(closex), (int) Math.round(closey), height, width)) {
			px = (int) Math.round(closex);
			py = (int) Math.round(closey);
		} else {
			closex = tempx;
			closey = tempy;
		}
	}
	
	/* render the player - return the image that corresponds
	 * to the right direction based on the array */
	public PositionedImage render() {
		return new PositionedImage(images[currPos], px, py, 100);
	}
	
	/* key pressed event
	 * check the up/down/left/right directions
	 * and set the direction based on that */
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
		case KeyEvent.VK_SHIFT:
			speed = 0.5f;
			break;
		default:
			return;
		}
	}
	
	/* key released event
	 * check the up/down/left/right directions
	 * and unset the direction based on that */
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
		case KeyEvent.VK_SHIFT:
			speed = 0.1f;
			break;
		default:
			return;
		}
	}
	/* uneeded for the player class */
	public void keyTyped(KeyEvent e) {}

	/* check if something has collided with something else */
	public void collidedWith(GameElement ge) {
		if (ge instanceof Crystal) {
			Crystal c = (Crystal)ge;
			c.collect();
			score += 1;
			mommy.updateScore(score);
		} else if (ge instanceof EvilCrystal) {
			EvilCrystal c = (EvilCrystal)ge;
			c.collect();
			mommy.mainMenu();
		}
	}
}
