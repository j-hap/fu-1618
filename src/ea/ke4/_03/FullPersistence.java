package ea.ke4._03;

interface FullPersistence {
  AddressBook loadBook(String name) throws AddressBookException;

  void storeBook(AddressBook book, String name) throws AddressBookException;
}