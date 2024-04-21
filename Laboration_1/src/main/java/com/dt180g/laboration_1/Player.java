package com.dt180g.laboration_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Class representing a Player for the game rock, paper and scissors.
 * @author Erik Ström
 */
public class Player {
    private final Random rand = new Random();
    private final String playerName;
    private final List<Tool> tools;
    private Tool selectedTool;
    private int winCount = 0;

    /**
     * Constructor for Player, initializing the list of tools needed.
     * @param nameOfPlayer the player's name.
     */
    public Player(final String nameOfPlayer) {
        /**
         * create a new ArrayList containing the
         * three types of tool options for the player
         */
        this.tools = new ArrayList<>(
            Arrays.asList(new ToolRock(), new ToolPaper(),
                    new ToolScissors())
        );
        /**
         * set the player's name to the given name
         */
        this.playerName = nameOfPlayer;
    }

    /**
     * Accessor for player's name.
     * @return the name of player
     */
    public String getName() {
        return this.playerName;
    }

    /**
     * Accessor for current win count.
     * @return the amount of times the player has won
     */
    public int getWinCount() {
        return this.winCount;
    }

    /**
     * Instructs the player to choose one of available tools.
     * This procedure randomizes the selection.
     * @return a randomly selected tool
     */
    public Tool selectTool() {
        this.selectedTool = this.tools.get(rand.nextInt(tools.size()));
        return this.selectedTool;
    }

    /**
     * Declare that the player has won a battle of rock, paper and scissors.
     * The count of wins is incremented and a status report is returned.
     * @return current state of player in terms of win counts
     */
    public String declareAsWinner() {
        ++this.winCount;
        return String.format("%s wins and has a total of %d victories.",
                this.playerName, this.winCount);
    }

    /**
     * Overridden method used to output player tool selection.
     * @return the currently selected tool
     */
    @Override public String toString() {
        return String.format("%s selects %s", this.playerName, this.selectedTool.toString());
    }
}
