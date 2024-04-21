package com.dt180g.laboration_2.decorators;

import com.dt180g.laboration_2.components.Content;
import com.dt180g.laboration_2.components.MessageInterface;

/**
 * Represents an operative who can access an encryption depth.
 * @author Frank Curwen
 */
public abstract class Operative extends Content {
    private Content content;

    /**
     * Constructor method for Operative decorator.
     * @param content Content, the content object to decorate.
     */
    protected Operative(Content content) {
        // Set the content object to decorate
        this.content = content;
    }

    /**
     * Returns the message stored in the decorated content object.
     * @return String, the message.
     */
    @Override
    public String getMessage() {
        // Return the message from the decorated content object
        return ((MessageInterface) content).getMessage();
    }
}
