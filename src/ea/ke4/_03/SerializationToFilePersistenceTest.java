package ea.ke4._03;

public class SerializationToFilePersistenceTest {
  public static void main(String[] args) {
    ArrayListAddressBook book = new ArrayListAddressBook(5);
    book.addPerson(new Person("Frodo Baggins"));
    book.addPerson(new Person("Samwise Gamgee"));
    book.addPerson(new Person("Gandalf"));
    book.addPerson(new Person("Legolas"));
    book.addPerson(new Person("Aragorn"));
    SerializationToFilePersistence serializer = new SerializationToFilePersistence();
    String filename = "testbook";

    try {
      serializer.storeBook(book, filename);
    } catch (AddressBookException e) {
      System.out.println("Failed to write AddressBook to file:");
      e.printStackTrace();
    }

    AddressBook bookFromFile = null;
    try {
      bookFromFile = serializer.loadBook(filename);
    } catch (AddressBookException e) {
      System.out.println("Failed to load AddressBook from file:");
      e.printStackTrace();
    }
    bookFromFile.print();
  }
}
