package ea.ke7._01.client;

import ea.ke7._01.common.Counter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

class CounterActionListener implements ActionListener {
  private TextField counterDisplay;
  private Counter counter;
  private final Operation operation;

  static enum Operation {
    INCREMENT, DECREMENT, RESET;
  }

  public CounterActionListener(Counter counter, TextField counterDisplay, Operation operation) {
    this.counter = counter;
    this.counterDisplay = counterDisplay;
    this.operation = operation;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      switch (operation) {
        case INCREMENT:
          counter.increment();
          break;
        case DECREMENT:
          counter.decrement();
          break;
        case RESET:
          counter.reset();
          break;
      }
      counterDisplay.setText(counter.getValueAsString());
    } catch (RemoteException error) {
      System.out.println("Failed to control counter.");
    }
  }
}
