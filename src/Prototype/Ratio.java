package Prototype;

import java.awt.event.MouseEvent;

import processing.core.PApplet;

public class Ratio {

	PApplet stage;
	public float x;
	public float y;
	public float minX;
	public float maxX;
	public float minY;
	public float maxY;
	boolean capped = false;

	public Ratio() {
	}

	public Ratio(float x, float y) {
		set(x, y);
		stage = Prototype.stage;
		stage.registerPre(this);
	}

	public Ratio(float x, float y, float minX, float maxX,  float minY, float maxY) {
		set(x, y);
		setMinMax(minX, maxX, minY, maxY);
		stage = Prototype.stage;
		stage.registerPre(this);
		stage.registerMouseEvent(this);
	}

	public float getRatio() {
		return x / y;
	}

	public void set(float x, float y) {
		this.x = (!capped) ? x : PApplet.constrain(x, minX, maxX);
		this.y = (!capped) ? y : PApplet.constrain(y, minY, maxY);
	}

	public void set(float x, float y, float minX, float maxX,  float minY, float maxY) {
		capped = true;
		set(x, y);
		setMinMax(minX, maxX, minY, maxY);
	}

	public void setMinMax(float minX, float maxX, float minY, float maxY) {
		capped = true;
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
	}

	Ratio get() {
		Ratio copy;
		if(!capped) {
			copy = new Ratio(x, y);
		} else {
			copy = new Ratio(x, y, minX, maxX, minY, maxY);
		}
		return copy;
	}

	public void pre() {
		if(capped) {
			this.x = PApplet.constrain(this.x, minX, maxX);
			this.y = PApplet.constrain(this.y, minY, maxY);
		}
	}
	
	public void mouseEvent(MouseEvent e) {
		this.x = PApplet.constrain(this.x, minX, maxX);
		this.y = PApplet.constrain(this.y, minY, maxY);
	}
}
