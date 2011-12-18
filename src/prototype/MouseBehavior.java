package prototype;

import java.awt.event.MouseEvent;

public abstract class MouseBehavior implements Behavior, PrototypeConstants {
	
	protected Part parent;
	boolean mouseOver;
	boolean lastState;
	
	public MouseBehavior() {
		mouseOver = false;
		lastState = false;
	}
	
	public final void initBehavior(Part parent) {
		this.parent = parent;
	}

	public final void mouseEvent(MouseEvent event) {
		lastState = mouseOver;
		mouseOver = parent.mouseInside();
		switch (event.getID()) {
		case MouseEvent.MOUSE_PRESSED:
			mousePressed(event);
			break;
		case MouseEvent.MOUSE_RELEASED:
			mouseReleased(event);
			break;
		case MouseEvent.MOUSE_CLICKED:
			mouseClicked(event);
			break;
		case MouseEvent.MOUSE_DRAGGED:
			mouseDragged(event);
			break;
		case MouseEvent.MOUSE_MOVED:
			mouseMoved(event);
			break;
		}
	}
	
	public int type() {
		return MOUSE;
	}
	
	public void mousePressed(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) { }
	public void mouseClicked(MouseEvent e) { }
	public void mouseDragged(MouseEvent e) { }
	public void mouseMoved(MouseEvent e) { }	
}
