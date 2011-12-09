class AnchorRecipe extends Blueprint {
  boolean dragging = false;
  AnchorRecipe() {
    //This is mandatory. Call it to set the desired initial Width and Height of you Blueprint
    size.set(12, 12);
    //Sets the pivot point to the center of the image. This can also be done at runtime,
    //Part.pivot.set(x, y)
    pivot.set(.5, .5);

    //These options can also be configured in the blueprint. Options bellow are the default config.
    //pos.set(0, 0); //Initia position.
    //scale.set(1, 1);
    //rel.set(0, 0); // Point in relation to the parent.
    //visible = true; //A invisible object does not receive mouse events and is not displayed.
    //enabled = true; //Set receiving mouse events on/off.
    //alpha = 1;
    //showPivot = false;
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
      if(this.dragging) {
        resizePic(e);
      }
      break;
    case PartEvent.MOUSE_RELEASED:
      dragging = false;
      break;
    }
  }

  void resizePic(PartEvent e) {
  }
}

//You can reutilize your own blueprints by extending them.
class AnchorRecipeTL extends AnchorRecipe {
  AnchorRecipeTL() {
    super(); //Always call Super().
  }

  void resizePic(PartEvent e) {
<<<<<<< HEAD
    int incX = e.localMouseX-e.plocalMouseX;
    int incY = e.localMouseY-e.plocalMouseY;
    buttonPart.size.set( buttonPart.size.x - incX, buttonPart.size.y - incY);
    buttonPart.pos.set( buttonPart.pos.x + incX, buttonPart.pos.y + incY);
=======
    int modX = e.localX - e.plocalX;
    int modY = e.localY - e.plocalY;
    buttonPart.size.set( buttonPart.size.x - modX, buttonPart.size.y -  modY);
    buttonPart.pos.set( buttonPart.pos.x + modX, buttonPart.pos.y + modY);
>>>>>>> Mouse Local Fixed
  }
}

class AnchorRecipeTR extends AnchorRecipe {
  boolean dragging = false;
  AnchorRecipeTR() {
    super(); //Always call Super().
    rel.set(1,0); //Relative to the top right corner;
  }

<<<<<<< HEAD
  void resizePic(PartEvent e) {
    int incX = e.localMouseX-e.plocalMouseX;
    int incY = e.localMouseY-e.plocalMouseY;
    buttonPart.size.set( buttonPart.size.x + incX, buttonPart.size.y - incY);
    buttonPart.pos.set( buttonPart.pos.x, buttonPart.pos.y + incY);
=======
  void resizePic(PartEvent e) {    
    int modX = e.localX - e.plocalX;
    int modY = e.localY - e.plocalY;
    buttonPart.size.set( buttonPart.size.x + modX, buttonPart.size.y -  modY);
    buttonPart.pos.set( buttonPart.pos.x, buttonPart.pos.y + modY);
>>>>>>> Mouse Local Fixed
  }
}

class AnchorRecipeBR extends AnchorRecipe {
  AnchorRecipeBR() {
    super(); //Always call Super().
    rel.set(1,1); //Relative to the bottom right corner;
  }

<<<<<<< HEAD
  void resizePic(PartEvent e) {
    int incX = e.localMouseX-e.plocalMouseX;
    int incY = e.localMouseY-e.plocalMouseY;
    buttonPart.size.set( buttonPart.size.x + incX, buttonPart.size.y + incY);
=======
  void resizePic(PartEvent e) {    
    int modX = e.localX - e.plocalX;
    int modY = e.localY - e.plocalY;
    buttonPart.size.set( buttonPart.size.x + modX, buttonPart.size.y +  modY);
>>>>>>> Mouse Local Fixed
  }
}

class AnchorRecipeBL extends AnchorRecipe {
  AnchorRecipeBL() {
    super(); //Always call Super().
    rel.set(0,1); //Relative to the bottom left corner;
  }

  void resizePic(PartEvent e) {
<<<<<<< HEAD
    int incX = e.localMouseX-e.plocalMouseX;
    int incY = e.localMouseY-e.plocalMouseY;
    buttonPart.size.set( buttonPart.size.x - incX, buttonPart.size.y + incY);
    buttonPart.pos.set( buttonPart.pos.x + incX, buttonPart.pos.y);
=======
    int modX = e.localX - e.plocalX;
    int modY = e.localY - e.plocalY;
    buttonPart.size.set( buttonPart.size.x - modX, buttonPart.size.y +  modY);
    buttonPart.pos.set( buttonPart.pos.x + modX, buttonPart.pos.y);
>>>>>>> Mouse Local Fixed
  }
}

