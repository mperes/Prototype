//As I am arelady extending Container, there is no need to extend Blueprint.
class Knob extends Blueprint {

  boolean dragging = false;
  
  Knob() {
    //Always call super(w, h)
    super(containerH/3, containerH);
    pivot.set(1, 0); // Sets the pivtor point to the top right corner.
    pos.setMinMax(containerH/3, containerW, 0, 0); //Contrains the knob to the area of the container.
  }
  
  void description() {
    rectMode(CORNER);
    fill(50);
    noStroke();
    rect(0, 0, containerH/3, containerH);
  }
  
  public void partEvent(PartEvent event) {
    switch (event.getID()) {
    case PartEvent.PART_PRESSED:
      this.dragging = true;
      break;
    case PartEvent.MOUSE_DRAGGED:
      if(this.dragging) {
        event.part.pos.x += mouseX-pmouseX;
        event.part.parent.size.x = event.part.pos.x;//constrain(event.part.pos.x, 0, containerW);
      }
      break;
    case PartEvent.MOUSE_RELEASED:
      dragging = false;
      break;
    }
  }
  
}

