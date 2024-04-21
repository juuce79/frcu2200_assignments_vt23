package com.dt180g.laboration_3.support;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Represents a logger for the Hanoi game.
 * @author Frank Curwen
 */
public class HanoiLogger {
    // Singleton instance
    private static HanoiLogger instance = null;
    // Logger
    private Logger logger;

    /**
     * Private constructor for the HanoiLogger class.
     */
    private HanoiLogger() {
        // Initialize logger
        if (AppConfig.shouldUseLog()) {
            initializeLogger();
        }
    }

    /**
     * Returns the instance of the HanoiLogger class.
     * @return the instance of the HanoiLogger class
     */
    public static HanoiLogger getInstance() {
        // If the singleton instance is null, create a new HanoiLogger instance
        if (instance == null) {
            instance = new HanoiLogger();
        }
        // If the logger inside the HanoiLogger instance is null, and if the application 
        // configuration allows logging, initialize the logger.
        if (instance.logger == null && AppConfig.shouldUseLog()) {
            instance.initializeLogger();
        }
        // Return the singleton instance of HanoiLogger
        return instance;
    }    

    /**
     * Closes the logger.
     */
    public void closeLogger() {
        // Check if the logger is not null and if the
        // application configuration allows for logging.
        if (logger != null && AppConfig.shouldUseLog()) {
            // For each handler associated with the logger
            for (Handler handler : logger.getHandlers()) {
                // Close the handler.
                handler.close();
                // Remove the handler from the logger, so it will no longer 
                // receive logging messages.
                logger.removeHandler(handler);
            }
        }
    }    

    /**
     * Resets the logger.
     */
    public void resetLogger() {
        // Check if the application configuration allows for logging
        if (AppConfig.shouldUseLog()) {
            // Call the closeLogger method to close and remove all handlers 
            // from the current logger.
            closeLogger();
            
            // Call the initializeLogger method to initialize a new logger 
            // with the same configuration.
            initializeLogger();
        }
    }
    
    /**
     * Logs an informational message if logging is enabled in the application 
     * configuration and if the logger has been initialized.
     * @param message the message to be logged
     */
    public void logInfo(String message) {
        // Check if the logger is not null and if the application
        // configuration allows for logging.
        if (logger != null && AppConfig.shouldUseLog()) {
            // Log the provided message at the INFO level.
            logger.info(message);
        }
    }

    /**
     * Initializes the logger with a custom file handler and formatter.
     */
    private void initializeLogger() {
        try {
            // Create a new FileHandler that specifies the log file path
            FileHandler handler = new FileHandler(AppConfig.getLogFilePath());
            
            // Set the log level for the handler to INFO
            handler.setLevel(Level.INFO);
            
            // Set a custom formatter for the handler that formats log records as a single line of text
            handler.setFormatter(new SimpleFormatter() {
                @Override
                public synchronized String format(LogRecord record) {
                    // Format the log record to include only the log message and a new line
                    return String.format("%s%n", record.getMessage());
                }
            });

            // Get the logger instance for the HanoiLogger class
            logger = Logger.getLogger(HanoiLogger.class.getName());
            
            // Disable the use of parent handlers to ensure only our custom FileHandler is used
            logger.setUseParentHandlers(false);
            
            // Add the file handler to the logger
            logger.addHandler(handler);
            
            // Set the log level for the logger to INFO
            logger.setLevel(Level.INFO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
