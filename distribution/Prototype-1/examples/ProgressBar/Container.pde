class Container extends Blueprint {
    
  Container() {
    super();
    width = 46;
    height = 30;
    scaleGrid = new Box(15);
    
    //Set the part to be not interactive
    intractable = false;
  }
  
  void description() {
    PImage picture = loadImage("container.png");
    blueprint.image(picture, 0, 0);
  }
  
}

