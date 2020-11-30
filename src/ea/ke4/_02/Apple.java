package ea.ke4._02;

public class Apple extends Fruit {
  Apple(int weight) {
    super(weight);
  }

  @Override
  public void print() {
    System.out.print("I'm an Apple and ");
    super.print();
  }
}