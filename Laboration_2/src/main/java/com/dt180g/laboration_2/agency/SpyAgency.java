package com.dt180g.laboration_2.agency;

/**
 * Simple representation of a spy agency.
 * @author Erik Ström
 */
public class SpyAgency {

    /**
     * Default constructor.
     */
    public SpyAgency() { }

    /**
     * Procedure to simulate transfer of message between handlers.
     * @param message text content to be transferred.
     */
    public void runAgency(final String message) {
        SpyHandler source = new SpyHandler("Lennart");
        source.sendMessage(message, new SpyHandler("Lisa"));
    }
}
