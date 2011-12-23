package prototype.behaviors.mouse;

import java.awt.event.MouseEvent;
import prototype.States;

public class Hover extends MouseOverBehavior {
	public Hover() {
		super();
	}
	
	public void onRollOver(MouseEvent e) {
		this.parent.state(States.HOVER);
	}
	public void onRollOut(MouseEvent e) {
		this.parent.state(States.DEFAULT);
		
	}
}
