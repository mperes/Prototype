package Prototype;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PMatrix3D;

public class ImagePart implements Part {
	private Blueprint blueprint;
	public Part parent;
	ArrayList<ImagePart> parts;

	private PImage diffuseMap;
	private SmartInt width;
	private SmartInt height;
	private SmartInt x;
	private SmartInt y;
	private SmartFloat relX;
	private SmartFloat relY;
	private SmartFloat scaleX;
	private SmartFloat scaleY;
	private SmartFloat pivotX;
	private SmartFloat pivotY;
	private SmartFloat rotation;
	private SmartFloat alpha;

	private float left;
	private float top;
	private float right;
	private float bottom;

	private boolean visible;
	private boolean enabled;
	public boolean showPivot; 

	private float[] localMouse;
	private PMatrix3D localModel;

	public ImagePart (Blueprint blueprint) {
		this.initPart(blueprint);
	}

	public ImagePart (Blueprint blueprint, float x, float y) {
		this.initPart(blueprint);
		this.setX(x);
		this.setY(y);
	}	

	private void initPart (Blueprint blueprint) {
		if(blueprint.width == 0 || blueprint.height == 0) {
			throw new RuntimeException("A Part's height or width cannnot be 0. Set the 'width' and 'height' in your your class constructor to fix this.");
		}
		this.localMouse = new float[] {0, 0, 0, 0};
		this.localModel = new PMatrix3D();
		this.parts = new ArrayList<ImagePart>();
		this.getBlueprint(blueprint);

		this.width = new SmartInt(blueprint.width);
		this.height = new SmartInt(blueprint.height);
		this.x = new SmartInt(blueprint.x);
		this.y = new SmartInt(blueprint.y);
		this.relX = new SmartFloat(blueprint.relX);
		this.relY = new SmartFloat(blueprint.relY);
		this.scaleX = new SmartFloat(blueprint.scaleX);
		this.scaleY = new SmartFloat(blueprint.scaleY);
		this.pivotX = new SmartFloat(blueprint.pivotX);
		this.pivotY = new SmartFloat(blueprint.pivotY);
		this.rotation = new SmartFloat(blueprint.rotation);
		this.alpha = new SmartFloat(blueprint.alpha, 0, 1);

		this.visible(blueprint.visible);
		this.enabled(blueprint.enabled);
		this.showPivot(blueprint.showPivot);

		this.setBlueprint().initBlueprint();

		this.readBlueprint();
		this.calcBox();
	}

	private void calcBox() {
		left = -getPivotX() * getWidth();
		top = -getPivotY() * getHeight();
		right =  left + getWidth();
		bottom = top + getHeight();
	}

	public void readBlueprint() {
		Prototype.offScreenBuffer.beginDraw();
		setBlueprint().description();
		Prototype.offScreenBuffer.endDraw();
		diffuseMap = Prototype.addDiffuseMap(setBlueprint(), getWidth(), getHeight());
	}

	public void draw() {
		if(visible) {
			float translateX = (parent == null) ? getX() : (int)(getX() + parent.getWidth() * getRelX());
			float translateY = (parent == null) ? getY() : (int)(getY() + parent.getHeight() * getRelY());
			Prototype.stage.pushMatrix();
			Prototype.stage.translate(translateX, translateY);
			if(getRotation() != 0) {
				Prototype.stage.rotate(PApplet.radians(getRotation()));
			}
			this.localModel = Coordinates.getCurrentModel().get();
			Prototype.stage.pushStyle();
			Prototype.stage.tint(255, 255*getAlpha());
			if(setBlueprint().scaleGrid != null) {
				scale9Grid(this.getWidth(), this.getHeight(), this.getPivotX(), this.getPivotY(), this.blueprint.scaleGrid, this.diffuseMap);
			} else {
				drawPlane(this.getWidth(), this.getHeight(), this.getPivotX(), this.getPivotY(), this.diffuseMap);
			}
			if (showPivot) { 
				drawPivot();
			}
			drawParts();
			Prototype.stage.popStyle();
			Prototype.stage.popMatrix();
		}
	}

	void drawParts() {
		for(int p=0; p < parts.size(); p++) {
			ImagePart part = parts.get(p);
			part.draw();
		}
	}

	private void drawPivot() {
		Prototype.stage.pushStyle();
		Prototype.stage.fill(255, 0, 0);
		Prototype.stage.noStroke();
		Prototype.stage.rectMode(PConstants.CENTER);
		Prototype.stage.rect(0, 0, 5, 5);
		Prototype.stage.popStyle();
	}

