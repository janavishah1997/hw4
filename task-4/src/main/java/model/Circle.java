package model;

import java.awt.*;

/**
 * Represents a circle.
 */
public class Circle extends AbstractShape {
  private Ellipse e;

  /**
   * Constructs a circle whose bounding rectangle is at topLeft
   *
   * @param topLeft top-left corner of circle's bounding rectangle
   */
  public Circle(Point topLeft) {
    x = (int) topLeft.getX();
    y = (int) topLeft.getY();
    e = new Ellipse(topLeft);
  }

  /**
   * Sets diameter of circle to be difference in x coordinates
   * between top-left corner of this circle's bounding rectangle
   * and x coordinate of bottomRight
   *
   * @param bottomRight point used to determine diameter of circle
   */
  public void setDiameter(Point bottomRight) {
    int diam = (int) bottomRight.getX() - x;
    e.setWidth(diam);
    e.setHeight(diam);
  }

  @Override
  public void draw(Graphics g) {
    e.draw(g);
  }

  @Override
  public boolean contains(Point point) {
    return e.contains(point);
  }

  @Override
  public void translate(int dx, int dy) {
    super.translate(dx, dy);
    e.translate(dx, dy);
  }

  @Override
  public void select() {
    super.select();
    e.select();
  }

  @Override
  public void unselect() {
    super.unselect();
    e.unselect();
  }
}
