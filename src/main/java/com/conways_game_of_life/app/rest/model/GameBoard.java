package com.conways_game_of_life.app.rest.model;

/**
 * A record representing the dimensions of the game board for Conway's Game of Life.
 *
 * @param rows the number of rows in the board
 * @param cols the number of columns in the board
 */
public record GameBoard(int rows, int cols) {
}
