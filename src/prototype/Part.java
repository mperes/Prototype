package prototype;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PMatrix3D;

public class Part implements PrototypeConstants, PartListener {	
	private Part parent;
	ArrayList<Part> parts;
	Map<String, Behavior> behaviors;
	ArrayList<PartListener> listeners;
	private final int type;

	private int initialWidth;
	private int initialHeight;
	
	//TODO (Change to protected)
	public SmartInt width;
	public SmartInt height;
	public SmartInt x;
	public SmartInt y;
	public SmartFloat relX;
	public SmartFloat relY;
	public SmartFloat scaleX;
	public SmartFloat scaleY;
	public SmartFloat pivotX;
	public SmartFloat pivotY;
	public SmartFloat rotation;
	public SmartFloat alpha;

	private Box boundingBox;
	private Box scaleGrid;
	private float pivotModelX;
	private float pivotModelY;
	private int collisionMethod;

	private boolean visible;
	private boolean enabled;
	private boolean showPivot;

	private float[] localMouse;
	protected PMatrix3D localModel;

	//Belongs to ImagePart only
	public PImage texture;
	private DynamicImage dynamicTexture;
	private float[][][] faces;
	
	//TODO
	//boolean passThrough;
	
	//Properties, used for listeners. Change here if you want to extend Part.
	public enum Field
	{
		WIDTH, HEIGHT, X, Y, RELX, RELY, SCALEX,
		SCALEY,PIVOTX, PIVOTY, ROTATION, ALPHA,
		VISIBLE, ENABLED, SHOWPIVOT, NOVALUE;  
	}

	//Image Part
	public Part (String texturePath, Behavior... behaviors) {
		this.type = IMAGE;
		this.texture = Prototype.loadTexture(texturePath);
		this.initWithDefaultValues();
		this.width(this.texture.width);
		this.height(this.texture.height);
		this.initialWidth = this.width();
		this.initialHeight = this.height();
		for(Behavior b : behaviors) {
			if(!this.behaviors.containsKey(Utils.className(b))) {
				b.initBehavior(this);
				this.behaviors.put(Utils.className(b), b);
			} else {
				System.err.println("The behavior "+ Utils.className(b) +" was already added to this part.");
			}

		}
	}

	//Shape Part
	public Part (int width, int height, DynamicImage imageRecipe, Behavior... behaviors) {
		this.type = SHAPE;
		this.dynamicTexture = imageRecipe;
		this.initWithDefaultValues();
		this.width(width);
		this.height(height);
		this.initialWidth = this.width();
		this.initialHeight = this.height();
		for(Behavior b : behaviors) {
			if(!this.behaviors.containsKey(Utils.className(b))) {
				b.initBehavior(this);
				this.behaviors.put(Utils.className(b), b);
			} else {
				System.err.println("The behavior "+ Utils.className(b) +" was already added to this part.");
			}

		}
	}

	public Part(PartBuilder builder) {
		this.type = builder.type();
		initWithBuilderValues(builder);
		//Error checking & individual part setup
		switch (this.type) {
		case SHAPE:
			if(builder.width() == 0 || builder.height() == 0 || builder.dynamicTexture() == null) {
				throw new RuntimeException( "Invalid shape part.");
			}
			this.dynamicTexture = builder.dynamicTexture();
			break;
		case TEXT:
			break;
		case IMAGE:
			if(builder.texture() == "") {
				throw new RuntimeException( "Invalid shape part.");
			}
			if(builder.scaleGrid() != null) {
				this.scaleGrid = builder.scaleGrid();
				this.faces = new float[3][3][8];
			}
			this.texture = Prototype.loadTexture(builder.texture());
			this.width(this.texture.width);
			this.height(this.texture.height);
			this.initialWidth = this.width();
			this.initialHeight = this.height();
			break;
		}
	}

	//Image child part
	public Part part(String texturePath, Behavior... behaviors) {
		Part newPart = new Part(texturePath, behaviors);
		newPart.parent(this);
		this.parts.add(newPart);
		return newPart;
	}

	//Shape child part
	public Part part(int width, int height, DynamicImage imageRecipe, Behavior... behaviors) {
		Part newPart = new Part(width, height, imageRecipe, behaviors);
		newPart.parent(this);
		this.parts.add(newPart);
		return newPart;
	}

	//Shape child part
	public Part part(PartBuilder builder) {
		Part newPart = new Part(builder);
		newPart.parent(this);
		this.parts.add(newPart);
		return newPart;
	}

