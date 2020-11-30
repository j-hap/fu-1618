package ea.ke4._02;

public class Pear extends Fruit {
  Pear(int weight) {
    super(weight);
  }

  @Override
  public void print() {
    System.out.print("I'm a Pear and ");
    super.print();
  }
}