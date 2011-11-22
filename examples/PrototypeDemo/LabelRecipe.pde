class LabelRecipe extends Blueprint {

  LabelRecipe() {
    //This is mandatory. Call it to set the desired initial Width and Height of you Blueprint.
    super(165, 50);
    pivot.set(.5, .5); //Sets the pivot point to the center of the blueprint.
    rel.set(.5, .5); //Sets the this blueprint relative to the center of its parent;  
  }

  //Describe you blueprint here by simple describing a drawing like you would in the main graphics.
  void description() {
    //I am using a image to draw text here, as I did not manage to draw text properly. Even using JAVA2D
    //The text display dis thin black line around it when in certain positions of the screen;
    PImage label = loadImage("buttonLabel.png");
    this.image(label, 0, 0);
  }
}

