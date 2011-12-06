import processing.opengl.*;
import javax.media.opengl.GL;
import Prototype.*;
       
Prototype prototype;
Part buttonPart;
Part anchorTL;

//Necessary to make straight OpenGL calls.
PGraphicsOpenGL pgl;
GL gl;

PGraphics teste;
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

  buttonPart = prototype.part(new ButtonRecipe());
  buttonPart.pos.set(60, 60);
  //Could be done instead by, imagePart = prototype.part(new ImageRecipe(), 60, 60);
  //Or by setting Blueprint.pos(x, y).
  
  //A Part can have sbuparts. Subpart last added are drawn on top of the canvas.
  Part anchorTL = buttonPart.subpart(new AnchorRecipeTL());
  //The second and third parameters are optional, they set the initial location of the Sbupart (Default is 0,0)
  //according to the relation point 'Blueprint.rel' (Default is 0,0).
  ////You should be aware that this location is is relation to the position of the parent bot.
  buttonPart.subpart(new AnchorRecipeTR());
  buttonPart.subpart(new AnchorRecipeBR());
  buttonPart.subpart(new AnchorRecipeBL());
  buttonPart.subpart(new LabelRecipe());
  buttonPart.size.setMinMax(220, 400, 70, 400); //Sets the MIN/Max values for the part width and height.
}

void draw() {
  background(40);
}