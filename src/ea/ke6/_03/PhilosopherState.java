package ea.ke6._03;

import java.awt.Color;

/**
 * States that a Philosopher can have, including a color to each state
 */
enum PhilosopherState {
  THINKING(Color.BLUE),
  HUNGRY(Color.RED),
  EATING(Color.GREEN);

  public final Color stateColor;

  PhilosopherState(Color stateColor) {
    this.stateColor = stateColor;
  }
}
