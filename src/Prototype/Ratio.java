package Prototype;

import processing.core.PApplet;

public class Ratio {
	
	PApplet stage;
	public float x;
	public float y;
	private float minX;
	private float maxX;
	private float minY;
	private float maxY;
	
	public Ratio() {
	}
	
	public Ratio(float x, float y) {
		set(x, y);
	}
	
	public Ratio(float x, float y, float minX, float maxX,  float minY, float maxY) {
		stage = Prototype.stage;
		setMixMax(minX, maxX, minY, maxY);
		set(x, y);
		stage.registerPre(this);
	}
	
	public float getRatio() {
		return x / y;
	}
	
	public void set(float x, float y) {
		this.x = (stage == null) ? x : PApplet.constrain(x, minX, maxX);
		this.y = (stage == null) ? y : PApplet.constrain(y, minY, maxY);
	}
	
	public void setMixMax(float minX, float maxX, float minY, float maxY) {
		if(stage != null) {
			this.minX = minX;
			this.maxX = maxX;
			this.minY = minY;
			this.maxY = maxY;
		} else {
			System.err.println("It is not possible to change the Min and Max values, in this Ratio Instance.");
		}
	}
	
	Ratio get() {
		Ratio copy = new Ratio(x, y);
		return copy;
	}
	
	public void pre() {
		this.x = PApplet.constrain(this.x, minX, maxX);
		this.y = PApplet.constrain(this.y, minY, maxY);
	}
}
