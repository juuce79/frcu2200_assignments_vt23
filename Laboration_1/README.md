# Laboration 1

## Environments & Tools

* MSI GE72MVR 7RG Apache Pro
* Windows 11 Pro 22H2 22621.1413
* Ubuntu Desktop 23.04
* Visual Studio Code 1.77.0 (on Windows 11 Pro)
* Visual Studio Code 1.78.2 (on Ubuntu Desktop 23.04)
* OpenJDK 17.0.6 LTS
* Apache Maven 3.9.1 (on Windows 11 Pro)
* Apache Maven 3.8.7 (on Ubuntu Desktop 23.04)
* Windows Terminal 1.16.10262.0
* Microsoft PowerShell 7.3.3
* Google Chrome 111.0.5563.147

## Purpose

The aim of this lab is to enhance the cohesiveness and scalability of a **Rock, Paper, Scissors** game implementation by applying the **Strategy** Design Pattern, while addressing the issues of low cohesion, tight coupling, and potential violation of the game's core rules.

**Concrete Goals:**

* Refactor the current implementation to utilize the **Strategy** Design Pattern by introducing an abstract `Tool` class and deriving concrete tool classes from it.
* Modify the `Player` constructor to correctly instantiate and use the new concrete tool classes.
* Refactor the `Game::determineWinner(..)` method to eliminate the use of switch / case statements based on tool names and to ensure the game's integrity by adhering to the core rules.
* Ensure that the final implementation adheres to the provided class diagram and specifications.

## Procedures

For this assignment, most of the work has already been done as this game already functions, with some caveats, without the need for further editing of the code. What follows is a short explanation of how the code has been changed and/or implemented to improve the game, as per the assignment instructions.

**`Game::determinWinner`**
The switch/case construct has been changed to if-else statements to check which tool each player has selected and to determine the winner accordingly. This works by checking which tool the first player has chosen, and then determines the winner based on the weakness of the second player's tool. For example, if the first player has chosen `ToolRock`, the method checks if the second player's tool is `ToolScissors` (which is weak to `ToolRock`), and returns `player1` if that is the case. If the second player's tool is not `ToolScissors`, it must be `ToolPaper` (which beats `ToolRock`), so the method returns `player2`. The same logic is applied for the other tools. Finally, if there is some bug or something else goes wrong, a catch-all condition (the final `else` statement) has been added which chooses `Player1` as the winner.

**`Player::Player`**
In the Player construct, `Tool("Rock")`, `Tool("Paper")` and `Tool("Scissors")` have been updated to the new sub-method names, `ToolRock()`, `ToolPaper()` and `ToolScissors()` and `Tool` has been removed from `ArrayList<Tool>` as the `Tool` class is not being called anymore.

**`Tool`**
The `Tool` class has been made abstract and has also had an abstract method, `getWeakness()`, added that must be implemented by the concrete tool classes `ToolPaper`, `ToolRock`, or `ToolScissors` to return the `Tool` object representing the weakness of the current tool.

**`ToolRock`, `ToolPaper`, `ToolScissors`**
The `ToolPaper`, `ToolRock`, and `ToolScissors` classes are the concrete subclasses that extend the `Tool` class. Each subclass has a constructor that calls the super constructor with the name of the tool (`"Paper"`, `"Rock"`, or `"Scissors"`). Each subclass also overrides the `getWeakness()` method to return the `Tool` object representing the weakness of the current tool, which is another tool that can beat the current tool in the game.

`ToolPaper` returns `ToolScissors` as the weakness, `ToolRock` returns `ToolPaper` as the weakness, and `ToolScissors` returns `ToolRock` as the weakness. These weaknesses are then used in `Game::determineWinner` to determine which player has won.

## Discussion

The purpose of this lab was to modify the existing Rock, Paper, Scissors game to make it more secure and remove the possibility of bugs, as well as to implement the Strategy Design Pattern to enhance the cohesiveness and scalability of the solution. In order to achieve these goals, several modifications were made to the original code.

Firstly, the `determineWinner` method was modified to make it more secure and remove the possibility of bugs. This was achieved by adding a series of conditional statements that check the tool selections of both players and determine the winner based on the rules of the game. By using conditional statements instead of hardcoding the winning combinations, the code was made more scalable and adaptable to potential changes in the game rules in the future.

Secondly, the `Tool` class was modified to be abstract and concrete subclasses were added for each tool to make the application more secure. By creating separate subclasses for each tool, the unique behaviors of each tool were encapsulated and this ensures that they can also easily be extended or modified in the future. This design also adds an additional layer of security to the code, as it prevents any unintended manipulation of the tool classes by external entities.

Finally, the `Player` constructor was modified to take the new subclasses into account. This allowed the easy instantiation of the appropriate tool objects based on the player's (random) selection. Again, by encapsulating the unique behaviors of each tool in their respective subclasses, a more secure and robust solution was created.

Overall, the modifications made to the code were successful in achieving the lab's goals. The new implementation of the Rock, Paper, Scissors game is more secure, adaptable, and scalable than the original, thanks to the use of the Strategy Design Pattern and the modifications made to the `determineWinner` method and the `Tool` and `Player` classes. The success of the lab was evaluated by testing the code thoroughly, both by running the java app and compiling it using `mvn clean verify`, and no issues or bugs were found. In conclusion, the lab was completed successfully and the modifications to the code were effective.
