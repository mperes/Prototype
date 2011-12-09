package Prototype;

import processing.core.PGraphics;

public abstract class Blueprint {
	
	public int type = Part.IMAGE;
	public PGraphics blueprint;
	
	public int x;
	public int y;
	public float relX;
	public float relY;
	public int width;
	public int height;
	public float scaleX;
	public float scaleY;
	public float pivotX;
	public float pivotY;
	public float rotation;
	public float alpha;
	
	public boolean visible;
	public boolean enabled;
	public boolean showPivot;
	public boolean unique ;
	
	public Box scaleGrid;
	
	public Blueprint() {
		type = Part.IMAGE;
		scaleX = 1;
		scaleY = 1;
		alpha = 1;	
		visible = true;
		enabled = true;
		showPivot = false;
		unique = false;
	}
	
	final void initBlueprint(PGraphics canvas) {
		blueprint = canvas;//Prototype.offScreenBuffer; 
	}
	
	public abstract void description();
	
	public void partEvent(PartEvent event) {}
	
	
}
