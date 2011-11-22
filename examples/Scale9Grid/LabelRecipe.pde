class LabelRecipe extends Blueprint {

  LabelRecipe() {
    //This is mandatory. Call it to set the desired initial Width and Height of you Blueprint.
    super(165, 50);
    pivot.set(.5, .5); //Sets the pivot point to the center of the blueprint.
    rel.set(.5, .5); //Sets the this blueprint relative to the center of its parent;  
  }

  //Describe you blueprint here by simple describing a drawing like you would in the main graphics.
  void description() {
    smooth();
    textAlign(CENTER, CENTER);
    PFont font;
    font = loadFont("Dialog-18.vlw"); 
    textFont(font); 
    text("Drag the anchors\nto resize", 82, 25);
  }
}

