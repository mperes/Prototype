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

static final int SLIDER_WIDTH = 300;

void setup() {
  size(800, 600, OPENGL);

  //Its is necessary to initialize an Prototype instance before using the library.
  //Think of it like a canvas where your prototype is gonna run.
  // prototype = Prototype.createPrototype(this);

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
  background(0);
  translate(width/2, height/2);
  rotateZ(PI/3);
  //hint(DISABLE_DEPTH_TEST);
 // clip(50, 50, 60, 60);
  fill(0, 0, 255);
  noStroke();
  rect(30, 30, 150, 150);
 // noClip();

  beginMask(50, 50, 150, 150);

  fill(255, 0, 0, 255);
  noStroke();
  rect(100, 100, 150, 150);

  endMask();
  //println(group.value().asInt());
  //println(group.height() +" "+ group.width());
  //println(slider.value().asInt());
  //println(radio.value().asBol());
}

void clearGL(int c) {  
  background(255);
}

void beginMask(int x, int y, int w, int h) {
  pgl = (PGraphicsOpenGL) g;
  gl = pgl.beginGL();
  gl.glColorMask(false, false, false, false); 
  gl.glDepthMask(true);
  //translate(0, 0, 10);
  fill(255, 255);
  noStroke();
  rect(x, y, w, h);
  gl.glDepthMask(false);
  gl.glColorMask(true, true, true, true);
  //translate(0, 0, 12); 
  gl.glDepthFunc(GL.GL_EQUAL);
  /*
  gl.glDisable(GL.GL_DEPTH_TEST);
  gl.glEnable(GL.GL_BLEND);
  gl.glBlendFunc(GL.GL_DST_COLOR, GL.GL_ZERO);
  fill(255, 0);
  noStroke();
  rect(x, y, w, h);
  gl.glBlendFunc(GL.GL_ONE_MINUS_DST_ALPHA, GL.GL_ONE);
  //pgl.endGL();
  */
}

void endMask() {
  //gl.glEnable(GL.GL_DEPTH_TEST);
  //gl.glDisable(GL.GL_BLEND);
  pgl.endGL();
}

void clip(float x1, float y1, float x2, float y2) {
  pgl = (PGraphicsOpenGL) g;
  gl = pgl.beginGL();
  flush();
  gl.glEnable(GL.GL_SCISSOR_TEST);

  float h = y2 - y1;
  gl.glScissor((int)x1, (int)(height - y1 - h), (int)(x2 - x1), (int)h);
}

public void noClip() {
  pgl = (PGraphicsOpenGL) g;
  gl = pgl.beginGL();
  flush();
  gl.glDisable(GL.GL_SCISSOR_TEST);
}

