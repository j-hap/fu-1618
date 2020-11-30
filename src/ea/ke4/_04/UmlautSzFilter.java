package ea.ke4._04;

import java.io.IOException;

class UmlautSzFilter implements CharEingabeStrom {
  private CharEingabeStrom eingabeStrom;
  private int naechstesZ = -1;

  public UmlautSzFilter(CharEingabeStrom cs) { eingabeStrom = cs; }

  public int read() throws IOException {
    if(naechstesZ != -1) {
      int z = naechstesZ;
      naechstesZ = -1;
      return z;
    } else {
      int z = eingabeStrom.read();
      if(z == -1)  return -1;
      switch((char)z) {
        case '\u00C4':  naechstesZ = 'e'; return 'A';
        case '\u00D6':  naechstesZ = 'e'; return 'O';
        case '\u00DC':  naechstesZ = 'e'; return 'U';
        case '\u00E4':  naechstesZ = 'e'; return 'a';
        case '\u00F6':  naechstesZ = 'e'; return 'o';
        case '\u00FC':  naechstesZ = 'e'; return 'u';
        case '\u00DF':  naechstesZ = 's'; return 's';
        default:        return z;
      }
    }
  }
}