package ea.ke2._02;

public class Buffer {
  private Entry first;
  private Entry currentEntry;
  // for this implementation, we do not need to keep track
  // of the number of entries

  Buffer(int nEntries) throws IllegalArgumentException {
    if (nEntries <= 0) {
      throw new IllegalArgumentException("Number of entries must be > 0!");
    }
    // creates the entries
    first = new Entry(null, null, null);
    currentEntry = first;
    for (int iEntry = 1; iEntry < nEntries; ++iEntry) {
      currentEntry.next = new Entry(null, null, currentEntry);
      currentEntry = currentEntry.next;
    }
    // closing the circular references
    currentEntry.next = first;
    first.previous = currentEntry;
    // currentEntry is now the Entry before first, so the first "insert" call placed content in "first"
  }

  public void insert(String s) {
    currentEntry = currentEntry.next;
    currentEntry.element = s;
  }

  public void print() {
    Entry firstToPrint = currentEntry;
    // does the round trip
    do {
      System.out.println(currentEntry.element);
      currentEntry = currentEntry.next;
    } while (currentEntry != firstToPrint);
  }

  private static class Entry {
    // nested class that only Buffer uses
    // private so no other class can use it
    // static so it can't access non-static members of containing class
    private String element;
    private Entry next;
    private Entry previous;

    private Entry(String element, Entry next, Entry previous) {
      this.element = element;
      this.next = next;
      this.previous = previous;
    }
  }
}