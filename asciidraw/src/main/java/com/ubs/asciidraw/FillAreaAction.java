package com.ubs.asciidraw;

import com.ubs.asciidraw.AsciiDrawAction;
import com.ubs.asciidraw.AsciiCanvas;

class FillAreaAction implements AsciiDrawAction {

  private AsciiCanvas canvas = null;

  FillAreaAction(AsciiCanvas canvas) {
    this.canvas = canvas;
  }

  public boolean execute(Command command) {
    if (canvas == null)
      return false;
    FillAreaCommand fillCmd = (FillAreaCommand)command;
    return fillArea(fillCmd.getX(), fillCmd.getY(), fillCmd.getColor());
  }

  public boolean fillArea(int x, int y, char color) {
    char original = canvas.get(y,x);
    fill(x, y, original, color);
    return true;
  }

  private void fill(int x, int y, char original, char color) {

    if (x < 0 || x >= canvas.getColCount())
        return;

    if (y < 0 || y >= canvas.getRowCount())
        return;

    if (canvas.get(y,x) == color)
        return;

    if (original != canvas.get(y,x))
        return;
    
    canvas.set(y,x,color);

    fill(x+1, y, original, color);
    fill(x-1, y, original, color);
    fill(x, y+1, original, color);
    fill(x, y-1, original, color);
  }

}