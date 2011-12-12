class LabelRecipe extends Blueprint {

  LabelRecipe() {
    //As a rule of a thumb, always call super() as your first line of the constructor.
    super();
    
    //Sets the the part as a shape part. Draws the description everyframe instead of using a Image buffer.
    //Default type is Part.IMAGE.
    type = Part.SHAPE;
    
    //This is mandatory. Call it to set the desired initial Width and Height of you Blueprint.
    width = 60;
    height = 50;

    //Sets the pivot point to the center of the blueprint.
    pivotX = .5;
    pivotY = .5;
    
    //Relative to the center of the parent.
    relX = .5;
    relY = .5;
  }

  //Describe you blueprint here by simple describing a drawing like you would in the main graphics.
  //The only difference is that all the commands should be preceded by 'blueprint.'
  void description() {
    blueprint.smooth();
    blueprint.textAlign(CENTER, CENTER);
    fill(70);
    blueprint.textFont(font); 
    blueprint.text(round(map(progress.getWidth(), knob.getWidth(), progressBarW, 0, 100 )) + "%", progressBarX-progressBarW/2, progressBarY-20);
  }
}

