package com.conways_game_of_life.app.rest.Controller;

import org.springframework.web.bind.annotation.GetMapping;

public interface GameController {

  String getPage();

  void createGame(int length, int width, String id);

  void toggleCell(int row, int col);

//  boolean[][] getGame();

  boolean[][] getNextGeneration();

}
