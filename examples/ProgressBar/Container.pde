class Container extends Blueprint {
    
  Container() {
    super(containerW, containerH);
  }
  
  void description() {
    rectMode(CORNER);
    fill(150);
    noStroke();
    rect(0, 0, containerW, containerH);
  }
  
}

