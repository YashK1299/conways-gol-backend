package com.conways_game_of_life.app.rest.model;

/**
 * This interface represents a single game of Conway's Game of Life.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life">Conway's Game Of Life</a>
 */
public interface GameModel {

  /**
   * Edits the size of the game board.
   *
   * @param rows the updated number of rows (rows > 0).
   * @param cols the updated number of columns (cols > 0).
   * @throws IllegalArgumentException if invalid values for rows and columns provided.
   */
  void editBoardSize(int rows, int cols) throws IllegalArgumentException;

  /**
   * Get the current game board.
   *
   * @return the current game board matrix.
   */
  boolean[][] getBoard();

  /**
   * Creates a random game board placing live cells randomly throughout the game board.
   */
  void randomBoard();

  /**
   * Toggles a cell from live to dead and vice-versa.
   *
   * @param row the row in which the cell is present.
   * @param col the column in which the cell is present.
   * @throws IllegalArgumentException if invalid values for row and col provided.
   */
  void toggleCell(int row, int col) throws IllegalArgumentException;

  /**
   * Triggers evolution to next generation in the game.
   */
  void nextGeneration();

  /**
   * Check if the game is over (reached extinction) or not.
   *
   * @return true if game reached extinction else false.
   */
  boolean isGameOver();

  /**
   * Resets the game board to an empty board keeping the previous number of rows and cols.
   */
  void resetBoard();
}
