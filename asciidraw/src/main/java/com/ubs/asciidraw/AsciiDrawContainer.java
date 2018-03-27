package com.ubs.asciidraw;

import java.lang.String;
import java.util.Scanner;
import java.util.List;


import com.ubs.asciidraw.CommandLineParser;
import com.ubs.asciidraw.AsciiCanvas;
import com.ubs.asciidraw.DrawLineAction;
import com.ubs.asciidraw.DrawRectangleAction;
import com.ubs.asciidraw.FillAreaAction;;



public class AsciiDrawContainer {

  private AsciiCanvas canvas = null;
  Scanner inputScanner = null;

  public void run() {

    inputScanner = new Scanner(System.in).useDelimiter("\\n");
    
    while(true) {
      System.out.print("enter command: " );
      String input = inputScanner.next();

      if (!input.isEmpty()) {
        List<String> inputTokens = CommandLineParser.getTokensFromString(input);
        Command theCommand = CommandLineParser.validateTokensAndGetCommand(inputTokens);
        executeCommand(theCommand);
      }
    }
  }


  public void executeCommand(Command command) {

    if (!command.isValid())
      return;

    AsciiDrawAction action = null;
    switch (command.getCommandType()) {

      case CreateCanvasCommand:
        CreateCanvasCommand createCanvasCmd = (CreateCanvasCommand)command;
        createCanvas(createCanvasCmd.getWidth(), createCanvasCmd.getHeight());
        break;

      case DrawLineCommand:
        action = new DrawLineAction(canvas);
        break;

      case DrawRectangleCommand:
        action = new DrawRectangleAction(canvas);
        break;

      case FillAreaCommand:
        action = new FillAreaAction(canvas);
        break;
      
      case QuitCommand:
        if (inputScanner != null)
          inputScanner.close();
        System.exit(0);
        break;

      default:
        break;
    }

    if (action != null) {
      if (action.execute(command))
        canvas.print();
    }

  }

  private void createCanvas(int width, int height) {
    canvas = new AsciiCanvas(height, width-2);
    canvas.print();
  }

}