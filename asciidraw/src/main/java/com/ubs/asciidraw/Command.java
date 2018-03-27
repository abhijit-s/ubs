package com.ubs.asciidraw;

import java.lang.String;
import java.util.List;
import java.util.ArrayList;

enum CommandType {
  InvalidCommand,
  CreateCanvasCommand,
  DrawLineCommand,
  DrawRectangleCommand,
  FillAreaCommand,
  QuitCommand
}

abstract class Command {
  
  private boolean valid = false;
  protected List<String> argsList = null;
  protected CommandType commandType;

  public Command(List<String> args) {
    if (args != null)
      this.argsList = args;    
    this.commandType = CommandType.InvalidCommand;
  }

  public abstract boolean validate();

  public boolean isValid() {
    return valid;
  }

  public void setValid() {
    this.valid = true;
  }
    
  public CommandType getCommandType() {
    return commandType;
  }

}

class InvalidCommand extends Command {

  InvalidCommand() {
    super(null);
    this.commandType = CommandType.InvalidCommand;
  }

  public boolean validate() {
    return false;
  }
}

class CreateCanvasCommand extends Command {
  private int width;
  private int height;

  public CreateCanvasCommand(List<String> args) {
    super(args);
    this.commandType = CommandType.CreateCanvasCommand;
  }

  public int getWidth() {
    return width;
  }
  
  public int getHeight() {
    return height;
  }

  public boolean validate() {
    int w, h;
    if (argsList.size() < 2)
      return false;

    try {
      w = Integer.parseInt(argsList.get(0));
      h = Integer.parseInt(argsList.get(1));
    }
    catch (NumberFormatException nf) {
      return false;
    }

    if (w <= 0 || h <= 0)
      return false;
  
    this.width = w;
    this.height = h;

    this.setValid();
    return true;
  }
}

class DrawLineCommand extends Command {
  private int x1, y1, x2, y2;

  public DrawLineCommand(List<String> args) {
    super(args);
    this.commandType = CommandType.DrawLineCommand;
  }

  public int getX1() {
    return x1;
  }

  public int getY1() {
    return y1;
  }

  public int getX2() {
    return x2;
  }

  public int getY2() {
    return y2;
  }

  protected boolean validateValues() {

    if (argsList.size() < 4)
      return false;

    int x1, y1, x2, y2;
    try {
      x1 = Integer.parseInt(argsList.get(0));
      y1 = Integer.parseInt(argsList.get(1));
      x2 = Integer.parseInt(argsList.get(2));
      y2 = Integer.parseInt(argsList.get(3));
    }
    catch (NumberFormatException nf) {
      return false;
    }

    if (x1 <= 0 || y1 <= 0 || x2 <= 0 || y2 <= 0)
      return false;
  
    this.x1 = x1 - 1;
    this.y1 = y1 - 1;
    this.x2 = x2 - 1 ;
    this.y2 = y2 - 1;

    this.setValid();

    return true;

  }

  private boolean validateStraightLines() {
    if ((y1 == y2 && x1 != x2)  || (x1 == x2 && y1 != y2))
      return true;

    return false;
  }

  public boolean validate() {
    boolean result = validateValues();
    if (result)
      result = validateStraightLines();

    return result;
  }

}


class DrawRectangleCommand extends DrawLineCommand {

  public DrawRectangleCommand(List<String> args) {
    super(args);
    super.commandType = CommandType.DrawRectangleCommand;
  }

  public boolean validate() {
    return validateValues();
  }

}

class FillAreaCommand extends Command {
  private int x, y;
  private char color;

  public FillAreaCommand(List<String> args) {
    super(args);
    this.commandType = CommandType.FillAreaCommand;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public char getColor() {
    return color;
  }

  public boolean validate() {

    if (argsList.size() < 2)
      return false;

    int x, y;
    try {
      x = Integer.parseInt(argsList.get(0));
      y = Integer.parseInt(argsList.get(1));
    }
    catch (NumberFormatException nf) {
      return false;
    }
  
    if (x <= 0 || y <= 0)
      return false;

    char color = argsList.get(2).charAt(0);
  
    this.x = x - 1;
    this.y = y - 1;
    this.color = color;

    this.setValid();

    return true;

  }

}

class QuitCommand extends Command {

  public QuitCommand(List<String> args) {
    super(null);
    this.commandType = CommandType.QuitCommand;
  }

  public boolean validate() {
    this.setValid();
    return true;
  }
}