	//Pre-Built Part
	public void part(Part newPart) {
		newPart.parent(this);
		parts.add(newPart);
	}

	public void initWithBuilderValues(PartBuilder builder) {
		this.localMouse = new float[] {0, 0, 0, 0};
		this.localModel = new PMatrix3D();
		this.parts = new ArrayList<Part>();
		listeners = new ArrayList<PartListener>();
		this.boundingBox = new Box();

		this.behaviors = builder.behaviors();
		initBuilderBehaviors();

		this.width = new SmartInt(builder.width());
		this.height = new SmartInt(builder.height());
		this.initialWidth = this.width();
		this.initialHeight = this.height();

		this.x = new SmartInt(builder.x());
		this.y = new SmartInt(builder.y());
		this.relX = new SmartFloat(builder.relX(), 0, 1);
		this.relY = new SmartFloat(builder.relY(), 0, 1);
		this.scaleX = new SmartFloat(builder.scaleX());
		this.scaleY = new SmartFloat(builder.scaleY());
		this.pivotX = new SmartFloat(builder.pivotX(), 0, 1);
		this.pivotY = new SmartFloat(builder.pivotY(), 0, 1);
		this.rotation = new SmartFloat(builder.rotation());
		this.alpha = new SmartFloat(builder.alpha(), 0, 1);

		this.visible(builder.visible());
		this.enabled(builder.enabled());
		this.showPivot(builder.showPivot());

		this.collisionMethod = builder.collisionMethod();	

		this.behaviors = builder.behaviors();
	}

	private void initBuilderBehaviors() {
		for (Behavior behavior : behaviors.values()) {
			behavior.initBehavior(this);
		}
	}

	protected void initWithDefaultValues() {
		this.localMouse = new float[] {0, 0, 0, 0};
		this.localModel = new PMatrix3D();
		this.parts = new ArrayList<Part>();
		listeners = new ArrayList<PartListener>();
		this.behaviors = new HashMap<String, Behavior>();
		this.boundingBox = new Box();

		this.width = new SmartInt();
		this.height = new SmartInt();

		this.x = new SmartInt();
		this.y = new SmartInt();
		this.relX = new SmartFloat(0, 0, 1);
		this.relY = new SmartFloat(0, 0, 1);
		this.scaleX = new SmartFloat(1);
		this.scaleY = new SmartFloat(1);
		this.pivotX = new SmartFloat(0, 0, 1);
		this.pivotY = new SmartFloat(0, 0, 1);
		this.rotation = new SmartFloat();
		this.alpha = new SmartFloat(1, 0, 1);

		this.visible(true);
		this.enabled(true);
		this.showPivot(false);

		this.collisionMethod = BOX;
	}

	private void calcBox() {
		this.boundingBox.left = -this.pivotX() * this.width();
		this.boundingBox.top = -this.pivotY() * this.height();
		this.boundingBox.right =  this.boundingBox.left + this.width();
		this.boundingBox.bottom = this.boundingBox.top + this.height();
	}

	private void worldToLocal() {
		float translateX = (this.parent == null) ? this.x() : (int)(this.x() + (this.parent.width() * this.relX() + this.parent.left()));
		float translateY = (this.parent == null) ? this.y() : (int)(this.y() + (this.parent.height() * this.relY() + this.parent.top()));

		Prototype.stage.pushMatrix();
		Prototype.stage.translate(translateX, translateY);
		if(this.rotation() != 0) {
			Prototype.stage.rotate(PApplet.radians(this.rotation()));
		}
		if(this.scaleX() != 1 || this.scaleY() != 1) {
			Prototype.stage.scale(this.scaleX(), this.scaleY());
		}
	}

	private void localToWorld() {
		Prototype.stage.popMatrix();
	}

	private void updateLocalModel() {
		this.localModel = Utils.getCurrentModel().get();
		if(this.showPivot()) {
			pivotModelX = Prototype.stage.modelX(boundingBox.left + pivotX() * width(), boundingBox.top + pivotY() * height(), 0);
			pivotModelY = Prototype.stage.modelY(boundingBox.left + pivotX() * width(), boundingBox.top + pivotY() * height(), 0);
		}
	}

	public void draw() {
		if(visible) {
			this.worldToLocal();
			this.updateLocalModel();
			Prototype.stage.pushStyle();
			switch(this.type) {
			case IMAGE:
				drawImage();
				break;
			case SHAPE:
				drawShape();
				break;
			case TEXT:
				break;
			}
			Prototype.stage.popStyle();
			this.drawChildren();
			this.localToWorld();
		}
	}

