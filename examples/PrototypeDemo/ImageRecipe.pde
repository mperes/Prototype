class ImageRecipe extends Blueprint {

  ImageRecipe() {
    //This is mandatory. Call it to set the desired initial Width and Height of you Blueprint.
    super(135, 118);
    scaleGrid = new Box(15, 15, 15, 15);
    //pivot.set(.5,0);
  }

  //Describe you blueprint here by simple describing a drawing like you would in the main graphics.
  void description() {
    PImage spider = loadImage("roundsquare.png");
    blueprint.image(spider, 0, 0);
  }

  //Events that can be used at any part
  //PartEvent.MOUSE_PRESSED
  //PartEvent.MOUSE_RELEASED
  //PartEvent.MOUSE_CLICKED:
  //PartEvent.MOUSE_DRAGGED
  //PartEvent.MOUSE_MOVED
  //PartEvent.MOUSE_SCROLLED
  //PartEvent.PART_PRESSED
  //PartEvent.PART_RELEASED
  //PartEvent.PART_CLICKED
  //PartEvent.PART_DRAGGED
  //PartEvent.PART_MOVED
  //PartEvent.PART_SCROLLED
  //PartEvent.PART_DRAGGED:
  //PartEvent.PART_MOVED:
  
  public void partEvent(PartEvent event) {
    switch (event.getID()) {
    case PartEvent.PART_DRAGGED:
      event.part.pos.set( event.part.pos.x + mouseX-pmouseX, event.part.pos.y + mouseY-pmouseY);
      break;
    }
  }
}

