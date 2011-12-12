import processing.opengl.*;
import javax.media.opengl.GL;
import prototype.*;
       
//Necessary to make straight OpenGL calls.
PGraphicsOpenGL pgl;
GL gl;

Prototype prototype;
ArrayList<Part> particles;

void setup() {
  size(800, 600, OPENGL);
 
  //set vertical sync on to avoid tearing on the scrolling.  
  pgl = (PGraphicsOpenGL) g;
  gl = pgl.beginGL();
  gl.setSwapInterval(1); 
  pgl.endGL();

  //Its is necessary to initialize an Prototype instance before using the library.
  //Think of it like a canvas where your prototype is gonna run.
  prototype = Prototype.createPrototype(this);
  particles = new ArrayList<Part>();
  for(int p = 0; p < 30; p++) {
    prototype.part(new ParticleRecipe(), random(width), random(height));
  }
}

void draw() {
 background(230);
}
