package prototype.widgets;

import processing.core.PApplet;
import prototype.Part;
import prototype.PartBuilder;
import prototype.PartUpdateEvent;
import prototype.PrototypeConstants;
import prototype.behaviors.mouse.Drag;
import prototype.behaviors.mouse.Hover;

public class Slider extends Part implements PrototypeConstants, WidgetsConstants {
	
	private Part progress;
	private Part knob;
	private Part knobHighlight;
	private float min;
	private float max;
	
	public Slider(float min, float max, int width) {
		super(
			new PartBuilder(IMAGE).
			states(DEFAULT_SLIDER_CONTAINER).
			scaleGrid(15, 0).
			behaviors(new Hover())
		);
		this.progress = this.part(
			new PartBuilder(IMAGE).
			states(DEFAULT_SLIDER_PROGRESS, DEFAULT_SLIDER_PROGRESS_HOVER).
			scaleGrid(15, 0).
			relY(0.5f).
			pivotY(0.5f)
		);
		this.knob = this.part(
			new PartBuilder(IMAGE).
			states(DEFAULT_SLIDER_KNOB).
			pivotX(1).
			behaviors(new Drag())
		);
		this.knobHighlight = this.knob.part(DEFAULT_SLIDER_KNOB_HIGHLIGHT);
		this.width(width);
		//this.height(height);

		this.knobHighlight.alpha(0);
		this.knob.x.constrain(this.knob.width(), width);
		this.knob.y.constrain(0);
		this.progress.width.constrain(0, width);
		this.progress.width(this.knob.x());
		
		this.knob.addListener(this);
		
		this.min = min;
		this.max = max;
		//this.value(min);
	}
	
	@Override
	public void partUpdated(PartUpdateEvent event) {
		if(event.part == knob) {
			switch(event.field) {
			case X:
				this.progress.width(this.knob.x());
				this.knobHighlight.alpha(  PApplet.map(this.knob.x(), this.knob.width(), this.knob.width()+15, 0, 1) );
				this.value(PApplet.map(this.progress.width(), this.knob.width(), this.width(), 0, 100));
				break;
			}
		} else if (event.part == this) {
			switch(event.field) {
			case STATE:
				this.progress.state(this.state());
				this.knobHighlight.visible(this.state() == 1);
				break;
			}
		}
	}
	
	public float min() { return this.min; }
	public void min(float value) { this.min = value; }
	public float max() { return this.max; }
	public void max(float value) { this.max = value; }
}
