package prototype;

import processing.core.PGraphics;

public class PartBuilder {
	
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
	public boolean intractable;
	
	public Box scaleGrid;
	public String skin;
	
	public PartBuilder() {
		type = Part.IMAGE;
		collisionMethod = Part.DEFAULT;
		scaleX = 1;
		scaleY = 1;
		alpha = 1;	
		visible = true;
		enabled = true;
		showPivot = false;
		unique = false;
		intractable = true;
		skin = "";
	}
	
	PartBuilder x(int value) {
		this.x = value;
		return this;
	}
	
	PartBuilder y(int value) {
		this.y = value;
		return this;
	}
	
	PartBuilder relX(float value) {
		this.relX = value;
		return this;
	}
	
	PartBuilder relY(float value) {
		this.relY = value;
		return this;
	}
	
	PartBuilder width(int value) {
		this.width = value;
		return this;
	}
	
	PartBuilder height(int value) {
		this.height = value;
		return this;
	}
	
	PartBuilder scaleX(float value) {
		this.scaleX = value;
		return this;
	}
	
	PartBuilder scaleY(float value) {
		this.scaleY = value;
		return this;
	}
	
	PartBuilder alpha(float value) {
		this.alpha = value;
		return this;
	}
	
	PartBuilder visible(boolean value) {
		this.visible = value;
		return this;
	}
	
	PartBuilder enabled(boolean value) {
		this.enabled = value;
		return this;
	}
	
	PartBuilder showPivot(boolean value) {
		this.showPivot = value;
		return this;
	}
	
	PartBuilder unique(boolean value) {
		this.unique = value;
		return this;
	}
	
	PartBuilder intractable(boolean value) {
		this.intractable = value;
		return this;
	}
	
	PartBuilder skin(String value) {
		this.skin = value;
		return this;
	}
	
	public Part build() {
		if(this.type == Part.IMAGE) {
			return new ImagePart();
		} else {
			return new ShapePart();
		}
	}

}
