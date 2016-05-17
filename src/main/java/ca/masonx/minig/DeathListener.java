package ca.masonx.minig;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ca.masonx.leek.core.annotations.LeekEventHandler;
import ca.masonx.leek.core.events.EventHandlerRegister;
import ca.masonx.leek.core.render.PositionedImage;
import ca.masonx.leek.core.world.Entity;
import ca.masonx.leek.core.world.Level;

@LeekEventHandler
public class DeathListener extends Entity implements MouseListener, KeyListener {
	private static final long serialVersionUID = -3723108208280782367L;

	private final MiniGame daddy;

	public DeathListener(Level parent, MiniGame daddy) {
		super(parent);
		this.daddy = daddy;
		EventHandlerRegister.registerEventHandlers(parent, this);
	}
	
	public void mouseClicked(MouseEvent me) {
		daddy.mainLevel();
	}

	public void mouseEntered(MouseEvent arg0) {}

	public void mouseExited(MouseEvent arg0) {}

	public void mousePressed(MouseEvent arg0) {}

	public void mouseReleased(MouseEvent arg0) {}

	public PositionedImage render() {
		return null;
	}

	public void keyPressed(KeyEvent arg0) {daddy.mainLevel();}

	public void keyReleased(KeyEvent arg0) {}

	public void keyTyped(KeyEvent arg0) {}
}
