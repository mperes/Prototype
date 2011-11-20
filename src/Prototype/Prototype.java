package Prototype;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import processing.core.PApplet;


public class Prototype implements MouseWheelListener {
	private PApplet parent;
	private Tweener tweener;
	private ArrayList<Part> parts;

	public Prototype(PApplet parent) {	
		parent.registerPre(this);
		parent.registerDraw(this);
		parent.registerMouseEvent(this);
		parent.frame.addMouseWheelListener(this);

		this.parent = parent;
		parts = new ArrayList<Part>();
	}

	public void installTweenMatrix(boolean remove) {
		if(!remove && tweener != null) {
			tweener = new Tweener();
		} else {
			tweener = null;
		}
	}

	public Part part(Blueprint blueprint) {
		Part newPart = new Part(parent, blueprint);
		parts.add(newPart);
		return newPart;
	}
	
	public Part part(Blueprint blueprint, float x, float y) {
		Part newPart = new Part(parent, blueprint, x, y);
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
