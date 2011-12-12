import processing.opengl.*;
import javax.media.opengl.GL;
import prototype.*;
       
//Necessary to make straight OpenGL calls.
PGraphicsOpenGL pgl;
GL gl;

Prototype prototype;

final int progressBarX = 50;
final int progressBarY = 50;
final int progressBarW = 300;
final int progressBarH = 30;

Part progress;
Part knob;

PFont font;


int inactivityCounter;

void setup() {
  size(800, 600, OPENGL);
 
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
  prototype.part(new LabelRecipe(), 175, 10);
  progress = container.part(new Progress());
  knob = progress.part(new Knob());
  
  
  container.setWidth(progressBarW);
  container.setHeight(progressBarH);
  
   knob.x().constrain(knob.getWidth(), progressBarW);
   progress.width().constrain(0, progressBarW);
   progress.setWidth(knob.getX());
   
    inactivityCounter = 300;
  
}

void draw() {
 background(230);
   if(inactivityCounter >= 300) {
    knob.setX(map( sin(radians(millis()/20)), -1, 1, knob.getWidth(), progressBarW ));
    progress.setWidth(knob.getX());
  }
  inactivityCounter++;
}
