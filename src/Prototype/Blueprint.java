package prototype;

import processing.core.PGraphics;

public abstract class Blueprint {
	
	public int type = Part.IMAGE;
	public int collisionMethod;
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
	public boolean unique;
	public boolean interacble;
	
	public Box scaleGrid;
	
	public Blueprint() {
		type = Part.IMAGE;
		collisionMethod = Part.DEFAULT;
		scaleX = 1;
		scaleY = 1;
		alpha = 1;	
		visible = true;
		enabled = true;
		showPivot = false;
		unique = false;
		interacble = true;
	}
	
	final void initBlueprint(PGraphics canvas) {
		blueprint = canvas;
	}
	
	public abstract void description();
	
	public void partEvent(PartEvent event) {}
	
	
}
