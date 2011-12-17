package prototype;

import processing.core.PApplet;

public class SmartFloat implements SmartNumber {

	private float value;
	private float min;
	private float max;
	private boolean constrain;
	
	public SmartFloat() {
		this.value = 0;
		this.min = 0;
		this.max = 0;
		this.constrain = false;
	}
	
	public SmartFloat(float value) {
		this.value = value;
		this.min = 0;
		this.max = 0;
		this.constrain = false;
	}
	
	public SmartFloat(float value, float min, float max) {
		this.value = value;
		this.min = min;
		this.max = max;
		this.constrain = true;
	}
	
	public float value() {
		return (constrain) ? PApplet.constrain(this.value, this.min, this.max) : this.value;
	}
	
	public void value(float value) {
		this.value = (constrain) ? PApplet.constrain(value, this.min, this.max) : value;
	}
	
	public boolean constrain() {
		return this.constrain;
	}
	
	public void constrain(boolean state) {
		this.constrain = state;
	}
	
	public void constrain(float min, float max) {
		this.constrain = true;
		this.min = min;
		this.max = max;
	}
	
	public void constrain(float value) {
		this.constrain = true;
		this.min = value;
		this.max = value;
	}

}
