package com.conways_game_of_life.app.rest;

import com.conways_game_of_life.app.rest.controller.GameController;
import com.conways_game_of_life.app.rest.model.GameModel;
import com.conways_game_of_life.app.rest.model.GameModelImpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * The RestApiApplication class is the main entry point for the Conway's Game of Life REST API application.
 * It uses the Spring Boot framework to configure and start the REST API server.
 * It also supplies the {@link GameModel} to the {@link GameController}
 */
@SpringBootApplication
public class RestApiApplication {
  static final int DEFAULT_GAME_BOARD_ROWS = 20;
  static final int DEFAULT_GAME_BOARD_COLUMNS = 20;

  /**
   * The main method starts the Spring Boot application and runs the REST API server.
   *
   * @param args command line arguments passed to the application.
   */
  public static void main(String[] args) {
    SpringApplication.run(RestApiApplication.class, args);
  }

  /**
   * This method creates a {@link GameModel} instance with default values for the game
   * board rows, columns and random generator.
   *
   * @param randomGenerator random generator for model.
   * @return The {@link GameModel} instance created with default values.
   */
  @Bean
  public GameModel createGameModel(Random randomGenerator) {
    notNull(randomGenerator, "Null model provided for game");
    return new GameModelImpl(DEFAULT_GAME_BOARD_ROWS,
            DEFAULT_GAME_BOARD_COLUMNS,
            randomGenerator);
  }

  /**
   * This method creates a {@link Random} instance as a random value generator for the Game Model.
   * This can be extended tin the future to use different random value generators.
   * @return A random value generator to be used by the Game Model.
   */
  @Bean
  public Random createRandomGenerator() {
    return new Random();
  }

}
