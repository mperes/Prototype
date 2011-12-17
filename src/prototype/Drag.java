package prototype;

import java.awt.event.MouseEvent;

public class Drag extends MouseOverBehavior {
	
	public Drag() { super(); }
	
	public void onDrag(MouseEvent e) {
		 this.parent.x(this.parent.x() + this.parent.localMouseX()-this.parent.plocalMouseX());
         this.parent.y(this.parent.y() + this.parent.localMouseY()-this.parent.plocalMouseY());
	}
	
}
