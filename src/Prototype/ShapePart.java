package prototype;

public class ShapePart extends BasicPart implements Part {
	
	int collisionType;
	
	public ShapePart (Blueprint blueprint) {
		super(blueprint);
	}

	public ShapePart (Blueprint blueprint, float x, float y) {
		super(blueprint, x, y);
	}	

	protected void initPart (Blueprint blueprint) {
		basicSetup(blueprint);
		this.getBlueprint().initBlueprint(Prototype.stage.g);
		this.calcBox();
	}

	public void drawPart() {
		boolean scaled = false;
		if(widthToScale() != 1 || heightToScale() != 1) {
			Prototype.stage.pushMatrix();
			Prototype.stage.scale(widthToScale(), heightToScale());
			scaled = true;
		}
		this.getBlueprint().description();
		if(scaled) { Prototype.stage.popMatrix(); }
	}
	
	public boolean mouseInside() {
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
