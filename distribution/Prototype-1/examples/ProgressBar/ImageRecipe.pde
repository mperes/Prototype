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
    this.image(spider, 0, 0);
  }
  public void partEvent(PartEvent event) {
    switch (event.getID()) {	
    case PartEvent.MOUSE_PRESSED:
      break;
    case PartEvent.MOUSE_RELEASED:
      break;
    case PartEvent.MOUSE_CLICKED:
      break;
    case PartEvent.MOUSE_DRAGGED:
      break;
    case PartEvent.MOUSE_MOVED:
      break;
    case PartEvent.MOUSE_SCROLLED:
      break;
    case PartEvent.PART_PRESSED:
      break;
    case PartEvent.PART_RELEASED:
      break;
    case PartEvent.PART_CLICKED:
      break;
    case PartEvent.PART_DRAGGED:
      event.part.pos.set( event.part.pos.x + mouseX-pmouseX, event.part.pos.y + mouseY-pmouseY);
      break;
    case PartEvent.PART_MOVED:
      break;
    case PartEvent.PART_SCROLLED:
      break;
    }
  }
}

