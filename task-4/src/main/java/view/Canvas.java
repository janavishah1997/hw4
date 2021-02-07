package view;

import model.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a drawing
 */
public class Canvas extends JPanel {

  private static int WIDTH = 500;
  private static int HEIGHT = 500;
  private DrawingPad editor;
  private List<Shape> shapes;

  /**
   * Constructor sets up drawing window
   *
   * @param ed the drawing editor that edits this drawing
   */
  public Canvas(DrawingPad ed) {
    super();
    setMinimumSize(new Dimension(WIDTH, HEIGHT));
    setBackground(new Color(255, 247, 203));
    shapes = new LinkedList<Shape>();
    editor = ed;
    DrawingMouseListener dml = new DrawingMouseListener();
    addMouseListener(dml);
    addMouseMotionListener(dml);
    setVisible(true);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (Shape f : shapes) {
      f.draw(g);
    }
  }

  /**
   * Adds a figure to this drawing
   *
   * @param f the figure to add to this drawing
   */
  public void addFigure(Shape f) {
    shapes.add(f);
    repaint();
  }

  /**
   * Removes a figure from this drawing
   *
   * @param f the figure to remove from this drawing
   */
  public void removeFigure(Shape f) {
    shapes.remove(f);
    repaint();
  }

  /**
   * Gets the figure at a given point.
   *
   * @param point the point at which figure is to be located
   * @return figure at given point or null if no such figure exists
   */
  public Shape getFigureAtPoint(Point point) {
    for (Shape f : shapes) {
      if (f.contains(point)) {
        return f;
      }
    }
    return null;
  }

  /**
   * Grab all the selected figures into a list
   */
  private List<Shape> getSelectedFigures() {
    List<Shape> selectedShapes = new ArrayList<Shape>();
    for (Shape f : shapes) {
      if (f.isSelected()) {
        selectedShapes.add(f);
        f.unselect();
      }
    }
    return selectedShapes;
  }

  /**
   * Groups selected figures
   */
  public void groupSelectedFigures() {
    // TODO Implement me!
  }

  /**
   * Ungroup selected figures
   */
  public void ungroupSelectedFigures() {
    // TODO Implement me!
  }

  /**
   * Represents a mouse listener for this drawing Note to CPSC 210 students:
   * this is an example of an inner class - it is defined within the scope of
   * another class (in this case Drawing) which is referred to as the outer
   * class. Notice how the methods in this inner class have access to the
   * private fields of the outer class (in this case 'editor').
   */
  private class DrawingMouseListener extends MouseAdapter {
    /**
     * Forward mouse pressed event to the active tool
     */
    public void mousePressed(MouseEvent e) {
      editor.sendMousePressedToActiveTool(e);
    }

    /**
     * Forward mouse released event to the active tool
     */
    public void mouseReleased(MouseEvent e) {
      editor.sendMouseReleasedToActiveTool(e);
    }

    /**
     * Forward mouse clicked event to the active tool
     */
    public void mouseClicked(MouseEvent e) {
      editor.sendMouseClickedToActiveTool(e);
    }

    /**
     * Forward mouse dragged event to the active tool
     */
    public void mouseDragged(MouseEvent e) {
      editor.sendMouseDraggedToActiveTool(e);
    }
  }
}
