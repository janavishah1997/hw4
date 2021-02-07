package controller;

import view.DrawingPad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class UnGroupTool extends DrawingTool {

  public UnGroupTool(DrawingPad editor, JComponent parent) {
    super(editor, parent);
  }

  @Override
  protected void createButton(JComponent parent) {
    button = new JButton("UnGroup");
    addToParent(parent);
  }

  @Override
  protected void addListener() {
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        editor.setActiveDrawingTool(UnGroupTool.this);
        editor.getCurrentDrawing().ungroupSelectedFigures();
      }
    });
  }

  @Override
  public void mousePressedInDrawingArea(MouseEvent e) {

  }

  @Override
  public void mouseReleasedInDrawingArea(MouseEvent e) {

  }

  @Override
  public void mouseClickedInDrawingArea(MouseEvent e) {

  }

  @Override
  public void mouseDraggedInDrawingArea(MouseEvent e) {

  }
}
