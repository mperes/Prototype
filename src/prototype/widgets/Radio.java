package prototype.widgets;

import prototype.Part;
import prototype.PartBuilder;
import prototype.PartUpdateEvent;
import prototype.Prototype;
import prototype.PrototypeConstants;
import prototype.ShapeRender;
import prototype.Utils;
import prototype.behaviors.mouse.Hover;
import prototype.behaviors.mouse.ToggleValue;

public class Radio extends Part implements PrototypeConstants, WidgetsConstants {

	public Part container;
	public Part check;
	public Part label;

	public Radio(String label) {
		super(
				0, 0,
				new ShapeRender() {
					public void draw() {}
				}
				);

		this.container = this.part(DEFAULT_TOGGLE_CONTAINER);
		this.check = this.part(
				new PartBuilder(IMAGE).
				states(DEFAULT_TOGGLE_CHECK,DEFAULT_TOGGLE_CHECK_HOVER).
				behaviors(new ToggleValue(), new Hover())
				);
		this.label = this.part(label, DEFAULT_TEXT_DARK, Prototype.fontH1);
		this.label.relY(0.5f);
		this.label.pivotY(0.5f);
		this.label.x(this.container.width()*1.5f);

		this.check.addListener(this);
		this.check.value(false);

		this.width(this.container.width() + this.label.width() + this.label.x());
		this.height(container.height());
	}

	public Radio(String label, boolean initialState) {
		super(
				0, 0,
				new ShapeRender() {
					public void draw() {}
				}
				);

		this.container = this.part(DEFAULT_TOGGLE_CONTAINER);
		this.check = this.part(
				new PartBuilder(IMAGE).
				states(DEFAULT_TOGGLE_CHECK,DEFAULT_TOGGLE_CHECK_HOVER).
				behaviors(new ToggleValue(), new Hover())
				);
		this.label = this.part(label, DEFAULT_TEXT_DARK, Prototype.fontH1);
		this.label.relY(0.5f);
		this.label.pivotY(0.5f);
		this.label.x(this.container.width()*1.5f);

		this.check.addListener(this);
		this.check.value(initialState);

		this.width(this.container.width() + this.label.width() + this.label.x());
		this.height(container.height());
	}

	public Radio(String label, boolean initialState, int width) {

		super(
			0, 0,
			new ShapeRender() {
				public void draw() {}
			}
		);

		this.container = this.part(DEFAULT_TOGGLE_CONTAINER);
		this.check = this.part(
			new PartBuilder(IMAGE).
			states(DEFAULT_TOGGLE_CHECK,DEFAULT_TOGGLE_CHECK_HOVER).
			behaviors(new ToggleValue(), new Hover())
		);
		int textWidth = width - (int)(this.container.width()*1.5f);
		this.label = this.part(label, DEFAULT_TEXT_DARK, Prototype.fontH1, textWidth);
		this.label.relY(0.5f);
		this.label.pivotY(0.5f);
		this.label.x(this.container.width()*1.5f);

		this.check.addListener(this);
		this.check.value(initialState);

		this.width((int)(this.container.width()*1.5f) + this.label.width());
		this.height(container.height());
	}

	@Override
	public void partUpdated(PartUpdateEvent event) {
		if(event.part == check) {
			switch(event.field) {
			case VALUE:
				this.value(this.check.value().asBol());
				this.check.alpha(this.value().asBol());
				break;
			}
		}
	}

}
