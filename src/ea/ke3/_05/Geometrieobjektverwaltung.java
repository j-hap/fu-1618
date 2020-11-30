package ea.ke3._05;

public class Geometrieobjektverwaltung {
  public static void main(String[] args) {
    Geometrieobjekt geometrieobjekt = null;

    if (args[0].equals("Kreis"))
      geometrieobjekt = new Kreis();
    if (args[0].equals("Bogenstueck"))
      geometrieobjekt = new Bogenstueck();
    if (args[0].equals("Rechteck"))
      geometrieobjekt = new Rechteck();
    /* ... */
    if (geometrieobjekt == null) {
      System.out.println("Ein " + args[0]
              + " ist bisher nicht implementiert");
    } else {
      geometrieobjekt.druckeEigenschaften();
    }
  }
}

abstract class Geometrieobjekt {
  public void druckeEigenschaften() {
    // call abstract class implementations for concrete value
    System.out.print(getName() + " ");
    if (istPunktsymmetrisch()) {
      System.out.print("ist punktsymmetrisch");
    } else {
      System.out.print("ist nicht punktsymmetrisch");
    }

    System.out.print(" und ");

    if(istGeschlossen()){
      System.out.println("geschlossen");
    } else {
      System.out.println("offen");
    }
  }
  public abstract String getName();
  public abstract boolean istPunktsymmetrisch();
  public abstract boolean istGeschlossen();
}

class Kreis extends Geometrieobjekt {
  public String getName() { return "Kreis"; };
  public boolean istGeschlossen() { return true; }
  public boolean istPunktsymmetrisch() { return true; }
}

class Rechteck extends Geometrieobjekt {
  public String getName() { return "Rechteck"; };
  public boolean istGeschlossen() { return true; }
  public boolean istPunktsymmetrisch() { return true; }
}

class Bogenstueck extends Geometrieobjekt {
  public String getName() { return "Bogenstueck"; };
  public boolean istGeschlossen() { return false; }
  public boolean istPunktsymmetrisch() { return false; }
}