package ea.ke4._01;

class LinkedList1618Mod<ET> {
  private Entry header = new Entry(null, null, null); /**/

  private int size = 0;

  public LinkedList1618Mod() {
    header.next = header;
    header.previous = header;
  }

  public ET getLast() {
    if (size == 0) throw new java.util.NoSuchElementException();
    return header.previous.element;
  }

  public ET removeLast() {
    Entry lastEntry = header.previous; /**/
    if (lastEntry == header) throw new java.util.NoSuchElementException();
    lastEntry.previous.next = lastEntry.next;
    lastEntry.next.previous = lastEntry.previous;
    size--;
    return lastEntry.element;
  }

  public void addLast(ET e) {
    Entry newEntry = new Entry(e, header, header.previous); /**/
    header.previous.next = newEntry;
    header.previous = newEntry;
    size++;
  }

  public int size() {
    return size;
  }

  private class Entry { /**/
    private ET element; /**/

    private Entry next; /**/

    private Entry previous; /**/

    private Entry(ET element, Entry next, Entry previous) { /**/
      this.element = element;
      this.next = next;
      this.previous = previous;
    }
  }

  public class ListIterator {
    private int nextIndex = 0;

    private Entry next = header.next; /**/

    boolean hasNext() {
      return nextIndex != size;
    }

    ET next() {
      if (nextIndex == size) throw new java.util.NoSuchElementException();
      ET elem = next.element;
      next = next.next;
      nextIndex++;
      return elem;
    }
  }

  public ListIterator listIterator() {
    return new ListIterator();
  }
}
