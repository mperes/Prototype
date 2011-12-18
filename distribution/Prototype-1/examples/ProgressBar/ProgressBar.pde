//This is a little demo of a component built using the prototype libray. The code for this widget
//was already put in te library and can be invoked by adding a new Part of the subclass Slide.

import processing.opengl.*;
import prototype.*;

Prototype prototype;

PFont font;
static final int SLIDER_WIDTH = 300;

void setup() {
  size(400, 130, OPENGL);
  
  //Its is necessary to initialize an Prototype instance before using the library.
  //Think of it like a canvas where your prototype is gonna run.
  prototype = Prototype.createPrototype(this);

  Part slider = prototype.part(new MySlider(SLIDER_WIDTH));
  
  for(int x = 0; x < 100; x++) {
    //Part newPart = prototype.part(new MySlider(SLIDER_WIDTH));
    //newPart.x( random(width) );
    //newPart.y( random(height) );
    //newPart.visible(false);
  }
  
  slider.x( width/2-slider.width()/2);
  slider.y(height/2-slider.height()/2);
}

void draw() {
  background(255);
  println(frameRate);
}

