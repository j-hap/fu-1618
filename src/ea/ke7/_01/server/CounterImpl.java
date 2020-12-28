package ea.ke7._01.server;

import ea.ke7._01.common.Counter;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

class CounterImpl extends UnicastRemoteObject implements Counter {
  private int startValue;
  private int value;
  private int min = 0;
  private int max = 99;

  CounterImpl(int start) throws RemoteException {
    startValue = start;
    value = start;
  }

  public void increment() {
    if (value < max) {
      value++;
    }
  }

  public void decrement() {
    if (value > min) {
      value--;
    }
  }

  public void reset() {
    value = startValue;
  }

  public String getValueAsString() {
    return String.valueOf(value);
  }
}
