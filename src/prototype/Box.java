package prototype;

public class Box {
	public float left;
	public float top;
	public float right;
	public float bottom;
	
	public Box() {
		this.left = 0;
		this.top = 0;
		this.right = 0;
		this.bottom = 0;
	}
	
	public Box(float value) {
		this.left = value;
		this.top = value;
		this.right = value;
		this.bottom = value;
	}
	
	public Box(float left, float top, float right, float bottom) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}
	
	public Box(float[] dimensions) {
		this.left = (dimensions.length > 0) ? dimensions[0] : 0 ;
		this.top = (dimensions.length > 1) ? dimensions[1] : 0 ;
		this.right = (dimensions.length > 2) ? dimensions[2] : 0 ;
		this.bottom = (dimensions.length > 3) ? dimensions[3] : 0 ;
	}
	
	public float[] get() {
		return new float[] { this.left, this.top, this.right, this.bottom };
	}
	
	public float width() {
		return this.right - this.left;
	}
	
	public float height() {
		return this.bottom - this.top;
	}
}
