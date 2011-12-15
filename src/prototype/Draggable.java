package prototype;

import java.awt.event.MouseEvent;

public class Draggable extends MouseOverBehavior {
	
	public Draggable() { super(); }
	
	public void onDrag(MouseEvent e) {
		 parent.setX(parent.getX() + parent.localMouseX()-parent.plocalMouseX());
         parent.setY(parent.getY() + parent.localMouseY()-parent.plocalMouseY());
	}
	
}
