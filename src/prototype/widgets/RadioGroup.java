package prototype.widgets;

import prototype.Part;
import prototype.PartList;
import prototype.PartUpdateEvent;

public class RadioGroup extends PartList {
	
	private Part currentOption;
	
	public RadioGroup(int width, int margin, String...strings) {
		super();
		this.margin(margin);
		for(int y = 0; y < strings.length; y++) {
			Part newRadio = new Radio(strings[y], false, width);
			this.add(newRadio);
			newRadio.addListener(this);
			newRadio.value(y);
		}
		this.value(-1);
	}
	
	@Override
	public void partUpdated(PartUpdateEvent event) {
		if(event.part != this) {
			switch(event.field) {
			case VALUE:
				if(event.part.value().asBol()) {
					currentOption = event.part;
					this.value(event.part.value().asInt());
					for(int r = 0; r < children().size(); r++) {
						if(r == event.part.value().asInt()) {
							continue;
						}
						((Radio)children().get(r)).check.value(false);
					}
				} else {
					if(event.part == currentOption) {
						this.value(-1);
						currentOption = null;
					}
				}
				break;
			}
		}
	}
}
