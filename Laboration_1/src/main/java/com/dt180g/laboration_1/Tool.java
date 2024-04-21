package com.dt180g.laboration_1;

/**
 * The tool to be used in a game of Rock, Paper and Scissors.
 * @author Erik Ström
 */
public abstract class Tool {
    private final String toolName;

    /**
     * Public construction which initialize members.
     * @param nameOfTool the name of tool
     */
    protected Tool(final String nameOfTool) {
        this.toolName = nameOfTool;
    }

    /**
     * Overridden method used by clients to output name of tool.
     * @return name of the tool
     */
    @Override public String toString() {
        return this.toolName;
    }

    /**
     * Abstract method that must be implemented by
     * concrete tool classes to return the Tool object
     * representing the weakness of the current tool
     *
     * @return the Tool object representing the weakness of this tool
     */
    public abstract Tool getWeakness();
}
