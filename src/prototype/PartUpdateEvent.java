package prototype;

public class PartUpdateEvent {
	
	Part part;
	Part.Field field;
	
	public PartUpdateEvent(Part part, Part.Field field) {
		this.part = part;
		this.field = field;
	}
}
