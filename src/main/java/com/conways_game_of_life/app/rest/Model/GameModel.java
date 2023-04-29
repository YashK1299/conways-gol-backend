package com.conways_game_of_life.app.rest.Model;

public interface GameModel {
  boolean isGameOver();

  boolean[][] getBoard();

  void toggleCell(int row, int col);

  boolean[][] nextGeneration();

  void reset();
}
