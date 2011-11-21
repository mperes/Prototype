package Prototype;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.Arrays;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Part {
	private Blueprint blueprint;
	public Part parent;
	ArrayList<Part> parts;


	public Ratio pos;
	public Ratio rel;
	public Ratio size;
	public Ratio scale;
	public Ratio pivot;
	public float left;
	public float top;
	public float right;
	public float bottom;
	boolean visible;
	boolean enabled;
	private float alpha;
	public boolean showPivot;  

	public Part (Blueprint blueprint) {
		this.pos = blueprint.pos.get();
		initPart(blueprint);
	}

	public Part (Blueprint blueprint, float x, float y) {
		this.pos = new Ratio(x, y);
		initPart(blueprint);
	}

	private void initPart (Blueprint blueprint) {
		this.setBlueprint(blueprint);
		this.rel = this.getBlueprint().rel.get();
		this.size = new Ratio(this.getBlueprint().initialWidth, this.getBlueprint().initialHeight);
		this.scale = this.getBlueprint().scale.get();
		this.pivot = this.getBlueprint().pivot.get();

		visible = this.getBlueprint().visible;
		enabled = this.getBlueprint().enabled;
		alpha = alpha(this.getBlueprint().alpha);
		showPivot = this.getBlueprint().showPivot;


		this.getBlueprint().initBlueprint();
		calcBox();
		readBlueprint();

		parts = new ArrayList<Part>();
	}

	private void calcBox() {
		left = pos.x - pivot.x * size.x * scale.x;
		top = pos.y - pivot.y * size.y * scale.y;
		if(parent != null) {
			left += rel.x * parent.size.x;
			top += rel.y * parent.size.y;
		}
		right =  left + size.x * scale.x;
		bottom = top + size.y * scale.y;
	}

	public void readBlueprint() {
		getBlueprint().beginDraw();
		getBlueprint().description();
		getBlueprint().endDraw();
	}

	public void draw() {
		if(visible) {
			float translateX = (parent == null) ? pos.x : pos.x + parent.size.x * rel.x;
			float translateY = (parent == null) ? pos.y : pos.y + parent.size.y * rel.y;
			Prototype.stage.pushMatrix();
			Prototype.stage.translate(translateX, translateY);
			Prototype.stage.pushStyle();
			Prototype.stage.tint(255, 255*alpha);
			if(blueprint.scaleGrid != null) {
				draw9SPlane(size, pivot, blueprint.scaleGrid, blueprint);
			} else {
				drawPlane(size, pivot, blueprint);
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
		return (Prototype.stage.mouseX > shiftX+left && Prototype.stage.mouseX < shiftX+right && Prototype.stage.mouseY > shiftY+top && Prototype.stage.mouseY < shiftY+bottom) ? true : false;
	}

	public boolean mouseReallyInside(int shiftX, int shiftY) {
		if (mouseInside(shiftX, shiftY)) {
			int pixelX = Prototype.stage.mouseX - (int) (shiftX+left);
			int pixelY = Prototype.stage.mouseY - (int) (shiftY+top);
			PImage buffer = getBlueprint().get();
			buffer.resize((int)size.x * (int)scale.x, (int)size.y * (int)scale.y);
			buffer.loadPixels();
			if (buffer.pixels[PApplet.constrain( pixelX + pixelY * (int)size.x, 0, buffer.pixels.length-1)] == 0x00000000) {
				buffer.updatePixels();
				return false;
			}
			buffer.updatePixels();
			return true;    
		}
		return false;
	}

	public Part subpart(Blueprint blueprint) {
		Part newPart = new Part(blueprint);
		newPart.parent = this;
		parts.add(newPart);
		return newPart;
	}

	public Part subpart(Blueprint blueprint, float x, float y) {
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
				if(mouseReallyInside((int)shiftX, (int)shiftY)) {
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
	
	@SuppressWarnings("deprecation")
	void drawPlane(Ratio size, Ratio pivot, PImage texture) {
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
	
	@SuppressWarnings("deprecation")
	void draw9SPlane(Ratio size, Ratio pivot, Box box, PImage texture) {
		
		float relX = -size.x * pivot.x;
		float relY = -size.y * pivot.y;
		float[] dW = {box.left, size.x-(box.left+box.right), box.right};
		float[] dH = {box.top, size.y-(box.top+box.bottom), box.bottom};
		float[] textW = { dW[0]/texture.width, dW[1]/texture.width, dW[2]/texture.width };
		float[] textH = { dH[0]/texture.height, dH[1]/texture.height, dH[2]/texture.height };
		float textWI = 0;
		float textHI = 0;
		System.out.println(Arrays.toString(dW));
		System.out.println(Arrays.toString(dH));
		System.out.println(Arrays.toString(textW));
		System.out.println(Arrays.toString(textH));
		System.out.println("-----------------");
		Prototype.stage.pushStyle();
		Prototype.stage.textureMode(PConstants.NORMALIZED);
		Prototype.stage.noStroke();
		
		for(int sectionW = 0; sectionW < 3; sectionW++) {
		  for(int sectionH = 0; sectionH < 3; sectionH++) {
			  Prototype.stage.beginShape();
			  Prototype.stage.texture(texture);
			  Prototype.stage.vertex(relX, relY, textWI, textHI);
			  Prototype.stage.vertex(relX+dW[sectionW], relY, textW[sectionW], textHI);
			  Prototype.stage.vertex(relX+dW[sectionW], relY+dH[sectionH], textW[sectionW], textH[sectionH]);
			  Prototype.stage.vertex(relX, relY+dH[sectionH], textWI, textH[sectionH]);
			  Prototype.stage.endShape(PConstants.CLOSE);
		    relY += dH[sectionH];
		    textHI += textH[sectionH];
		  }
		  relY = -size.y * pivot.y;
		  relX += dW[sectionW];
		  textHI = 0;
		  textWI += textW[sectionW];
		}
		
		Prototype.stage.popStyle();
	}
	
}

//Comment
