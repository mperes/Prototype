package Prototype;

import java.awt.event.MouseEvent;

import processing.core.PApplet;

public class RatioInt {

	PApplet stage;
	public int x;
	public int y;
	public int minX;
	public int maxX;
	public int minY;
	public int maxY;
	boolean capped = false;

	public RatioInt() {
		set(0, 0);
		stage = Prototype.stage;
		stage.registerPre(this);
		stage.registerMouseEvent(this);
		stage.registerDraw(this);
	}

	public RatioInt(int x, int y) {
		set(x, y);
		stage = Prototype.stage;
		stage.registerPre(this);
		stage.registerMouseEvent(this);
		stage.registerDraw(this);
	}

	public RatioInt(int x, int y, int minX, int maxX,  int minY, int maxY) {
		set(x, y);
		setMinMax(minX, maxX, minY, maxY);
		stage = Prototype.stage;
		stage.registerPre(this);
		stage.registerMouseEvent(this);
		stage.registerDraw(this);
	}

	public int getRatio() {
		return x / y;
	}

	public void set(int x, int y) {
		this.x = (!capped) ? x : PApplet.constrain(x, minX, maxX);
		this.y = (!capped) ? y : PApplet.constrain(y, minY, maxY);
	}

	public void set(int x, int y, int minX, int maxX,  int minY, int maxY) {
		capped = true;
		set(x, y);
		setMinMax(minX, maxX, minY, maxY);
	}

	public void setMinMax(int minX, int maxX, int minY, int maxY) {
		capped = true;
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
	}

	RatioInt get() {
		RatioInt copy;
		if(!capped) {
			copy = new RatioInt(x, y);
		} else {
			copy = new RatioInt(x, y, minX, maxX, minY, maxY);
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
		if(capped) {
			this.x = PApplet.constrain(this.x, minX, maxX);
			this.y = PApplet.constrain(this.y, minY, maxY);
		}
	}

	public void draw() {
		if(capped) {
			this.x = PApplet.constrain(this.x, minX, maxX);
			this.y = PApplet.constrain(this.y, minY, maxY);
		}
	}
}
