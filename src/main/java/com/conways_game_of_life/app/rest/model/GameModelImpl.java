package com.conways_game_of_life.app.rest.model;

import java.util.Random;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * The GameModelImpl class implements the GameModel interface and
 * represents the implementation of a single game of Conway's Game of Life.
 * It is represented by the civilization in the form of a boolean matrix game board
 * and the current integer population of the civilization.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life">Conway's Game Of Life</a>
 */
public class GameModelImpl implements GameModel {
  private final Random random;
  private boolean[][] gameBoard;
  private int population;

  /**
   * This constructs an instance of the GameModelImpl.
   *
   * @param rows   the number of rows in the game board (rows > 0).
   * @param cols   the number of columns in the game board (cols > 0).
   * @param random the random value generator used to randomize board at any moment.
   * @throws IllegalArgumentException if invalid input provided.
   */
  public GameModelImpl(int rows, int cols, Random random) throws IllegalArgumentException {
    notNull(random, "Null value provided for random generator");
    if (rows <= 0 || cols <= 0) {
      throw new IllegalArgumentException("Invalid value for rows or columns: " + rows + "," + cols);
    }
    this.gameBoard = new boolean[rows][cols];
    this.population = 0;
    this.random = random;
  }

  @Override
  public void editBoardSize(int rows, int cols) throws IllegalArgumentException {
    if (rows <= 0 || cols <= 0) {
      throw new IllegalArgumentException("Invalid input: " + rows + "," + cols);
    }
    this.gameBoard = new boolean[rows][cols];
    this.population = 0;
  }

  @Override
  public boolean[][] getBoard() {
    return this.gameBoard;
  }

  @Override
  public void randomBoard() {
    for (int i = 0; i < this.gameBoard.length; i++)
      for (int j = 0; j < this.gameBoard[0].length; j++) {
        this.gameBoard[i][j] = this.random.nextBoolean();
        this.population += this.gameBoard[i][j] ? 1 : 0;
      }
  }

  @Override
  public void toggleCell(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row >= this.gameBoard.length || col < 0 || col >= this.gameBoard[0].length) {
      throw new IllegalArgumentException("Invalid input: " + row + "," + col);
    }
    this.gameBoard[row][col] = !this.gameBoard[row][col];
    this.population += this.gameBoard[row][col] ? 1 : -1;
  }

  private int getAliveNeighbours(int row, int col) {
    int aliveNeighbours = 0;
    for (int i = -1; i <= 1; i++)
      for (int j = -1; j <= 1; j++)
        if ((row + i >= 0 && row + i < this.gameBoard.length)
            && (col + j >= 0 && col + j < this.gameBoard[0].length))
          aliveNeighbours += this.gameBoard[row + i][col + j] ? 1 : 0;
    aliveNeighbours -= this.gameBoard[row][col] ? 1 : 0; // Accounting for itself being counted as aliveNeighbours
    return aliveNeighbours;
  }

  @Override
  public void nextGeneration() {
    int rows = this.gameBoard.length;
    int cols = this.gameBoard[0].length;
    boolean[][] next_gen = new boolean[rows][cols];
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        int aliveNeighbours = getAliveNeighbours(row, col);
        // Implementing Rules of Life:
        if (this.gameBoard[row][col] && (aliveNeighbours < 2)) {// Cell dies due to under-population
          next_gen[row][col] = false;
          this.population -= 1;
        } else if (this.gameBoard[row][col] && (aliveNeighbours > 3)) { // Cell dies due to over-population
          next_gen[row][col] = false;
          this.population -= 1;
        } else if (!this.gameBoard[row][col] && (aliveNeighbours == 3)) { // New cell is born
          next_gen[row][col] = true;
          this.population += 1;
        } else // Remains the same
          next_gen[row][col] = this.gameBoard[row][col];
      }
    }
    this.gameBoard = next_gen;
  }

  @Override
  public boolean isGameOver() {
    return this.population == 0;
  }

  @Override
  public void resetBoard() {
    this.gameBoard = new boolean[this.gameBoard.length][this.gameBoard[0].length];
    this.population = 0;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Game Model:\n");
    for (boolean[] rows : this.gameBoard) {
      for (boolean cell : rows) {
        if (cell)
          sb.append(" * ");
        else
          sb.append(" - ");
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
