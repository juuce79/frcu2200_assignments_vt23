# Laboration 3

## Environments & Tools

* MSI GE72MVR 7RG Apache Pro
* Ubuntu Desktop 23.04 Lunar
* Visual Studio Code 1.78.2
* OpenJDK 17.0.6 LTS
* Apache Maven 3.8.7

## Purpose

The aim of this lab is to implement a text-based version of the **Tower of Hanoi** game, while applying key software engineering concepts such as the **Command** Design Pattern, **Singleton** pattern, and custom logging for recording move history. The finished product needs to support undo and redo mechanics, as well as offer replay functionality.

**Concrete Goals:**

* Implement the `HanoiLogger` class as a **Lazy Singleton** to log move activities during the game, adhering to lazy initialization and utilizing a customized formatter for log contents.
* Develop the `MoveCommand` and `NewGameCommand` classes as concrete commands implementing the `CommandInterface`, ensuring proper interaction with the `HanoiEngine`.
* Implement the `CommandManager` class as an **Eager Singleton** to handle command invocations, manage undo / redo mechanics, and maintain the state of executed, undone, and redoable commands.
* Create the Replayer class for replaying previous games using recorded log files, leveraging the `CommandManager` for command invocations and handling output silencing.
* Ensuring that the final implementation is in line with the design specifications, passes all provided unit tests, and demonstrates proficiency in applying relevant software engineering concepts.

## Procedures

For this assignment, in order to get a working solution, new classes were to be implemented without modyifying any existing classes. The classes to be implemented were `CommandManager`, `HanoiLogger`, `MoveCommand`, `NewGameCommand` and `Replayer`. The provided unit tests were to be used to verify that the solution works as intended. The following steps were taken to complete the assignment:

```java
public class CommandManager
```

The `CommandManager` class is a singleton class that manages and keeps track of executed and unexecuted moves in the Tower of Hanoi game. The following components were implemented into this class according to the class design diagram.

In the variable part of the class there are three objects. The `INSTANCE` field is a public static final instance of the `CommandManager` itself, ensuring that only one instance of the `CommandManager` exists throughout the application, adhering to the singleton design pattern. There are also two `Deque` (double ended queue) instances `undoMoves` and `redoMoves` which are used to store move commands that can be undone or redone.

We also have the following methods in the CommandManager class. The `executeCommand()` method takes a command as an argument and executes it. If the command is a `NewGameCommand`, it clears all the stored moves.

```java
if (command instanceof NewGameCommand) {
    clearMoves();
}
```

If the command is a `MoveCommand`, it validates the command, executes it, adds it to the `undoMoves` stack and clears the `redoMoves` stack.

```java
try {
    command.execute();
    if (command instanceof MoveCommand) {
        MoveCommand moveCommand = (MoveCommand) command;
        undoMoves.push(moveCommand);
        redoMoves.clear();
    }
}
```

If the command is not valid, it catches the `InvalidMoveException` and logs the exception message.

```java
catch (InvalidMoveException e) {
    System.out.println(e.getMessage());
}
```

The `undoMove()` method checks if there are any moves to undo (if `undoMoves` is not empty). If there are, it removes the last move from the `undoMoves` stack `MoveCommand moveCommand = undoMoves.pop();`, unexecutes it `moveCommand.unExecute();`, and pushes it to the `redoMoves` stack `redoMoves.push(moveCommand);`. The `redoMove()` method checks if there are any moves to redo (if `redoMoves` is not empty). If there are, it removes the last move from the `redoMoves` stack `MoveCommand moveCommand = undoMoves.pop();`, executes it `moveCommand.execute();`, and pushes it to the `undoMoves` stack `undoMoves.push(moveCommand);`.

The `getUndoAmount()` and `getRedoAmount()` methods return the number of moves that can be undone and redone respectively by using a size int from `java.util.Deque.size()`.

