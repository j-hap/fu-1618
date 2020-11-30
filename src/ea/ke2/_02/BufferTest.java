package ea.ke2._02;

class BufferTest {
  public static void main(String[] args) {
    Buffer b = new Buffer(5);
    b.insert("This");
    b.print();
    System.out.println("--------------------------");
    b.insert("is");
    b.print();
    System.out.println("--------------------------");
    b.insert("buffer");
    b.print();
    System.out.println("--------------------------");
    b.insert("content");
    b.print();
    System.out.println("--------------------------");
    b.insert("SALAMI");
    b.print();
    System.out.println("--------------------------");
    b.insert("first Override");
    b.print();
  }
}