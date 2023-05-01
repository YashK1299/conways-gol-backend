package com.conways_game_of_life.app.rest.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomRandom extends Random {
  private boolean nextBoolean = true;

  @Override
  public boolean nextBoolean() {
    this.nextBoolean = !this.nextBoolean;
    return nextBoolean;
  }
}

class GameModelImplTest {
  Random randomMock;
  private GameModel emptyGame;
  private GameModel populatedGame;

  @BeforeEach
  void setUp() {
    this.randomMock = new CustomRandom();
    this.emptyGame = new GameModelImpl(5, 6, randomMock);
    this.populatedGame = new GameModelImpl(3, 3, randomMock);
    this.populatedGame.randomBoard();
  }

  @Test
  void testConstructorWithInvalidValues() {
    Random random = new CustomRandom();
    IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> new GameModelImpl(0, 5, random)
    );
    assertEquals(thrown.getMessage(), "Invalid value for rows or columns: 0,5");
    thrown = assertThrows(
            IllegalArgumentException.class,
            () -> new GameModelImpl(5, 0, random)
    );
    assertEquals(thrown.getMessage(), "Invalid value for rows or columns: 5,0");
    thrown = assertThrows(
            IllegalArgumentException.class,
            () -> new GameModelImpl(-5, 2, random)
    );
    assertEquals(thrown.getMessage(), "Invalid value for rows or columns: -5,2");
    thrown = assertThrows(
            IllegalArgumentException.class,
            () -> new GameModelImpl(2, -5, random)
    );
    assertEquals(thrown.getMessage(), "Invalid value for rows or columns: 2,-5");
    thrown = assertThrows(
            IllegalArgumentException.class,
            () -> new GameModelImpl(-5, -2, random)
    );
    assertEquals(thrown.getMessage(), "Invalid value for rows or columns: -5,-2");
    thrown = assertThrows(
            IllegalArgumentException.class,
            () -> new GameModelImpl(0, 0, random)
    );
    assertEquals(thrown.getMessage(), "Invalid value for rows or columns: 0,0");
    thrown = assertThrows(
            IllegalArgumentException.class,
            () -> new GameModelImpl(1, 1, null)
    );
    assertEquals(thrown.getMessage(), "Null value provided for random generator");
  }

  @Test
  void testConstructor() {
    GameModel game = new GameModelImpl(5, 6, this.randomMock);
    assertEquals(
            """
                    Game Model:
                     -  -  -  -  -  -\s
                     -  -  -  -  -  -\s
                     -  -  -  -  -  -\s
                     -  -  -  -  -  -\s
                     -  -  -  -  -  -\s
                    """, game.toString(), "Testing constructor with valid values");
  }

  @Test
  void testEditBoardSizeWithInvalidValues() {
    IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.emptyGame.editBoardSize(0, 5)
    );
    assertEquals(thrown.getMessage(), "Invalid input: 0,5");
    thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.emptyGame.editBoardSize(5, 0)
    );
    assertEquals(thrown.getMessage(), "Invalid input: 5,0");
    thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.emptyGame.editBoardSize(-5, 2)
    );
    assertEquals(thrown.getMessage(), "Invalid input: -5,2");
    thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.emptyGame.editBoardSize(2, -5)
    );
    assertEquals(thrown.getMessage(), "Invalid input: 2,-5");
    thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.emptyGame.editBoardSize(-5, -2)
    );
    assertEquals(thrown.getMessage(), "Invalid input: -5,-2");
    thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.emptyGame.editBoardSize(0, 0)
    );
    assertEquals(thrown.getMessage(), "Invalid input: 0,0");
  }

  @Test
  void testEditBoardSize() {
    assertEquals("""
                    Game Model:
                     -  -  -  -  -  -\s
                     -  -  -  -  -  -\s
                     -  -  -  -  -  -\s
                     -  -  -  -  -  -\s
                     -  -  -  -  -  -\s
                    """, this.emptyGame.toString(),
            "Before: Testing an Empty Board editBoardSize with valid values");
    this.emptyGame.editBoardSize(3, 3);
    assertEquals("""
                    Game Model:
                     -  -  -\s
                     -  -  -\s
                     -  -  -\s
                    """, this.emptyGame.toString(),
            "After: Testing an Empty Board editBoardSize with valid values");
    assertEquals("""
                    Game Model:
                     -  *  -\s
                     *  -  *\s
                     -  *  -\s
                    """, this.populatedGame.toString(),
            "Before: Testing a Populated Board editBoardSize with valid values");
    this.populatedGame.editBoardSize(4, 2);
    assertEquals(
            """
                    Game Model:
                     -  -\s
                     -  -\s
                     -  -\s
                     -  -\s
                    """, this.populatedGame.toString(),
            "After: Testing a Populated Board editBoardSize with valid values");
  }

  @Test
  void testGetBoardForEmptyGame() {
    boolean[][] expectedGameBoard = new boolean[5][6];
    boolean[][] actualGameBoard = this.emptyGame.getBoard();
    assertArrayEquals(expectedGameBoard, actualGameBoard);
  }

  @Test
  void testGetBoardForPopulatedGame() {
    boolean[][] expectedGameBoard = new boolean[][]{
            {false, true, false},
            {true, false, true},
            {false, true, false}
    };
    boolean[][] actualGameBoard = this.populatedGame.getBoard();
    assertArrayEquals(expectedGameBoard, actualGameBoard);
  }

  @Test
  void testRandomBoard() {
    assertEquals("""
                    Game Model:
                     -  -  -  -  -  -\s
                     -  -  -  -  -  -\s
                     -  -  -  -  -  -\s
                     -  -  -  -  -  -\s
                     -  -  -  -  -  -\s
                    """, this.emptyGame.toString(),
            "Before: Testing an Empty Board of size 5, 5 randomBoard with valid values");
    this.emptyGame.randomBoard();
    assertEquals("""
                    Game Model:
                     *  -  *  -  *  -\s
                     *  -  *  -  *  -\s
                     *  -  *  -  *  -\s
                     *  -  *  -  *  -\s
                     *  -  *  -  *  -\s
                    """, this.emptyGame.toString(),
            "After: Testing an Empty Board of size 5, 5 randomBoard with valid values");
    this.emptyGame.editBoardSize(4, 2);
    assertEquals("""
                    Game Model:
                     -  -\s
                     -  -\s
                     -  -\s
                     -  -\s
                    """, this.emptyGame.toString(),
            "Before: Testing an Empty Board of size 4, 2 randomBoard with valid values");
    this.emptyGame.randomBoard();
    assertEquals("""
                    Game Model:
                     *  -\s
                     *  -\s
                     *  -\s
                     *  -\s
                    """, this.emptyGame.toString(),
            "After: Testing an Empty Board of size 4, 2 randomBoard with valid values");
  }

  @Test
  void testToggleCellWithInvalidInputs() {
    IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.emptyGame.toggleCell(-5, 2)
    );
    assertEquals(thrown.getMessage(), "Invalid input: -5,2");
    thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.emptyGame.toggleCell(2, -5)
    );
    assertEquals(thrown.getMessage(), "Invalid input: 2,-5");
    thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.emptyGame.toggleCell(-5, -2)
    );
    assertEquals(thrown.getMessage(), "Invalid input: -5,-2");
    thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.emptyGame.toggleCell(5, 0)
    );
    assertEquals(thrown.getMessage(), "Invalid input: 5,0");
    thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.emptyGame.toggleCell(2, 6)
    );
    assertEquals(thrown.getMessage(), "Invalid input: 2,6");
    thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.emptyGame.toggleCell(5, 6)
    );
    assertEquals(thrown.getMessage(), "Invalid input: 5,6");
    thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.populatedGame.toggleCell(-5, -2)
    );
    assertEquals(thrown.getMessage(), "Invalid input: -5,-2");
    thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.populatedGame.toggleCell(4, 4)
    );
    assertEquals(thrown.getMessage(), "Invalid input: 4,4");
  }

  @Test
  void testToggleCell() {
    assertFalse(this.emptyGame.getBoard()[1][1]);
    this.emptyGame.toggleCell(1, 1);
    assertTrue(this.emptyGame.getBoard()[1][1]);
    assertTrue(this.emptyGame.getBoard()[1][1]);
    this.emptyGame.toggleCell(1, 1);
    assertFalse(this.emptyGame.getBoard()[1][1]);
    assertTrue(this.populatedGame.getBoard()[0][1]);
    this.populatedGame.toggleCell(0, 1);
    assertFalse(this.populatedGame.getBoard()[0][1]);
  }

  @Test
  void testNextGeneration() {
    boolean[][] beforeGameBoard = new boolean[][]{
            {false, false, false},
            {false, false, false},
            {false, false, false}
    };
    boolean[][] afterGameBoard = new boolean[][]{
            {false, false, false},
            {false, false, false},
            {false, false, false}
    };
    this.emptyGame.editBoardSize(3, 3);
    assertArrayEquals(beforeGameBoard, this.emptyGame.getBoard(),
            "Before: Testing for dead civilization");
    this.emptyGame.nextGeneration();
    assertArrayEquals(afterGameBoard, this.emptyGame.getBoard(),
            "After: Testing for dead civilization");
    this.emptyGame.resetBoard();
    beforeGameBoard = new boolean[][]{
            {false, false, false},
            {false, true, false},
            {false, false, false}
    };
    afterGameBoard = new boolean[][]{
            {false, false, false},
            {false, false, false},
            {false, false, false}
    };
    this.emptyGame.toggleCell(1, 1);
    assertArrayEquals(beforeGameBoard, this.emptyGame.getBoard(),
            "Before: Testing for cell with no neighbours");
    this.emptyGame.nextGeneration();
    assertArrayEquals(afterGameBoard, this.emptyGame.getBoard(),
            "After: Testing for cell with no neighbours");
    this.emptyGame.resetBoard();
    beforeGameBoard = new boolean[][]{
            {false, false, false},
            {false, true, false},
            {false, true, false}
    };
    afterGameBoard = new boolean[][]{
            {false, false, false},
            {false, false, false},
            {false, false, false}
    };
    this.emptyGame.toggleCell(1, 1);
    this.emptyGame.toggleCell(2, 1);
    assertArrayEquals(beforeGameBoard, this.emptyGame.getBoard(),
            "Before: Testing for cell with 1 neighbours");
    this.emptyGame.nextGeneration();
    assertArrayEquals(afterGameBoard, this.emptyGame.getBoard(),
            "After: Testing for cell with 1 neighbours");
    this.emptyGame.resetBoard();
    beforeGameBoard = new boolean[][]{
            {false, true, false},
            {false, true, false},
            {false, true, false}
    };
    afterGameBoard = new boolean[][]{
            {false, false, false},
            {true, true, true},
            {false, false, false}
    };
    this.emptyGame.toggleCell(0, 1);
    this.emptyGame.toggleCell(1, 1);
    this.emptyGame.toggleCell(2, 1);
    assertArrayEquals(beforeGameBoard, this.emptyGame.getBoard(),
            "Before: Testing for cell with 2 neighbours");
    this.emptyGame.nextGeneration();
    assertArrayEquals(afterGameBoard, this.emptyGame.getBoard(),
            "After: Testing for cell with 2 neighbours");
    this.emptyGame.resetBoard();
    beforeGameBoard = new boolean[][]{
            {false, true, false},
            {false, true, true},
            {false, true, false}
    };
    afterGameBoard = new boolean[][]{
            {false, true, true},
            {true, true, true},
            {false, true, true}
    };
    this.emptyGame.toggleCell(0, 1);
    this.emptyGame.toggleCell(1, 1);
    this.emptyGame.toggleCell(1, 2);
    this.emptyGame.toggleCell(2, 1);
    assertArrayEquals(beforeGameBoard, this.emptyGame.getBoard(),
            "Before: Testing for cell with 3 neighbours");
    this.emptyGame.nextGeneration();
    assertArrayEquals(afterGameBoard, this.emptyGame.getBoard(),
            "After: Testing for cell with 3 neighbours");
    this.emptyGame.resetBoard();
    beforeGameBoard = new boolean[][]{
            {false, true, false},
            {true, true, true},
            {false, true, false}
    };
    afterGameBoard = new boolean[][]{
            {true, true, true},
            {true, false, true},
            {true, true, true}
    };
    this.emptyGame.toggleCell(0, 1);
    this.emptyGame.toggleCell(1, 0);
    this.emptyGame.toggleCell(1, 1);
    this.emptyGame.toggleCell(1, 2);
    this.emptyGame.toggleCell(2, 1);
    assertArrayEquals(beforeGameBoard, this.emptyGame.getBoard(),
            "Before: Testing for cell with 4 neighbours");
    this.emptyGame.nextGeneration();
    assertArrayEquals(afterGameBoard, this.emptyGame.getBoard(),
            "After: Testing for cell with 4 neighbours");
    this.emptyGame.resetBoard();
    beforeGameBoard = new boolean[][]{
            {false, false, false},
            {true, false, true},
            {false, false, false}
    };
    afterGameBoard = new boolean[][]{
            {false, false, false},
            {false, false, false},
            {false, false, false}
    };
    this.emptyGame.toggleCell(1, 0);
    this.emptyGame.toggleCell(1, 2);
    assertArrayEquals(beforeGameBoard, this.emptyGame.getBoard(),
            "Before: Testing for dead cell with 2 neighbours");
    this.emptyGame.nextGeneration();
    assertArrayEquals(afterGameBoard, this.emptyGame.getBoard(),
            "After: Testing for dead cell with 2 neighbours");
    this.emptyGame.resetBoard();
    beforeGameBoard = new boolean[][]{
            {false, true, false},
            {true, false, true},
            {false, false, false}
    };
    afterGameBoard = new boolean[][]{
            {false, true, false},
            {false, true, false},
            {false, false, false}
    };
    this.emptyGame.toggleCell(0, 1);
    this.emptyGame.toggleCell(1, 0);
    this.emptyGame.toggleCell(1, 2);
    assertArrayEquals(beforeGameBoard, this.emptyGame.getBoard(),
            "Before: Testing for dead cell with 3 neighbours");
    this.emptyGame.nextGeneration();
    assertArrayEquals(afterGameBoard, this.emptyGame.getBoard(),
            "After: Testing for dead cell with 3 neighbours");
    this.emptyGame.resetBoard();
  }

  @Test
  void isGameOver() {
    assertTrue(this.emptyGame.isGameOver());
    this.emptyGame.toggleCell(0, 0);
    assertFalse(this.emptyGame.isGameOver());
    this.emptyGame.toggleCell(1, 1);
    this.emptyGame.toggleCell(2, 2);
    this.emptyGame.nextGeneration();
    assertFalse(this.emptyGame.isGameOver());
    this.emptyGame.nextGeneration();
    assertTrue(this.emptyGame.isGameOver());
    this.emptyGame.toggleCell(0, 0);
    assertFalse(this.emptyGame.isGameOver());
    this.emptyGame.resetBoard();
    assertTrue(this.emptyGame.isGameOver());
    this.emptyGame.toggleCell(0, 0);
    assertFalse(this.emptyGame.isGameOver());
    this.emptyGame.editBoardSize(2, 2);
    assertTrue(this.emptyGame.isGameOver());
    this.emptyGame.randomBoard();
    assertFalse(this.emptyGame.isGameOver());
  }

  @Test
  void resetBoard() {
    boolean[][] beforeGameBoard = new boolean[][]{
            {false, true, false},
            {true, false, true},
            {false, true, false}
    };
    boolean[][] afterGameBoard = new boolean[][]{
            {false, false, false},
            {false, false, false},
            {false, false, false}
    };
    assertArrayEquals(beforeGameBoard, this.populatedGame.getBoard());
    this.populatedGame.resetBoard();
    assertArrayEquals(afterGameBoard, this.populatedGame.getBoard());
    beforeGameBoard = new boolean[][]{
            {false, false, false, false, false, false},
            {false, false, false, false, false, false},
            {false, false, false, false, false, false},
            {false, false, false, false, false, false},
            {false, false, false, false, false, false}
    };
    afterGameBoard = new boolean[][]{
            {false, false, false, false, false, false},
            {false, false, false, false, false, false},
            {false, false, false, false, false, false},
            {false, false, false, false, false, false},
            {false, false, false, false, false, false}
    };
    assertArrayEquals(beforeGameBoard, this.emptyGame.getBoard());
    this.populatedGame.resetBoard();
    assertArrayEquals(afterGameBoard, this.emptyGame.getBoard());
  }
}