package prototype;

import processing.core.PApplet;

public class Slider extends Part {
	
	private Part progress;
	private Part knob;
	private Part knobHighlight;
	
	public Slider(int width) {
		super(
			new PartBuilder(IMAGE).
			texture(DEFAULT_SLIDER_CONTAINER).
			scaleGrid(15, 0)
		);
		this.progress = this.part(
			new PartBuilder(IMAGE).
			texture(DEFAULT_SLIDER_PROGRESS).
			scaleGrid(15, 0).
			relY(0.5f).
			pivotY(0.5f)
		);
		this.knob = this.part(
			new PartBuilder(IMAGE).
			texture(DEFAULT_SLIDER_KNOB).
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
	}
	
	@Override
	public void partUpdated(PartUpdateEvent event) {
		switch(event.field) {
		case X:
			this.progress.width(this.knob.x());
			this.knobHighlight.alpha(  PApplet.map(this.knob.x(), this.knob.width(), this.knob.width()+15, 0, 1) );
			//println( round(map(event.part.getX(), event.part.getWidth(), progressBarW, 0, 100)) + "%");
			break;
		}
	}
}
