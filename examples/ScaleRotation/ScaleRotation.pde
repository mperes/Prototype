import processing.opengl.*;
import javax.media.opengl.GL;
import prototype.*;

//Necessary to make straight OpenGL calls.
PGraphicsOpenGL pgl;
GL gl;

PFont font;

Prototype prototype;
Part blueSquare;

int inactivityCounter;

void setup() {
  size(800, 600, OPENGL);
  
  //set vertical sync on to avoid tearing on the scrolling.  
  pgl = (PGraphicsOpenGL) g;
  gl = pgl.beginGL();
  gl.setSwapInterval(1); 
  pgl.endGL();
  
  //Loads some fancy font to use in the button.
  font = loadFont("ArialMT-14.vlw"); 

  //Its is necessary to initialize an Prototype instance before using the library.
  //Think of it like a canvas where your prototype is gonna run.
  prototype = Prototype.createPrototype(this);
  
  blueSquare = prototype.part(new BlueSquare());
  blueSquare.setX(width/2);
  blueSquare.setY(height/2);
  //Could be done instead by, imagePart = prototype.part(new ButtonRecipe(), width/2, height/2);
  //Or by setting 'x = 60; y = 60;' in the ButtonRecipe.

  //A Part can have sbuparts. Subpart last added are drawn on top of the canvas.
  blueSquare.part(new AnchorTL());
  //The second and third parameters are optional, they set the initial location of the Sbupart (Default is 0,0)
  //according to the relation point 'Blueprint.rel' (Default is 0,0).
  ////You should be aware that this location is is relation to the position of the parent bot.
  blueSquare.part(new AnchorTR());
  blueSquare.part(new AnchorBR());
  blueSquare.part(new AnchorBL());
  blueSquare.part(new PivotPoint());
  
  //Sets the MIN/Max values for the part width and height.
  blueSquare.width().constrain(200, 400);
  blueSquare.height().constrain(70, 400);
  
  inactivityCounter = 300;
}

void draw() {
  fill(16, 50);
  rect(0, 0, width, height);
  hint(DISABLE_DEPTH_TEST);
  if(inactivityCounter >= 300) {
    //blueSquare.setRotation( blueSquare.getRotation()+5);
    //blueSquare.setPivotX( map(sin(radians(millis()/20)),-1, 1, 0, 1) );
    //blueSquare.setPivotY( map(cos(radians(millis()/20)),-1, 1, 0, 1) );
  }
  inactivityCounter++;
}

