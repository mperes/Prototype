package prototype.widgets;

import prototype.Part;
import prototype.PartBuilder;
import prototype.Prototype;
import prototype.PrototypeConstants;
import prototype.behaviors.mouse.PushButton;
import prototype.behaviors.mouse.Stepper;
import prototype.behaviors.mouse.ToggleValue;

public class Button extends Part implements PrototypeConstants, WidgetsConstants {
	
	final static int textMargin = 15;
	Part label;
	
	public Button(String label) {
		super(
			new PartBuilder(IMAGE).
			states(DEFAULT_BUTTON_NORMAL, DEFAULT_BUTTON_HOVER, DEFAULT_BUTTON_PRESSED).
			behaviors(new ToggleValue(), new Stepper(), new PushButton())
		);
		this.label = this.part(label, DEFAULT_TEXT_LIGHT, Prototype.fontH1);
		this.label.relX.value(0.5f);
		this.label.relY.value(0.5f);
		this.label.pivotY(0.5f);
		this.label.x(-this.label.width()/2);
		this.width(this.label.width()+2*textMargin);
	}
	
	public Button(String label, int width) {
		super(
			new PartBuilder(IMAGE).
			states(DEFAULT_BUTTON_NORMAL, DEFAULT_BUTTON_HOVER, DEFAULT_BUTTON_PRESSED).
			behaviors(new ToggleValue(), new Stepper(), new PushButton()).
			width(width)
		);
		this.label = this.part(label, DEFAULT_TEXT_LIGHT, Prototype.fontH1, width-2*textMargin);
		this.label.relX.value(0.5f);
		this.label.relY.value(0.5f);
		this.label.pivotX(0.5f);
		this.label.pivotY(0.5f);
	}

}
