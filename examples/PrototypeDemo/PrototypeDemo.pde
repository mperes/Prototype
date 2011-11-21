import processing.opengl.*;
import codeanticode.glgraphics.*;
import Prototype.*;

Prototype prototype;
Part imagePart;
Part anchorTL;
GLGraphicsOffScreen standard;

void setup() {
  size(800, 600, GLConstants.GLGRAPHICS);
  smooth();
   standard = new GLGraphicsOffScreen(this, 800, 200, true);
  
  //Its is necessary to initialize an Prototype instance before using the library.
  //Think of it like a canvas where your prototype is gonna run.
  
  prototype = Prototype.createPrototype(this);
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
    standard.beginDraw();
  testDraw(standard);
  standard.endDraw();  
  image(standard.getTexture(), 0, 200);
  
  prototype.part(new BallRecipe(), 200, 200);
}

void draw() {
  //background(0);
}

void testDraw(GLGraphicsOffScreen renderer) {
  // draw ellipses
  renderer.noStroke();
  renderer.fill(255, 0, 0, 255);
  renderer.ellipse(500, 100, 180, 180);  
  // draw gradient
  for(int i = 0; i < 200; i++) {
    renderer.stroke(0, (i/200f)*255);
    renderer.line(600+i, 0, 600+i, 200);
  } 
}
