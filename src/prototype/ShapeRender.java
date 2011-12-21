package prototype;

public abstract class ShapeRender {
	
	Part parent;
	
	abstract public void draw();
	
	final public void fill(int color) {
		Prototype.stage.fill(color, 255*parent.alphaStack());
	}
	
	final public void stroke(int color) {
		Prototype.stage.stroke(color, 255*parent.alphaStack());
	}
	
}
