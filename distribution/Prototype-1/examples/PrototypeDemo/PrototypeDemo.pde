import Prototype.*;

Prototype prototype;
Part imagePart;
Part anchorTL;
void setup() {
  size(800, 600);
  
  //Its is necessary to initialize an Prototype instance before using the library.
  //Think of it like a canvas where your prototype is gonna run.
  prototype = new Prototype(this);
  imagePart = prototype.part(new ImageRecipe());
  imagePart.pos.set(60, 60);
  //Could be done instead by, imagePart = prototype.part(new ImageRecipe(), 60, 60);
  //Or by setting Blueprint.pos(x, y).
  
  //A Part can have sbuparts. Subpart last added are drawn on top of the canvas.
  Part anchorTL = imagePart.subpart(new AnchorRecipeTL());
  //The second and third parameters are optional, they set the initial location of the Sbupart (Default is 0,0)
  //according to the relation point 'Blueprint.rel' (Default is 0,0).
  //You should be aware that this location is is relation to the position of the parent bot.
  imagePart.subpart(new AnchorRecipeTR());
  imagePart.subpart(new AnchorRecipeBR());
  imagePart.subpart(new AnchorRecipeBL());
}

void draw() {
  rotate(radians(30));
  background(0);
}
