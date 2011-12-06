package Prototype;

import processing.core.PGraphics;

public abstract class Blueprint {
	
	public RatioInt size = new RatioInt(0, 0);
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
	public boolean makeUnique = false;
	
	public Blueprint() {
	}
	
	final void initBlueprint() {
		blueprint = Prototype.offScreenBuffer; 
	}
	
	public abstract void description();
	
	public void partEvent(PartEvent event) {}
	
	
}
