package com.conways_game_of_life.app.rest.controller;

import com.conways_game_of_life.app.rest.model.Cell;
import com.conways_game_of_life.app.rest.model.GameBoard;
import com.conways_game_of_life.app.rest.model.GameModel;
import com.conways_game_of_life.app.rest.model.GameModelImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GameControllerImplTest {
  GameController gameController;
  @Mock
  GameModel gameModel;

  @BeforeEach
  void setUp() {
    this.gameModel = mock(GameModelImpl.class);
    this.gameController = new GameControllerImpl(this.gameModel);
  }

  @Test
  void testConstructorWithNullModel() {
    IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> new GameControllerImpl(null)
    );
    assertEquals(thrown.getMessage(), "Null model provided for game");
  }

  @Test
  void testConstructorWithMockedModel() {
    when(this.gameModel.toString()).thenReturn("Mocked Game Model");
    assertEquals("Game Controller with: \n" +
                 "Mocked Game Model", new GameControllerImpl(this.gameModel).toString());
  }

  @Test
  void getPage() {
    assertEquals("""
            Welcome to Conway's Game of Life API.
             Endpoints available:
            \t PUT /gameoflife/editGameBoardSize,
            \t PUT /gameoflife/randomGame,
            \t PUT /gameoflife/toggleCell,
            \t GET /gameoflife/gameBoard,
            \t PUT /gameoflife/nextGen,
            \t GET /gameoflife/isGameOver,
            \t PUT /gameoflife/resetGame,""", this.gameController.getPage());
  }

  @Test
  void testEditGameBoardSizeWithNullOrInvalidValues() {
    IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.gameController.editGameBoardSize(null)
    );
    assertEquals(thrown.getMessage(), "Invalid input: null");
    thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.gameController.editGameBoardSize(new GameBoard(0, 5))
    );
    assertEquals(thrown.getMessage(), "Invalid input: GameBoard[rows=0, cols=5]");
    thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.gameController.editGameBoardSize(new GameBoard(5, 0))
    );
    assertEquals(thrown.getMessage(), "Invalid input: GameBoard[rows=5, cols=0]");
    thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.gameController.editGameBoardSize(new GameBoard(-5, 2))
    );
    assertEquals(thrown.getMessage(), "Invalid input: GameBoard[rows=-5, cols=2]");
    thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.gameController.editGameBoardSize(new GameBoard(2, -5))
    );
    assertEquals(thrown.getMessage(), "Invalid input: GameBoard[rows=2, cols=-5]");
    thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.gameController.editGameBoardSize(new GameBoard(-5, -2))
    );
    assertEquals(thrown.getMessage(), "Invalid input: GameBoard[rows=-5, cols=-2]");
    thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.gameController.editGameBoardSize(new GameBoard(0, 0))
    );
    assertEquals(thrown.getMessage(), "Invalid input: GameBoard[rows=0, cols=0]");
  }

  @Test
  void testEditGameBoardSize() {
    this.gameController.editGameBoardSize(new GameBoard(3, 3));
    verify(this.gameModel, times(1)).editBoardSize(3, 3);
  }

  @Test
  void randomBoard() {
    this.gameController.randomBoard();
    verify(this.gameModel, times(1)).randomBoard();
  }

  @Test
  void testToggleCellWithInvalidValues() {
    IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.gameController.toggleCell(null)
    );
    assertEquals(thrown.getMessage(), "Invalid input: null");
    thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.gameController.toggleCell(new Cell(-5, 2))
    );
    assertEquals(thrown.getMessage(), "Invalid input: Cell[row=-5, col=2]");
    thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.gameController.toggleCell(new Cell(2, -5))
    );
    assertEquals(thrown.getMessage(), "Invalid input: Cell[row=2, col=-5]");
    thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.gameController.toggleCell(new Cell(-5, -2))
    );
    assertEquals(thrown.getMessage(), "Invalid input: Cell[row=-5, col=-2]");
  }

  @Test
  void testToggleCell() {
    this.gameController.toggleCell(new Cell(2, 2));
    verify(this.gameModel, times(1)).toggleCell(2, 2);
  }

  @Test
  void testGetGameBoard() {
    boolean[][] testGameBoard = new boolean[][]{{false}};
    when(this.gameModel.getBoard()).thenReturn(testGameBoard);
    assertArrayEquals(testGameBoard, this.gameController.getGameBoard());
    verify(this.gameModel, times(1)).getBoard();
  }

  @Test
  void testNextGeneration() {
    this.gameController.nextGeneration();
    verify(this.gameModel, times(1)).nextGeneration();
  }

  @Test
  void testIsGameOver() {
    when(this.gameModel.isGameOver()).thenReturn(false);
    assertFalse(this.gameController.isGameOver());
    verify(this.gameModel, times(1)).isGameOver();
    when(this.gameModel.isGameOver()).thenReturn(true);
    assertTrue(this.gameController.isGameOver());
    verify(this.gameModel, times(2)).isGameOver();
  }

  @Test
  void testResetGame() {
    this.gameController.resetGame();
    verify(this.gameModel, times(1)).resetBoard();
  }
}