	public void pre() {
		calcBox();
		updateParts();
		updateLocalMouse();
	}

	void updateLocalMouse() {
		localMouse = Coordinates.localMouse(localModel);
	}

	void updateParts() {
		for(int p=0; p<parts.size(); p++) {
			ImagePart part = parts.get(p);
			part.pre();
		}
	}

	public boolean mouseInside(int shiftX, int shiftY) {
		calcBox();
		return (
				localMouseX() > left &&
				localMouseX() < right &&
				localMouseY() > top &&
				localMouseY() < bottom
				)
				? true : false;
	}

	public boolean mouseReallyInside(int shiftX, int shiftY) {
		if (mouseInside(shiftX, shiftY)) {
			int offSetMouseX = localMouseX() + Math.round(getWidth() * getPivotX());
			int offSetMouseY = localMouseY() + Math.round(getHeight() * getPivotY());
			PImage buffer = this.diffuseMap.get();
			buffer.resize(getWidth() * (int)getScaleX(), getHeight() * (int)getScaleY());
			buffer.loadPixels();
			if (buffer.pixels[(int) PApplet.constrain( offSetMouseX + offSetMouseY * getWidth(), 0, buffer.pixels.length-1)] == 0x00000000) {
				buffer.updatePixels();
				return false;
			}
			buffer.updatePixels();
			return true;    
		}
		return false;
	}

	public ImagePart part(Blueprint blueprint) {
		ImagePart newPart = new ImagePart(blueprint);
		newPart.parent = this;
		parts.add(newPart);
		return newPart;
	}

	public ImagePart part(Blueprint blueprint, float x, float y) {
		ImagePart newPart = new ImagePart(blueprint, x, y);
		newPart.parent = this;
		parts.add(newPart);
		return newPart;
	}

	public void mouseEvent(MouseEvent event) {
		if(enabled && visible) {
			for(int p=0; p < parts.size(); p++) {
				ImagePart part = parts.get(p);		
				part.mouseEvent(event);		
			}
			blueprint.partEvent(new PartEvent(this, event));
		}
	}

	public void mouseEvent(MouseWheelEvent event) {
		if(enabled && visible) {
			for(int p=0; p < parts.size(); p++) {
				ImagePart part = parts.get(p);		
				part.mouseEvent(event);		
			}
			blueprint.partEvent(new PartEvent(this, event));
		}
	}

	public boolean partEvent(MouseEvent event, float shiftX, float shiftY) {
		if(enabled && visible) {
			boolean found = false;
			for(int p=parts.size()-1; p >= 0 ; p--) {
				ImagePart part = parts.get(p);	
				found = part.partEvent(event, left+shiftX, top+shiftY);
			}
			if(!found) {
				if(mouseReallyInside((int)shiftX, (int)shiftY)) {
					updateLocalMouse();
					blueprint.partEvent(new PartEvent(this, event, event.getID()-500));
					return true;
				}
			}
		}
		return false;
	}

