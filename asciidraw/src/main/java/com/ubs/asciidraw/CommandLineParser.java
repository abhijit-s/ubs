package com.ubs.asciidraw;

import java.lang.String;
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.lang.Class;
import java.lang.reflect.Constructor;

import com.ubs.asciidraw.Command;

class CommandLineParser {
  
  private static Map<String, Class> validCommands;

  static {
    validCommands = new HashMap<>();
    validCommands.put("C", CreateCanvasCommand.class);
    validCommands.put("L", DrawLineCommand.class);
    validCommands.put("R", DrawRectangleCommand.class);
    validCommands.put("B", FillAreaCommand.class);
    validCommands.put("Q", QuitCommand.class);
  }

  public static List<String> getTokensFromString(String input)
  {
    String[] tokens = input.split("[\\s]");
    return Arrays.asList(tokens);
  }

  public static Command validateTokensAndGetCommand(List<String> tokens) {
    
    Class<?> clazz = validCommands.get(tokens.get(0));
    
    try {
      if (clazz != null) {
          Constructor<?> cmdCtor = clazz.getConstructor(List.class);
          Command command = (Command)cmdCtor.newInstance(tokens.subList(1, tokens.size()));
          if (!command.validate())
            return new InvalidCommand();
          else 
            return command;
      	}
    }
    catch (Exception e) {
        e.printStackTrace();
    }
    return new InvalidCommand();
    
  }
}
