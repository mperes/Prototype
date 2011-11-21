package Prototype;

import codeanticode.glgraphics.GLGraphicsOffScreen;

public abstract class Blueprint extends GLGraphicsOffScreen {
	
	public Ratio size = new Ratio(0, 0);
	public Ratio pos = new Ratio(0, 0);
	public Ratio rel = new Ratio(0, 0);
	public Ratio scale = new Ratio(1, 1);
	public Ratio pivot = new Ratio(0, 0, 0, 1, 0, 1);
	public boolean visible = true;
	public boolean enabled = true;
	public float alpha = 1;
	public boolean showPivot = false;
	
	public Blueprint(int width, int height) {
		super(Prototype.stage, width, height, true);
		size.set(width, height);
	}
	
	public abstract void description();
	
	public void partEvent(PartEvent event) {}
	
	void clearBlueprint() {
		this.loadPixels();
		for (int p = 0; p < this.pixels.length; p++) {
			this.pixels[p] = 0x00000000;
		}
		
	}
	
}
