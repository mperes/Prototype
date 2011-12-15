package prototype;

public class ShapePart extends Part {
	
	int collisionType;
	
	public ShapePart (Skin blueprint, Behavior... behaviors) {
		super(blueprint, behaviors);
	}

	public ShapePart (Skin blueprint, float x, float y, Behavior... behaviors) {
		super(blueprint, x, y, behaviors);
	}	

	protected void initPart (Skin blueprint) {
		basicSetup(blueprint);
		this.getBlueprint().initBlueprint(Prototype.stage.g);
		this.calcBox();
	}

	public void drawPart() {
		Prototype.stage.pushMatrix();
		Prototype.stage.translate(-getWidth()*getPivotX(), -getHeight()*getPivotY());
		if(widthToScale() != 1 || heightToScale() != 1) {
			Prototype.stage.scale(widthToScale(), heightToScale());
		}
		this.getBlueprint().description();
		Prototype.stage.popMatrix();
	}
	
	public boolean mouseInside() {
		//updateLocalMouse();
		switch(getBlueprint().collisionMethod) {
		case Part.DEFAULT:
			return boxCollide(localMouseX(), localMouseY());
		case Part.BOX:
			return boxCollide(localMouseX(), localMouseY());
		case Part.CIRCLE:
			return circleCollide(localMouseX(), localMouseY());
		case Part.PIXEL:
			System.err.println("Pixel collision not implemeneted for ShapePart. Switching to Box Collision");
			return boxCollide(localMouseX(), localMouseY());
		default:
			System.err.println("Invalid Collision Method. Switching to Box Collision");
			return boxCollide(localMouseX(), localMouseY());
		}
	}
	
}