	public boolean partEvent(MouseWheelEvent event, float shiftX, float shiftY) {
		if(enabled && visible) {
			for(int p=parts.size()-1; p >= 0 ; p--) {
				ImagePart part = parts.get(p);		
				if(part.partEvent(event, left+shiftX, top+shiftY)) {
					part.blueprint.partEvent(new PartEvent(part, event, event.getID()-500));
					break;
				}
			}
			if(mouseReallyInside((int)shiftX, (int)shiftY)) {
				updateLocalMouse();
				blueprint.partEvent(new PartEvent(this, event, event.getID()-500));
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	void drawPlane(float width, float height, float pivotX, float pivotY, PImage texture) {
		Prototype.stage.pushStyle();
		Prototype.stage.textureMode(PConstants.NORMALIZED);
		Prototype.stage.noStroke();
		Prototype.stage.beginShape();
		Prototype.stage.texture(texture);
		Prototype.stage.vertex(-width*pivotX, -height*pivotY, 0, 0);
		Prototype.stage.vertex(width*(1-pivotX), -height*pivotY, 1, 0);
		Prototype.stage.vertex(width*(1-pivotX), height*(1-pivotY), 1, 1);
		Prototype.stage.vertex(-width*pivotX, height*(1-pivotY), 0, 1);
		Prototype.stage.endShape(PConstants.CLOSE);
		Prototype.stage.popStyle();
	}

	void scale9Grid(int width, int height, float pivotX, float pivotY, Box box, PImage texture) {
		PImage img = Prototype.stage.createImage(width, height, PConstants.ARGB);
		img.loadPixels();
		int dW = width - texture.width;
		int dH = height - texture.height;
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				if(x < box.left) {
					if(y < box.top) {
						img.pixels[ (x+y*width)] = texture.pixels[x+y*texture.width];
					} else if(y >= height-box.bottom) {
						img.pixels[ (x+y*width)] = texture.pixels[x+(y-dH)*texture.width];
					} else {
						img.pixels[ (x+y*width)] = texture.pixels[x+(int)box.top*texture.width];
					}
				} else if(x >= width-box.right) {
					if(y < box.top) {
						img.pixels[ (x+y*width)] = texture.pixels[(x-dW)+y*texture.width];
					} else if(y >= height-box.bottom) {
						img.pixels[ (x+y*width)] = texture.pixels[(x-dW)+(y-dH)*texture.width];
					} else {
						img.pixels[ (x+y*width)] = texture.pixels[(x-dW)+(int)box.top*texture.width];
					}
				} else {
					if(y < box.top) {
						img.pixels[ (x+y*width)] = texture.pixels[(int)box.left+y*texture.width];
					} else if(y >= height-box.bottom) {
						img.pixels[ (x+y*width)] = texture.pixels[(int)box.left+(y-dH)*texture.width];
					} else {
						img.pixels[ (x+y*width)] = texture.pixels[(int)box.left+(int)box.top*texture.width];
					}
				}
			}
		}
		img.updatePixels();
		drawPlane(width, height, pivotX, pivotY, img);
	}

	public void getBlueprint(Blueprint blueprint) { this.blueprint = blueprint; }
	public Blueprint setBlueprint() { return blueprint; }

	public SmartInt width() { return width; }
	public int getWidth() { return this.width.value(); }
	public void setWidth(float value) { this.width.value(value); }

	public SmartInt height() { return height; }
	public int getHeight() { return this.height.value(); }
	public void setHeight(float value) { this.height.value(value); }

	public SmartInt x() { return x; }
	public int getX() { return this.x.value(); }
	public void setX(float value) { this.x.value(value); }

	public SmartInt y() { return y; }
	public int getY() { return this.y.value(); }
	public void setY(float value) { this.y.value(value); }

	public SmartFloat relX() { return relX; }
	public float getRelX() { return this.relX.value(); }
	public void setRelX(float value) { this.relX.value(value); }

	public SmartFloat relY() { return relY; }
	public float getRelY() { return this.relY.value(); }
	public void setRelY(float value) { this.relY.value(value); }

	public SmartFloat scaleX() { return scaleX; }
	public float getScaleX() { return this.scaleX.value(); }
	public void setScaleX(float value) { this.scaleX.value(value); }

	public SmartFloat scaleY() { return scaleY; }
	public float getScaleY() { return this.scaleY.value(); }
	public void setScaleY(float value) { this.scaleY.value(value); }

	public SmartFloat pivotX() { return pivotX; }
	public float getPivotX() { return this.pivotX.value(); }
	public void setPivotX(float value) { this.pivotX.value(value); }

	public SmartFloat pivotY() { return pivotY; }
	public float getPivotY() { return this.pivotY.value(); }
	public void setPivotY(float value) { this.pivotY.value(value); }

	public SmartFloat rotation() { return rotation; }
	public float getRotation() { return this.rotation.value(); }
	public void setRotation(float value) { this.rotation.value(value); }

	public SmartFloat alpha() { return alpha; }
	public float getAlpha() { return this.alpha.value(); }
	public void setAlpha(float value) { this.alpha.value(value); }

	public int localMouseX() { return (int)(localMouse[0]); }
	public int localMouseY() { return (int)(localMouse[1]);	}

	public int plocalMouseX() { return (int)(localMouse[2]);}
	public int plocalMouseY() { return (int)(localMouse[3]);}

	public boolean visible() { return this.visible; }
	public void visible(boolean state) { this.visible = state; }

	public boolean enabled() { return this.enabled; }
	public void enabled(boolean state) { this.enabled = state; }

	public boolean showPivot() { return this.showPivot; }
	public void showPivot(boolean state) { this.showPivot = state; }
}
