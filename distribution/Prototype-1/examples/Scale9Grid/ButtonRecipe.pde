class ButtonRecipe extends Blueprint {

  boolean dragging = false;
  ButtonRecipe() {
    //This is mandatory. Call it to set the desired initial Width and Height of you Blueprint.
    size.set(135, 118);
    scaleGrid = new Box(15, 15, 15, 15);
  }

  //Describe you blueprint here by simple describing a drawing like you would in the main graphics.
  void description() {
    PImage squareRound = loadImage("roundsquare.png");
    blueprint.image(squareRound, 0, 0);
  }

  public void partEvent(PartEvent event) {
<<<<<<< HEAD
    switch (event.getID()) {	
    case PartEvent.PART_DRAGGED:
        int incX = event.localMouseX-event.plocalMouseX;
    int incY = event.localMouseY-event.plocalMouseY;
    println(incX);
    println(incY);
    println("---------------");
      //event.part.pos.set( event.part.pos.x + mouseX-pmouseX, event.part.pos.y + mouseY-pmouseY);
      event.part.pos.set( event.part.pos.x + event.localMouseX-event.plocalMouseX, event.part.pos.y + event.localMouseY-event.plocalMouseY);
=======
    switch (event.getID()) {
    case PartEvent.PART_PRESSED:
      this.dragging = true;
      break;
    case PartEvent.MOUSE_DRAGGED:
      if(this.dragging) {
        //int incX = event.localMouseX-event.plocalMouseX;
        //int incY = event.localMouseY-event.plocalMouseY;
        int incX = mouseX-pmouseX;
        int incY = mouseY-pmouseY;
        println(incX);
        println(incY);
        println("--------------");
        buttonPart.pos.set( buttonPart.pos.x + incX, buttonPart.pos.y + incY);
        //event.part.pos.set( event.part.pos.x + incX, event.part.pos.y + incY);
      }
      break;
    case PartEvent.MOUSE_RELEASED:
      dragging = false;
>>>>>>> Mouse Local Fixed
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