	private void drawImage() {
		Prototype.stage.tint(255, 255*alpha());
		if(this.scaleGrid != null) {
			scale9Grid(this.initialWidth, this.initialHeight, this.pivotX(), this.pivotY(), this.scaleGrid, this.texture);
		} else {
			drawPlane(this.initialWidth, this.initialHeight, this.pivotX(), this.pivotY(), this.texture);
		}
	}

	private void drawShape() {
		Prototype.stage.pushMatrix();
		Prototype.stage.translate(-this.width()*this.pivotX(), -this.height()*this.pivotY());
		if(this.widthToScale() != 1 || this.heightToScale() != 1) {
			Prototype.stage.scale(this.widthToScale(), this.heightToScale());
		}
		dynamicTexture.draw();
		Prototype.stage.popMatrix();
	}

	@SuppressWarnings("deprecation")
	void drawPlane(float width, float height, float pivotX, float pivotY, PImage texture) {
		Prototype.stage.pushMatrix();
		if(widthToScale() != 1 || heightToScale() != 1) {
			Prototype.stage.scale(widthToScale(), heightToScale());
		}
		Prototype.stage.pushStyle();
		Prototype.stage.textureMode(PConstants.NORMALIZED);
		Prototype.stage.noStroke();
		Prototype.stage.beginShape(PConstants.QUADS);
		Prototype.stage.texture(texture);
		Prototype.stage.vertex(-width*pivotX, -height*pivotY, 0, 0);
		Prototype.stage.vertex(width*(1-pivotX), -height*pivotY, 1, 0);
		Prototype.stage.vertex(width*(1-pivotX), height*(1-pivotY), 1, 1);
		Prototype.stage.vertex(-width*pivotX, height*(1-pivotY), 0, 1);
		Prototype.stage.endShape();
		Prototype.stage.popStyle();
		Prototype.stage.popMatrix();
	}

