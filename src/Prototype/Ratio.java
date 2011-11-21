package Prototype;

import processing.core.PApplet;

public class Ratio {
	
	PApplet parent;
	public float x;
	public float y;
	private float minX = Float.MIN_VALUE  ;
	private float maxX = Float.MAX_VALUE ;
	private float minY = Float.MIN_VALUE ;
	private float maxY = Float.MAX_VALUE ;
	
	public Ratio() {
	}
	
	public Ratio(float x, float y) {
		set(x, y);
	}
	
	public Ratio(PApplet parent, float x, float y, float minX, float maxX,  float minY, float maxY) {
		this.parent = parent;
		setMixMax(minX, maxX, minY, maxY);
		set(x, y);
		parent.registerPre(this);
	}
	
	public float getRatio() {
		return x / y;
	}
	
	public void set(float x, float y) {
		this.x = PApplet.constrain(x, minX, maxX);
		this.y = PApplet.constrain(y, minY, maxY);
	}
	
	public void setMixMax(float minX, float maxX, float minY, float maxY) {
		if(parent != null) {
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
	
	void pre() {
		this.x = PApplet.constrain(this.x, minX, maxX);
		this.y = PApplet.constrain(this.y, minY, maxY);
	}
}
