import com.miguelperes.tweener.*;

Tweener tweener;
public float miguel = 10;
Test myTest;

void setup() {
tweener  = new Tweener(this);
tweener.tween(this, "miguel", Ease.easeOutCubic, 20, 100, 100, 100);
tweener.invoke(this, "hello", 30);
myTest = new Test();
}

void draw() {
  //println(miguel);
}

public void hello(Delay d) {
  println("hello");
}

