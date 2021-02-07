package model;

import java.awt.*;

/**
 * Represents shape figures in the drawing application.
 */
public abstract class AbstractShape implements Shape {
  /* offsets for drawing shadow when figure is selected */
  protected static final int DX = 1;
  protected static final int DY = 1;
  /* colour of shadow */
  protected static final Color SHADOW_COLOR = new Color(130, 130, 230);
  /* x and y coordinate of Figure */
  protected int x;
  protected int y;
  /* true if this shape is selected, false otherwise */
  protected boolean selected = false;

  public abstract void draw(Graphics g);

  public abstract boolean contains(Point point);

  public void translate(int dx, int dy) {
    x += dx;
    y += dy;
  }

  public void select() {
    selected = true;
  }

  public void unselect() {
    selected = false;
  }

  public boolean isSelected() {
    return selected;
  }
}

