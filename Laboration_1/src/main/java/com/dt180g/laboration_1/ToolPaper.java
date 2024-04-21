package com.dt180g.laboration_1;

/**
 * a concrete implementation of the Tool
 * abstract class representing the Paper tool
 */
public class ToolPaper extends Tool {

    /**
     * creates a new ToolPaper object with the name "Paper"
     */
    public ToolPaper() {
        super("Paper");
    }

    /**
     * returns the Tool object that can beat
     * this Paper tool (which is Scissors)
     * 
     * @return the Tool object representing the weakness of Paper
     */
    @Override public Tool getWeakness() {
        return new ToolScissors();
    }
    
}
