package controller;

import model.Shape;
import view.DrawingPad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

/**
 * Represents selection tools in the drawing application.
 */
public class SelectionTool extends DrawingTool {

  /**
   * Constructor
   *
   * @param editor the drawing editor
   * @param parent the parent component to which this tool will be added
   */
  public SelectionTool(DrawingPad editor, JComponent parent) {
    super(editor, parent);
  }

  @Override
  protected void createButton(JComponent parent) {
    button = new JButton("Select");
    addToParent(parent);
  }

  @Override
  protected void addListener() {
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        editor.setActiveDrawingTool(SelectionTool.this);
      }
    });
  }

  @Override
  public void mousePressedInDrawingArea(MouseEvent e) {
    Shape shape = editor.getFigureInDrawing(e.getPoint());
    if (shape != null) {
      if (!shape.isSelected())
        shape.select();
      else
        shape.unselect();
    }
    editor.validate();

  }

  @Override
  public void mouseClickedInDrawingArea(MouseEvent e) {

  }

  @Override
  public void mouseDraggedInDrawingArea(MouseEvent e) {

  }

  @Override
  public void mouseReleasedInDrawingArea(MouseEvent e) {

  }
}
