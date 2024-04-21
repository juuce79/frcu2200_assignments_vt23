package com.dt180g.laboration_3.invoker;

import java.util.Deque;
import java.util.LinkedList;

import com.dt180g.laboration_3.commands.CommandInterface;
import com.dt180g.laboration_3.commands.MoveCommand;
import com.dt180g.laboration_3.commands.NewGameCommand;
import com.dt180g.laboration_3.validation.InvalidMoveException;

/**
 * Represents a command manager for the Hanoi game.
 * Keeps track of executed and unexecuted moves.
 * @author Frank Curwen
 */
public class CommandManager {
    // Singleton instance
    public static final CommandManager INSTANCE = new CommandManager();

    // Deques for storing moves
    private Deque<MoveCommand> undoMoves;
    private Deque<MoveCommand> redoMoves;

    /**
     * Executes a command.
     * @param command the command to execute
     */
    public void executeCommand(CommandInterface command) {
        // Clear moves if new game
        if (command instanceof NewGameCommand) {
            clearMoves();
        }
        // Execute command
        try {
            // Validate move
            command.execute();
            // Check if move command
            if (command instanceof MoveCommand) {
                // Cast to move command
                MoveCommand moveCommand = (MoveCommand) command;
                // Add to undo stack
                undoMoves.push(moveCommand);
                // Clear redo stack
                redoMoves.clear();
            }
          // Catch invalid move exception
        } catch (InvalidMoveException e) {
            // Log the exception
            System.out.println(e.getMessage());
        }
    }

    /**
     * Undoes the last move.
     */
    public void undoMove() {
        // Check if moves exist
        if (!undoMoves.isEmpty()) {
            // Pop the last move
            MoveCommand moveCommand = undoMoves.pop();
            // Undo the move
            moveCommand.unExecute();
            // Add to redo stack
            redoMoves.push(moveCommand);
        }
    }

    /**
     * Redoes the last undone move.
     */
    public void redoMove() {
        // Check if moves exist
        if (!redoMoves.isEmpty()) {
            // Pop the last move
            MoveCommand moveCommand = redoMoves.pop();
            // Execute the move
            moveCommand.execute();
            // Add to undo stack
            undoMoves.push(moveCommand);
        }
    }

    /**
     * Gets the amount of moves that can be undone.
     * @return the amount of moves that can be undone
     */
    public int getUndoAmount() {
        return undoMoves.size();
    }

    /**
     * Gets the amount of moves that can be redone.
     * @return the amount of moves that can be redone
     */
    public int getRedoAmount() {
        return redoMoves.size();
    }

    /**
     * Creates a new command manager.
     */
    private CommandManager() {
        // Create the move stacks
        undoMoves = new LinkedList<>();
        redoMoves = new LinkedList<>();
    }

    /**
     * Clears the moves.
     */
    private void clearMoves() {
        // Clear the stacks
        undoMoves.clear();
        redoMoves.clear();
    }
}
