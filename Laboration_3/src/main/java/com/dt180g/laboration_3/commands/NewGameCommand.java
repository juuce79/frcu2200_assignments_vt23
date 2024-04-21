package com.dt180g.laboration_3.commands;

import com.dt180g.laboration_3.receiver.HanoiEngine;
import com.dt180g.laboration_3.support.AppConfig;
import com.dt180g.laboration_3.support.HanoiLogger;

/**
 * Represents a command for starting a new game of Hanoi.
 * Contains the code that logs the moves, undos and redos
 * and the amount of discs used for restarting an
 * uncompleted game.
 * @author Frank Curwen
 */
public class NewGameCommand implements CommandInterface {
    // The amount of discs to use in the new game
    private int discAmount;

    /**
     * Creates a new game command with the specified amount of discs.
     * @param discAmount The amount of discs to use in the new game
     */
    public NewGameCommand(int discAmount) {
        // Validate disc amount
        this.discAmount = discAmount;
    }

    /**
     * Executes the command.
     */
    @Override
    public void execute() {
        // Validate disc amount
        if (discAmount < AppConfig.DISC_AMOUNT_MINIMUM) {
            // Set to minimum amount
            discAmount = AppConfig.DISC_AMOUNT_MINIMUM;
          // Validate disc amount
        } else if (discAmount > AppConfig.DISC_AMOUNT_MAXIMUM) {
            // Set to maximum amount
            discAmount = AppConfig.DISC_AMOUNT_MAXIMUM;
        }
        // Reset the game with the disc amount
        HanoiEngine.INSTANCE.resetGame(discAmount);
        // Reset the logger
        HanoiLogger.getInstance().resetLogger();
        // Log the number of discs used in the new game
        HanoiLogger.getInstance().logInfo(String.valueOf(discAmount));
    }
}
