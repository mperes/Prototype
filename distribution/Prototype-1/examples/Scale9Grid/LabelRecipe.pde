class LabelRecipe extends Blueprint {

  LabelRecipe() {
     //As a rule of a thumb, always call super() as your first line of the constructor.
    super();
    
    //This is mandatory. Call it to set the desired initial Width and Height of you Blueprint.
    width = 165;
    height = 50;
    
    //Sets the pivot point to the center of the blueprint.
    pivotX = .5;
    pivotY = .5;
    
    //Sets the this blueprint relative to the center of its parent;
    relX = .5;
    relY = .5;
  }

  //Describe you blueprint here by simple describing a drawing like you would in the main graphics.
  //The only difference is that all the commands should be preceded by 'blueprint.'
  void description() {
    PFont font;
    font = loadFont("Dialog-18.vlw"); 
    blueprint.smooth();
    blueprint.textAlign(CENTER, CENTER);
    blueprint.textFont(font); 
    blueprint.text("Drag the anchors\nto resize", 82, 25);
  }
}

