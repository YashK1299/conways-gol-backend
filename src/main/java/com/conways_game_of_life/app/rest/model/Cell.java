package com.conways_game_of_life.app.rest.model;

/**
 * A record representing the coordinates of a cell in the
 * game board representing Conway's Game of Life.
 *
 * @param row the row in which the cell is present.
 * @param col the column in which the cell is present.
 */
public record Cell(int row, int col) {
}
