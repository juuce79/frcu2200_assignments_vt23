package com.dt180g.laboration_1;

/**
 * The main starting point for laboration 1.
 * @author Erik Ström
 */
public final class Lab1 {
    private Lab1() { // Utility classes should not have a public or default constructor
        throw new IllegalStateException("Utility class");
    }

    /**
     * Initializing the game object with two players and runs the simulation.
     * @param args command arguments. Will not be relevant here.
     */
    public static void main(final String... args) {
        Game game = new Game(new Player("Kevin"), new Player("Elvira"));
        game.runBattle();
    }
}
