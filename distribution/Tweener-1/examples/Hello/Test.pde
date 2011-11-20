public class Test {
  public float bla = 0;
  Test() {
    tweener.invoke(this, "helloWorld", 30);
    //tweener.tween(this, "bla", Ease.easeOutCubic, 20, 100, 100, 100, "helloWorld");
  }
  public void helloWorld(Delay d) {
    println("hello world");
  }
}


