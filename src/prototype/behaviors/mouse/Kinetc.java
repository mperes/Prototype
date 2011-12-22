package prototype.behaviors.mouse;

import java.awt.event.MouseEvent;

import prototype.Prototype;

public class Kinetc extends MouseOverBehavior {
	
	float[] pointsX;
	float[] pointsY;
	int counter;
	float kinetcSpeedX;
	float kinetcSpeedY;
	float startTime;
	int maxCount;
	
	
	public Kinetc() { 
		super();
		Prototype.stage().registerPre(this);
		this.maxCount = 10;
		this.pointsX = new float[this.maxCount];
		this.pointsY = new float[this.maxCount];
	}
	
	public void onStartDragging(MouseEvent e) {
		this.startTime = Prototype.stage().millis();
		this.pointsX = new float[this.maxCount];
		this.pointsY = new float[this.maxCount];
		this.counter = 0;
	}
	
	public void onDrag(MouseEvent e) {		
        addPoint(
        		this.parent.localMouseX()-this.parent.plocalMouseX(),
        		this.parent.localMouseY()-this.parent.plocalMouseY()
        );
        this.parent.x(this.parent.x() + this.parent.localMouseX()-this.parent.plocalMouseX());
        this.parent.y(this.parent.y() + this.parent.localMouseY()-this.parent.plocalMouseY());
	}
	
	public void onStopDragging(MouseEvent e) {	
		this.kinetcSpeedX = this.averageSpeed(this.pointsX) / (Prototype.stage().millis() - this.startTime) * 100;
		this.kinetcSpeedY = this.averageSpeed(this.pointsY) / (Prototype.stage().millis() - this.startTime) * 100;
	}
	
	public void pre() {
		if(Math.abs(this.kinetcSpeedX) >= 1) {
			this.parent.x(this.parent.x() + this.kinetcSpeedX);
			this.kinetcSpeedX *= .9;
		}
		if(Math.abs(kinetcSpeedY) >= 1) {
			this.parent.y(this.parent.y() + this.kinetcSpeedY);
			this.kinetcSpeedY *= .9;
		}
	}
	
	void addPoint(float x, float y) {
		for(int i = 0; i < this.pointsX.length-1; i++) {
			this.pointsX[i] = this.pointsX[i+1];
			this.pointsY[i] = this.pointsY[i+1];
		}
		if(this.counter < this.pointsX.length) {
			this.pointsX[this.counter] = x;
			this.pointsY[this.counter] = y;
			this.counter++;
		} else {
			this.pointsX[this.pointsX.length-1] = x;
			this.pointsY[this.pointsX.length-1] = y;
		}
	}
	
	float averageSpeed(float[] axis) {
		float total = 0;
		int to = (this.counter < this.maxCount) ? this.counter : this.maxCount;
		for(int i = 1; i < to; i++) {
			total += axis[i] - axis[i-1];
		}
		return (this.counter < this.maxCount) ? total / (this.counter-1) : total / (this.maxCount-1);
	}
	
}
