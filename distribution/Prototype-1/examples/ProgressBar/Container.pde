class Container extends Blueprint {
    
  Container() {
    super();
    
    width = 46;
    height = 30;
    scaleGrid = new Box(15);
  }
  
  void description() {
    PImage picture = loadImage("container.png");
    blueprint.image(picture, 0, 0);
  }
  
}

