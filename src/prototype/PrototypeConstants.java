package prototype;

public interface PrototypeConstants {
	//Part types
	public static final int TYPES = 3;
	public static final int SHAPE = 0;
	public static final int IMAGE = 1;
	public static final int TEXT = 2;

	//Collision Methods;
	public static final int BOX = 0;
	public static final int CIRCLE = 1;
	public static final int PIXEL = 2;
	
	//Skins: Slider
	public static final String DEFAULT_SLIDER_CONTAINER = "default/slider/container.png";
	public static final String DEFAULT_SLIDER_CONTAINER_HOVER = "default/slider/container.png";
	public static final String DEFAULT_SLIDER_PROGRESS = "default/slider/progress.png";
	public static final String DEFAULT_SLIDER_PROGRESS_HOVER = "default/slider/progress.png";
	public static final String DEFAULT_SLIDER_KNOB = "default/slider/knob.png";
	public static final String DEFAULT_SLIDER_KNOB_HOVER = "default/slider/knob.png";
	public static final String DEFAULT_SLIDER_KNOB_HIGHLIGHT = "default/slider/knob_highlight.png";
	
	//Behavior Types
	public static final int MOUSE = 0;
	public static final int KEYBOARD = 1;
}
