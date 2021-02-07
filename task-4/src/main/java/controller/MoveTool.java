package controller;

import model.Shape;
import view.DrawingPad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

/**
 * Represents tools for moving a figure in the drawing editor
 */
public class MoveTool extends DrawingTool {

  private Shape shapeToMove;
  private Point start;

  /**
   * Constructor
   *
   * @param editor the drawing editor
   * @param parent the parent component to which this tool will be added
   */
  public MoveTool(DrawingPad editor, JComponent parent) {
    super(editor, parent);
    shapeToMove = null;
    start = null;
  }

  @Override
  protected void createButton(JComponent parent) {
    button = new JButton("Move");
    addToParent(parent);
  }

  @Override
  protected void addListener() {
    button.addActionListener(new MoveToolClickHandler());
  }

  @Override
  public void mousePressedInDrawingArea(MouseEvent e) {
    shapeToMove = editor.getFigureInDrawing(e.getPoint());
    if (shapeToMove != null) {
      shapeToMove.select();
      start = e.getPoint();
    }
  }

  @Override
  public void mouseReleasedInDrawingArea(MouseEvent e) {
    if (shapeToMove != null) {
      shapeToMove.unselect();
      shapeToMove = null;
    }
  }

  @Override
  public void mouseClickedInDrawingArea(MouseEvent e) {
  }

  @Override
  public void mouseDraggedInDrawingArea(MouseEvent e) {
    if (shapeToMove != null) {
      int dx = (int) (e.getPoint().getX() - start.getX());
      int dy = (int) (e.getPoint().getY() - start.getY());
      start = e.getPoint();
      shapeToMove.translate(dx, dy);
    }
  }

  /**
   * Listener for click events on this move tool
   */
  private class MoveToolClickHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      editor.setActiveDrawingTool(MoveTool.this);
    }
  }
}
