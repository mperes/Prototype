package prototype;

public abstract class ShapeRender {
	
	Part parent;
	
	abstract void draw();
	
	final void fill(int color) {
		Prototype.stage.fill(color, parent.alphaStack());
	}
	
	final void stroke(int color) {
		Prototype.stage.stroke(color, parent.alphaStack());
	}
	
}
