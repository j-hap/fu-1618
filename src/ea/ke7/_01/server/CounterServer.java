package ea.ke7._01.server;

import ea.ke7._01.common.Counter;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class CounterServer {
  private void startRmiRegistry() {
    try {
      java.rmi.registry.LocateRegistry.createRegistry(1099);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  private Counter createCounter() {
    try {
      return new CounterImpl(10);
    } catch (RemoteException e) {
      System.out.println("Failed to create Counter.");
      e.printStackTrace();
      System.exit(1);
    }
    return null;
  }

  private void register(Counter counter) {
    try {
      Naming.rebind("Counter", counter);
    } catch (RemoteException e) {
      System.out.println("Failed to register Counter in Service. Is it running?");
      e.printStackTrace();
      System.exit(1);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

  public CounterServer() {
    // creates a new counter and registers it
    startRmiRegistry();
    Counter counter = createCounter();
    register(counter);
  }

  public static void main(String[] args) {
    new CounterServer();
    System.out.println("Server started.");
  }
}
