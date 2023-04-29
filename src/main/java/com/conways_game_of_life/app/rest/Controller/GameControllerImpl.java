package com.conways_game_of_life.app.rest.Controller;

import com.conways_game_of_life.app.rest.Model.GameModel;
import com.conways_game_of_life.app.rest.Model.GameModelImpl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gameoflife")
public class GameControllerImpl implements GameController {
  private String id;
  private GameModel game;

  @GetMapping(value = "/")
  public String getPage() {
    return "Welcome to Conway's Game of Life API. Endpoints available: POST /gameoflife/create/game/{id}, PUT /gameoflife/toggle-cell, GET /gameoflife/next-gen, PUT /gameoflife/reset";
  }

  @Override
  @PostMapping(value = "/create/game/{id}")
  public void createGame(@RequestParam int length, @RequestParam int width, @PathVariable String id) {
    this.id = id;
    this.game = new GameModelImpl(length, width);
  }

  @Override
  @PutMapping(value = "/toggle-cell")
  public void toggleCell(@RequestParam int row, @RequestParam int col) {
    this.game.toggleCell(row, col);
  }

//  @Override
//  @GetMapping(value = "/game")
//  public boolean[][] getGame() {
//    return this.game.getBoard();
//  }

  @Override
  @GetMapping(value = "/next-gen")
  public boolean[][] getNextGeneration() {
    return this.game.nextGeneration();
  }

  @PutMapping("/reset")
  public void reset() {
    this.game.reset();
  }

}