	void scale9Grid(int width, int height, float pivotX, float pivotY, Box box, PImage texture) {
		Prototype.stage.pushMatrix();
		Prototype.stage.translate(-width*pivotX, -height*pivotY);
		if(this.widthToScale() != 1 || this.heightToScale() != 1) {
			Prototype.stage.scale(this.widthToScale(), this.heightToScale());
		}
		faces[0] = new float[][] {
				{ 0, 0, box.left/widthToScale(), box.top/heightToScale(), 0, 0, box.left, box.top }, 
				{ box.left/widthToScale(), 0, width-box.right/widthToScale(), box.top/heightToScale(), box.left, 0, texture.width-box.right, box.top },
				{ width-box.right/widthToScale(), 0, width, box.top/heightToScale(), texture.width-box.right, 0, texture.width, box.top } 
		};
		faces[1] = new float[][]{ 
				{ faces[0][0][0], box.top/heightToScale(), faces[0][0][2], height-box.bottom/heightToScale(), faces[0][0][4], box.top+1, faces[0][0][6],  texture.height-box.bottom-1 }, 
				{ faces[0][1][0], box.top/heightToScale(), faces[0][1][2], height-box.bottom/heightToScale(), faces[0][1][4], box.top+1, faces[0][1][6],  texture.height-box.bottom-1 },
				{ faces[0][2][0], box.top/heightToScale(), faces[0][2][2], height-box.bottom/heightToScale(), faces[0][2][4], box.top+1, faces[0][2][6],  texture.height-box.bottom-1 } 
		};
		faces[2] = new float[][] {
				{ faces[0][0][0], height-box.bottom/heightToScale(), faces[0][0][2], height, faces[0][0][4], texture.height-box.bottom, faces[0][0][6],  texture.height},
				{ faces[0][1][0], height-box.bottom/heightToScale(), faces[0][1][2], height, faces[0][1][4], texture.height-box.bottom, faces[0][1][6],  texture.height},
				{ faces[0][2][0], height-box.bottom/heightToScale(), faces[0][2][2], height, faces[0][2][4], texture.height-box.bottom, faces[0][2][6],  texture.height}
		};		
		Prototype.stage.pushStyle();
		Prototype.stage.noStroke();
		Prototype.stage.textureMode(PConstants.IMAGE);
		Prototype.stage.beginShape(PConstants.QUADS);
		Prototype.stage.texture(texture);
		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3; col++) {
				rectShape(texture, faces[row][col][0], faces[row][col][1], faces[row][col][2], faces[row][col][3], faces[row][col][4], faces[row][col][5], faces[row][col][6], faces[row][col][7]);
			}
		}
		Prototype.stage.endShape();
		Prototype.stage.popStyle();
		Prototype.stage.popMatrix();
	}

	private void rectShape(PImage texture, float x1, float y1, float x2, float y2, float tx1, float ty1, float tx2, float ty2) {
		Prototype.stage.vertex(x1, y1, tx1, ty1);
		Prototype.stage.vertex(x2, y1, tx2, ty1);
		Prototype.stage.vertex(x2, y2, tx2, ty2);
		Prototype.stage.vertex(x1, y2, tx1, ty2);
	}

	void drawChildren() {
		for(int p=0; p < parts.size(); p++) {
			Part part = parts.get(p);
			part.draw();
		}
	}

	public void drawPivot() {
		if(visible()) {
			if(showPivot()  && pivotModelX >= 0 && pivotModelX <= Prototype.stage.width &&
				pivotModelY >= 0 && pivotModelY <= Prototype.stage.height) {
				//fillPivot(Utils.getComplementar(Prototype.stage.get(pivotModelX+1, pivotModelY)));
				Prototype.stage.image(Prototype.pivot, pivotModelX-5, pivotModelY-5);
			}		
			for(int p=0; p < parts.size(); p++) {
				Part part = parts.get(p);
				part.drawPivot();
			}
		}
	}

	void fillPivot(int color) {
		Prototype.pivot.loadPixels();
		if(Prototype.pivot.pixels[61] != color) {
			for(int p = 0; p < Prototype.pivotPixels.length; p++) {
				Prototype.pivot.pixels[Prototype.pivotPixels[p]] = color;
			}
		}
		Prototype.pivot.updatePixels();
	}

	public void pre() {
		calcBox();
		updateParts();
		updateLocalMouse();
	}

	void updateLocalMouse() {
		localMouse = Utils.localMouse(localModel);
	}

	void updateParts() {
		for(int p=0; p<parts.size(); p++) {
			Part part = parts.get(p);
			part.pre();
		}
	}

	public boolean mouseInside() {
		switch(this.collisionMethod) {
		case BOX:
			return boxCollide(this.localMouseX(), this.localMouseY());
		case CIRCLE:
			return circleCollide(this.localMouseX(), this.localMouseY());
		case PIXEL:
			return pixelCollide(this.localMouseX(), this.localMouseY());
		default:
			System.err.println("Invalid Collision Method. Switching to Box Collision");
			return boxCollide(localMouseX(), localMouseY());
		}
	}

	public boolean boxCollide(int x, int y) {
		calcBox();
		return (
				x > this.boundingBox.left &&
				x < this.boundingBox.right &&
				y > this.boundingBox.top &&
				y < this.boundingBox.bottom
		)
		? true : false;
	}

	public boolean circleCollide(int x, int y) {
		calcBox();
		float center_x = this.boundingBox.left + boundingBox.width()/2;
		float center_y = this.boundingBox.top + boundingBox.height()/2;
		float radius = (this.width() > this.height()) ? this.width()/2 : this.height()/2;		
		return (x-center_x)*(x-center_x) + (y - center_y)*(y - center_y) < radius*radius;
	}

	public boolean pixelCollide(int x, int y) {
		if (boxCollide(x, y)) {
			int offSetMouseX = x + Math.round(this.width() * this.pivotX());
			int offSetMouseY = y + Math.round(this.height() * this.pivotY());
			//this.diffuseMap.loadPixels();
			if (this.texture.pixels[PApplet.constrain( offSetMouseX + offSetMouseY * this.width(), 0, this.texture.pixels.length-1)] == 0x00000000) {
				this.texture.updatePixels();
				return false;
			}
			//this.diffuseMap.updatePixels();
			return true;    
		}
		return false;
	}

	public Behavior addBehavior(Behavior behavior) {
		if(!this.behaviors.containsKey(Utils.className(behavior))) {
			behavior.initBehavior(this);
			this.behaviors.put(Utils.className(behavior), behavior);
			return behavior;
		} else {
			System.err.println("The behavior "+ Utils.className(behavior) +" was already added to this part.");
			return this.behaviors.get(Utils.className(behavior));
		}
	}

	public void partUpdated(PartUpdateEvent event) {
		switch(event.field) {
		case WIDTH:
			break;
		case HEIGHT:
			break;
		case X:
			break;
		case Y:
			break;
		case RELX:
			break;
		case RELY:
			break;
		case SCALEX:
			break;
		case SCALEY:
			break;
		case PIVOTX:
			break;
		case PIVOTY:
			break;
		case ROTATION:
			break;
		case ALPHA:
			break;
		case VISIBLE:
			break;
		case ENABLED:
			break;
		case SHOWPIVOT:
			break;
		}
	}

	public void addListener(PartListener listener) {
		listeners.add(listener);
	}

	protected void propagatePartUpdate(PartUpdateEvent event) {
		for(PartListener listener : listeners) {
			listener.partUpdated(event);
		}
	}

	public float widthToScale() {
		return (float)this.width() / this.initialWidth;
	}
	public float heightToScale() {
		return (float)this.height() / this.initialHeight;
	}

	public int width() { return this.width.value(); }
	public void width(float value) { 
		this.width.value(value);
		propagatePartUpdate( new PartUpdateEvent(this, Field.WIDTH) );
	}

	public int height() { return this.height.value(); }
	public void height(float value) {
		this.height.value(value);
		propagatePartUpdate( new PartUpdateEvent(this, Field.HEIGHT) );
	}

	public int x() { return this.x.value(); }
	public void x(float value) { 
		this.x.value(value);
		propagatePartUpdate( new PartUpdateEvent(this, Field.X) );
	}

	public int y() { return this.y.value(); }
	public void y(float value) {
		this.y.value(value);
		propagatePartUpdate( new PartUpdateEvent(this, Field.Y) );
	}

	public float relX() { return this.relX.value(); }
	public void relX(float value) {
		this.relX.value(value);
		propagatePartUpdate( new PartUpdateEvent(this, Field.RELX) );
	}

	public float relY() { return this.relY.value(); }
	public void relY(float value) {
		this.relY.value(value);
		propagatePartUpdate( new PartUpdateEvent(this, Field.RELY) );
	}

	public float scaleX() { return this.scaleX.value(); }
	public void scaleX(float value) {
		this.scaleX.value(value);
		propagatePartUpdate( new PartUpdateEvent(this, Field.SCALEX) );
	}

	public float scaleY() { return this.scaleY.value(); }
	public void scaleY(float value) {
		this.scaleY.value(value);
		propagatePartUpdate( new PartUpdateEvent(this, Field.SCALEY) );
	}

	public float pivotX() { return this.pivotX.value(); }
	public void pivotX(float value) {
		this.pivotX.value(value);
		propagatePartUpdate( new PartUpdateEvent(this, Field.PIVOTX) );
	}

	public float pivotY() { return this.pivotY.value(); }
	public void pivotY(float value) {
		this.pivotY.value(value);
		propagatePartUpdate( new PartUpdateEvent(this, Field.PIVOTY) );
	}

	public float rotation() { return this.rotation.value(); }
	public void rotation(float value) {
		this.rotation.value(value);
		propagatePartUpdate( new PartUpdateEvent(this, Field.ROTATION) );
	}

	public float alpha() { return this.alpha.value(); }
	public void alpha(float value) {
		this.alpha.value(value);
		propagatePartUpdate( new PartUpdateEvent(this, Field.ALPHA) );
	}

	//REFACTORY
	public int localMouseX() { updateLocalMouse(); return (int)(localMouse[0]); }
	public int localMouseY() { updateLocalMouse(); return (int)(localMouse[1]);	}

	public int plocalMouseX() { return (int)(localMouse[2]);}
	public int plocalMouseY() { return (int)(localMouse[3]);}

	public boolean visible() { return this.visible; }
	public void visible(boolean state) {
		this.visible = state;
		propagatePartUpdate( new PartUpdateEvent(this, Field.VISIBLE) );
	}

	public boolean enabled() { return this.enabled; }
	public void enabled(boolean state) {
		this.enabled = state;
		propagatePartUpdate( new PartUpdateEvent(this, Field.ENABLED) );
	}

	public boolean showPivot() { return this.showPivot; }
	public void showPivot(boolean state) {
		this.showPivot = state;
		propagatePartUpdate( new PartUpdateEvent(this, Field.SHOWPIVOT) );
	}

	public float left() { calcBox(); return boundingBox.left; }
	public float top() { calcBox(); return boundingBox.top; }
	public float right() { calcBox(); return boundingBox.right; }
	public float bottom() { calcBox(); return boundingBox.bottom; }

	public Part parent() { return this.parent; }
	protected void parent(Part parent) { this.parent = parent; }
}
