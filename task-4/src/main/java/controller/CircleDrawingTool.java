package controller;

import model.Circle;
import view.DrawingPad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class CircleDrawingTool extends DrawingTool {
  private Circle current;

  public CircleDrawingTool(DrawingPad editor, JComponent parent) {
    super(editor, parent);
    current = null;
  }

  @Override
  protected void createButton(JComponent parent) {
    button = new JButton("Circle");
  }

  @Override
  protected void addListener() {
    button.addActionListener(new CircleToolClickListener());
  }

  @Override
  public void mousePressedInDrawingArea(MouseEvent e) {
    current = new Circle(e.getPoint());
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
    current.setDiameter(e.getPoint());
  }

  /**
   * Listener for mouse events on this circle tool
   */
  private class CircleToolClickListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      editor.setActiveDrawingTool(CircleDrawingTool.this);
    }
  }
}
