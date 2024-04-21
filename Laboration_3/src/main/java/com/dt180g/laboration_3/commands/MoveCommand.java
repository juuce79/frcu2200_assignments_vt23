package com.dt180g.laboration_3.commands;

import com.dt180g.laboration_3.receiver.HanoiEngine;
import com.dt180g.laboration_3.support.AppConfig;
import com.dt180g.laboration_3.support.HanoiLogger;

/**
 * Represents a command for moving a disc in the Hanoi game.
 * @author Frank Curwen
 */
public class MoveCommand implements CommandInterface {
    // The towers to move the disc from and to
    private int fromTower;
    private int toTower;

    /**
     * Creates a move command with the specified towers.
     * @param fromTower The tower to move the disc from
     * @param toTower The tower to move the disc to
     */
    public MoveCommand(int fromTower, int toTower) {
        // Validate towers
        this.fromTower = fromTower;
        this.toTower = toTower;
    }

    /**
     * Executes the command.
     */
    @Override
    public void execute() {
        // Move the disc
        HanoiEngine.INSTANCE.performMove(fromTower, toTower, true);
        // Create the move string
        String move = String.format("%d %d", fromTower, toTower);
        // Log the move
        HanoiLogger.getInstance().logInfo(move);
    }
    
    /**
     * Undoes the command.
     */
    public void unExecute() {
        // Move the disc
        HanoiEngine.INSTANCE.performMove(toTower, fromTower, false);
        // Log the undo
        HanoiLogger.getInstance().logInfo(AppConfig.LOG_UNDO_SYMBOL);
    }
}
