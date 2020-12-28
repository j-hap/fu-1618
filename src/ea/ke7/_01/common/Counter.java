package ea.ke7._01.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Counter extends Remote {
  void increment() throws RemoteException;
  void decrement() throws RemoteException;
  void reset() throws RemoteException;
  String getValueAsString() throws RemoteException;
}

