//As I am arelady extending Container, there is no need to extend Blueprint.
class KnobHighlight extends Blueprint {

  boolean dragging = false;
  
  KnobHighlight() {
    super();
    width = 30;
    height = 30;
    
    pivotX = 1;
    
    //Set the part to be not interactive
    interacble = false;
  }
  
  void description() {
    PImage picture = loadImage("knob_highlight.png");
    blueprint.image(picture, 0, 0);
  }
}

