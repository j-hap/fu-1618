package ea.ke6._03;

import java.awt.*;

/**
 * A layout that arrages objects in a circular manner
 */
class CircularLayout implements LayoutManager, java.io.Serializable {
  /**
   * This value indicates that the first oject shall be aligned at
   * 12 o'clock
   */
  public static final int TWELVE = 0;
  /**
   * This value indicates that the first oject shall be aligned at
   * 3 o'clock
   */
  public static final int THREE = 1;
  /**
   * This value indicates that the first oject shall be aligned at
   * 6 o'clock
   */
  public static final int SIX = 2;
  /**
   * This value indicates that the first oject shall be aligned at
   * 9 o'clock
   */
  public static final int NINE = 3;

  /**
   * <code>newAlign</code> is the property that determines
   * how the first object is oriented when more than one
   * object is inserted for the Java 2 platform,
   * v1.2 and greater.
   * It can be one of the following three values:
   * <ul>
   * <li><code>TWELVE</code>
   * <li><code>THREE</code>
   * <li><code>SIX</code>
   * <li><code>NINE</code>
   * </ul>
   *
   * @serial
   * @see #getAlignment
   * @see #setAlignment
   * @since 1.2
   */
  private int align;

  /**
   * Determines whether or not the first element is placed in the circles center.
   */
  private final boolean hasCenter;


  /**
   * Constructs a new <code>CircularLayout</code> with a 12 o'clock alignment
   * and no center element.
   */
  public CircularLayout() {
    this(TWELVE, false);
  }

  /**
   * Constructs a new <code>CircularLayout</code> with specified
   * alignment and no center element.
   */
  public CircularLayout(int align) {
    this(align, false);
  }

  /**
   * Constructs a new <code>CircularLayout</code> with the specified
   * alignment and center element state.
   * The value of the alignment argument must be one of
   * <code>CircularLayout.TWELVE</code>, <code>CircularLayout.THREE</code>,
   * <code>CircularLayout.SIX</code> or <code>CircularLayout.NINE</code>.
   *
   * @param align the alignment value
   */
  public CircularLayout(int align, boolean hasCenter) {
    this.hasCenter = hasCenter;
    setAlignment(align);
  }

  /**
   * Gets the alignment for this layout.
   * Possible values are <code>CircularLayout.TWELVE</code>,
   * <code>CircularLayout.THREE</code>, <code>CircularLayout.SIX</code>,
   * or <code>CircularLayout.NINE</code>.
   *
   * @return the alignment value for this layout
   * @see CircularLayout#setAlignment
   * @since JDK1.1
   */
  public int getAlignment() {
    return align;
  }

  /**
   * Sets the alignment for this layout.
   * Possible values are
   * <ul>
   * <li><code>CircularLayout.TWELVE</code>
   * <li><code>CircularLayout.THREE</code>
   * <li><code>CircularLayout.SIX</code>
   * <li><code>CircularLayout.NINE</code>
   * </ul>
   *
   * @param align one of the alignment values shown above
   * @see #getAlignment()
   * @since JDK1.1
   */
  public void setAlignment(int align) {
    this.align = align;
  }

  /**
   * Adds the specified component to the layout.
   * Not used by this class.
   *
   * @param name the name of the component
   * @param comp the component to be added
   */
  public void addLayoutComponent(String name, Component comp) {
  }

  /**
   * Removes the specified component from the layout.
   * Not used by this class.
   *
   * @param comp the component to remove
   * @see java.awt.Container#removeAll
   */
  public void removeLayoutComponent(Component comp) {
  }

  private double getDeltaPhi(int nComponents) {
    return 2 * Math.PI / (nComponents - (hasCenter ? 1 : 0));
  }

