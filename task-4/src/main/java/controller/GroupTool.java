package controller;

import view.DrawingPad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

/**
 * Represents selection tools in the drawing application.
 */
public class GroupTool extends DrawingTool {

  /**
   * Constructor
   *
   * @param editor the drawing editor
   * @param parent the parent component to which this tool will be added
   */
  public GroupTool(DrawingPad editor, JComponent parent) {
    super(editor, parent);
  }

  @Override
  protected void createButton(JComponent parent) {
    button = new JButton("Group");
    addToParent(parent);
  }

  @Override
  protected void addListener() {
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        editor.setActiveDrawingTool(GroupTool.this);
        editor.getCurrentDrawing().groupSelectedFigures();
      }
    });
  }

  @Override
  public void mousePressedInDrawingArea(MouseEvent e) {

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
