//This a demo showing all the samples Widgets included in the Prototype Library
//To see how to build you own widgets, look at the sample code at the examples.

import processing.opengl.*;
import prototype.*;
import prototype.widgets.*;

Prototype prototype;
Slider slider;

static final int SLIDER_WIDTH = 300;

void setup() {
  size(800, 600, OPENGL);
  
  //Its is necessary to initialize an Prototype instance before using the library.
  //Think of it like a canvas where your prototype is gonna run.
  prototype = Prototype.createPrototype(this);
  
  //Slider widget receives (float min, float max, int width)
  slider = new Slider(0, 500, SLIDER_WIDTH);
  prototype.part(slider);
  
  slider.x(50);
  slider.y(50);
  slider.width(500);
}

void draw() {
  background(255);
  println(slider.value().asInt());
}

