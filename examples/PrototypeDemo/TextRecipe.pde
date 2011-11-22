class Bar extends Blueprint {
  Bar() {
    //This is mandatory. Call it to set the desired initial Width and Height of you Blueprint.
    super(200, 20);
  }

  //Describe you blueprint here by simple describing a drawing like you would in the main graphics.
  void description() {
    noStroke();
    fill(255,0,0);
    rect(0,0, 200, 20);
  }
}
