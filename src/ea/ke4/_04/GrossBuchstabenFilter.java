package ea.ke4._04;

import java.io.IOException;

class GrossBuchstabenFilter implements CharEingabeStrom {
  private CharEingabeStrom eingabeStrom;

  public GrossBuchstabenFilter(CharEingabeStrom cs) { eingabeStrom = cs; }

  public int read() throws IOException {
    int z = eingabeStrom.read();
    if(z == -1) return -1;
    else return Character.toUpperCase((char)z);
  }
}