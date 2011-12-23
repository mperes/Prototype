//A very basic behavior.
class ChangePivot extends MouseOverBehavior {
  public void onStopDragging(MouseEvent e) {
    float xDiff = (float)(parent.localMouseX()+parent.pivotX()*parent.width()) / blueSquare.width();
    float yDiff = (float)(parent.localMouseY()+parent.pivotY()*parent.height()) / blueSquare.height();
    parent.pivotX( xDiff );
    parent.pivotY( yDiff );
    blueSquare.x( mouseX );
    blueSquare.y( mouseY );
    cursor(ARROW);
  }

  public void onStartDragging(MouseEvent e) {
   cursor(CROSS);
  }
}

