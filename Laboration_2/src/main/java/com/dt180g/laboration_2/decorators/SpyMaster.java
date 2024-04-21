package com.dt180g.laboration_2.decorators;

import com.dt180g.laboration_2.components.Content;
import com.dt180g.laboration_2.support.Constants;

/**
 * Represents a spy master who can access an encryption depth and decrypt messages with a specific decryption key.
 * @author Frank Curwen
 */
public final class SpyMaster extends Operative {
    private int decryptionKey;

    /**
     * Constructor method for SpyMaster decorator.
     * @param content Content, the content object to decorate.
     */
    public SpyMaster(Content content) {
        // Call the constructor of the superclass, passing in the decorated content object
        super(content);
        
        // Get the encryption depth of the content object by calling the static method Content.getEncryptionLevel(),
        // passing in a reference to this SpyMaster object
        int encryptionDepth = Content.getEncryptionLevel(this);
        
        // Calculate the decryption key by subtracting the encryption depth modulo the alphabet length from the alphabet length
        this.decryptionKey = Constants.ALPHABET_LENGTH - (encryptionDepth % Constants.ALPHABET_LENGTH);
    }

    /**
     * Returns the message stored in the decorated content object, decrypted with the spy master's decryption key.
     * @return String, the decrypted message.
     */
    @Override
    public String getMessage() {
        String encryptedMessage = super.getMessage();
        return cipher(encryptedMessage, decryptionKey);
    }
}
