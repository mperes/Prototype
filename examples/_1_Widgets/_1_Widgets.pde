//This a demo showing all the samples Widgets included in the Prototype Library
//To see how to build you own widgets, look at the sample code at the examples.

import processing.opengl.*;
import prototype.*;
import prototype.widgets.*;

Prototype prototype;
WidgetListener listener;
Part slider;
Part radio;
Part radio2;
Part radio3;
Part button;
Part group;

static final int SLIDER_WIDTH = 300;

void setup() {
  size(800, 600, OPENGL);

  //Its is necessary to initialize an Prototype instance before using the library.
  //Think of it like a canvas where your prototype is gonna run.
  prototype = Prototype.createPrototype(this);

  //Slider widget receives (float min, float max, int width)
  slider = prototype.part(new Slider(0, 500, SLIDER_WIDTH));

  radio = prototype.part(new Radio("I gotta download Prototype Library", true));
  radio2 = prototype.part(new Radio("Or maybe not"));
  radio3 = prototype.part(new Radio("I'm actually not sure of what I want", false, 150));
  
  button = prototype.part(new Button("Nice button"));

  slider.x(50);
  slider.y(50);
  radio.x(50);
  radio.y(130);
  radio2.x(50);
  radio2.y(160);
  radio3.x(50);
  radio3.y(190);
  
  button.x(50);
  button.y(260);
  
  listener = new WidgetListener();
  
  slider.addListener(listener);
  radio.addListener(listener);
  radio2.addListener(listener);
  radio3.addListener(listener);
  button.addListener(listener);
  
 group = prototype.part(
    new RadioGroup(
      150, 10,
      "Group Option 1",
      "Group Option 2",
      "Group Option 3"
    )
  );
  
  group.x(300);
  group.y(130);
  group.addListener(listener);
  
}

void draw() {
  background(#9e9e9e);
  //println(group.value().asInt());
  //println(group.height() +" "+ group.width());
  //println(slider.value().asInt());
  //println(radio.value().asBol());
}

