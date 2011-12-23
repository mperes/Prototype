//A very basic behavior.
class ScaleBehavior extends MouseOverBehavior {
  
  ScaleBehavior() {
    super();
  }
  
  public void onDrag(MouseEvent e) {
    float xDiff = (this.parent.localMouseX()-this.parent.plocalMouseX())*2;
    float yDiff = (this.parent.localMouseY()-this.parent.plocalMouseY())*2;
    blueSquare.width( blueSquare.width() + xDiff);
    blueSquare.height( blueSquare.height() + yDiff);
  }
  
  public void onRollOver(MouseEvent e) { cursor(HAND); }
  public void onRollOut(MouseEvent e) { cursor(ARROW); }

}
