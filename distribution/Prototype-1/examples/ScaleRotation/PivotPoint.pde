class PivotPoint extends Blueprint {
  boolean dragging = false;
  PivotPoint() {
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
    blueprint.ellipseMode(CORNER);
    blueprint.fill(#FF0000, 200);
    blueprint.ellipse(0, 0, 12, 12);
  }

  public void partEvent(PartEvent e) {
    switch (e.getID()) {
    case PartEvent.PART_PRESSED:
      this.dragging = true;
      break;
    case PartEvent.MOUSE_DRAGGED:
      if (this.dragging) {
        e.part.setX(e.part.getX() + e.localX-e.plocalX);
                e.part.setY(e.part.getY() + e.localY-e.plocalY);
       // handleMouseDrag(e);
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
