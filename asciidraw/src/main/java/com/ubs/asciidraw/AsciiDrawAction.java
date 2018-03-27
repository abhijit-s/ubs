package com.ubs.asciidraw;

import com.ubs.asciidraw.Command;

interface AsciiDrawAction {
  boolean execute(Command command);
}