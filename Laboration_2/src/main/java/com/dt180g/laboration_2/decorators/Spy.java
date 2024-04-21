package com.dt180g.laboration_2.decorators;

import com.dt180g.laboration_2.components.Content;
import com.dt180g.laboration_2.support.Constants;

/**
 * Represents a spy who can access an encryption depth and encrypt messages with a random encryption level.
 * @author Frank Curwen
 */
public final class Spy extends Operative {
    private int encryptionLevel;

    /**
     * Constructor method for Spy decorator.
     * @param content Content, the content object to decorate.
     */
    public Spy(Content content) {
        super(content);
        // do-while loop to ensure that the encryption level is not 26
        do {
            // Generate a random encryption level between 10 and 30
            this.encryptionLevel = (int) (Math.random() * (Constants.UPPER_BOUNDARY - Constants.LOWER_BOUNDARY) + Constants.LOWER_BOUNDARY);
          // Generate again if the encryption level is 26  
        } while (this.encryptionLevel == 26);
        // Increase the global encryption level by the encryption level of the spy
        Content.increaseEncryptionLevel(encryptionLevel);
    }

    /**
     * Returns the message stored in the decorated content object, encrypted with the spy's encryption level.
     * @return String, the encrypted message.
     */
    @Override
    public String getMessage() {
        // Get the encrypted message from the decorated content object
        String encryptedMessage = super.getMessage();
        return cipher(encryptedMessage, encryptionLevel);
    }
}