  /**
   * Determines the preferred size of the container argument using
   * this CircularLayout.
   * <p>
   * The preferred size is determined by the largest angular slice
   * needed for a single component to not overlap with any other
   * component.
   *
   * @param parent the container that needs to be laid out
   * @return the preferred dimensions to lay out the
   * subcomponents of the specified container
   * @see Container
   * @see #minimumLayoutSize
   * @see java.awt.Container#getPreferredSize
   */
  public Dimension preferredLayoutSize(Container parent) {
    synchronized (parent.getTreeLock()) {
      int nComponents = parent.getComponentCount();
      double deltaPhi = getDeltaPhi(nComponents);
      // component extent onto tangent to circle to determine needed arc length
      double maxArc = 0;
      int iComp = hasCenter ? 1 : 0;
      for (; iComp < nComponents; ++iComp) {
        Component comp = parent.getComponent(iComp);
        Dimension d = comp.getPreferredSize();
        double compAngle = iComp * deltaPhi + align * Math.PI;
        double arc = Math.abs(d.width * Math.cos(compAngle)) + Math.abs(d.height * Math.sin(compAngle));
        maxArc = Math.max(maxArc, arc);
      }
      int radius = (int) (maxArc / (2 * Math.tan(deltaPhi)));
      return new Dimension(radius, radius);
    }
  }

  /**
   * Returns the minimum dimensions needed to layout the <i>visible</i>
   * components contained in the specified parent container.
   *
   * @param parent the container that needs to be laid out
   * @return the minimum dimensions to lay out the
   * subcomponents of the specified container
   * @see #preferredLayoutSize
   * @see java.awt.Container
   * @see java.awt.Container#doLayout
   */
  public Dimension minimumLayoutSize(Container parent) {
    // lazy way
    return preferredLayoutSize(parent);
  }

  /**
   * Lays out the container. This method lets each
   * <i>visible</i> component take
   * its preferred size by reshaping the components in the
   * parent container in order to satisfy the alignment of
   * this <code>CircularLayout</code> object.
   *
   * @param parent the specified component being laid out
   * @see Container
   * @see java.awt.Container#doLayout
   */
  public void layoutContainer(Container parent) {
    synchronized (parent.getTreeLock()) {
      Insets insets = parent.getInsets();
      int nComponents = parent.getComponentCount();
      Dimension usableDim = new Dimension(parent.getWidth() - (insets.left + insets.right), parent.getHeight() - (insets.top + insets.bottom));
      int radius = Math.min(usableDim.width, usableDim.height) / 2;

      double deltaPhi = getDeltaPhi(nComponents);
      // determines largest extent of all components normal to circle to shrink radius
      double maxDeltaRadius = 0;
      for (int iComp = (hasCenter ? 1 : 0); iComp < nComponents; iComp++) {
        Component comp = parent.getComponent(iComp);
        Dimension d = comp.getPreferredSize();
        double compAngle = iComp * deltaPhi + align * Math.PI;
        double deltaRadius = Math.abs(d.width * Math.sin(compAngle)) + Math.abs(d.height * Math.cos(compAngle));
        maxDeltaRadius = Math.max(maxDeltaRadius, deltaRadius);
      }

      // center position must be calculated with unshrunk radius
      int xCenter = insets.left + radius;
      int yCenter = insets.top + radius;

      radius -= maxDeltaRadius / 2;

      if (this.hasCenter) {
        Component comp = parent.getComponent(0);
        Dimension dim = comp.getPreferredSize();
        // diagonal of the center component shall be as big as the inner diameter
        double innerRadius = radius - (maxDeltaRadius / 2);
        double ratio = dim.width / dim.height;
        dim.height = (int) (2 * innerRadius / Math.sqrt(ratio * ratio + 1));
        dim.width = (int) (ratio * dim.height);
        comp.setBounds(xCenter - dim.width / 2, yCenter - dim.height / 2, dim.width, dim.height);
      }

      for (int iComp = (hasCenter ? 1 : 0); iComp < nComponents; iComp++) {
        Component comp = parent.getComponent(iComp);
        Dimension dim = comp.getPreferredSize();
        double compAngle = iComp * deltaPhi + align * Math.PI;

        int x = (int) (xCenter + radius * Math.sin(compAngle) - dim.width / 2);
        int y = (int) (yCenter - radius * Math.cos(compAngle) - dim.height / 2);
        comp.setBounds(x, y, dim.width, dim.height);
      }
    }
  }

  /**
   * Returns a string representation of this <code>CircularLayout</code>
   * object and its values.
   *
   * @return a string representation of this layout
   */
  public String toString() {
    String str = "";
    switch (align) {
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
