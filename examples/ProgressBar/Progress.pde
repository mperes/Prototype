//As I am arelady extending Container, there is no need to extend Blueprint.
class Progress extends Container {
  int margin = 2;
  
  Progress() {
    super();
    width = 33;
    height = 30;
    pivotY = .5;
    relY = .5;
    scaleGrid = new Box(16, 15, 16, 15); //Define the 9 scale grid box.
    
    //Set the part to be not interactive
    intractable = false;
  }
  
  void description() {
    PImage picture = loadImage("progress.png");
    blueprint.image(picture, 0, 0);
  }
  
}

