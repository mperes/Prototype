package Prototype;

import processing.core.*;

public abstract class Blueprint {
	
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
	public Box scaleGrid;
	
	public PGraphics blueprint;
	
	public Blueprint(int initialWidth, int initialHeight) {
		this.initialWidth = initialWidth;
		this.initialHeight = initialHeight;
		blueprint = Prototype.stage.createGraphics(initialWidth, initialHeight, PConstants.JAVA2D);
	}
	
	final void initBlueprint() {
	//	setParent(Prototype.stage);
	//	setPrimary(false);
//		setPath(null);
//		setSize(initialWidth, initialHeight);
	}
	
	public abstract void description();
	
	public void partEvent(PartEvent event) {}
	
	void clearBlueprint() {
		blueprint.loadPixels();
		for(int p = 0; p < blueprint.pixels.length; p++) {
			blueprint.pixels[p] = 0x00000000;
		}
	}
	
}
