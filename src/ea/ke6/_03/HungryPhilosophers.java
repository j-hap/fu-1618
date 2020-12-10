package ea.ke6._03;

public class HungryPhilosophers {
  public static void main(String[] args) {
    int nSeats = 5;
    new Table(nSeats, new Waiter(nSeats - 1));
    return;
  }
}
