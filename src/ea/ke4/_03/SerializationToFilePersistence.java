package ea.ke4._03;

import java.io.*;

public class SerializationToFilePersistence implements FullPersistence {
  private static final String path = System.getProperty("user.home") + File.separator + "addressbooks" + File.separator;

  private String getFullName(String name) {
    return path + name;
  }

  void validateStoragePath() throws AddressBookException {
    File storagePath = new File(path);
    if (!storagePath.exists()) {
      if (!storagePath.mkdir()) {
        throw new AddressBookException("Could no create storage directory.");
      }
    }
  }

  @Override
  public AddressBook loadBook(String name) throws AddressBookException {
    AddressBook bookFromFile = null;
    try {
      InputStream is = new FileInputStream(getFullName(name));
      ObjectInputStream ois = new ObjectInputStream(is);
      bookFromFile = (AddressBook) ois.readObject();
      ois.close();
    } catch (ClassNotFoundException | IOException e) {
      throw new AddressBookException(e.getMessage());
    }
    return bookFromFile;
  }

  @Override
  public void storeBook(AddressBook book, String name) throws AddressBookException {
    // makes sure the store path exists
    validateStoragePath();
    try {
      OutputStream os = new FileOutputStream(getFullName(name));
      ObjectOutputStream oos = new ObjectOutputStream(os);
      oos.writeObject(book);
      oos.close();
    } catch (IOException e) {
      throw new AddressBookException(e.getMessage());
    }
  }
}
