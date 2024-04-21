package com.dt180g.laboration_1;

/**
 * a concrete implementation of the Tool
 * abstract class representing the Rock tool
 */
public class ToolRock extends Tool {

    /**
     * creates a new ToolRock object with the name "Rock"
     */
    public ToolRock() {
        super("Rock");
    }

    /**
     * returns the Tool object that can beat
     * this Rock tool (which is Paper)
     * 
     * @return the Tool object representing the weakness of Rock
     */
    @Override public Tool getWeakness() {
        return new ToolPaper();
    }
    
}
