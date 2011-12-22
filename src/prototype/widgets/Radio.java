package prototype.widgets;

import prototype.Part;
import prototype.PartBuilder;
import prototype.PartUpdateEvent;
import prototype.Prototype;
import prototype.PrototypeConstants;
import prototype.behaviors.mouse.ToggleValue;

public class Radio extends Part implements PrototypeConstants, WidgetsConstants {
	
	public Part check;
	public Part label;
	
	public Radio(String label) {
		super((DEFAULT_TOGGLE_CONTAINER));
		this.check = this.part(DEFAULT_TOGGLE_CHECK_HOVER, new ToggleValue());
		//this.label = this.part(
		//new PartBuilder(TEXT).text(label).color(DEFAULT_TEXT_COLOR).font(Prototype.fontH1).relY(0.5f).pivotY(0.5f));
		this.label = this.part(label, DEFAULT_TEXT_COLOR, Prototype.fontH1);
		this.label.relY.value(0.5f);
		this.label.pivotY(0.5f);
		this.label.x(this.width()*1.5f);
				
		this.value(false);
		this.check.alpha(0);
		
		this.check.addListener(this);
	}
	
	public Radio(String caption, boolean initialState) {
		super((DEFAULT_TOGGLE_CONTAINER));
		this.check = this.part(DEFAULT_TOGGLE_CHECK_HOVER, new ToggleValue());

		this.check.addListener(this);
		this.check.value(initialState);
	}
	
	@Override
	public void partUpdated(PartUpdateEvent event) {
		switch(event.field) {
		case VALUE:
			this.value(this.check.value().asBol());
			this.check.alpha(this.value().asBol());
			break;
		}
	}
	
}
