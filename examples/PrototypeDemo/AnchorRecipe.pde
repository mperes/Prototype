class AnchorRecipe extends Blueprint {
  boolean dragging = false;
  AnchorRecipe() {
    //This is mandatory. Call it to set the desired initial Width and Height of you Blueprint
    super(12, 12);
    //Sets the pivot point to the center of the image. This can also be done at runtime,
    //Part.pivot.set(x, y)
    pivot.set(.5, .5);

    //These options can also be configured in the blueprint. Options bellow are the default config.
    //pos.set(0, 0); //Initia position.
    //scale.set(1, 1);
    //rel.set(0, 0); // Point in relation to the parent.
    //visible = true; //A invisible object does not receive mouse events and is not displayed.
    //enabled = true; //Set receiving mouse events on/off.
    //alpha = 1;
    //showPivot = false;
  }

  void description() {
    smooth();
    ellipseMode(CORNER);
    noStroke();
    //I still have to investigate this, but when drawing a circle with the same size as the blueprint,
    //the circle gets cropped.
    ellipse(0, 0, 11, 11);
  }

  public void partEvent(PartEvent event) {
    switch (event.getID()) {
    case PartEvent.PART_PRESSED:
      this.dragging = true;
      break;
    case PartEvent.MOUSE_DRAGGED:
      if (this.dragging) {
        resizePic();
      }
      break;
    case PartEvent.MOUSE_RELEASED:
      dragging = false;
      break;
    }
  }

  void resizePic() {
  }
}

//You can reutilize your own blueprints by extending them.
class AnchorRecipeTL extends AnchorRecipe {
  AnchorRecipeTL() {
    super(); //Always call Super().
  }

  void resizePic() {
    imagePart.size.set( imagePart.size.x - (mouseX-pmouseX), imagePart.size.y - (mouseY-pmouseY));
    imagePart.pos.set( imagePart.pos.x + (mouseX-pmouseX), imagePart.pos.y + (mouseY-pmouseY));
  }
}

class AnchorRecipeTR extends AnchorRecipe {
  boolean dragging = false;
  AnchorRecipeTR() {
    super(); //Always call Super().
    rel.set(1, 0); //Relative to the top right corner;
  }

  void resizePic() {
    imagePart.size.set( imagePart.size.x + (mouseX-pmouseX), imagePart.size.y - (mouseY-pmouseY));
    imagePart.pos.set( imagePart.pos.x, imagePart.pos.y + (mouseY-pmouseY));
  }
}

class AnchorRecipeBR extends AnchorRecipe {
  AnchorRecipeBR() {
    super(); //Always call Super().
    rel.set(1, 1); //Relative to the bottom right corner;
  }

  void resizePic() {
    imagePart.size.set( imagePart.size.x + (mouseX-pmouseX), imagePart.size.y + (mouseY-pmouseY));
  }
}

class AnchorRecipeBL extends AnchorRecipe {
  AnchorRecipeBL() {
    super(); //Always call Super().
    rel.set(0, 1); //Relative to the bottom left corner;
  }

  void resizePic() {
    imagePart.size.set( imagePart.size.x - (mouseX-pmouseX), imagePart.size.y + (mouseY-pmouseY));
    imagePart.pos.set( imagePart.pos.x + (mouseX-pmouseX), imagePart.pos.y);
  }
}

