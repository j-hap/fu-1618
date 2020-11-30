package ea.ke4._03;

import java.io.Serializable;

interface AddressBook extends Serializable {
  void addPerson(Person person);

  void print();
}