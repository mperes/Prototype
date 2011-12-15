package prototype;

import java.awt.event.MouseEvent;

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
		Prototype.stage.registerPre(this);
		maxCount = 10;
		pointsX = new float[maxCount];
		pointsY = new float[maxCount];
	}
	
	public void onStartDragging(MouseEvent e) {
		startTime = Prototype.stage.millis();
		pointsX = new float[maxCount];
		pointsY = new float[maxCount];
		counter = 0;
	}
	
	public void onDrag(MouseEvent e) {		
        addPoint(
        		parent.localMouseX()-parent.plocalMouseX(),
        		parent.localMouseY()-parent.plocalMouseY()
        );
		parent.setX(parent.getX() + parent.localMouseX()-parent.plocalMouseX());
        parent.setY(parent.getY() + parent.localMouseY()-parent.plocalMouseY());
	}
	
	public void onStopDragging(MouseEvent e) {	
		kinetcSpeedX = averageSpeed(pointsX) / (Prototype.stage.millis() - startTime) * 100;
		kinetcSpeedY = averageSpeed(pointsY) / (Prototype.stage.millis() - startTime) * 100;
	}
	
	public void pre() {
		if(Math.abs(kinetcSpeedX) >= 1) {
			parent.setX(parent.getX() + kinetcSpeedX);
			kinetcSpeedX *= .9;
		}
		if(Math.abs(kinetcSpeedY) >= 1) {
			parent.setY(parent.getY() + kinetcSpeedY);
			kinetcSpeedY *= .9;
		}
	}
	
	void addPoint(float x, float y) {
		for(int i = 0; i < pointsX.length-1; i++) {
			pointsX[i] = pointsX[i+1];
			pointsY[i] = pointsY[i+1];
		}
		if(counter < pointsX.length) {
			pointsX[counter] = x;
			pointsY[counter] = y;
			counter++;
		} else {
			pointsX[pointsX.length-1] = x;
			pointsY[pointsX.length-1] = y;
		}
	}
	
	float averageSpeed(float[] axis) {
		float total = 0;
		int to = (counter < maxCount) ? counter : maxCount;
		for(int i = 1; i < to; i++) {
			total += axis[i] - axis[i-1];
		}
		return (counter < maxCount) ? total / (counter-1) : total / (maxCount-1);
	}
	
}
