class Label extends Blueprint {
  PFont font;
  Label() {
    //This is mandatory. Call it to set the desired initial Width and Height of you Blueprint.
    super(200, 20);
    font = loadFont("Dialog-18.vlw"); 
  }

  //Describe you blueprint here by simple describing a drawing like you would in the main graphics.
  void description() {
    canvas.textFont(font); 
    canvas.textAlign(CENTER, CENTER);
    canvas.fill(255);
    canvas.text("word", 100, 10);
  }
}
