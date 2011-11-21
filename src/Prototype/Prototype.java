package Prototype;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import processing.core.PApplet;


public class Prototype implements MouseWheelListener {
	static PApplet stage;
	private Tweener tweener;
	private ArrayList<Part> parts;
	
	private static Prototype instance;

	private Prototype(PApplet stage) {	
		stage.registerPre(this);
		stage.registerDraw(this);
		stage.registerMouseEvent(this);
		stage.frame.addMouseWheelListener(this);

		Prototype.stage = stage;
		parts = new ArrayList<Part>();
	}
	
	public static Prototype createPrototype(PApplet stage) {
        if( instance == null ) {
            instance = new Prototype(stage);
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
		Part newPart = new Part(stage, blueprint);
		parts.add(newPart);
		return newPart;
	}
	
	public Part part(Blueprint blueprint, float x, float y) {
		Part newPart = new Part(stage, blueprint, x, y);
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
			Part bot = parts.get(p);
			bot.pre();
		}
	}

	public void draw(){
		drawParts();
	}

	void drawParts() {
		for(int p=0; p < parts.size(); p++) {
			Part bot = parts.get(p);
			bot.draw();
		}
	}

	public void mouseEvent(MouseEvent event) {	
		for(int p=0; p < parts.size(); p++) {
			Part part = parts.get(p);		
			part.mouseEvent(event);
		}
		for(int p=0; p < parts.size(); p++) {
			Part part = parts.get(p);		
			 if(part.partEvent(event, 0, 0)) {
				 break;
			 }
		}
	}
	
	public void mouseWheelMoved(MouseWheelEvent event) {	
		for(int p=0; p < parts.size(); p++) {
			Part part = parts.get(p);		
			part.mouseEvent(event);
		}
		for(int p=0; p < parts.size(); p++) {
			Part part = parts.get(p);		
			 if(part.partEvent(event, 0, 0)) {
				 break;
			 }
		}
	}

}
