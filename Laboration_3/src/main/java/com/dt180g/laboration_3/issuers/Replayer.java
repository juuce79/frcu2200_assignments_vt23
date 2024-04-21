package com.dt180g.laboration_3.issuers;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;

import com.dt180g.laboration_3.commands.CommandInterface;
import com.dt180g.laboration_3.commands.MoveCommand;
import com.dt180g.laboration_3.commands.NewGameCommand;
import com.dt180g.laboration_3.commands.ShowCommand;
import com.dt180g.laboration_3.invoker.CommandManager;
import com.dt180g.laboration_3.support.AppConfig;

/**
 * Represents a replayer that reads a log file and replays the moves.
 * @author Frank Curwen
 */
public class Replayer {
    private BufferedReader bufferedReader;

    /**
     * Initializes the BufferedReader with the log file.
     * @throws IOException if an I/O error occurs when opening the log file
     * @throws URISyntaxException if the log file path is not a valid URI
     */
    public Replayer() throws IOException, URISyntaxException {
        // Initialize the BufferedReader with the log file
        this.bufferedReader = new BufferedReader(
            // Initialize a new FileReader with the log file path
            new java.io.FileReader(AppConfig.getLogFilePath()));
    }

    /**
     * Initializes a new instance of the Replayer class with a pre-initialized BufferedReader.
     * @param bufferedReader A BufferedReader object used for reading the input stream.
     * @throws IllegalArgumentException if the bufferedReader parameter is null.
     */
    public Replayer(BufferedReader bufferedReader) {
        // Check if the bufferedReader parameter is null
        if (bufferedReader == null) {
            // If it is null, throw an IllegalArgumentException with an appropriate error message
            throw new IllegalArgumentException("BufferedReader cannot be null");
        }
        // Assign the bufferedReader to the instance variable
        this.bufferedReader = bufferedReader;
    }

    /**
     * Runs the replay of the log file.
     * @throws IOException if an I/O error occurs when reading the log file
     * @throws NumberFormatException if a line in the log file cannot be parsed as an integer
     * @throws IndexOutOfBoundsException if a move in the log file refers to a non-existent tower
     * @throws IllegalStateException if the BufferedReader has not been initialized
     */
    public void runReplay() throws IOException, NumberFormatException, IndexOutOfBoundsException {
        // Check if the BufferedReader has been initialized
        if (bufferedReader == null) {
            // If not, throw an exception
            throw new IllegalStateException("BufferedReader not initialized");
        }
    
        // Read the first line of the log file to get the disc amount
        String line = bufferedReader.readLine();
        // Parse the disc amount from the line
        int discAmount = Integer.parseInt(line);
        // Initialize a new game with the disc amount
        CommandManager.INSTANCE.executeCommand(new NewGameCommand(discAmount));
    
        // Read each subsequent line of the log file
        while ((line = bufferedReader.readLine()) != null) {
            // If the line is the undo symbol, undo the last move
            if (line.equals(AppConfig.LOG_UNDO_SYMBOL)) {
                // Undo the last move
                CommandManager.INSTANCE.undoMove();
            // Otherwise, parse the move from the line and execute it
            } else {
                // Parse the move from the line
                String[] move = line.split(" ");
                // Parse the from tower and to tower from the move
                int fromTower = Integer.parseInt(move[0]);
                // Subtract 1 from the to tower to account for the 0-based index
                int toTower = Integer.parseInt(move[1]);
                // Initialize a new move command with the from tower and to tower
                CommandInterface moveCommand = new MoveCommand(fromTower, toTower);
                // Execute the move command
                CommandManager.INSTANCE.executeCommand(moveCommand);
    
                // If the replay moves flag is enabled, print the current game state after each move
                if (AppConfig.shouldShowReplayMoves()) {
                    // Initialize a new show command
                    CommandInterface showCommand = new ShowCommand();
                    // Execute the show command
                    CommandManager.INSTANCE.executeCommand(showCommand);
                }
            }
        }
    
        // Close the BufferedReader after the replay is finished
        bufferedReader.close();
    }
}
