package prototype;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

abstract public interface Part {
	
	public static final int SHAPE = 0;
	public static final int IMAGE = 1;
	
	public static final int DEFAULT = 0;
	public static final int BOX = 1;
	public static final int CIRCLE = 2;
	public static final int PIXEL = 3;
	
	public void pre();
	public void draw();
	public void drawPivot();
		
	public Part part(Blueprint blueprint);
	public Part part(Blueprint blueprint, float x, float y);
	
	public void mouseEvent(MouseEvent event);
	public void mouseEvent(MouseWheelEvent event);
	public boolean partEvent(MouseEvent event, float shiftX, float shiftY);
	public boolean partEvent(MouseWheelEvent event, float shiftX, float shiftY);
	
	public Part getParent();
	public void setParent(Part parent);
	
	public Blueprint getBlueprint();
	public void setBlueprint(Blueprint blueprint);

	SmartInt width();
	int getWidth();
	void setWidth(float value);

	SmartInt height();
	int getHeight();
	void setHeight(float value);

	SmartInt x();
	int getX();
	void setX(float value);

	SmartInt y();
	int getY();
	void setY(float value);

	SmartFloat relX();
	float getRelX();
	void setRelX(float value);

	SmartFloat relY();
	float getRelY();
	void setRelY(float value);

	SmartFloat scaleX();
	float getScaleX();
	void setScaleX(float value);

	SmartFloat scaleY();
	float getScaleY();
	void setScaleY(float value);

	SmartFloat pivotX();
	float getPivotX();
	void setPivotX(float value);

	SmartFloat pivotY();
	float getPivotY();
	void setPivotY(float value);

	SmartFloat rotation();
	float getRotation();
	void setRotation(float value);

	SmartFloat alpha();
	float getAlpha();
	void setAlpha(float value);

	boolean visible();
	void visible(boolean state);

	boolean enabled();
	void enabled(boolean state);

	boolean showPivot();
	void showPivot(boolean state);
	
	boolean interactble();
	void interactble(boolean state);
	
	public int localMouseX();
	public int localMouseY();
	public int plocalMouseX();
	public int plocalMouseY();
	
	float left();
	float top();
	float right();
	float bottom();
}
