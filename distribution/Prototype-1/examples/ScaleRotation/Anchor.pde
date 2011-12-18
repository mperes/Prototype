class Anchor implements DynamicImage {
  public void draw() {
    smooth();
    noStroke();
    rectMode(CORNER);
    fill(#e410dd, 200);
    rect(0, 0, 12, 12);
  }
}

