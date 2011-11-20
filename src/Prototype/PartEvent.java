package Prototype;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class PartEvent {
	public Part part;
	private int step;
	private int x;
	private int y;
	private int id;
	
	public final static int MOUSE_CLICKED = 500;
	public final static int MOUSE_PRESSED = 501;
	public final static int MOUSE_RELEASED = 502;
	public final static int MOUSE_MOVED = 503;
	public final static int MOUSE_DRAGGED = 506;
	public final static int MOUSE_SCROLLED = 507;
	public final static int PART_CLICKED = 0;
	public final static int PART_PRESSED = 1;
	public final static int PART_RELEASED = 2;
	public final static int PART_MOVED = 3;
	public final static int PART_DRAGGED = 6;
	public final static int PART_SCROLLED = 7;

	public PartEvent(Part part, MouseEvent event) {
		this.part = part;
		x = event.getX();
		y = event.getX();
		id = event.getID();
	}
	
	public PartEvent(Part part, MouseWheelEvent event) {
		this.part = part;
		step =  event.getWheelRotation();
		x = event.getX();
		y = event.getX();
		id = 507;
	}
	
	public PartEvent(Part part, MouseEvent event, int id) {
		this.part = part;
		x = event.getX();
		y = event.getX();
		this.id = id;
	}
	
	public PartEvent(Part part, MouseWheelEvent event, int id) {
		this.part = part;
		step =  event.getWheelRotation();
		x = event.getX();
		y = event.getX();
		this.id = id;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getStep() {
		return step;
	}
	
	public int getID() {
		return id;
	}
}
