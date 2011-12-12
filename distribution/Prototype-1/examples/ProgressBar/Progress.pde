//As I am arelady extending Container, there is no need to extend Blueprint.
class Progress extends Container {
  int margin = 2;
  
  Progress() {
    super();
    width = 19;
    height = 18;
    pivotY = .5;
    relY = .5;
    scaleGrid = new Box(9); //Some borders to preserve the transparency around the bar.
  }
  
  void description() {
    PImage picture = loadImage("progress.png");
    blueprint.image(picture, 0, 0);
  }
  
}

