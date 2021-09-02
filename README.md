# NightShade - 'The Dark'

## Description
'The Dark' is a networked 2D platformer game where players race to reach the finish line first. 
Players run along static and moving platforms, collecting power ups to gain advantages such as higher jumps or immunity. 
Players face a variety of obstacles throughout the game, including enemies and pools of lava, which kill the player if touched.
An ominous black cloud chases the players across each level, taking out any player who gets caught in it. 

The game has two modes; a single player mode and a multiplayer mode.
In single player mode, the player competes against AI players of variable difficulty levels, and the player is able to choose the number of AI opponents and the difficulty level of each.
In multiplayer mode, up to 4 players compete against each other across a local network, each connecting to the server using the IP address and port number.

The game is played using the arrow keys to move the player left, right, or to jump. The initial menu screen provides a set of instructions for new players on how to play the game.

## Technologies used
The game is mainly programmed in Java and JavaFX was used for animation. 
The GUI screens (e.g. the menu screen, lobby screen etc.) use FXML with a model view controller (MVC) design pattern.
The networking was implemented as a client-server model using TCP, where the central server manages the game.
Javadoc comments were added consistently throughout the code for greater readability, and JUnit tests were used to test functionality within all testable packages.

## Running the game
- Run the Server  
- Run the game using `mvn clean javafx:run`  
(Run the game multiple times to connect multiple clients)  

## Contributing to the project
Read [CONTRIBUTING.md](CONTRIBUTING.md)