package ea.ke6._03;

import java.awt.*;

/**
 * class that draws a circle to imitate a rice bowl
 */
class Bowl extends ColoredCircle {
  public Bowl() {
    setFillColor(Color.LIGHT_GRAY);
    setPreferredSize(new Dimension(20, 20));
  }
}
