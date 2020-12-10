package ea.ke6._04;


class Konto {
  volatile int kontoStand = 0;

  public synchronized void einzahlen(int betrag) {
    int hilfsvariable = kontoStand;
    hilfsvariable = hilfsvariable + betrag;
    kontoStand = hilfsvariable;
  }
}
class EinThread extends Thread {
  static int nummer = 0;
  String name;
  Konto meinKonto;

  public EinThread(String s, Konto k) {
    nummer++;
    name = s;
    meinKonto = k;
  }
  public void run() {
    boolean bv = true;
    while (bv) {
      meinKonto.einzahlen(20);
      yield();
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
      }
    }
  }
}

class NochEinThread extends EinThread {
  public NochEinThread(String s, Konto k) {
    super(s, k);
  }
  public void run() {
    while (true) {
      /*** 1 ***/
      System.out.println(nummer); // static package private static member variable of parent class can be accessed
      // this object does not own a reference to threadPit, so no access to its members (except for its Konto see below)
      // this object does not own a reference to threadTom, so no access to its members
      System.out.println(meinKonto.kontoStand); // parent object was given a reference to Pit's Konto in the constructor, so access to all package private members
      // local variable bv of parent's run() does not exist, because run was overridden.
      yield();
      try {
        Thread.sleep(6000);
      } catch (InterruptedException e) {
      }
    }
  }
}

public class GemeinsameVariablen {
  public static void main(String[] args) {
    Konto k1 = new Konto();
    Konto k2 = new Konto();
    Thread threadPit = new EinThread("Pit", k1);
    Thread threadTom = new EinThread("Tom", k2);
    Thread threadEva = new NochEinThread("Eva", k1);
    threadPit.start();
    threadTom.start();
    threadEva.start();
  }
}
