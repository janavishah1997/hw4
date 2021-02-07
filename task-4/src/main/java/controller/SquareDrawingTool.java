package controller;

import model.Square;
import view.DrawingPad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

/**
 * Represents a tool for drawing a square
 */
public class SquareDrawingTool extends DrawingTool {

  private Square current;

  public SquareDrawingTool(DrawingPad editor, JComponent parent) {
    super(editor, parent);
    current = null;
  }

  @Override
  protected void createButton(JComponent parent) {
    button = new JButton("Square");
  }

  @Override
  protected void addListener() {
    button.addActionListener(new SquareToolClickListener());
  }

  @Override
  public void mouseClickedInDrawingArea(MouseEvent e) {
  }

  @Override
  public void mousePressedInDrawingArea(MouseEvent e) {
    current = new Square(e.getPoint());
    current.setSide(e.getPoint());
    editor.addToDrawing(current);
  }

  @Override
  public void mouseReleasedInDrawingArea(MouseEvent e) {
    current = null;
  }

  @Override
  public void mouseDraggedInDrawingArea(MouseEvent e) {
    current.setSide(e.getPoint());
  }

  /**
   * Listener for mouse events on this square tool
   */
  private class SquareToolClickListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      editor.setActiveDrawingTool(SquareDrawingTool.this);
    }
  }
}
