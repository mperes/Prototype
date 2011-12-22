package prototype;

public class PartValue {
	private boolean asBol;
	private int asInt;
	private float asFloat;
	private String asString;
	
	public PartValue() {
		asBol = false;
		asInt = 0;
		asFloat = 0;
		asString = "";
	}
	public PartValue(boolean asBol, int asInt, float asFloat, String asString) {
		
	}
	
	public void value(boolean asBol) {
		this.asBol = asBol;
	}
	public void value(float asFloat) {
		this.asFloat = asFloat;
		this.asInt = Math.round(asFloat);
	}
	public void value(String asString) {
		this.asString = asString;
	}
	
	public boolean asBol() { return this.asBol; }
	public int asInt() { return this.asInt; }
	public float asFloat() { return asFloat; }
	public String asString() { return asString; }
}
