package prototype;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

public class Prototype implements MouseWheelListener {
	static PImage pivot;
	static int[] pivotPixels;
	static PApplet stage;
	static PGraphics offScreenBuffer;
	Tweener tweener;
	ArrayList<Part> parts;
	static Map<String, PImage> diffuseMaps;
	
	private static Prototype instance;

	private Prototype(PApplet stage) {		
		stage.registerPre(this);
		stage.registerDraw(this);
		stage.registerMouseEvent(this);
		stage.frame.addMouseWheelListener(this);

		Prototype.stage = stage;
		offScreenBuffer = Prototype.stage.createGraphics(Prototype.stage.width, Prototype.stage.height, PConstants.JAVA2D);
		parts = new ArrayList<Part>();
		diffuseMaps = new HashMap<String, PImage>();
		pivot = Prototype.stage.loadImage("pivot.png");
		pivotPixels = new int[] {5, 16, 26, 27, 28, 36, 40, 46, 52, 55, 56, 57, 60, 63, 64, 65, 68, 74, 80, 84, 92, 93, 94, 104, 115 };
	}
	
	public static Prototype createPrototype(PApplet stage) {
        if( instance == null ) {
            instance = new Prototype(stage);
            return instance;
        }
        System.err.println("It is not possible to have more than one instace of Prototype in your Sketch.");
        return instance;
	}

	public void installTweenMatrix(boolean remove) {
		if(!remove && tweener != null) {
			tweener = new Tweener();
		} else {
			tweener = null;
		}
	}

	public Part part(Blueprint blueprint) {
		Part newPart;
		switch(blueprint.type) {
		case Part.IMAGE:
			newPart = new ImagePart(blueprint);
			break;
		case Part.SHAPE:
			newPart = new ShapePart(blueprint);
			break;
		default:
			throw new RuntimeException("The declared part type is not valid.");
		}
		parts.add(newPart);
		return newPart;
	}
	
	public Part part(Blueprint blueprint, float x, float y) {
		Part newPart;
		switch(blueprint.type) {
		case Part.IMAGE:
			newPart = new ImagePart(blueprint, x, y);
			break;
		case Part.SHAPE:
			newPart = new ShapePart(blueprint, x, y);
			break;
		default:
			throw new RuntimeException("The declared part type is not valid.");
		}
		parts.add(newPart);
		return newPart;
	}

	public void pre(){
		//Updates TweenMatrix
		if(tweener != null) {
			tweener.update();
		}
		updateParts();
	}

	private void updateParts() {
		for(int p=0; p<parts.size(); p++) {
			Part part = parts.get(p);
			part.pre();
		}
	}

	public void draw(){
		drawParts();
	}

	void drawParts() {
		for(int p=0; p < parts.size(); p++) {
			Part part = parts.get(p);
			part.draw();
		}
		for(int p=0; p < parts.size(); p++) {	
			Part part = parts.get(p);
			part.drawPivot();
		}
	}

	public void mouseEvent(MouseEvent event) {	
		for(int p=parts.size()-1; p >= 0; p--) {
			Part part = parts.get(p);		
			part.mouseEvent(event);
		}
		for(int p=parts.size()-1; p >= 0; p--) {
			Part part = parts.get(p);		
			 if(part.partEvent(event, 0, 0)) {
				 break;
			 }
		}
	}
	
	public void mouseWheelMoved(MouseWheelEvent event) {	
		for(int p=parts.size()-1; p >= 0; p--) {
			Part part = parts.get(p);		
			part.mouseEvent(event);
		}
		for(int p=parts.size()-1; p >= 0; p--) {
			Part part = parts.get(p);		
			 if(part.partEvent(event, 0, 0)) {
				 break;
			 }
		}
	}
	
	public static void clearBuffer() {
		Prototype.offScreenBuffer.loadPixels();
		for(int p = 0; p < Prototype.offScreenBuffer.pixels.length; p++) {
			Prototype.offScreenBuffer.pixels[p] = 0x00000000;
		}
		Prototype.offScreenBuffer.updatePixels();
	}
	
	public static PImage addDiffuseMap(Blueprint blueprint, int w, int h) {
		if(blueprint.unique) {
			PImage newImg = Prototype.stage.createImage(w, h, PConstants.ARGB);
			newImg.set(0, 0, offScreenBuffer);
			String randUID = UUID.randomUUID().toString();
			diffuseMaps.put(randUID, newImg);
			Prototype.clearBuffer();
			return diffuseMaps.get(randUID);
		}
		Class<?> blueprintClass = blueprint.getClass();
		while(blueprintClass.getName() != "prototype.Blueprint") {
			Class<?> bluePrintSuperClass = blueprintClass.getSuperclass();
			if(bluePrintSuperClass.getName() == "prototype.Blueprint"){
				if(!diffuseMaps.containsKey(blueprintClass)) {
					PImage newImg = Prototype.stage.createImage(w, h, PConstants.ARGB);
					newImg.set(0, 0, offScreenBuffer);
					diffuseMaps.put(blueprintClass.getName(), newImg);
				}

				Prototype.clearBuffer();
				return diffuseMaps.get(blueprintClass.getName());
			}
			blueprintClass = bluePrintSuperClass;
		}
		throw new RuntimeException("this should not happen!");
	}
}
