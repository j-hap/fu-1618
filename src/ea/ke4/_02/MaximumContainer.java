package ea.ke4._02;

public class MaximumContainer<T extends Comparable<T>> {
  T currentElement = null;

  public T get() {
    return currentElement;
  }

  public boolean store(T newElement) {
    // tries to store the given element in the container. only successful if newElement > currentElement or
    // currentElement = 0 returns status if the given element was stored

    if (currentElement == null) {
      currentElement = newElement;
      return true;
    }

    boolean givenElementIsBigger = newElement.compareTo(currentElement) > 0;

    if (givenElementIsBigger) {
      currentElement = newElement;
    }
    return givenElementIsBigger;
  }

  public MaximumContainer(T element) {
    currentElement = element;
  }

  // default constructor shall also be enabled
  public MaximumContainer() {
  }
}
