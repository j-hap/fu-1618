package ea.ke4._02;

public class MaximumContainerTest {
  public static void main(String[] args) {
    MaximumContainer<Fruit> fruitContainer = new MaximumContainer<Fruit>();
    fruitContainer.store(new Apple(1));
    fruitContainer.store(new Pear(3));
    fruitContainer.store(new Apple(2));
    fruitContainer.store(new Pear(2));
    fruitContainer.get().print();
    fruitContainer.store(new Apple(6));
    fruitContainer.get().print();
  }
}
