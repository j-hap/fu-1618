package ea.ke7._01.client;

import ea.ke7._01.common.Counter;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class CountFrame extends Frame {
  private Counter counter;

  private void getServerCounter() {
    try {
      counter = (Counter) Naming.lookup("rmi://localhost/Counter");
    } catch (NotBoundException e) {
      System.out.println("No Counter found on Server.");
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (RemoteException e) {
      System.out.println("Could not connect to Naming service. Is it running?");
    }
    if (counter == null) {
      System.exit(1);
    }
  }

  public CountFrame() {
    getServerCounter();

    setSize(200, 100);
    setResizable(false);
    setLayout(new BorderLayout());

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    Panel centerPanel = new Panel();
    centerPanel.setLayout(new FlowLayout());
    add(centerPanel, BorderLayout.NORTH);

    TextField counterDisplay = new TextField(3); // three columns wide
    try {
      counterDisplay.setText(counter.getValueAsString());
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    counterDisplay.setEditable(false);

    Button b;
    b = new Button("<");
    b.addActionListener(new CounterActionListener(counter, counterDisplay, CounterActionListener.Operation.DECREMENT));
    centerPanel.add(b);

    centerPanel.add(counterDisplay);

    b = new Button(">");
    b.addActionListener(new CounterActionListener(counter, counterDisplay, CounterActionListener.Operation.INCREMENT));
    centerPanel.add(b);

    b = new Button("RESET");
    b.addActionListener(new CounterActionListener(counter, counterDisplay, CounterActionListener.Operation.RESET));
    add(b, BorderLayout.SOUTH);

    setLocationRelativeTo(null);
    setVisible(true);
  }

  public static void main(String[] args) {
    new CountFrame();
  }
}
