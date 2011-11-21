class BallRecipe extends Blueprint {
  BallRecipe() {
     super(180, 180);
  }
  
  void description() {
    noStroke();
    fill(255, 0, 0, 255);
    ellipseMode(CORNER);
    ellipse(0, 0, 180, 180);
  }
}
