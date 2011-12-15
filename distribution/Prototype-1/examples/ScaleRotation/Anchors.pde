class Anchor extends Blueprint {
  boolean dragging = false;
  Anchor() {
    //As a rule of a thumb, always call super() as your first line of the constructor.
    super();

    //This is mandatory. Call it to set the desired initial Width and Height of you Blueprint
    width = 12;
    height = 12;
    //Sets the pivot point to the center of the image. This can also be done at runtime,
    //Part.pivot.set(x, y)
    pivotX = .5;
    pivotY = .5;

    //Sets the the part as a shape part. Draws the description everyframe instead of using a Image buffer.
    //Default type is Part.IMAGE.
    type = Part.SHAPE;
    
    collisionMethod = Part.BOX;
  }

  void description() {
    blueprint.smooth();
    blueprint.noStroke();
    blueprint.rectMode(CORNER);
    blueprint.fill(#e410dd, 200);
    blueprint.rect(0, 0, 12, 12);
  }

  public void partEvent(PartEvent e) {
    switch (e.getID()) {
    case PartEvent.PART_PRESSED:
      this.dragging = true;
      break;
    case PartEvent.MOUSE_DRAGGED:
      if (this.dragging) {
        handleMouseDrag(e);
      }
      break;
    case PartEvent.MOUSE_RELEASED:
      dragging = false;
      break;
    }
  }

  void handleMouseDrag(PartEvent e) {
  }

  void resizePic(PartEvent e, int w, int h) {
    int newWidth = blueSquare.getWidth() - w*(e.localX - e.plocalX);
    int newHeight = blueSquare.getHeight() - h*(e.localY - e.plocalY);
    blueSquare.setWidth(newWidth);
    blueSquare.setHeight(newHeight);
  }
}

//You can reutilize your own blueprints by extending them.
class AnchorTL extends Anchor {
  AnchorTL() {
    super(); //Always call Super().
    //Relative to top left (Default)
    //relX = 0;
    //relX = 0;
  }
  void handleMouseDrag(PartEvent e) {
    resizePic(e, 1, 1);
  }
}

class AnchorTR extends Anchor {
  boolean dragging = false;
  AnchorTR() {
    super(); //Always call Super().

    //Relative to the top right corner;
    relX = 1;
    //relY = 0;
  }

  void handleMouseDrag(PartEvent e) {    
    resizePic(e, -1, 1);
    //resizePic(e, 0, 1, 0, 1);
  }
}

class AnchorBR extends Anchor {
  AnchorBR() {
    super(); //Always call Super().
    //Relative to the bottom right corner;
    relX = 1;
    relY = 1;
  }

  void handleMouseDrag(PartEvent e) {    
    resizePic(e, -1, -1);
  }
}

class AnchorBL extends Anchor {
  AnchorBL() {
    super(); //Always call Super().
    //Relative to the bottom left corner;
    relX = 0;
    relY = 1;
  }

  void handleMouseDrag(PartEvent e) {
    resizePic(e, 1, -1);
  }
}

