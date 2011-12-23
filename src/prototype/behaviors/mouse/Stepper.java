package prototype.behaviors.mouse;

import java.awt.event.MouseEvent;

public class Stepper extends MouseOverBehavior {
	public Stepper() { super(); }
	
	public void onClick(MouseEvent e) {
		 this.parent.value(this.parent.value().asInt()+1);
	}
}
