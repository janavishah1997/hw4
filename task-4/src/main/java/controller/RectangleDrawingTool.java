package controller;

import model.Rectangle;
import view.DrawingPad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class RectangleDrawingTool extends DrawingTool {
  private Rectangle current;

  /**
   * Constructor
   *
   * @param editor the drawing editor
   * @param parent the parent to which this tool will be added
   */
  public RectangleDrawingTool(DrawingPad editor, JComponent parent) {
    super(editor, parent);
    current = null;
  }

  @Override
  protected void createButton(JComponent parent) {
    button = new JButton("Rectangle");
  }

  @Override
  protected void addListener() {
    button.addActionListener(new RectangleToolClickHandler());
  }

  @Override
  public void mousePressedInDrawingArea(MouseEvent e) {
    current = new Rectangle(e.getPoint());
    current.setWidth(e.getPoint());
    current.setHeight(e.getPoint());
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
   * Listener for click events on this rectangle tool
   */
  private class RectangleToolClickHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      editor.setActiveDrawingTool(RectangleDrawingTool.this);
    }
  }
}

