import processing.opengl.*;
import javax.media.opengl.GL;
import Prototype.*;
       
//Necessary to make straight OpenGL calls.
PGraphicsOpenGL pgl;
GL gl;

Prototype prototype;

int containerW = 300;
int containerH = 30;

Part progress;

PGraphics teste;
void setup() {
  size(800, 600, OPENGL);
 
   //set vertical sync on to avoid tearing on the scrolling.  
  //pgl = (PGraphicsOpenGL) g;
  //gl = pgl.beginGL();
  //gl.setSwapInterval(1); 
  //pgl.endGL();
 
 //hint(DISABLE_OPENGL_2X_SMOOTH);

  //Its is necessary to initialize an Prototype instance before using the library.
  //Think of it like a canvas where your prototype is gonna run.
  prototype = Prototype.createPrototype(this);
  
  Part container = prototype.part(new Container(), 50, 50);
  progress = container.subpart(new Progress());
  Part knob = progress.subpart(new Knob());
  progress.size.x = 5;
  
}

void draw() {
  background(16);
  //println(progress.size.x);
    //println(progress.size.y);
    //println(progress.size.minY);
    //println(progress.size.maxX);
    //println("-----------------------");
}
