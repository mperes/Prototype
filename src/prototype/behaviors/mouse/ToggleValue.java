package prototype.behaviors.mouse;

import java.awt.event.MouseEvent;

public class ToggleValue extends MouseOverBehavior {
	public ToggleValue() { super(); }
	
	public void onClick(MouseEvent e) {
		 this.parent.value(!this.parent.value().asBol());
	}
}
