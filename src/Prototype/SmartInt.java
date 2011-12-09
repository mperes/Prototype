package Prototype;

import processing.core.PApplet;

public class SmartInt implements SmartNumber {

	private int value;
	private int min;
	private int max;
	private boolean constrain;
	
	public SmartInt() {
		this.constrain = false;
	}
	
	public SmartInt(int value) {
		this.value = value;
		this.constrain = false;
	}
	
	public SmartInt(int value, int min, int max) {
		this.value = value;
		this.min = min;
		this.max = max;
		this.constrain = true;
	}
	
	public int value() {
		return (constrain) ? PApplet.constrain(this.value, this.min, this.max) : this.value;
	}
	
	public void value(int value) {
		System.out.println(constrain);
		this.value = (constrain) ? PApplet.constrain(value, this.min, this.max) : value;
	}
	
	public void value(float value) {
		int roundedValue = PApplet.round(value);
		this.value = (constrain) ? PApplet.constrain(roundedValue, this.min, this.max) : roundedValue;
	}
	
	public boolean constrain() {
		return this.constrain;
	}
	
	public void constrain(boolean state) {
		this.constrain = state;
	}
	
	public void constrain(int min, int max) {
		this.constrain = true;
		this.min = min;
		this.max = max;
	}
	
	public void constrain(float min, float max) {
		this.constrain = true;
		this.min = PApplet.round(min);
		this.max = PApplet.round(max);
	}

}
