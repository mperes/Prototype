package prototype.widgets;

import prototype.Part;
import prototype.PartList;
import prototype.PartUpdateEvent;
import prototype.Prototype;
import prototype.behaviors.mouse.ToggleValue;

public class TextList extends PartList {
	
	private Part currentOption;
	
	public TextList(int width, int margin, int color, String...strings) {
		super();
		this.margin(margin);
		for(int y = 0; y < strings.length; y++) {
			Part newLabel = new Part(strings[y], color, Prototype.fontH1, width, new ToggleValue());
			this.add(newLabel);
			newLabel.value(y);
			newLabel.addListener(this);
		}
		this.value(-1);
	}
	
	@Override
	public void partUpdated(PartUpdateEvent event) {
		if(event.part != this) {
			switch(event.field) {
			case VALUE:
				this.value(event.part.value().asInt());
				break;
			}
		}
	}
}
