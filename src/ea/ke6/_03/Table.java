package ea.ke6._03;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

class Table extends JFrame {
  private final boolean[] stickIsAvailable;
  private final TableGraphics tg;
  private int nSittingPhilosophers = 0;

  public synchronized void getStick(int position) {
    position = position % stickIsAvailable.length;
    while (!stickIsAvailable[position]) {
      try {
        wait();
      } catch (InterruptedException e) { /* nothing, will try again anyway */ }
    }
    stickIsAvailable[position] = false;
    tg.hideStick(position);
  }

  public synchronized void setStick(int position) {
    position = position % stickIsAvailable.length;
    stickIsAvailable[position] = true;
    tg.showStick(position);
    // a new stick is available, it's not very hygienic that they aren't washed in between uses
    notifyAll();
  }

  public Table(int nSeats, Waiter waiter) {
    tg = new TableGraphics(nSeats);
    stickIsAvailable = new boolean[nSeats];
    // makes all sticks available
    Arrays.fill(stickIsAvailable, true);

    setSize(600, 600);
    setLayout(new CircularLayout(CircularLayout.TWELVE, true));
    add(tg);

    // place philosophers at table and let them do their thing immediately, does not matter
    // if one starts eating before all others are placed, first come, first served
    System.out.println(getInsets().top);
    for (int iPhilosopher = 0; iPhilosopher < nSeats; ++iPhilosopher) {
      Philosopher p = new Philosopher(this, iPhilosopher, waiter);
      add(p);
      new Thread(p).start();
    }

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    setVisible(true);
  }
}
