package ea.ke3._02;

public enum ThwRang {
  HELFER, TRUPPFUEHRER, GRUPPENFUEHRER, ZUGTRUPPFUEHRER, ZUGFUEHRER;

  private boolean istVorgesetzterVor(ThwRang other) {
    return this.ordinal() > other.ordinal();
  }

  @Override
  public String toString() {
    return super.toString().charAt(0) + super.toString().substring(1).toLowerCase();
  }

  static public void printAll() {
    for (ThwRang r : ThwRang.values()) {
      System.out.println(r.toString());
    }
  }

  public static void main(String[] args) {
    printAll();
    System.out.println(HELFER.istVorgesetzterVor(GRUPPENFUEHRER));
    System.out.println(TRUPPFUEHRER.istVorgesetzterVor(HELFER));
    System.out.println(GRUPPENFUEHRER.istVorgesetzterVor(GRUPPENFUEHRER));
  }
}
