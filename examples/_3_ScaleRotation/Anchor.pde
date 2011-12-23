class Anchor extends ShapeRender {
  public void draw() {
    smooth();
    noStroke();
    rectMode(CORNER);
    fill(#e410dd);
    rect(0, 0, 12, 12);
  }
}

