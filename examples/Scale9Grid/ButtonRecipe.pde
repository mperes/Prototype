class ButtonRecipe extends Blueprint {

  boolean dragging = false;
  ButtonRecipe() {
    //This is mandatory. Call it to set the desired initial Width and Height of you Blueprint.
    size.set(135, 118);
    
    //This configures the 0 scale grid box (left, top, right, bottom).
    scaleGrid = new Box(15, 15, 15, 15);
  }

  //Describe you blueprint here by simple describing a drawing like you would in the main graphics.
  void description() {
    PImage squareRound = loadImage("roundsquare.png");
    blueprint.image(squareRound, 0, 0);
  }

<<<<<<< HEAD
  public void partEvent(PartEvent event) {
    switch (event.getID()) {
=======
  public void partEvent(PartEvent e) {
    switch (e.getID()) {
>>>>>>> Mouse Local Fixed
    case PartEvent.PART_PRESSED:
      this.dragging = true;
      break;
    case PartEvent.MOUSE_DRAGGED:
      if(this.dragging) {
<<<<<<< HEAD
        int incX = event.localMouseX-event.plocalMouseX;
        int incY = event.localMouseY-event.plocalMouseY;
        println(incX);
        println(incY);
        println("--------------");
        buttonPart.pos.set( buttonPart.pos.x + incX, buttonPart.pos.y + incY);
        //event.part.pos.set( event.part.pos.x + incX, event.part.pos.y + incY);
=======
        int modX = e.screenX - e.pscreenX;
        int modY = e.screenY - e.pscreenY;
         e.part.pos.set( e.part.pos.x + modX, e.part.pos.y + modY);
>>>>>>> Mouse Local Fixed
      }
      break;
    case PartEvent.MOUSE_RELEASED:
      dragging = false;
      break;
    }
  }

  //Other Part.Event that can be used
  //PartEvent.MOUSE_PRESSED
  //PartEvent.MOUSE_RELEASED
  //PartEvent.MOUSE_CLICKED
  //PartEvent.MOUSE_DRAGGED
  //PartEvent.MOUSE_MOVED
  //PartEvent.MOUSE_SCROLLED
  //PartEvent.PART_PRESSED
  //PartEvent.PART_RELEASED
  //PartEvent.PART_CLICKED
  //PartEvent.PART_DRAGGED
  //PartEvent.PART_MOVED
  //PartEvent.PART_SCROLLED
}

