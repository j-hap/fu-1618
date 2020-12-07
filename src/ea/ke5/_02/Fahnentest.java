package ea.ke5._02;

import java.util.ArrayList;

class Fahnenmast {
  private final ArrayList<Fahnenbeobachter> watchers = new ArrayList<Fahnenbeobachter>();
  private Fahnenposition status;

  public void addFahnenbeobachter(Fahnenbeobachter neuerbeobachter) {
    watchers.add(neuerbeobachter);
  }

  public void removeFahnenbeobachter(Fahnenbeobachter neuerbeobachter) {
    watchers.remove(neuerbeobachter);
  }

  public void setStatus(Fahnenposition fahnenposition) {
    status = fahnenposition;
    for (Fahnenbeobachter w : watchers) {
      w.fahnenstatusGeaendert(this);
    }
  }

  public Fahnenposition getFahnenposition() {
    return status;
  }
}

enum Fahnenposition {
  OBEN, HALBMAST, UNBEFLAGGT
}

// Der View
class Tourist implements Fahnenbeobachter {
  private String name = "Unbekannt";

  public Tourist(String name) {
    this.name = name;
  }

  public void fahnenstatusGeaendert(Fahnenmast fahnenmast) {
    System.out.println(name + " vermeldet fuer den Fahnenmast: "
            + fahnenmast.getFahnenposition());
  }
}

interface Fahnenbeobachter {
  public void fahnenstatusGeaendert(Fahnenmast fahnenmast);
}

class Fahnenbedienung {
  private Fahnenmast fahnenmast;

  public Fahnenbedienung(Fahnenmast fahnenmast) {
    this.fahnenmast = fahnenmast;
  }

  public void aendereBeflaggung(Fahnenposition fahnenposition) {
    fahnenmast.setStatus(fahnenposition);
  }
}

public class Fahnentest {
  public static void main(String[] args) {

    Fahnenmast meinFahnenmast = new Fahnenmast();

    Fahnenbedienung bedienung1 = new Fahnenbedienung(meinFahnenmast);
    Fahnenbedienung bedienung2 = new Fahnenbedienung(meinFahnenmast);

    Fahnenbeobachter anna = new Tourist("Anna");
    Fahnenbeobachter bernd = new Tourist("Bernd");
    Fahnenbeobachter charly = new Tourist("Charly");

    bedienung1.aendereBeflaggung(Fahnenposition.OBEN);

    meinFahnenmast.addFahnenbeobachter(bernd);

    /* d) ------------------------------- */
    meinFahnenmast.addFahnenbeobachter(new Fahnenbeobachter() {
      @Override
      public void fahnenstatusGeaendert(Fahnenmast fahnenmast) {
        System.out.println("Michael vermeldet fuer den Fahnenmast: "
                + fahnenmast.getFahnenposition());
      }
    });
    meinFahnenmast.addFahnenbeobachter(
            (Fahnenmast fahnenmast) -> {
              System.out.println("Sabine vermeldet fuer den Fahnenmast: "
                      + fahnenmast.getFahnenposition());
            });
    /* ------------------------------ d) */

    bedienung1.aendereBeflaggung(Fahnenposition.HALBMAST);

    meinFahnenmast.addFahnenbeobachter(anna);
    meinFahnenmast.addFahnenbeobachter(charly);
    meinFahnenmast.removeFahnenbeobachter(bernd);

    bedienung2.aendereBeflaggung(Fahnenposition.UNBEFLAGGT);
    bedienung2.aendereBeflaggung(Fahnenposition.OBEN);

    meinFahnenmast.addFahnenbeobachter(bernd);
  }
}