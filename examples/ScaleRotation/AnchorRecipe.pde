class AnchorRecipe extends Blueprint {
  boolean dragging = false;
  AnchorRecipe() {
    //As a rule of a thumb, always call super() as your first line of the constructor.
    super();

    //This is mandatory. Call it to set the desired initial Width and Height of you Blueprint
    width = 12;
    height = 12;
    //Sets the pivot point to the center of the image. This can also be done at runtime,
    //Part.pivot.set(x, y)
    pivotX = .5;
    pivotY = .5;

    //This you can play with:
    /*
    int x;
    int y;
    float relX;
    float relY;
    int width;
    int height;
    float scaleX;
    float scaleY;
    float pivotX;
    float pivotY;
    float rotation;
    float alpha;

    boolean visible;
    boolean enabled;
    boolean showPivot;
    boolean unique ;
    */
  }

  void description() {
    blueprint.smooth();
    blueprint.ellipseMode(CORNER);
    //Circle size is set to 11 due to the line stroke.
    blueprint.ellipse(0, 0, 11, 11);
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
    int newWidth = imagePart.getWidth() - w*(e.localX - e.plocalX);
    int newHeight = imagePart.getHeight() - h*(e.localY - e.plocalY);
    imagePart.setWidth(newWidth);
    imagePart.setHeight(newHeight);
  }
}

//You can reutilize your own blueprints by extending them.
class AnchorRecipeTL extends AnchorRecipe {
  AnchorRecipeTL() {
    super(); //Always call Super().
    //Relative to top left (Default)
    //relX = 0;
    //relX = 0;
  }
  void handleMouseDrag(PartEvent e) {
    resizePic(e, 1, 1);
  }
}

class AnchorRecipeTR extends AnchorRecipe {
  boolean dragging = false;
  AnchorRecipeTR() {
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

class AnchorRecipeBR extends AnchorRecipe {
  AnchorRecipeBR() {
    super(); //Always call Super().
    //Relative to the bottom right corner;
    relX = 1;
    relY = 1;
  }

  void handleMouseDrag(PartEvent e) {    
    resizePic(e, -1, -1);
  }
}

class AnchorRecipeBL extends AnchorRecipe {
  AnchorRecipeBL() {
    super(); //Always call Super().
    //Relative to the bottom left corner;
    relX = 0;
    relY = 1;
  }

  void handleMouseDrag(PartEvent e) {
    resizePic(e, 1, -1);
  }
}

