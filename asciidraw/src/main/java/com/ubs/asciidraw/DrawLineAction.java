package com.ubs.asciidraw;

import com.ubs.asciidraw.Command;
import com.ubs.asciidraw.AsciiDrawAction;
import com.ubs.asciidraw.AsciiCanvas;

class DrawLineAction implements AsciiDrawAction {

  protected AsciiCanvas canvas = null;

  DrawLineAction(AsciiCanvas canvas) {
    this.canvas = canvas;
  }

  public boolean execute(Command command) {
    if (canvas == null)
      return false;
    DrawLineCommand drawLineCmd = (DrawLineCommand)command;
    return drawLine(drawLineCmd.getX1(), drawLineCmd.getY1(), drawLineCmd.getX2(), drawLineCmd.getY2());
  }

  protected boolean drawLine(int x1, int y1, int x2, int y2) {
    if (!canvas.withinColLimit(x1) || !canvas.withinColLimit(x2))
      return false;

    if (!canvas.withinRowLimit(y1) || !canvas.withinRowLimit(y2))
      return false;
    
    if (y1 == y2 && x1 < x2)
      drawHorizontalLine(x1, x2, y1);

    else if (x1 == x2 && y1 < y2)
      drawVerticalLine(x1, y1, y2);

    return true;
  }

  protected void drawHorizontalLine(int x1, int x2, int y) {
    
    for (int i = x1; i <= x2; i++)
      canvas.set(y, i, 'x');
  }

  protected void drawVerticalLine(int x, int y1, int y2) {
    for (int i = y1; i <= y2; i++)
      canvas.set(i, x, 'x');
  }
  
  protected boolean withinBounds(int v1, int v2, int last) {
    if (v1 < 0 || v1 > last || v2 < 0 || v2 > last)
      return false;
    
    return true;
  }
}