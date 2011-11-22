class Bar extends Blueprint {
  Bar() {
    //This is mandatory. Call it to set the desired initial Width and Height of you Blueprint.
    super(200, 20);
  }

  //Describe you blueprint here by simple describing a drawing like you would in the main graphics.
  void description() {
    fill(255,0,0);
    rect(0,0, 200, 20);
  }
}

class Knob extends Blueprint {
  
  boolean dragging = false;
  Knob() {
    //This is mandatory. Call it to set the desired initial Width and Height of you Blueprint.
    super(10, 20);
    pivot.set(.5, 0);
    pos.set(5,0,0, bar.size.x-5, 0, 0);
    //pos.setMixMax();
  }

  //Describe you blueprint here by simple describing a drawing like you would in the main graphics.
  void description() {
    fill(200,0,0);
    noStroke();
    rect(0,0, 10, 20);
  }
  
  public void partEvent(PartEvent event) {
    switch (event.getID()) {
    case PartEvent.PART_PRESSED:
      this.dragging = true;
      break;
    case PartEvent.MOUSE_DRAGGED:
      if(this.dragging) {
        event.part.pos.x += mouseX-pmouseX;
        //println(event.part.pos.x);
                //event.part.pos.y += mouseY-pmouseY;
      }
      break;
    case PartEvent.MOUSE_RELEASED:
      dragging = false;
      break;
    }
  }
}

