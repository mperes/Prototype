import processing.opengl.*;
import javax.media.opengl.GL;
import Prototype.*;

Prototype prototype;
Part buttonPart;
Part anchorTL;

//Necessary to make straight OpenGL calls.
PGraphicsOpenGL pgl;
GL gl;

PFont font;

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

  buttonPart = prototype.part(new ButtonRecipe());
  buttonPart.setX(width/2);
  buttonPart.setY(height/2);
  //Could be done instead by, imagePart = prototype.part(new ButtonRecipe(), 60, 60);
  //Or by setting 'x = 60; y = 60;' in the ButtonRecipe.

  //A Part can have sbuparts. Subpart last added are drawn on top of the canvas.
  anchorTL = buttonPart.part(new AnchorRecipeTL());
  //The second and third parameters are optional, they set the initial location of the Sbupart (Default is 0,0)
  //according to the relation point 'Blueprint.rel' (Default is 0,0).
  ////You should be aware that this location is is relation to the position of the parent bot.
  buttonPart.part(new AnchorRecipeTR());
  buttonPart.part(new AnchorRecipeBR());
  buttonPart.part(new AnchorRecipeBL());
  Part label = buttonPart.part(new LabelRecipe());
  
  //Sets the MIN/Max values for the part width and height.
  buttonPart.width().constrain(220, 400);
  buttonPart.height().constrain(70, 400);

  buttonPart.setRotation(30);
}

void draw() {
  background(255);
}

