package com.ubs.asciidraw;

import java.lang.StringBuilder;
import java.lang.String;
import java.util.Arrays;

class AsciiCanvas {

  private int rows;
  private int cols;
  private char[][] canvas = null;
  private boolean empty = true;

  AsciiCanvas(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
    this.canvas = new char[this.rows][this.cols];
    this.empty = false;
  }

  public boolean isEmpty() {
    return empty;
  }

  public char get(int r, int c) {
    return canvas[r][c];
  }

  public void set(int r, int c, char val) {
    canvas[r][c] = val;
  }

  public int getRowCount() {
    return rows;
  }

  public int getColCount() {
    return cols;
  }

  public char[] getRow(int row) {
    return canvas[row];
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();

    char[] header = new char[cols+2];
    Arrays.fill(header, '-');
    sb.append(header).append('\n');
    for (int r = 0; r < rows; r++) {
      sb.append('|');
      for (int c = 0; c < cols; c++) {
        if (canvas[r][c] > 0)
          sb.append(canvas[r][c]);
        else
          sb.append(' ');
      }
      sb.append('|').append('\n');
    }
    sb.append(header).append('\n');

    return sb.toString();
  }

  public void print() {
    System.out.println(this);
  }

  public boolean withinRowLimit(int r) {
    return r >= 0 && r < rows;
  }

  public boolean withinColLimit(int c) {
    return c >= 0 && c < cols;
  }
}