package controller;

import model.Ellipse;
import view.DrawingPad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

/**
 * Represents Ellipse drawing tool
 */
public class EllipseDrawingTool extends DrawingTool {
  private Ellipse current;

  /**
   * Constructor
   *
   * @param editor the drawing editor
   * @param parent the parent component in the containment hierarchy
   */
  public EllipseDrawingTool(DrawingPad editor, JComponent parent) {
    super(editor, parent);
    current = null;
  }

  @Override
  protected void createButton(JComponent parent) {
    button = new JButton("Ellipse");
  }

  @Override
  protected void addListener() {
    button.addActionListener(new EllipseToolClickHandler());
  }

  @Override
  public void mousePressedInDrawingArea(MouseEvent e) {
    current = new Ellipse(e.getPoint());
    editor.addToDrawing(current);
  }

  @Override
  public void mouseReleasedInDrawingArea(MouseEvent e) {
    current = null;
  }

  @Override
  public void mouseClickedInDrawingArea(MouseEvent e) {

  }

  @Override
  public void mouseDraggedInDrawingArea(MouseEvent e) {
    current.setWidth(e.getPoint());
    current.setHeight(e.getPoint());
  }

  /**
   * Ellipse tool mouse listener
   */
  private class EllipseToolClickHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      editor.setActiveDrawingTool(EllipseDrawingTool.this);
    }
  }
}
