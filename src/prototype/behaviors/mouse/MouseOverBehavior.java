package prototype.behaviors.mouse;

import java.awt.event.MouseEvent;

public class MouseOverBehavior extends MouseBehavior {
	
	boolean lastState;
	boolean dragging;
	boolean pressed;
	
	public MouseOverBehavior() {
		super();
		dragging = false;
		pressed = false;
		
	}
	
	public void mousePressed(MouseEvent e) {
		if(mouseOver) { onPress(e); pressed = true; }
	}
	public void mouseReleased(MouseEvent e) {
		if(mouseOver) { onRelease(e); }
		else { onReleaseOutside(e); }
		if(dragging) {
			onStopDragging(e);
			dragging = false;	
		}
		pressed = false;
	}
	public void mouseClicked(MouseEvent e) {
		if(mouseOver) { onClick(e); }
	}
	public void mouseDragged(MouseEvent e) {
		if(pressed) {
			if(mouseOver || dragging) {
				if(!dragging) { onStartDragging(e); }
				dragging = true;
				onDrag(e);
			}
		}
	}
	public void mouseMoved(MouseEvent e) {
		if(mouseOver) { onRollOver(e); lastState = true; }
		else if(!mouseOver && lastState) { onRollOut(e); lastState = false; }
	}
	
	public void onPress(MouseEvent e) {}
	public void onRelease(MouseEvent e) {}
	public void onReleaseOutside(MouseEvent e) {}
	public void onClick(MouseEvent e) {}
	public void onStartDragging(MouseEvent e) {}
	public void onDrag(MouseEvent e) {}
	public void onStopDragging(MouseEvent e) {}
	public void onRollOver(MouseEvent e) {}
	public void onRollOut(MouseEvent e) {}
	
}
