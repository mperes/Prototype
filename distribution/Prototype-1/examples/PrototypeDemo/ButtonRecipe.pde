class ButtonRecipe extends Blueprint {

  ButtonRecipe() {
    //This is mandatory. Call it to set the desired initial Width and Height of you Blueprint.
    super(185, 70);
    scaleGrid = new Box(15, 15, 15, 15);
    //size.setMinMax(185, 400, 70, 400); //Set the Min/Max values for the width and height of this part.
  }

  //Describe you blueprint here by simple describing a drawing like you would in the main graphics.
  void description() {
    PImage squareRound = loadImage("roundsquare.png");
    this.image(squareRound, 0, 0);
  }
  
  public void partEvent(PartEvent event) {
    switch (event.getID()) {	
    case PartEvent.PART_DRAGGED:
      event.part.pos.set( event.part.pos.x + mouseX-pmouseX, event.part.pos.y + mouseY-pmouseY);
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

