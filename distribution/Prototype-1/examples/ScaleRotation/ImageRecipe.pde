class ImageRecipe extends Blueprint {

  boolean dragging = false;
  ImageRecipe() {
    //As a rule of a thumb, always call super() as your first line of the constructor.
    super();

    //This is mandatory. Call it to set the desired initial Width and Height of you Blueprint.
    width = 300;
    height = 200;
    
    //Sets the pivot point to the center of the image. This can also be done at runtime,
    pivotX = .5;
    pivotY = .5;
    
    //Display the Part's pivot point.
    showPivot = true;
  }

  //Describe you blueprint here by simple describing a drawing like you would in the main graphics.
  void description() {
    PImage picture = loadImage("spider.jpg");
    blueprint.image(picture, 0, 0);
  }

  public void partEvent(PartEvent e) {
    switch (e.getID()) {
    case PartEvent.PART_PRESSED:
      this.dragging = true;
      break;
    case PartEvent.MOUSE_MOVED:
      inactivityCounter = 0;
      break;
    case PartEvent.MOUSE_DRAGGED:
      if (this.dragging) {
        int modX = e.screenX - e.pscreenX;
        int modY = e.screenY - e.pscreenY;
        e.part.setX(e.part.getX() + modX);
        e.part.setY(e.part.getY() + modY);
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
  //PartEvent.PART_ROLLOVER
  //PartEvent.PART_SCROLLED
}

