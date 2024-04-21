package com.dt180g.laboration_2.components;

/**
 * Represents a container for storing an encrypted message.
 * @author Frank Curwen
 */
public class Container extends Content {
    private String message;

    /**
     * Constructor method for Container class.
     * @param message String, the message to be encrypted and stored.
     * @param baseEncryption int, the level of encryption to use. If negative, defaults to 10.
     */
    public Container(String message, int baseEncryption) {
        // If the provided baseEncryption value is negative, set it to the default value of 10
        if (baseEncryption < 0) {
            baseEncryption = 10;
        }
    
        // Set the encryption level in the Content class to the provided baseEncryption value
        Content.setEncryptionLevel(baseEncryption);
    
        // Encrypt the provided message using the cipher method and the baseEncryption value, then store it in the container
        this.message = cipher(message, baseEncryption);
    }

    /**
     * Returns the encrypted message stored in the container.
     * @return String, the encrypted message.
     */
    @Override
    public String getMessage() {
        return message;
    }
}
