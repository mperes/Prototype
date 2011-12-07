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

  public void partEvent(PartEvent event) {
    switch (event.getID()) {
    case PartEvent.PART_PRESSED:
      this.dragging = true;
      break;
    case PartEvent.MOUSE_DRAGGED:
      if(this.dragging) {
        resizePic(event);
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
    //int posX = 
    int incX = e.localMouseX-e.plocalMouseX;
    int incY = e.localMouseY-e.plocalMouseY;
    println(incX);
    println(incY);
    println("-----------------");
      //buttonPart.size.set( buttonPart.size.x - incX, buttonPart.size.y - incY);
      //buttonPart.pos.set( buttonPart.pos.x + incX, buttonPart.pos.y + incY);
      buttonPart.size.set( buttonPart.size.x - incX, buttonPart.size.y);
      buttonPart.pos.set( buttonPart.pos.x + incX, buttonPart.pos.y);
    //buttonPart.size.set( buttonPart.size.x - (mouseX-pmouseX), buttonPart.size.y - (mouseY-pmouseY));
    //buttonPart.pos.set( buttonPart.pos.x + (mouseX-pmouseX), buttonPart.pos.y + (mouseY-pmouseY));
  }
}

class AnchorRecipeTR extends AnchorRecipe {
  boolean dragging = false;
  AnchorRecipeTR() {
    super(); //Always call Super().
    rel.set(1,0); //Relative to the top right corner;
  }

  void resizePic(PartEvent e) {
    buttonPart.size.set( buttonPart.size.x + (mouseX-pmouseX), buttonPart.size.y - (mouseY-pmouseY));
    buttonPart.pos.set( buttonPart.pos.x, buttonPart.pos.y + (mouseY-pmouseY));
  }
}

class AnchorRecipeBR extends AnchorRecipe {
  AnchorRecipeBR() {
    super(); //Always call Super().
    rel.set(1,1); //Relative to the bottom right corner;
  }

  void resizePic(PartEvent e) {
    buttonPart.size.set( buttonPart.size.x + (mouseX-pmouseX), buttonPart.size.y + (mouseY-pmouseY));
  }
}

class AnchorRecipeBL extends AnchorRecipe {
  AnchorRecipeBL() {
    super(); //Always call Super().
    rel.set(0,1); //Relative to the bottom left corner;
  }

  void resizePic(PartEvent e) {
    buttonPart.size.set( buttonPart.size.x - (mouseX-pmouseX), buttonPart.size.y + (mouseY-pmouseY));
    buttonPart.pos.set( buttonPart.pos.x + (mouseX-pmouseX), buttonPart.pos.y);
  }
}

