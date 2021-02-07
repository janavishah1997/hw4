package view;

import controller.*;
import model.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

/**
 * The main window of the drawing application.
 */
public class DrawingPad extends JFrame {

  private static int WIDTH = 500;
  private static int HEIGHT = 500;
  private static int TOOL_WIDTH = 100;

  // The drawings in the application (only one of which is displayed at any given time)
  private List<Canvas> drawings;

  // The tools in the application, which act like radio buttons. One tool is always active
  private List<DrawingTool> drawingTools;

  // Maintain references to current tool and current drawing
  private Canvas currentDrawing;
  private DrawingTool activeDrawingTool;

  // Constructor
  public DrawingPad() {
    super("DrawingApp");

    // Initialize Fields
    drawings = new LinkedList<Canvas>();
    drawingTools = new LinkedList<DrawingTool>();
    activeDrawingTool = null;
    currentDrawing = null;

    // Initialize graphics
    setLayout(new BorderLayout());
    setMinimumSize(new Dimension(WIDTH, HEIGHT));
    createMenus();
    createTools();
    addDrawing(new Canvas(this));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  /**
   * Adds a drawing to the editor
   *
   * @param newCanvas the new drawing to add
   */
  private void addDrawing(Canvas newCanvas) {
    if (newCanvas != null) {
      if (currentDrawing != null) {
        // Remove the current drawing from the graphics hierarchy
        remove(currentDrawing);
      }
      drawings.add(newCanvas);
      currentDrawing = newCanvas;

      add(newCanvas, BorderLayout.CENTER);
      validate();
    }
  }

  /**
   * Adds given figure to drawing
   *
   * @param f the figure to add to the drawing
   */
  public void addToDrawing(Shape f) {
    currentDrawing.addFigure(f);
  }

  /**
   * Removes given figure from drawing
   *
   * @param f the figure to remove from the drawing
   */
  public void removeFromDrawing(Shape f) {
    currentDrawing.removeFigure(f);
  }

  /**
   * Pass mouse pressed event to active drawing tool
   *
   * @param e the mouse event
   */
  void sendMousePressedToActiveTool(MouseEvent e) {
    if (activeDrawingTool != null)
      activeDrawingTool.mousePressedInDrawingArea(e);
    repaint();
  }

  /**
   * Pass mouse released event to active drawing tool and
   * repaint drawing
   *
   * @param e the mouse event
   */
  void sendMouseReleasedToActiveTool(MouseEvent e) {
    if (activeDrawingTool != null)
      activeDrawingTool.mouseReleasedInDrawingArea(e);
    repaint();
  }

  /**
   * Pass mouse clicked event to active drawing tool
   *
   * @param e the mouse event
   */
  public void sendMouseClickedToActiveTool(MouseEvent e) {
    if (activeDrawingTool != null)
      activeDrawingTool.mouseClickedInDrawingArea(e);
  }

  /**
   * Send mouse dragged event to active drawing tool and repaint
   *
   * @param e the mouse event
   */
  public void sendMouseDraggedToActiveTool(MouseEvent e) {
    if (activeDrawingTool != null)
      activeDrawingTool.mouseDraggedInDrawingArea(e);
    repaint();
  }

  /**
   * Sets the given tool as the active tool
   *
   * @param aDrawingTool the tool to activate
   */
  public void setActiveDrawingTool(DrawingTool aDrawingTool) {
    if (activeDrawingTool != null)
      activeDrawingTool.deactivate();
    aDrawingTool.activate();
    activeDrawingTool = aDrawingTool;
  }

  /**
   * Gets figure at given point.
   *
   * @param point the point at which figure is to be found
   * @return the figure at the given point or null if no such figure exists
   */
  public Shape getFigureInDrawing(Point point) {
    return currentDrawing.getFigureAtPoint(point);
  }

  /**
   * Get the current drawing
   *
   * @return The active drawing
   */
  public Canvas getCurrentDrawing() {
    return currentDrawing;
  }

  /**
   * Helper to set up menus.
   */
  private void createMenus() {
    JMenuBar menuBar = new JMenuBar();

    JMenu drawingMenu = new JMenu("Drawing");
    JMenuItem drawingItem = new JMenuItem("New");
    drawingItem.addActionListener(new NewDrawingAction());
    drawingMenu.add(drawingItem);
    menuBar.add(drawingMenu);
    this.setJMenuBar(menuBar);
  }

  /**
   * Helper to create tools.
   */
  private void createTools() {
    JPanel toolArea = new JPanel();
    toolArea.setLayout(new GridLayout(0, 1));
    toolArea.setSize(new Dimension(TOOL_WIDTH, HEIGHT));
    add(toolArea, BorderLayout.WEST);

    SelectionTool selecTool = new SelectionTool(this, toolArea);
    drawingTools.add(selecTool);
    selecTool.activate();
    GroupTool groupTool = new GroupTool(this, toolArea);
    drawingTools.add(groupTool);
    UnGroupTool ungroupTool = new UnGroupTool(this, toolArea);
    drawingTools.add(ungroupTool);
    groupTool.activate();
    MoveTool moveTool = new MoveTool(this, toolArea);
    drawingTools.add(moveTool);
    RectangleDrawingTool rectTool = new RectangleDrawingTool(this, toolArea);
    drawingTools.add(rectTool);
    SquareDrawingTool sqrTool = new SquareDrawingTool(this, toolArea);
    drawingTools.add(sqrTool);
    EllipseDrawingTool ellipseTool = new EllipseDrawingTool(this, toolArea);
    drawingTools.add(ellipseTool);
    CircleDrawingTool circTool = new CircleDrawingTool(this, toolArea);
    drawingTools.add(circTool);
  }

  /**
   * Represents a listener that defines the action to be performed
   * when the user wants to create a new drawing.
   */
  private class NewDrawingAction extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
      addDrawing(new Canvas(DrawingPad.this));
    }
  }
}