The private `CommandManager` constructor initializes `undoMoves` and `redoMoves` as `LinkedList` instances from `java.util.LinkedList`. `LinkedLists` have been used as they provide efficient implementations of stack operations, specifically push (add an element to the front) and pop (remove an element from the front), both of which are used for managing the history of moves. Since `LinkedLists` in Java are doubly-linked lists, these operations are performed in constant time, irrespective of the size of the list, ensuring fast and efficient management of the move history for the undo/redo functionality in the game.

Finally, the `clearMoves()` method is used to clear both the `undoMoves` and the `redoMoves` stacks.

```java
public class HanoiLogger
```

The `HanoiLogger` class is a custom logging class that uses the `java.util.logging` package to log information related to the Hanoi game. It is designed as a Singleton class.

The `HanoiLogger` constructor is private to prevent other classes from creating new instances. Instead, the `getInstance()` method is used to obtain the single `HanoiLogger` instance. If the instance doesn't already exist, it is created.

The `initializeLogger()` method is used to create and set up a `java.util.logging.Logger` instance. It creates a `FileHandler` that writes logs to the file path obtained from `AppConfig.getLogFilePath()`. The handler is set to log messages of `Level.INFO` and above. A custom `SimpleFormatter` is set for the handler to format the log messages to match the formatting example in the assignment PDF. The logger is then fetched using `Logger.getLogger(HanoiLogger.class.getName())`, and the `FileHandler` is added to it. The logger is also set to `Level.INFO` and configured to not use any parent handlers.

The `logInfo()` method logs an informational message if logging is enabled and the logger has been initialized.

```java
if (logger != null && AppConfig.shouldUseLog()) {
    logger.info(message);
}
```

The `closeLogger()` method closes all handlers attached to the logger and removes them if logging is enabled and the logger has been initialized.

```java
if (logger != null && AppConfig.shouldUseLog()) {
    for (Handler handler : logger.getHandlers()) {
        handler.close();
        logger.removeHandler(handler);
    }
}
```

The `resetLogger()` method closes all handlers and reinitializes the logger if logging is enabled. It is used to reset the logger to its initial state when a new game is started.

```java
if (AppConfig.shouldUseLog()) {
    closeLogger();
    initializeLogger();
}
```

Logging is by default enabled in `AppConfig`.

```java
private static boolean useLog = true;
public static boolean shouldUseLog() { return useLog; }
```

```java
public class MoveCommand implements CommandInterface
```

The `MoveCommand` class represents a command for moving a disc in the Towers of Hanoi game. It implements the `CommandInterface`, and thus provides an implementation for the `execute()` method defined in the interface.

The `MoveCommand` class has two instance variables, `fromTower` and `toTower`, representing the towers from which and to which a disc is moved, respectively. These variables are set through the class constructor.

In the `execute()` method, a call is made to `HanoiEngine.INSTANCE.performMove(fromTower, toTower, true)`. This line of code executes the action of moving a disc from `fromTower` to `toTower` using the `HanoiEngine` singleton instance. The `true` argument indicates that this is a normal move (not an undo operation). The method also constructs a log message and logs it using `HanoiLogger.getInstance().logInfo(move);` with the move variable being `String move = String.format("%d %d", fromTower, toTower);`.

The `unExecute()` method is used to undo a move. It calls `HanoiEngine.INSTANCE.performMove(toTower, fromTower, false)`, which moves a disc back from `toTower` to `fromTower`. The `false` argument indicates that this is an undo operation. The method also logs an undo symbol using `HanoiLogger.getInstance().logInfo(AppConfig.LOG_UNDO_SYMBOL);`.

```java
public class NewGameCommand implements CommandInterface
```

The `NewGameCommand` class represents a command for starting a new game. This class also implements the `CommandInterface`, thus providing an implementation for the `execute()` method defined in the interface.

The `NewGameCommand` class constructor accepts the number of discs to be used in the new game. This number is then stored in the `discAmount` instance variable, `this.discAmount = discAmount;`.

