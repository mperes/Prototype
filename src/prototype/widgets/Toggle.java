package prototype.widgets;

import processing.core.PApplet;
import prototype.Part;
import prototype.PartBuilder;
import prototype.PartUpdateEvent;
import prototype.PrototypeConstants;
import prototype.behaviors.mouse.Drag;

public class Toggle extends Part implements PrototypeConstants, WidgetsConstants {
	
	private Part check;
	private float min;
	private float max;
	private boolean value;
	
	public Toggle(String caption) {
		super((DEFAULT_TOGGLE_CONTAINER));
		this.check = this.part(DEFAULT_TOGGLE_CHECK_HOVER);
		
		check.visible(false);
		//this.knob.addListener(this);
		
		this.value = false;
	}
	
	@Override
	public void partUpdated(PartUpdateEvent event) {
		switch(event.field) {
		case X:
//			this.progress.width(this.knob.x());
//			this.knobHighlight.alpha(  PApplet.map(this.knob.x(), this.knob.width(), this.knob.width()+15, 0, 1) );
//			this.value = PApplet.map(this.progress.width(), this.knob.width(), this.width(), 0, 100);
			break;
		}
	}
	
	public float min() { return this.min; }
	public void min(float value) { this.min = value; }
	public float max() { return this.max; }
	public void max(float value) { this.max = value; }
}
