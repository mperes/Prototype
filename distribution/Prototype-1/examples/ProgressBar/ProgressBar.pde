import processing.opengl.*;
import javax.media.opengl.GL;
import prototype.*;

//Necessary to make straight OpenGL calls.
PGraphicsOpenGL pgl;
GL gl;

Prototype prototype;

final int progressBarW = 300;
final int progressBarH = 30;
final int progressBarX = 50;
final int progressBarY = 50;

Part progress;
Part knob;
Part highlight;

PFont font;

void setup() {
  size(400, 130, OPENGL);

  //set vertical sync on to avoid tearing on the scrolling.  
  pgl = (PGraphicsOpenGL) g;
  gl = pgl.beginGL();
  gl.setSwapInterval(1); 
  pgl.endGL();

  font = loadFont("ArialMT-14.vlw"); 
  
  //Its is necessary to initialize an Prototype instance before using the library.
  //Think of it like a canvas where your prototype is gonna run.
  prototype = Prototype.createPrototype(this);

  Part container = prototype.part(new Container(), progressBarX, progressBarY);
  //prototype.part(new LabelRecipe(), 175, 5);
  progress = container.part(new Progress());
  knob = progress.part(new Knob());
  highlight = knob.part(new KnobHighlight());


  container.setWidth(progressBarW);
  container.setHeight(progressBarH);

  knob.x().constrain(knob.getWidth(), progressBarW);
  progress.width().constrain(0, progressBarW);
  progress.setWidth(knob.getX());

  highlight.setAlpha(0);
}

void draw() {
  background(255);
}

