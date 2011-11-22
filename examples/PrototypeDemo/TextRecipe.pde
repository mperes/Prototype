class Label extends Blueprint {
  
  Label() {
    //This is mandatory. Call it to set the desired initial Width and Height of you Blueprint.
    super(200, 20);
    pivot.set(.5, .5);
    rel.set(.5, .5);
  }

  //Describe you blueprint here by simple describing a drawing like you would in the main graphics.
  void description() {
    PFont font = loadFont("Dialog-18.vlw");
    blueprint.noStroke();
    fill(#00deff);
    rect(0, 0, 200, 20);
    blueprint.smooth(); 
    blueprint.textFont(font); 
    blueprint.textAlign(CENTER, CENTER);
    blueprint.fill(255);
    blueprint.text("My Button", 100, 10);
  }
}
