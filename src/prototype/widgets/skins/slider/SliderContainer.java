package prototype.widgets.skins.slider;

import processing.core.*;
import prototype.*;

class SliderContainer extends Skin {

	public SliderContainer() {
		super();
		width = 31;
		height = 30;
		scaleGrid = new Box(15);

		//Set the part to be not interactive
		intractable = false;
	}

	public void description() {
		PImage picture = Prototype.stage.loadImage("container.png");
		blueprint.image(picture, 0, 0);
	}

}
