package ea.ke4._04;

import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    String s = "f\u00DF\u00D6";

    CharEingabeStrom cs;
    cs = new StringLeser( s );
    cs = new UmlautSzFilter( cs );
    cs = new GrossBuchstabenFilter( cs );

    int z = cs.read();
    while(z != -1) {
      System.out.print((char)z);
      z = cs.read();
    }
    System.out.println("");
  }
}