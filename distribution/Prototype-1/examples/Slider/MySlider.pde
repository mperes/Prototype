public class MySlider extends Part {

  private Part progress;
  private Part knob;
  private Part knobHighlight;

  public MySlider(int width) {
    //Here we define the look of the part itself. Look at the partbuilder class for more details.
    //The scaleGrid(15) defines a 9 scale grid of 15 pixels in every direction.
    //Shorter version of scaleGrid(15, 15, 15, 15).
    super(
    new PartBuilder(Prototype.IMAGE).
      texture("container.png").
      scaleGrid(15, 0)
      );
      
    //Definition of the childpart for the progressbar.
    //PivotY defines the pivot point of the part to be in the middle vertical middle of the part.
    //Values for the pivotX and pivotY can vary from 0-1.
    this.progress = this.part(
    new PartBuilder(Prototype.IMAGE).
      texture("progress.png").
      scaleGrid(15, 0).
      relY(0.5f).
      pivotY(0.5f)
      );
      
    //Definition of the childpart for the knob;
    //New stuff here is that we add a drag behavior to the part.
    this.knob = this.part(
    new PartBuilder(Prototype.IMAGE).
      texture("knob.png").
      pivotX(1).
      behaviors(new Drag())
      );
      
    //Definition of the childpart for the knob highlight. Pay attention that this part is actually a child of the Knob part.
    //This a shorter declaration, without using the PartBuilder. Pretty much pass the texture and Behaviors.
    this.knobHighlight = this.knob.part("knob_highlight.png");

    this.width(width);

    //this.knobHighlight.alpha(0);
    
    //here we are contraining some values. You can turn the constraining off, or change the range during rutime.
    this.knob.x.constrain(this.knob.width(), width);
    this.knob.y.constrain(0);
    this.progress.width.constrain(0, width);
    
    this.progress.width(this.knob.x());

    //Any part propagates when one of its property has changed. To listen to this changes
    //you call part.addListener(PartListener). Parts already implement PartListener by default.
    this.knob.addListener(this);
  }

    //Here is our event handler method. PartUpdateEvent only has two properties, the Part that is
    //propagating the update and the property altered.
    public void partUpdated(PartUpdateEvent event) {
    switch(event.field) {
    case X:
      this.progress.width(this.knob.x());
      this.knobHighlight.alpha(  PApplet.map(this.knob.x(), this.knob.width(), this.knob.width()+15, 0, 1) );
      println( round(map(this.progress.width(), this.knob.width(), this.width(), 0, 100)) + "%");
      //this.knobHighlight.alpha(0);
      break;
    }
  }
}

