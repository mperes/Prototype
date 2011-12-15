package prototype;

public class Box {
	public float left;
	public float top;
	public float right;
	public float bottom;
	
	public Box(float left, float top, float right, float bottom) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}
	
	public Box(float value) {
		this.left = value;
		this.top = value;
		this.right = value;
		this.bottom = value;
	}
}
