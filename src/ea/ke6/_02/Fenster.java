package ea.ke6._02;

import java.awt.*;
import java.awt.event.*;

public class Fenster extends Frame implements ActionListener {
  boolean state = false;

  Fenster() {
    setSize(300, 100);
    setLocation(100, 100);

    Button b = new Button("Kick me!");
    b.addActionListener(this);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent ev) {
        System.exit(0);
      }
    });

    add(b, BorderLayout.CENTER);
    actionPerformed(null);
    setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {
    if (!state)
      setTitle("Da staunt ihr ...");
    else
      setTitle("... nicht schlecht.");
    state = !state;
  }

  public static void main(String[] args) {
    new Fenster();
    System.out.println("Also ich bin hier jetzt fertig.");
  }
}