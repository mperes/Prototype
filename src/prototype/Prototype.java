package prototype;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

public class Prototype implements PrototypeConstants {
	static PImage pivot;
	static int[] pivotPixels;
	static PApplet stage;
	static PGraphics offScreenBuffer;
	ArrayList<Part> parts;
	private static Map<String, PImage> textures;
	
	private static Prototype instance;
	static int checkOffScreenParts;

	private Prototype(PApplet stage) {		
		Prototype.stage = stage;
		offScreenBuffer = Prototype.stage.createGraphics(Prototype.stage.width, Prototype.stage.height, PConstants.JAVA2D);
		parts = new ArrayList<Part>();
		textures = new HashMap<String, PImage>();
		pivot = Prototype.stage.loadImage("pivot.png");
		pivotPixels = new int[] {5, 16, 26, 27, 28, 36, 40, 46, 52, 55, 56, 57, 60, 63, 64, 65, 68, 74, 80, 84, 92, 93, 94, 104, 115 };
		
		stage.registerPre(this);
		stage.registerDraw(this);
		Prototype.stage.registerMouseEvent(this);
		checkOffScreenParts = 15;
	}
	
	public static Prototype createPrototype(PApplet stage) {
        if( instance == null ) {
            instance = new Prototype(stage);
            return instance;
        }
        System.err.println("It is not possible to have more than one instace of Prototype in your Sketch.");
        return instance;
	}

	//Image Part
	public Part part(String texturePath, Behavior... behaviors) {
		Part newPart = new Part(texturePath, behaviors);
		parts.add(newPart);
		return newPart;
	}
	
	//Shape Part
	public Part part(int width, int height, DynamicImage imageRecipe, Behavior... behaviors) {
		Part newPart = new Part(width, height, imageRecipe, behaviors);
		parts.add(newPart);
		return newPart;
	}
	
	//Shape Part
	public Part part(PartBuilder builder) {
		Part newPart = new Part(builder);
		parts.add(newPart);
		return newPart;
	}
	
	//Pre-Built Part
	public Part part(Part newPart) {
		parts.add(newPart);
		return newPart;
	}

	public void pre(){
		for(int p=0; p<parts.size(); p++) {
			Part part = parts.get(p);
			part.pre();
		}
	}

	public void draw(){
		for(int p=0; p < parts.size(); p++) {
			Part part = parts.get(p);
			part.draw();
		}
		for(int p=0; p < parts.size(); p++) {	
			Part part = parts.get(p);
			part.drawPivot();
		}
	}
	
	public static void clearBuffer() {
		Prototype.offScreenBuffer.loadPixels();
		for(int p = 0; p < Prototype.offScreenBuffer.pixels.length; p++) {
			Prototype.offScreenBuffer.pixels[p] = 0x00000000;
		}
		Prototype.offScreenBuffer.updatePixels();
	}
	
	public static PImage loadTexture(String texturePath) {
		PImage texture = stage.loadImage(texturePath);
		offScreenBuffer.beginDraw();
		offScreenBuffer.image(texture, 0, 0);
		Prototype.offScreenBuffer.endDraw();
		
		if(!textures.containsKey(texturePath)) {
			PImage savedTexture = Prototype.stage.createImage(texture.width, texture.height, PConstants.ARGB);
			savedTexture.set(0, 0, offScreenBuffer);
			textures.put(texturePath, savedTexture);
		}

		Prototype.clearBuffer();
		return textures.get(texturePath);
	}
	
	public final void mouseEvent(MouseEvent event) {
		for(int p = parts.size()-1; p >= 0; p--) {
			Part childPart = parts.get(p);
			boolean found = Prototype.propagateMouseEvent(childPart, event);
			if(found) { break; }
		}
	}
	
	static boolean propagateMouseEvent(Part part, MouseEvent event) {
		boolean over = false;
		for(int p = part.parts.size()-1; p >= 0; p--) {
			Part childPart = part.parts.get(p);
			boolean found = Prototype.propagateMouseEvent(childPart, event);
			if(found) { return true; }
		}
		for (Behavior b : part.behaviors.values()) {
			if(b.type() == MOUSE) {
				((MouseBehavior)b).mouseEvent(event);
				if(((MouseBehavior)b).parent.mouseInside()) {
					over = true;
				}
			}
		}
		return over;
	}
	
	public void checkOffScreenParts(int value) {
		checkOffScreenParts = value;
	}
}
