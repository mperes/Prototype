import processing.opengl.*;
import javax.media.opengl.GL;

import prototype.*;
import prototype.behaviors.mouse.*;



//Necessary to make straight OpenGL calls.
PGraphicsOpenGL pgl;
GL gl;

PFont font;

Prototype prototype;
Part blueSquare;
Part anchorTopLeft;
Part anchorTopRight;
Part anchorBottomRight;
Part anchorBottomLeft;
Part pivot;

int inactivityCounter;

void setup() {
  size(800, 600, OPENGL);

  //set vertical sync on to avoid tearing on the scrolling.  
  pgl = (PGraphicsOpenGL) g;
  gl = pgl.beginGL();
  gl.setSwapInterval(1); 
  pgl.endGL();

  inactivityCounter = 300;
  
  //Its is necessary to initialize an Prototype instance before using the library.
  //Think of it like a canvas where your prototype is gonna run.
  prototype = Prototype.createPrototype(this);

  //Instead of using image texture, you can use a regular processing drawing
  //function. In this case it is necessary it is also necessary to pass the width
  //and the height of the drawing (I will fix this in the future).
  //In the example bellow, I am using a anonnymous class, you can also
  //define  a class that implements DynamicImage (see Anchor tab) also.
  blueSquare = prototype.part(300, 200,
  new ShapeRender() {
    public void draw() {
      noStroke();
      fill(#10b0e4);
      smooth();
      rect(0, 0, 300, 200);
    }
  }
  );

  //Define some anchors for the square:
  //Pay attention that in the PartBuilder constructor I am passing SHAPE.
  //this allows me to pass a DynamicImage as texutre. In this case,
  //You also need to specify the part width and height.
  //If you notice some anchors are scaled by negative values, this is just
  //to operate a mirror on the part model.
  anchorTopLeft = blueSquare.part(
  new PartBuilder(Prototype.SHAPE).
    states(new Anchor()).
    pivotX(0.5f).
    pivotY(0.5f).
    width(12).
    height(12).
    scaleX(-1).
    scaleY(-1).
    behaviors(new ScaleBehavior())
    );

  anchorTopRight = blueSquare.part(
 new PartBuilder(Prototype.SHAPE).
    states(new Anchor()).
    pivotX(0.5f).
    pivotY(0.5f).
    width(12).
    height(12).
    relX(1.0f).
    scaleY(-1).
    behaviors(new ScaleBehavior())
    );

  anchorBottomRight = blueSquare.part(
  new PartBuilder(Prototype.SHAPE).
    states(new Anchor()).
    pivotX(0.5f).
    pivotY(0.5f).
    width(12).
    height(12).
    relX(1.0f).
    relY(1.0f).
    behaviors(new ScaleBehavior())
    );

  anchorBottomLeft = blueSquare.part(
  new PartBuilder(Prototype.SHAPE).
    states(new Anchor()).
    pivotX(0.5f).
    pivotY(0.5f).
    width(12).
    height(12).
    relY(1.0f).
    scaleX(-1).
    behaviors(new ScaleBehavior())
    );
      
  //Adds the ChangePivot Behavior to the blueSquare part.
  blueSquare.addBehavior(new ChangePivot());

  //Sets the pivot point to the middle of the bluesquare.
  blueSquare.pivotX(0.5f);
  blueSquare.pivotY(0.5f);
  
  blueSquare.showPivot(true);

  blueSquare.x(width/2);
  blueSquare.y(height/2);
  
  blueSquare.width.constrain(100, 600);
  blueSquare.height.constrain(100, 600);
}
void draw() {
  fill(16, 50);
  rect(0, 0, width, height);
  hint(DISABLE_DEPTH_TEST);
  if(inactivityCounter >= 300) {
    blueSquare.rotation( blueSquare.rotation()+5);
  }
  inactivityCounter++;
}

void mouseMoved() {
  inactivityCounter = 0;
}

