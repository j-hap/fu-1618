package ea.ke6._03;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * class that draws a circle with transparent background
 */
class ColoredCircle extends JPanel {
  private Color fillColor = getBackground();

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    Dimension s = getSize();
    // slightly smaller than available draw space (otherwise border is cut off)
    Ellipse2D ell = new Ellipse2D.Double(1, 1, s.width - 2, s.height - 2);
    g2d.setColor(fillColor);
    g2d.fill(ell);
    g2d.setColor(Color.BLACK);
    g2d.draw(ell);
  }

  public void setFillColor(Color newColor) {
    fillColor = newColor;
  }

  public ColoredCircle() {
    setOpaque(false);
  }
}
