package com.conways_game_of_life.app.rest.controller;

import com.conways_game_of_life.app.rest.model.Cell;
import com.conways_game_of_life.app.rest.model.GameBoard;
import com.conways_game_of_life.app.rest.model.GameModel;

/**
 * The GameController interface represents the Controller for Conway's Game of Life
 * It handles the working of the Model {@link GameModel} by parsing the game properties and
 * user actions to the model and conveys action outcomes to the user in some form.
 */
public interface GameController {

  /**
   * The landing page for the backend server with details to public endpoints.
   *
   * @return a string representing API details.
   */
  String getPage();

  /**
   * Changes game board size.
   *
   * @param board a record class {@link GameBoard} representing dimensions of the game board.
   * @throws IllegalArgumentException if null or invalid input provided.
   */
  void editGameBoardSize(GameBoard board) throws IllegalArgumentException;

  /**
   * Creates a randomized initial board for the game.
   */
  void randomBoard();

  /**
   * Toggles a cell in the game board from live to dead and vice-versa.
   *
   * @param cell a record class {@link Cell} representing coordinated of the cell in the game board.
   * @throws IllegalArgumentException if null or invalid input provided.
   */
  void toggleCell(Cell cell) throws IllegalArgumentException;

  /**
   * Gets the current game board.
   *
   * @return the current game board.
   */
  boolean[][] getGameBoard();

  /**
   * Executes the next generation in the game.
   */
  void nextGeneration();

  /**
   * Checks if the game is over(reached extinction) or now.
   *
   * @return true if game reached extinction else false.
   */
  boolean isGameOver();

  /**
   * Resets the game with an empty civilization of the same size as before.
   */
  void resetGame();
}
