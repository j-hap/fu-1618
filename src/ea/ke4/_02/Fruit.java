package ea.ke4._02;

public abstract class Fruit implements Comparable<Fruit> {
  private final int weight;

  protected Fruit(int weight) {
    this.weight = weight;
  }

  public int compareTo(Fruit other) {
    return this.weight - other.weight;
  }

  public void print() {
    System.out.println("I weigh " + getWeight());
  }
  public int getWeight() {
    return weight;
  }
}