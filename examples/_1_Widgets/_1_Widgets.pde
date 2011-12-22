//This a demo showing all the samples Widgets included in the Prototype Library
//To see how to build you own widgets, look at the sample code at the examples.

import processing.opengl.*;
import prototype.*;
import prototype.widgets.*;

Prototype prototype;
Part slider;

static final int SLIDER_WIDTH = 300;

void setup() {
  size(800, 600, OPENGL);
  
  //Its is necessary to initialize an Prototype instance before using the library.
  //Think of it like a canvas where your prototype is gonna run.
  prototype = Prototype.createPrototype(this);
  
  //Slider widget receives (float min, float max, int width)
  slider = prototype.part(new Slider(0, 500, SLIDER_WIDTH));
  
  slider.x(50);
  slider.y(50);
}

void draw() {
  background(255);
  println(slider.value().asInt());
}

