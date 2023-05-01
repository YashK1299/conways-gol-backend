package com.conways_game_of_life.app.rest.controller;

import com.conways_game_of_life.app.rest.model.Cell;
import com.conways_game_of_life.app.rest.model.GameBoard;
import com.conways_game_of_life.app.rest.model.GameModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Represents the Rest Controller for Conway's Game of Life
 * It exposes user actions through public GET and PUT API endpoints.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/gameoflife")
public class GameControllerImpl implements GameController {
  private final GameModel game;

  /**
   * This constructs an instance of the Rest Controller for Conway's Game Of Life
   * and sets up a default game for user to play.
   *
   * @param gameModel represents the model of conways game of life.
   * @throws IllegalArgumentException if null model provided.
   */
  @Autowired
  public GameControllerImpl(GameModel gameModel) {
    if (gameModel == null)
      throw new IllegalArgumentException("Null model provided for game");
    this.game = gameModel;
  }

  @GetMapping(value = "/")
  public String getPage() {
    return """
            Welcome to Conway's Game of Life API.
             Endpoints available:
            \t PUT /gameoflife/editGameBoardSize,
            \t PUT /gameoflife/randomGame,
            \t PUT /gameoflife/toggleCell,
            \t GET /gameoflife/gameBoard,
            \t PUT /gameoflife/nextGen,
            \t GET /gameoflife/isGameOver,
            \t PUT /gameoflife/resetGame,""";
  }

  @Override
  @PutMapping(value = "/editGameBoardSize")
  public void editGameBoardSize(@RequestBody GameBoard board) throws IllegalArgumentException {
    if (board == null || board.rows() <= 0 || board.cols() <= 0) {
      throw new IllegalArgumentException("Invalid input: " + board);
    }
    this.game.editBoardSize(board.rows(), board.cols());
  }

  @Override
  @PutMapping(value = "/randomGame")
  public void randomBoard() {
    this.game.randomBoard();
  }

  @Override
  @PutMapping(value = "/toggleCell")
  public void toggleCell(@RequestBody Cell cell) throws IllegalArgumentException {
    if (cell == null || cell.row() < 0 || cell.col() < 0) {
      throw new IllegalArgumentException("Invalid input: " + cell);
    }
    this.game.toggleCell(cell.row(), cell.col());
  }

  @Override
  @GetMapping(value = "/gameBoard")
  public boolean[][] getGameBoard() {
    return this.game.getBoard();
  }

  @Override
  @PutMapping(value = "/nextGen")
  public void nextGeneration() {
    this.game.nextGeneration();
  }

  @Override
  @GetMapping(value = "/isGameOver")
  public boolean isGameOver() {
    return this.game.isGameOver();
  }

  @Override
  @PutMapping("/resetGame")
  public void resetGame() {
    this.game.resetBoard();
  }

  @Override
  public String toString() {
    return "Game Controller with: \n" + this.game;
  }
}
