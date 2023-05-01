# [Conway's Game Of Life](http://en.wikipedia.org/wiki/Conway%27s_Game_of_Life)

This is the implementation of Conway's Game Of Life, and uses Springboot, Maven and Java to expose
all the required features for the game through following REST Api endpoints:

1. PUT /gameoflife/editGameBoardSize
2. PUT /gameoflife/randomGame
3. PUT /gameoflife/toggleCell
4. GET /gameoflife/gameBoard
5. PUT /gameoflife/nextGen
6. GET /gameoflife/isGameOver
7. PUT /gameoflife/resetGame

These endpoints can be used by the front end server to communicate with the backend and access all
the in game functionality.

## Problem Statement:
* Implement Conway's Game of Life (http://en.wikipedia.org/wiki/Conway%27s_Game_of_Life).
* Implementation should utilize Java and Maven(used Maven) or Gradle for the back end server, which
  should communicate with the front end via REST.
* Include unit tests

## Contents of the [repository](https://github.com/YashK1299/conways-gol-backend):
* Source Code in ```src/```
* Unit Tests in ```test/```
* Unit Tests Coverage Report in ```out/```
* Postman Collection: To help test api endpoints in ```out/```
* Generated Javadoc in ```out/```
* Find example Frontend code [here](https://github.com/YashK1299/conways-gol-ui)

## How to run:
* Clone and unzip the repository in your workspace
* Navigate to the repository
* Install jdk-17 using
  this [link](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).
* Install Maven 3.5+ using this [link](https://maven.apache.org/download.cgi)
* Import the code straight into one of the following IDEs:
    * [IntelliJ IDEA](https://spring.io/guides/gs/intellij-idea/)
    * [VSCode](https://spring.io/guides/gs/guides-with-vscode/)
* Run the following command from the root of your directory to start the server on your localhost:

```shell
mvn spring-boot:run
```