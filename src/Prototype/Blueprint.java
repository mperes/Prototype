package Prototype;

import processing.core.PApplet;
import processing.core.PGraphics2D;

public abstract class Blueprint extends PGraphics2D {
	
	final int initialWidth;
	final int initialHeight;
	
	public Ratio pos = new Ratio(0, 0);
	public Ratio rel = new Ratio(0, 0);
	public Ratio scale = new Ratio(1, 1);
	public Ratio pivot = new Ratio(0, 0, 0, 1, 0, 1);
	public boolean visible = true;
	public boolean enabled = true;
	public float alpha = 1;
	public boolean showPivot = false;
	
	public Blueprint(int initialWidth, int initialHeight) {
		this.initialWidth = initialWidth;
		this.initialHeight = initialHeight;
	}
	
	final void initBlueprint(PApplet parent) {
		setParent(parent);
		setPrimary(false);
		setPath(null);
		setSize(initialWidth, initialHeight);
	}
	
	public abstract void description();
	
	public void partEvent(PartEvent event) {}
	
}
