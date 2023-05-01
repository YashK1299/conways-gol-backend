package com.conways_game_of_life.app.rest;

import com.conways_game_of_life.app.rest.model.GameModel;
import com.conways_game_of_life.app.rest.model.GameModelImpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class RestApiApplication {
	static final int DEFAULT_GAME_BOARD_ROWS = 20;
	static final int DEFAULT_GAME_BOARD_COLUMNS = 20;
	static final Random DEFAULT_RANDOM_GENERATOR = new Random();

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}

	@Bean
	public GameModel createGameModel() {
		return new GameModelImpl(DEFAULT_GAME_BOARD_ROWS,
					DEFAULT_GAME_BOARD_COLUMNS,
					DEFAULT_RANDOM_GENERATOR);}

}
