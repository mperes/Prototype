package prototype;

import java.util.HashMap;
import java.util.Map;

public class PartBuilder implements PrototypeConstants {
	
	private final int type;
	private int x;
	private int y;
	private float relX;
	private float relY;
	private float scaleX;
	private float scaleY;
	private float pivotX;
	private float pivotY;
	private float rotation;
	private float alpha;

	private Box scaleGrid;
	private int collisionMethod;

	private boolean visible;
	private boolean enabled;
	private boolean showPivot;
	
	//Image parts
	private String texture;
	
	//Shape parts
	private int width;
	private int height;
	private DynamicImage dynamicTexture;
	
	private Map<String, Behavior> behaviors;
	
	public PartBuilder(int type) {
		if(type < 0 || type > TYPES) {
			throw new RuntimeException("Invalid part type.");
		}
		this.type = type;
		this.x = 0;
		this.y = 0;
		this.relX = 0;
		this.relY = 0;
		this.scaleX = 1;
		this.scaleY = 1;
		this.pivotX = 0;
		this.pivotY = 0;
		this.rotation = 0;
		this.alpha = 1;
		this.collisionMethod = BOX;
		this.visible = true;
		this.enabled = true;
		this.showPivot = false;
		
		//Image parts
		this.texture = "";
		
		//Shape parts
		width = 0;
		height = 0;
		
		behaviors = new HashMap<String, Behavior>();
	}
	
	public int type() {
		return this.type;
	}
	
	public int width() { return this.width; }
	public PartBuilder width(int value) {
		if(this.type == IMAGE) { System.err.println("This field has no effects over image parts."); };
		this.width = value; return this; 
	}

	public int height() { return this.height; }
	public PartBuilder height(int value) {
		if(this.type == IMAGE) { System.err.println("This field has no effects over image parts."); };
		this.height = value;
		return this;
	}

	public int x() { return this.x; }
	public PartBuilder x(int value) { this.x = value; return this; }

	public int y() { return this.y; }
	public PartBuilder y(int value) { this.y = value; return this; }

	public float relX() { return this.relX; }
	public PartBuilder relX(float value) { this.relX = value; return this; }

	public float relY() { return this.relY; }
	public PartBuilder relY(float value) { this.relY = value; return this; }

	public float scaleX() { return this.scaleX; }
	public PartBuilder scaleX(float value) { this.scaleX = value; return this; }

	public float scaleY() { return this.scaleY; }
	public PartBuilder scaleY(float value) { this.scaleY = value; return this; }

	public float pivotX() { return this.pivotX; }
	public PartBuilder pivotX(float value) { this.pivotX = value; return this; }

	public float pivotY() { return this.pivotY; }
	public PartBuilder pivotY(float value) { this.pivotY = value; return this; }

	public float rotation() { return this.rotation; }
	public PartBuilder rtation(float value) { this.rotation = value; return this; }

	public float alpha() { return this.alpha; }
	public PartBuilder alpha(float value) { this.alpha = value; return this; }

	public boolean visible() { return this.visible; }
	public PartBuilder visible(boolean value) { this.visible = value; return this; }

	public boolean enabled() { return this.enabled; }
	public PartBuilder enabled(boolean value) { this.enabled = value; return this; }

	public boolean showPivot() { return this.showPivot; }
	public PartBuilder showPivot(boolean value) { this.showPivot = value; return this; }
	
	public String texture() { return this.texture; }
	public DynamicImage dynamicTexture() { return this.dynamicTexture; }
	public PartBuilder texture(String value) { this.texture = value; return this; }
	public PartBuilder texture(DynamicImage value) { this.dynamicTexture = value; return this; }
	
	public int collisionMethod() { return this.collisionMethod; }
	public PartBuilder collisionMethod(int value) {
		if(this.type != IMAGE && value == PIXEL) {
			System.err.println("Pixel collision is only avaible for image parts.");
		} else {
			this.collisionMethod = value;
		}
		return this;
	}
	
	public Box scaleGrid() { return this.scaleGrid; }
	public PartBuilder scaleGrid(float value) { 
		if(this.type != IMAGE) {
			System.err.println("9 Scale grid is only avaible for image parts.");
		} else {
			this.scaleGrid = new Box(value);
		}
		return this;
	}
	public PartBuilder scaleGrid(float left, float top, float right, float bottom) {
		if(this.type != IMAGE) {
			System.err.println("9 Scale grid is only avaible for image parts.");
		} else {
			this.scaleGrid = new Box(left, top, right, bottom);
		}
		return this;
	}
	
	public Map<String, Behavior> behaviors() { return this.behaviors; }
	public PartBuilder behaviors(Behavior... behaviors) {
		for(Behavior b : behaviors) {
			if(!this.behaviors.containsKey(Utils.className(b))) {
				this.behaviors.put(Utils.className(b), b);
			} else {
				System.err.println("The behavior "+ Utils.className(b) +" was already added to this part.");
			}
		}
		return this;
	}
	
	public Part build() {
		return new Part(this);
	}
}
