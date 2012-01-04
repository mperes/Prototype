package prototype;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.media.opengl.GL;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.opengl.PGraphicsOpenGL;
import prototype.behaviors.mouse.MouseBehavior;

public class Prototype implements PrototypeConstants {
	static PImage pivot;
	static int[] pivotPixels;
	static PApplet stage;
	static PGraphics offScreenBuffer;
	ArrayList<Part> parts;
	private static Map<String, PImage> textures;
	
	private static Prototype instance;
	static int checkOffScreenParts;
	public static PFont fontH1;
	public static PFont fontH2;
	
	//OpenGL Stuff
	public static PGraphicsOpenGL pgl;
	public static GL gl;

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
		
		fontH1 = Prototype.stage.loadFont("default/fonts/default_h1.vlw");
		fontH2 = Prototype.stage.loadFont("default/fonts/default_h2.vlw");
		
		 pgl = (PGraphicsOpenGL) stage.g;
		 gl = pgl.beginGL();
		 gl.glDepthMask(false);
		 pgl.endGL();
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
	public Part part(int width, int height, ShapeRender imageRecipe, Behavior... behaviors) {
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
	
	//Text childPart
	public Part part(String text, int textColor, PFont textFont, Behavior... behaviors)  {
		Part newPart = new Part(text, textColor, textFont, behaviors);
		parts.add(newPart);
		return newPart;
	}
	public Part part(String text, int textColor, PFont textFont, int width, Behavior... behaviors)  {
		Part newPart = new Part(text, textColor, textFont, width, behaviors);
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
			part.pre(false);
		}
	}

	public void draw(){
		for(int p=0; p < parts.size(); p++) {
			Part part = parts.get(p);
			part.draw(false, false, false);
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
				if(((MouseBehavior)b).parent().mouseInside()) {
					if(b.parent().passThrough() == false) {
						over = true;
					}
				}
			}
		}
		return over;
	}
	
	public void checkOffScreenParts(int value) {
		checkOffScreenParts = value;
	}
	
	public static PApplet stage() {
		return stage;
	}
}