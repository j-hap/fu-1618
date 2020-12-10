package ea.ke6._03;

import java.awt.*;

class TableGraphics extends ColoredCircle {
  public void setStickVisible(int iStick, boolean status) {
    getComponent(iStick * 2 + 1).setVisible(status);
  }

  public void showStick(int iStick) {
    setStickVisible(iStick, true);
  }

  public void hideStick(int iStick) {
    setStickVisible(iStick, false);
  }

  public TableGraphics(int nSeats) {
    setPreferredSize(new Dimension(300, 300));
    setLayout(new CircularLayout());
    setFillColor(new Color(200, 150, 100));
    double deltaPhi = 2 * Math.PI / nSeats;
    for (int iSeat = 0; iSeat < nSeats; ++iSeat) {
      add(new Bowl());
      add(new Stick(iSeat * deltaPhi + deltaPhi / 2));
    }
  }
}