The overiding `execute()` method first validates the `discAmount` to make sure it is within the minimum and maximum range defined in `AppConfig`. If the `discAmount` is below the minimum, it is set to the minimum value. If it's above the maximum, it's set to the maximum. Once the validation is done, it calls `HanoiEngine.INSTANCE.resetGame(discAmount)`. This line of code resets the game with the validated `discAmount` using the `HanoiEngine` singleton instance. Then it calls `HanoiLogger.getInstance().resetLogger()`, resetting the logger by closing any existing handlers and re-initializing it. Finally, it logs the number of discs used in the new game using the `HanoiLogger`. This command is used whenever a new game is started, allowing the game's state and log to be completely reset and the game to be started with a fresh set of discs.

```java
public class Replayer
```

The `Replayer` class is responsible for replaying a sequence of moves recorded in the log file for the Towers of Hanoi game. It uses a `BufferedReader` to read through the log file, interpreting and executing each command.

Upon construction, a `Replayer` object is initialized with either a `BufferedReader` that reads from a log file or a pre-initialized `BufferedReader`.

The `runReplay()` method, which is called to start the replay, first checks if the `BufferedReader` has been initialized. If not, it throws an `IllegalStateException`. It then reads the first line of the log file, which represents the number of discs used in the game. It parses this value and uses it to initialize a new game by issuing a `NewGameCommand` to the `CommandManager`. The method then proceeds to read each subsequent line of the log file, which represents a game action. If the line is the undo symbol, it issues an `undoMove` command to the `CommandManager`. If the line is not the undo symbol, it assumes the line represents a move action and parses the line into `fromTower` and `toTower` integers. It then issues a `MoveCommand` with these values to the `CommandManager`. After each move, if the `shouldShowReplayMoves` flag is true, it issues a `ShowCommand` to the `CommandManager` to print the current game state. Once all lines have been read and the corresponding actions have been replayed, it closes the `BufferedReader`.

## Discussion

The purpose of this assignment was to implement a simulation of the Hanoi Towers game, where the player moves disks from one tower to another, following certain rules. The simulation needed to be interactive and provide options for starting new games, making moves and loading previous games. The assignment required the use of several software development principles, including the Command Design Pattern, custom exceptions, cohesive class hierarchies, documentation, and unit tests.

To achieve these goals, several classes were implemented based on the Class Design Diagram provided in the assignment. The `HanoiLogger` class provided logging functionality, while the `CommandManager` class implemented the Command Design Pattern to manage and execute user commands. The concrete `MoveCommand` and `NewGameCommand` classes represented specific types of commands, while the `Replayer` class implemented the functionality to replay previously saved games.

Throughout the implementation, the principles of proper file structure, package organization, naming conventions, and coding standards were followed to improve the readability and maintainability of the code. Additionally, unit tests were utilized to ensure that the code was working as expected.

Regarding the critical evaluation of the study's methodology and potential limitations, the methodology chosen for this study was effective. The application of the Command Design Pattern in the implementation of the game's functionality facilitated the abstraction of user commands, making the code more flexible and easier to extend with new commands in the future. The Singleton Design Pattern was also a good choice for the `CommandManager` and `HanoiLogger` classes, ensuring that only one instance of these classes exists throughout the application.

However, potential limitations and areas for improvement could be considered. For instance, the `Replayer` class currently reads from a file and performs the moves as fast as the machine can handle, which might not be desirable from a user perspective. Implementing an option for a delay mechanism would help in creating a more enjoyable user experience by letting the user choose to either watch each move in real time or continue as is. Furthermore, the `Replayer` class could also benefit from implementing a user-friendly way of browsing and choosing log files for replaying games. The user interface could also provide more feedback to the user regarding the success or failure of their actions. If an action fails, a detailed error message could be provided explaining why the action failed and what the user can do to fix it. This would make the game more enjoyable and accessible to users who are unfamiliar with the rules of the Hanoi Towers game.

In conclusion, while the implementation adheres to the Class Design Diagram and the instructions laid out in the assignment PDF by demonstrating the application of key software engineering concepts such as the Command and Singleton patterns, the application's design could be improved by making it more adaptable to future enhancements such as implementing a delay in the replay functionality or implementing more detailed error messages for a better user experience. These changes would provide a more user-friendly and robust Hanoi Towers game application.
