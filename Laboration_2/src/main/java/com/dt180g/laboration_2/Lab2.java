package com.dt180g.laboration_2;

import com.dt180g.laboration_2.agency.SpyAgency;

/**
 * The main starting point for laboration 2.
 * @author Erik Ström
 */
public final class Lab2 {

    private Lab2() { // Utility classes should not have a public or default constructor
        throw new IllegalStateException("Utility class");
    }

    /**
     * Parse command arguments and start agency simulation with stated message contents.
     * @param args command arguments.
     */
    public static void main(final String... args) {
        String content = validateArgument(args);
        new SpyAgency().runAgency(content);
    }

    /**
     * Validate command argument and return the message content.
     * @param args command arguments.
     * @return the message content.
     */
    private static String validateArgument(final String... args) {
        try {
            if (args[0].isEmpty()) {
                throw new IllegalArgumentException("Message content cannot be empty");
            }
            return args[0];
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            return "Original Message";
        }
    }
}

