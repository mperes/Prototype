import processing.opengl.*;
import javax.media.opengl.GL;
import prototype.*;

//Necessary to make straight OpenGL calls.
PGraphicsOpenGL pgl;
GL gl;

PFont font;

Prototype prototype;
Part imagePart;
Part anchorTL;

int inactivityCounter;

void setup() {
  size(800, 600, OPENGL);
  hint(ENABLE_OPENGL_4X_SMOOTH); 
  
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
  
  imagePart = prototype.part(new ImageRecipe());
  imagePart.setX(width/2);
  imagePart.setY(height/2);
  //Could be done instead by, imagePart = prototype.part(new ButtonRecipe(), width/2, height/2);
  //Or by setting 'x = 60; y = 60;' in the ButtonRecipe.

  //A Part can have sbuparts. Subpart last added are drawn on top of the canvas.
  anchorTL = imagePart.part(new AnchorRecipeTL());
  //The second and third parameters are optional, they set the initial location of the Sbupart (Default is 0,0)
  //according to the relation point 'Blueprint.rel' (Default is 0,0).
  ////You should be aware that this location is is relation to the position of the parent bot.
  imagePart.part(new AnchorRecipeTR());
  imagePart.part(new AnchorRecipeBR());
  imagePart.part(new AnchorRecipeBL());
  imagePart.part(new LabelRecipe());
  
  //Sets the MIN/Max values for the part width and height.
  imagePart.width().constrain(200, 400);
  imagePart.height().constrain(70, 400);
  
  inactivityCounter = 300;
}

void draw() {
  background(255);
  if(inactivityCounter >= 300) {
    imagePart.setRotation( imagePart.getRotation()+.5);
  }
  inactivityCounter++;
}

