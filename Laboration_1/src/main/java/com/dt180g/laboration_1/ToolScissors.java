package com.dt180g.laboration_1;

/**
 * a concrete implementation of the Tool
 * abstract class representing the Scissors tool
 */
public class ToolScissors extends Tool {

    /**
     * creates a new ToolScissors object with the name "Scissors"
     */
    public ToolScissors() {
        super("Scissors");
    }

    /**
     * returns the Tool object that can beat
     * this Scissors tool (which is Rock)
     * 
     * @return the Tool object representing the weakness of Scissors
     */
    @Override public Tool getWeakness() {
        return new ToolRock();
    }
    
}
