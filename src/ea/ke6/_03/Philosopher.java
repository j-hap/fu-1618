package ea.ke6._03;

import java.awt.*;
import java.util.Random;

class Philosopher extends ColoredCircle implements Runnable {
  private static final Random random = new Random();
  private final Table table;
  private final int position;
  private boolean hasChair = false;
  PhilosopherState state = PhilosopherState.THINKING;
  // time delay control constants
  private static final int T_EAT_MIN = 1000;
  private static final int T_EAT_MAX = 2000;
  private static final int T_THINK_MIN = 1000;
  private static final int T_THINK_MAX = 2000;
  private static final int T_STICK_DIFF_MIN = 0;
  private static final int T_STICK_DIFF_MAX = 1000;
  private final Waiter waiter;

  private void setState(PhilosopherState newState) {
    state = newState;
    setFillColor(newState.stateColor);
    this.repaint();
  }

  public Philosopher(Table table, int position, Waiter waiter) {
    this.table = table;
    this.position = position;
    setPreferredSize(new Dimension(30, 30));
    this.waiter = waiter;
  }

  private void pickUpStick(int position) {
    table.getStick(position);
  }

  private void pickUpLeftStick() {
    // tries to pick up stick to his left (same index as himself)
    pickUpStick(position + 1);
    System.out.println("Philosopher " + position + " picked up left stick.");
  }

  private void pickUpRightStick() {
    // tries to pick up stick to his left (index of himself +1)
    pickUpStick(position);
    System.out.println("Philosopher " + position + " picked up right stick.");
  }

  private void beLazy() {
    // up to one second between stick pickups
    try {
      Thread.sleep(random.nextInt(T_STICK_DIFF_MAX - T_STICK_DIFF_MIN) + T_STICK_DIFF_MIN);
    } catch (InterruptedException e) { /* nothing */ }
  }

  private void pickUpSticks() {
    pickUpLeftStick();
    // up to one second between stick pickups
    beLazy();
    pickUpRightStick();
  }

  private void putDownStick(int position) {
    table.setStick(position);
  }

  private void putDownLeftStick() {
    putDownStick(position);
    System.out.println("Philosopher " + position + " put down left stick.");
  }

  private void putDownRightStick() {
    putDownStick(position + 1);
    System.out.println("Philosopher " + position + " put down right stick.");
  }

  private void putDownSticks() {
    putDownLeftStick();
    beLazy();
    putDownRightStick();
  }

  private void eat() {
    setState(PhilosopherState.EATING);
    System.out.println("Philosopher " + position + " starts eating.");
    int tEat = random.nextInt(T_EAT_MAX - T_EAT_MIN) + T_EAT_MIN;
    try {
      Thread.sleep(tEat);
    } catch (InterruptedException e) { /* nothing */ }
    // puts back sticks
    System.out.println("Philosopher " + position + " finished eating.");
    putDownSticks();
  }

  private void think() {
    setState(PhilosopherState.THINKING);
    System.out.println("Philosopher " + position + " started thinking.");
    int tThink = random.nextInt(T_THINK_MAX - T_THINK_MIN) + T_THINK_MIN;
    try {
      Thread.sleep(tThink);
    } catch (InterruptedException e) { /* nothing */ }
    setState(PhilosopherState.HUNGRY);
    System.out.println("Philosopher " + position + " is hungry.");
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    // marks sitting philosophers with surrounding box
    if (hasChair) {
      g.drawRect(1, 1, getWidth() - 2, getHeight() - 2);
    }
  }

  @Override
  public void run() {
    while (true) {
      think();
      sitDown();
      pickUpSticks();
      eat();
      getUp();
    }
  }

  private void getUp() {
    if (waiter != null) {
      waiter.leaveSeat();
    }
    hasChair = false;
    System.out.println("Philosopher " + position + " got up.");
  }

  private void sitDown() {
    if (waiter != null) {
      waiter.getSeat();
    }
    hasChair = true;
    System.out.println("Philosopher " + position + " sat down.");
  }
}
