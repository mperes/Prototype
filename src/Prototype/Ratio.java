package Prototype;

import processing.core.PApplet;

public class Ratio {
	
	private float x;
	private float y;
	private float minX = Float.MIN_VALUE  ;
	private float maxX = Float.MAX_VALUE ;
	private float minY = Float.MIN_VALUE ;
	private float maxY = Float.MAX_VALUE ;
	
	public Ratio() {
	}
	
	public Ratio(float x, float y) {
		set(x, y);
	}
	
	public Ratio(float x, float y, float minX, float maxX,  float minY, float maxY) {
		setMixMax(minX, maxX, minY, maxY);
		set(x, y);
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getRatio() {
		return x / y;
	}
	
	public void set(float x, float y) {
		this.x = PApplet.constrain(x, minX, maxX);
		this.y = PApplet.constrain(y, minY, maxY);
	}
	
	public void setMixMax(float minX, float maxX, float minY, float maxY) {
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
	}
	
	Ratio get() {
		Ratio copy = new Ratio(getX(), getY());
		return copy;
	}
}
