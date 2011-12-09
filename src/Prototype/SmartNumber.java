package Prototype;

public interface SmartNumber {
	
	boolean constrain();
	void constrain(boolean state);
	void constrain(float min, float max);

}
