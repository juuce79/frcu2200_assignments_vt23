package com.dt180g.project.support;

import java.util.Arrays;
import java.util.logging.*;

/**
 * The ActivityLogger class is the class for logging the activities of the game.
 *
 * @author Frank Curwen
 */
public class ActivityLogger {
    public static final ActivityLogger INSTANCE = new ActivityLogger(); // The instance of the ActivityLogger class
    private final Logger logger; // The logger for the ActivityLogger class

    /**
     * Constructor for the ActivityLogger class. Sets the logger's formatter and handler.
     */
    private ActivityLogger() {
        logger = Logger.getLogger(ActivityLogger.class.getName()); // Set the logger
        logger.setUseParentHandlers(false); // Set the logger to not use the parent handlers
        ConsoleHandler consoleHandler = new ConsoleHandler(); // Set the console handler
        consoleHandler.setFormatter(new Formatter() { // Set the formatter for the console handler
            @Override
            public String format(LogRecord record) { // Override the format method
                return record.getMessage() + System.lineSeparator(); // Return the message of the log record
            }
        });
        logger.addHandler(consoleHandler); // Add the console handler to the logger
    }

    /**
     * Delay the execution of the logger.
     */
    private void delayExecution() {
        try { // Try to delay the execution
            Thread.sleep(AppConfig.SLEEP_DELAY); // Delay the execution
        } catch (InterruptedException e) { // Catch the interrupted exception
            System.out.println(Arrays.toString(e.getStackTrace())); // Print the stack trace
        }
    }

    /**
     * Perform the log.
     *
     * @param message The message to log
     */
    private void performLog(String message) {
        if (AppConfig.USE_SLEEP_DELAY) { // If the sleep delay is used
            delayExecution(); // Delay the execution
        }
        logger.info(message); // Log the message
    }

    /**
     * Log the round information.
     *
     * @param roundInfo The round information to log
     */
    public void logRoundInfo(String roundInfo) {
        performLog(AppConfig.ANSI_PURPLE + roundInfo + AppConfig.ANSI_RESET); // Log the round information
    }

    /**
     * Log the turn information.
     *
     * @param turnInfo The turn information to log
     */
    public void logTurnInfo(String turnInfo) {
        performLog(AppConfig.ANSI_BLUE + turnInfo + AppConfig.ANSI_RESET); // Log the turn information
    }

    /**
     * Log the attack information.
     *
     * @param attack The attack information to log
     */
    public void logAttack(String attack) {
        performLog(AppConfig.ANSI_GREEN + "    " + attack + AppConfig.ANSI_RESET); // Log the attack information
    }

    /**
     * Log the damage information.
     *
     * @param damage The damage information to log
     */
    public void logDamage(String damage) {
        performLog(AppConfig.ANSI_YELLOW + "        " + damage + AppConfig.ANSI_RESET); // Log the damage information
    }

    /**
     * Log the death information.
     *
     * @param death The death information to log
     */
    public void logDeath(String death) {
        performLog(AppConfig.ANSI_RED + "        " + death + AppConfig.ANSI_RESET); // Log the death information
    }

    /**
     * Log the healing information.
     *
     * @param healing The healing information to log
     */
    public void logHealing(String healing) {
        performLog(AppConfig.ANSI_MAGENTA + healing + AppConfig.ANSI_RESET); // Log the healing information
    }
}
