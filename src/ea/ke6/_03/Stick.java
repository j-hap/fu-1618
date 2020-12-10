package ea.ke6._03;

import javax.swing.*;
import java.awt.*;

/**
 * class that draws a rotated rectangle to imitate a chopstick
 */
class Stick extends JPanel {
  private final double drawAngle;
  static private final int WIDTH = 4;
  static private final int LENGTH = 16;

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    Dimension s = getPreferredSize();

    // moves rotation center (and origin) to middle of available space
    g2d.translate(s.width / 2, s.height / 2);
    Rectangle rect = new Rectangle(-WIDTH / 2, -LENGTH / 2, WIDTH, LENGTH);
    g2d.rotate(drawAngle);
    g2d.fill(rect);
  }

  public Stick(double angle) {
    this.drawAngle = angle;
    setPreferredSize(new Dimension(20, 20));
    setOpaque(false); // transparent background
  }
}
