package com.ubs.asciidraw;

import com.ubs.asciidraw.AsciiDrawAction;
import com.ubs.asciidraw.DrawLineAction;

class DrawRectangleAction extends DrawLineAction implements AsciiDrawAction {

    DrawRectangleAction(AsciiCanvas canvas) {
      super(canvas);
    }

    public boolean execute(Command command) {
      if (canvas == null)
        return false;
      DrawRectangleCommand drawRectCmd = (DrawRectangleCommand)command;
      return drawRectangle(drawRectCmd.getX1(), drawRectCmd.getY1(), drawRectCmd.getX2(), drawRectCmd.getY2());
    }

    
    private boolean drawRectangle(int x1, int y1, int x2, int y2) {
      if (!canvas.withinColLimit(x1) || !canvas.withinColLimit(x2))
        return false;

      if (!canvas.withinRowLimit(y1) || !canvas.withinRowLimit(y2))
        return false;
    
      this.drawHorizontalLine(x1, x2, y1);
      this.drawVerticalLine(x1, y1, y2);
      this.drawHorizontalLine(x1, x2, y2);
      this.drawVerticalLine(x2, y1, y2);

      return true;
    }
}