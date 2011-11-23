//As I am arelady extending Container, there is no need to extend Blueprint.
class Progress extends Container {
  int margin = 2;
  
  Progress() {
    super();
    scaleGrid = new Box(4, 4, 4, 4); //Some borders to preserve the transparency around the bar.
    size.setMinMax(0, containerW, containerH, containerH);
  }
  
  void description() {
    noStroke();
    rectMode(CORNER);
    fill(230);
    rect(margin, margin, containerW-2*margin, containerH-2*margin);
  }
  
}

