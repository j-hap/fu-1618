package ea.ke6._03;

import java.awt.*;

/**
 * A layout that arrages objects in a circular manner
 */
class CircularLayout implements LayoutManager, java.io.Serializable {
  static enum ZeroAngleReference {
    TWELVE, THREE, SIX, NINE;
  }

  private ZeroAngleReference zeroAngleReference;
  private final boolean hasCenterObject;

  public CircularLayout() {
    this(false, ZeroAngleReference.TWELVE);
  }

  public CircularLayout(boolean hasCenterObject) {
    this(hasCenterObject, ZeroAngleReference.TWELVE);
  }

  public CircularLayout(boolean hasCenterObject, ZeroAngleReference zeroAngleReference) {
    this.hasCenterObject = hasCenterObject;
    setAlignment(zeroAngleReference);
  }

  public void setAlignment(ZeroAngleReference zeroAngleReference) {
    this.zeroAngleReference = zeroAngleReference;
  }

  public void addLayoutComponent(String name, Component comp) {
  }

  public void removeLayoutComponent(Component comp) {
  }

  private double getDeltaPhi(int nComponents) {
    return 2 * Math.PI / (nComponents - (hasCenterObject ? 1 : 0));
  }

  private double getComponentAngle(int iComponent, double deltaPhi) {
    return iComponent * deltaPhi + zeroAngleReference.ordinal() * Math.PI / 2;
  }

  public Dimension preferredLayoutSize(Container parent) {
    synchronized (parent.getTreeLock()) {
      int nComponents = parent.getComponentCount();
      double deltaPhi = getDeltaPhi(nComponents);
      // component extent onto tangent to circle to determine needed arc length
      double maxArc = 0;
      int iComp = hasCenterObject ? 1 : 0;
      for (; iComp < nComponents; ++iComp) {
        Component comp = parent.getComponent(iComp);
        Dimension d = comp.getPreferredSize();
        double compAngle = getComponentAngle(iComp, deltaPhi);
        double arc = Math.abs(d.width * Math.cos(compAngle)) + Math.abs(d.height * Math.sin(compAngle));
        maxArc = Math.max(maxArc, arc);
      }
      int radius = (int) (maxArc / (2 * Math.tan(deltaPhi)));
      return new Dimension(radius, radius);
    }
  }

  public Dimension minimumLayoutSize(Container parent) {
    // lazy way
    return preferredLayoutSize(parent);
  }

  public void layoutContainer(Container parent) {
    synchronized (parent.getTreeLock()) {
      Insets insets = parent.getInsets();
      int nComponents = parent.getComponentCount();
      Dimension usableDim = new Dimension(parent.getWidth() - (insets.left + insets.right), parent.getHeight() - (insets.top + insets.bottom));
      int radius = Math.min(usableDim.width, usableDim.height) / 2;

      double deltaPhi = getDeltaPhi(nComponents);
      // determines largest extent of all components normal to circle to shrink radius
      double maxDeltaRadius = 0;
      for (int iComp = (hasCenterObject ? 1 : 0); iComp < nComponents; iComp++) {
        Component comp = parent.getComponent(iComp);
        Dimension d = comp.getPreferredSize();
        double compAngle = getComponentAngle(iComp, deltaPhi);
        double deltaRadius = Math.abs(d.width * Math.sin(compAngle)) + Math.abs(d.height * Math.cos(compAngle));
        maxDeltaRadius = Math.max(maxDeltaRadius, deltaRadius);
      }

      // center position must be calculated with unshrunk radius
      int xCenter = insets.left + radius;
      int yCenter = insets.top + radius;

      radius -= maxDeltaRadius / 2;

      if (this.hasCenterObject) {
        Component comp = parent.getComponent(0);
        Dimension dim = comp.getPreferredSize();
        // diagonal of the center component shall be as big as the inner diameter
        double innerRadius = radius - (maxDeltaRadius / 2);
        double ratio = dim.width / dim.height;
        dim.height = (int) (2 * innerRadius / Math.sqrt(ratio * ratio + 1));
        dim.width = (int) (ratio * dim.height);
        comp.setBounds(xCenter - dim.width / 2, yCenter - dim.height / 2, dim.width, dim.height);
      }

      for (int iComp = (hasCenterObject ? 1 : 0); iComp < nComponents; iComp++) {
        Component comp = parent.getComponent(iComp);
        Dimension dim = comp.getPreferredSize();
        double compAngle = getComponentAngle(iComp, deltaPhi);

        int x = (int) (xCenter + radius * Math.sin(compAngle) - dim.width / 2);
        int y = (int) (yCenter - radius * Math.cos(compAngle) - dim.height / 2);
        comp.setBounds(x, y, dim.width, dim.height);
      }
    }
  }

  public String toString() {
    String str = "";
    switch (zeroAngleReference) {
      case TWELVE:
        str = ",align=12oclock";
        break;
      case THREE:
        str = ",align=3oclock";
        break;
      case SIX:
        str = ",align=6oclock";
        break;
      case NINE:
        str = ",align=9oclock";
        break;
    }
    return getClass().getName() + "[" + str + "]";
  }
}
