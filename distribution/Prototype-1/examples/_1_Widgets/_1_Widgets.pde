//This a demo showing all the samples Widgets included in the Prototype Library
//To see how to build you own widgets, look at the sample code at the examples.

import processing.opengl.*;
import javax.media.opengl.GL;
import prototype.*;
import prototype.widgets.*;

PGraphicsOpenGL pgl;
GL gl;

Prototype prototype;
WidgetListener listener;
Part slider;
Part radio;
Part radio2;
Part radio3;
Part button;
Part group;

PImage pstate;

static final int SLIDER_WIDTH = 300;

void setup() {
  size(800, 600, P3D);

  //Its is necessary to initialize an Prototype instance before using the library.
  //Think of it like a canvas where your prototype is gonna run.
  prototype = Prototype.createPrototype(this);

/*
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
  */
}

void draw() {
  //background(#9e9e9e);
  clearGL(#9e9e9e);
  beginMask(50, 50, 150, 150);
  fill(255, 0, 0);
  rect(100, 100, 150, 150);
  endMask();
  //println(group.value().asInt());
  //println(group.height() +" "+ group.width());
  //println(slider.value().asInt());
  //println(radio.value().asBol());
}

void clearGL(int c) {
  background(255);
  pgl = (PGraphicsOpenGL) g;
  gl = pgl.beginGL();
  gl.glDisable(GL.GL_DEPTH_TEST);
  gl.glEnable(GL.GL_BLEND);
  gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
  fill(c);
  rect(0, 0, width, height);
  gl.glEnable(GL.GL_DEPTH_TEST);
  gl.glDisable(GL.GL_BLEND);
}

void beginMask(int x, int y, int w, int h) {
  pgl = (PGraphicsOpenGL) g;
  gl = pgl.beginGL();
 // gl.glAlphaFunc(GL.GL_GREATER, 0.0);
  gl.glDisable(GL.GL_DEPTH_TEST);
  gl.glEnable(GL.GL_BLEND);
 // gl.glEnable(GL.GL_APHA_TEST);
  gl.glBlendFunc(GL.GL_ZERO, GL.GL_ONE_MINUS_SRC_ALPHA);
  tint(0,0,0,255);
  noStroke();
  rect(x, y, w, h);
  gl.glBlendFunc(GL.GL_ONE_MINUS_DST_ALPHA, GL.GL_ONE);
}

void endMask() {
  gl.glEnable(GL.GL_DEPTH_TEST);
  gl.glDisable(GL.GL_BLEND);
  pgl.endGL();
  image(Prototype.offScreenBuffer.get(100, 100, 150, 150), 100, 100);
//  gl.glDisable(GL.GL_APHA_TEST);
}
