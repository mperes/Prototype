class WidgetListener implements PartListener {
  
  public WidgetListener() {
  }
  
  public void partUpdated(PartUpdateEvent event) {

    if (event.part == slider) {
      switch(event.field) {
      case VALUE:
        println("Slider: " + event.part.value().asInt());
        println("-----------------------------");
        break;
      }
    } 
    else if (event.part == radio || event.part == radio2 || event.part == radio3) {
      switch(event.field) {
      case VALUE:
        println("Radio: " + event.part.value().asBol());
        println("-----------------------------");
        break;
      }
    }
    else if(event.part == button) {
      switch(event.field) {
      case VALUE:
        println("Button: " + event.part.value().asBol());
        println("Button: " + event.part.value().asInt());
        println("-----------------------------");
        break;
      }
    }
  }
}

