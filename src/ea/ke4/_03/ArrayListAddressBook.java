package ea.ke4._03;

class ArrayListAddressBook implements AddressBook {
  private java.util.ArrayList<Person> persons;

  public ArrayListAddressBook(int initialCapacity) {
    initialCapacity = initialCapacity < 1 ? 1 : initialCapacity;
    persons = new java.util.ArrayList<Person>(initialCapacity);
  }

  @Override
  public void addPerson(Person person) {
    persons.add(person);
  }

  @Override
  public void print() {
    for (Person person : persons) {
      System.out.println(person.getName());
    }
  }
}
