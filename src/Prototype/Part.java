package Prototype;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Part {
	private Blueprint blueprint;
	public Part parent;
	ArrayList<Part> parts;
	
	public PImage diffuseMap;
	public Ratio pos;
	public Ratio rel;
	public RatioInt size;
	public Ratio scale;
	public Ratio pivot;
	public float rotation;
	public float left;
	public float top;
	public float right;
	public float bottom;
	boolean visible;
	boolean enabled;
	private float alpha;
	public boolean showPivot; 
	private float[] localMouse;
	private float[] plocalMouse; 

	public Part (Blueprint blueprint) {
		this.pos = blueprint.pos.get();
		initPart(blueprint);
	}

	public Part (Blueprint blueprint, float x, float y) {
		initPart(blueprint);
		this.pos.set(x, y);
	}

	private void initPart (Blueprint blueprint) {
		
		if(blueprint.size.x == 0 || blueprint.size.y == 0) {
			throw new RuntimeException("A Part's height or width cannnot be 0. Use size.set(w,h) on your class constructor to fix this.");
		}
		parts = new ArrayList<Part>();
		
		this.setBlueprint(blueprint);
		this.size = this.getBlueprint().size.get();
		this.rel = this.getBlueprint().rel.get();
		this.scale = this.getBlueprint().scale.get();
		this.pivot = this.getBlueprint().pivot.get();

		visible = this.getBlueprint().visible;
		enabled = this.getBlueprint().enabled;
		alpha = alpha(this.getBlueprint().alpha);
		showPivot = this.getBlueprint().showPivot;
		
		this.getBlueprint().initBlueprint();
		readBlueprint();
		calcBox();
	}

	private void calcBox() {
		left = -pivot.x * size.x;//pos.x - pivot.x * size.x * scale.x;
		top = -pivot.y * size.y;//pos.y - pivot.y * size.y * scale.y;
		if(parent != null) {
			//left += rel.x * parent.size.x;
			//top += rel.y * parent.size.y;
		}
		right =  left + size.x; //* scale.x;
		bottom = top + size.y; //* scale.y;
	}

	public void readBlueprint() {
		Prototype.offScreenBuffer.beginDraw();
		getBlueprint().description();
		Prototype.offScreenBuffer.endDraw();
		diffuseMap = Prototype.addDiffuseMap(this.blueprint, this.size.x, this.size.y);
	}

	public void draw() {
		if(visible) {
			float translateX = (parent == null) ? pos.x : (int)(pos.x + parent.size.x * rel.x);
			float translateY = (parent == null) ? pos.y : (int)(pos.y + parent.size.y * rel.y);
			Prototype.stage.pushMatrix();
			Prototype.stage.translate(translateX, translateY);
			if(rotation != 0) {
				Prototype.stage.rotate(PApplet.radians(rotation));
			}
			plocalMouse[0] = localMouse[0];
			plocalMouse[1] = localMouse[1];
			localMouse = Coordinates.localMouse();
			Prototype.stage.pushStyle();
			Prototype.stage.tint(255, 255*alpha);
			if(blueprint.scaleGrid != null) {
				scale9Grid(this.size, this.pivot, this.blueprint.scaleGrid, this.diffuseMap);
			} else {
				drawPlane(this.size, this.pivot, this.diffuseMap);
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
			Part part = parts.get(p);
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
	}

	void updateParts() {
		for(int p=0; p<parts.size(); p++) {
			Part part = parts.get(p);
			part.pre();
		}
	}

	private boolean mouseInside(int shiftX, int shiftY) {
		calcBox();
		return (
			localMouse[0] > left &&
			localMouse[0] < right &&
			localMouse[1] > top &&
			localMouse[1] < bottom
		)
		? true : false;
	}

	public boolean mouseReallyInside(int shiftX, int shiftY) {
		//float[] localMouse = Coordinates.localMouse();
		if (mouseInside(shiftX, shiftY)) {
			PImage buffer = this.diffuseMap.get();
			buffer.resize(size.x * (int)scale.x, size.y * (int)scale.y);
			buffer.loadPixels();
			if (buffer.pixels[(int) PApplet.constrain( localMouse[0] + localMouse[1] * size.x, 0, buffer.pixels.length-1)] == 0x00000000) {
				buffer.updatePixels();
				return false;
			}
			buffer.updatePixels();
			return true;    
		}
		return false;
	}

	public Part part(Blueprint blueprint) {
		Part newPart = new Part(blueprint);
		newPart.parent = this;
		parts.add(newPart);
		return newPart;
	}

	public Part part(Blueprint blueprint, float x, float y) {
		Part newPart = new Part(blueprint, x, y);
		newPart.parent = this;
		parts.add(newPart);
		return newPart;
	}

	public float alpha(float alpha) {
		return alpha = PApplet.constrain(alpha, 0, 1);
	}

	public float alpha() {
		return alpha;
	}

	public void setBlueprint(Blueprint blueprint) {
		this.blueprint = blueprint;
	}

	public Blueprint getBlueprint() {
		return blueprint;
	}

	public void mouseEvent(MouseEvent event) {
		if(enabled && visible) {
			for(int p=0; p < parts.size(); p++) {
				Part part = parts.get(p);		
				part.mouseEvent(event);		
			}
			blueprint.partEvent(new PartEvent(this, event));
		}
	}

	public void mouseEvent(MouseWheelEvent event) {
		if(enabled && visible) {
			for(int p=0; p < parts.size(); p++) {
				Part part = parts.get(p);		
				part.mouseEvent(event);		
			}
			blueprint.partEvent(new PartEvent(this, event));
		}
	}

	public boolean partEvent(MouseEvent event, float shiftX, float shiftY) {
		if(enabled && visible) {
			boolean found = false;
			for(int p=parts.size()-1; p >= 0 ; p--) {
				Part part = parts.get(p);		
				found = part.partEvent(event, left+shiftX, top+shiftY);
			}
			if(!found) {
				if(mouseInside((int)shiftX, (int)shiftY)) {
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
				Part part = parts.get(p);		
				if(part.partEvent(event, left+shiftX, top+shiftY)) {
					part.blueprint.partEvent(new PartEvent(part, event, event.getID()-500));
					break;
				}
			}
			if(mouseReallyInside((int)shiftX, (int)shiftY)) {
				blueprint.partEvent(new PartEvent(this, event, event.getID()-500));
				return true;
			}
		}
		return false;
	}

	
	//Old drawing functions using textured planes;
	@SuppressWarnings("deprecation")
	void drawPlane(RatioInt size, Ratio pivot, PImage texture) {
		Prototype.stage.pushStyle();
		Prototype.stage.textureMode(PConstants.NORMALIZED);
		Prototype.stage.noStroke();
		Prototype.stage.beginShape();
		Prototype.stage.texture(texture);
		Prototype.stage.vertex(-size.x*pivot.x, -size.y*pivot.y, 0, 0);
		Prototype.stage.vertex(size.x*(1-pivot.x), -size.y*pivot.y, 1, 0);
		Prototype.stage.vertex(size.x*(1-pivot.x), size.y*(1-pivot.y), 1, 1);
		Prototype.stage.vertex(-size.x*pivot.x, size.y*(1-pivot.y), 0, 1);
		Prototype.stage.endShape(PConstants.CLOSE);
		Prototype.stage.popStyle();
	}
	
	
	//Drawing functions using planes
//	void drawPlane(Ratio size, Ratio pivot, PImage texture) {
//		PImage resizedtexture = Prototype.stage.createImage(texture.width, texture.height, PConstants.ARGB); 
//		resizedtexture.set(0, 0, texture);
//		resizedtexture.resize((int)size.x, (int)size.y);
//		Prototype.stage.pushMatrix();
//		Prototype.stage.translate((int)(-size.x*pivot.x), (int)(-size.y*pivot.y));
//		Prototype.stage.image(resizedtexture, 0, 0);
//		Prototype.stage.popMatrix();
//	}

	void scale9Grid(RatioInt size, Ratio pivot, Box box, PImage texture) {
		int roundSizeX = size.x;
		int roundSizeY = size.y;
		PImage img = Prototype.stage.createImage(roundSizeX, roundSizeY, PConstants.ARGB);
		img.loadPixels();
		int dW = roundSizeX - texture.width;
		int dH = roundSizeY - texture.height;
		for(int y = 0; y < roundSizeY; y++) {
			for(int x = 0; x < roundSizeX; x++) {
				if(x < box.left) {
					if(y < box.top) {
						img.pixels[ (x+y*roundSizeX)] = texture.pixels[x+y*texture.width];
					} else if(y >= roundSizeY-box.bottom) {
						img.pixels[ (x+y*roundSizeX)] = texture.pixels[x+(y-dH)*texture.width];
					} else {
						img.pixels[ (x+y*roundSizeX)] = texture.pixels[x+(int)box.top*texture.width];
					}
				} else if(x >= roundSizeX-box.right) {
					if(y < box.top) {
						img.pixels[ (x+y*roundSizeX)] = texture.pixels[(x-dW)+y*texture.width];
					} else if(y >= roundSizeY-box.bottom) {
						img.pixels[ (x+y*roundSizeX)] = texture.pixels[(x-dW)+(y-dH)*texture.width];
					} else {
						img.pixels[ (x+y*roundSizeX)] = texture.pixels[(x-dW)+(int)box.top*texture.width];
					}
				} else {
					if(y < box.top) {
						img.pixels[ (x+y*roundSizeX)] = texture.pixels[(int)box.left+y*texture.width];
					} else if(y >= roundSizeY-box.bottom) {
						img.pixels[ (x+y*roundSizeX)] = texture.pixels[(int)box.left+(y-dH)*texture.width];
					} else {
						img.pixels[ (x+y*roundSizeX)] = texture.pixels[(int)box.left+(int)box.top*texture.width];
					}
				}
			}
		}
		img.updatePixels();
		drawPlane(size, pivot, img);
	}
	
	public float localMouseX() {
		return localMouse[0];
	}
	public float localMouseY() {
		return localMouse[1];
	}
	public float plocalMouseX() {
		return plocalMouse[0];
	}
	public float plocalMouseY() {
		return plocalMouse[1];
	}

}
