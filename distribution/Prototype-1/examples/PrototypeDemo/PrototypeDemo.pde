import processing.opengl.*;
import Prototype.*;

       PFont myFont;
       
Prototype prototype;
Part imagePart;
Part anchorTL;
Part bar;
Part knob;
PGraphics teste;
void setup() {
  size(800, 600, OPENGL);  
  //frameRate(2);
//       myFont = createFont("Verdana-24",18);
  //Its is necessary to initialize an Prototype instance before using the library.
  //Think of it like a canvas where your prototype is gonna run.
  prototype = Prototype.createPrototype(this);
  bar = prototype.part(new Bar(), 60, 60);
  knob = bar.subpart(new Knob());
  //imagePart = prototype.part(new ImageRecipe());
  //imagePart.pos.set(60, 60);
  //Could be done instead by, imagePart = prototype.part(new ImageRecipe(), 60, 60);
  //Or by setting Blueprint.pos(x, y).
  
  //A Part can have sbuparts. Subpart last added are drawn on top of the canvas.
 // Part anchorTL = imagePart.subpart(new AnchorRecipeTL());
  //The second and third parameters are optional, they set the initial location of the Sbupart (Default is 0,0)
  //according to the relation point 'Blueprint.rel' (Default is 0,0).
  ////You should be aware that this location is is relation to the position of the parent bot.
  //imagePart.subpart(new AnchorRecipeTR());
  //imagePart.subpart(new AnchorRecipeBR());
  //imagePart.subpart(new AnchorRecipeBL());
  //prototype.part(new TextRecipe(), 100, 100);
}

void draw() {
  background(40);
  println(knob.pos.x);
  println(knob.pos.maxX);
}
