//This a demo showing all the samples Widgets included in the Prototype Library
//To see how to build you own widgets, look at the sample code at the examples.

import processing.opengl.*;
import prototype.*;
import prototype.widgets.*;

Prototype prototype;
Part slider;
Part radio;

static final int SLIDER_WIDTH = 300;

void setup() {
  size(800, 600, OPENGL);

  //Its is necessary to initialize an Prototype instance before using the library.
  //Think of it like a canvas where your prototype is gonna run.
  prototype = Prototype.createPrototype(this);

  //Slider widget receives (float min, float max, int width)
  slider = prototype.part(new Slider(0, 500, SLIDER_WIDTH));

  radio = prototype.part(new Radio("I love this job"));
  Part radio2 = prototype.part(new Radio("I am kidding..."));
  Part radio3 = prototype.part(new Radio("I dont know"));

  slider.x(50);
  slider.y(50);
  radio.x(50);
  radio.y(100);
  radio2.x(50);
  radio2.y(130);
  radio3.x(50);
  radio3.y(160);
}

void draw() {
  background(#9e9e9e);
}

