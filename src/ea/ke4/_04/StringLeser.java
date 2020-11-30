package ea.ke4._04;

class StringLeser implements CharEingabeStrom {
  private char[] dieZeichen;
  private int index = 0;

  public StringLeser(String s) { dieZeichen = s.toCharArray(); }

  public int read() {
    if(index == dieZeichen.length) return -1;
    else return dieZeichen[index++];
  }
}