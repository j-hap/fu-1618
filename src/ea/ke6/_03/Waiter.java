package ea.ke6._03;

class Waiter {
  private int nSeatsTaken = 0;
  private final int N_GUESTS_ALLOWED;

  public Waiter(int nAllowed) {
    this.N_GUESTS_ALLOWED = nAllowed;
  }

  public synchronized void getSeat() {
    while (nSeatsTaken >= N_GUESTS_ALLOWED) {
      try {
        wait();
      } catch (InterruptedException e) { /* nothing */ }
    }
    ++nSeatsTaken;
  }

  public synchronized void leaveSeat() {
    --nSeatsTaken;
    // next hungry may sit
    notifyAll();
  }
}
