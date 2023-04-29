package com.conways_game_of_life.app.rest.Model;

public class GameModelImpl implements GameModel {
  private int population;
  private int length;
  private int width;
  private boolean[][] gameBoard;

  public GameModelImpl(int length, int width) {
    this.length = length;
    this.width = width;
    this.gameBoard = new boolean[length][width];
    this.population = 0;
  }

  @Override
  public void toggleCell(int row, int col) {
    this.gameBoard[row][col] = !this.gameBoard[row][col];
    this.population += this.gameBoard[row][col] ? 1 : -1;
  }

  @Override
  public boolean isGameOver() {
    return this.population==0;
  }

  @Override
  public boolean[][] getBoard() {
    return this.gameBoard;
  }

  private int getAliveNeighbours(int l, int m) {
    int aliveNeighbours = 0;
    for (int i = -1; i <= 1; i++)
      for (int j = -1; j <= 1; j++)
        if ((l + i >= 0 && l + i < this.length) && (m + j >= 0 && m + j < this.width))
          aliveNeighbours += this.gameBoard[l + i][m + j] ? 1 : -1;

    // The cell needs to be subtracted from
    // its neighbours as it was counted before
    aliveNeighbours -= this.gameBoard[l][m] ? -1 : 1;
    return aliveNeighbours;
  }

  @Override
  public boolean[][] nextGeneration() {
    boolean[][] next_gen = new boolean[this.length][this.width];

    // Loop through every cell
    for (int l = 0; l < this.length; l++)
    {
      for (int m = 0; m < this.width; m++)
      {
        // finding no Of Neighbours that are alive
        int aliveNeighbours = getAliveNeighbours(l, m);

        // Implementing the Rules of Life
        // Cell is lonely and dies
        if (this.gameBoard[l][m] && (aliveNeighbours < 2)) {
          next_gen[l][m] = false;
          this.population -= 1;
        }

          // Cell dies due to over population
        else if (this.gameBoard[l][m] && (aliveNeighbours > 3)) {
          next_gen[l][m] = false;
          this.population -= 1;
        }

          // A new cell is born
        else if (!this.gameBoard[l][m] && (aliveNeighbours == 3)) {
          next_gen[l][m] = true;
          this.population += 1;
        }

          // Remains the same
        else
          next_gen[l][m] = this.gameBoard[l][m];
      }
    }
    this.gameBoard = next_gen;
    return this.gameBoard;
  }

  @Override
  public void reset() {
    this.gameBoard = new boolean[this.length][this.width];
    this.population = 0;
  }

}
