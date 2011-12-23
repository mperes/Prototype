package prototype.behaviors.mouse;

import java.awt.event.MouseEvent;
import prototype.States;

public class PushButton extends MouseOverBehavior {
	public PushButton() {
		super();
	}
	
	public void onPress(MouseEvent e) {
		this.parent.state(States.PRESSED);
	}
	public void onRelease(MouseEvent e) {
		this.parent.state(States.HOVER);	
	}
	public void onReleaseOutside(MouseEvent e) {
		this.parent.state(States.DEFAULT);
	}
	public void onRollOver(MouseEvent e) {
		this.parent.state(States.HOVER);
	}
	public void onRollOut(MouseEvent e) {
		this.parent.state(States.DEFAULT);
		
	}
}
