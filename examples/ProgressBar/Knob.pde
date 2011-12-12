//As I am arelady extending Container, there is no need to extend Blueprint.
class Knob extends Blueprint {

  boolean dragging = false;
  
  Knob() {
    width = 18;
    height = 18;
    
    pivotX = .6;
    
    //Sets collision method to circle instead of pix;
    collisionMethod = Part.CIRCLE;
  }
  
  void description() {
    blueprint.smooth();
    blueprint.ellipseMode(CORNER);
    blueprint.noStroke();
    blueprint.fill(255, 30);
    blueprint.ellipse(1,1,16,16);
  }
  
  public void partEvent(PartEvent event) {
    switch (event.getID()) {
    case PartEvent.PART_PRESSED:
      this.dragging = true;
      break;
         case PartEvent.MOUSE_MOVED:
      inactivityCounter = 0;
      break;
    case PartEvent.MOUSE_DRAGGED:
      if(this.dragging) {
        event.part.setX(event.part.getX()+mouseX-pmouseX);
        progress.setWidth(event.part.getX());
      }
      break;
    case PartEvent.MOUSE_RELEASED:
      dragging = false;
      break;
    }
  }
  
}

