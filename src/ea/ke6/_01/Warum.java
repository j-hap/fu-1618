package ea.ke6._01;

import java.io.IOException;

public class Warum {
  public static void main(String[] argv) {
    Test t = new Test();
    t.start();
    t.dotry();
    // t.stop();
  }
}

class Test extends Thread {
  private boolean keepRunning = true;
  @Override
  public void run() {
    while (keepRunning) {
      System.out.println("Hallo, ich komme.");
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        // Sollte nicht geschehen, zur Sicherheit Ausgabemeldung
        System.out.println("Beim Schlafen unterbrochen");
      }
    }
  }

  // Zum Beenden Enter-Taste druecken
  void dotry() {
    try {
      System.in.read(); // Blockiert bis Zeile eingegeben
      keepRunning = false; // signalisiert Ende von run()
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("Ich gehe. Auf Wiedersehen!");
  }
}