package com.dt180g.laboration_2.components;

import com.dt180g.laboration_2.support.Constants;
import com.dt180g.laboration_2.support.InvalidAuthorizationException;
import com.dt180g.laboration_2.decorators.SpyMaster;

/**
 * Represents the basic functionality for encrypting and decrypting messages using the Caesar cipher.
 * @author Frank Curwen
 */
public abstract class Content implements MessageInterface {
    private static int encryptionLevel;

    /**
     * Increases the global encryption level.
     * @param level int, the amount to increase the encryption level by.
     */
    protected static void increaseEncryptionLevel(int level) {
        encryptionLevel += level;
    }

    /**
     * Sets the global encryption level.
     * @param level int, the encryption level to set.
     */
    protected static void setEncryptionLevel(int level) {
        encryptionLevel = level;
    }

    /**
     * Returns the encryption level of the given content object.
     * @param content Content, the content object to get the encryption level for.
     * @return int, the encryption level.
     * @throws InvalidAuthorizationException if the content object is an Operative or does not have an encryption depth.
     */
    protected static int getEncryptionLevel(Content content) throws InvalidAuthorizationException {
        // Check if the provided Content object is an instance of SpyMaster
        if (content instanceof SpyMaster) {
            // If it is, return the current encryption level
            return encryptionLevel;
        } else {
            // If it's not an instance of SpyMaster, throw an InvalidAuthorizationException
            throw new InvalidAuthorizationException("Only spy masters may access encryption depth.");
        }
    }

    /**
     * Encrypts a message using the Caesar cipher.
     * @param message String, the message to encrypt.
     * @param shift int, the amount to shift each character in the message by.
     * @return String, the encrypted message.
     * @throws IllegalArgumentException if the message is null.
     */
    protected String cipher(String message, int shift) {
        // Check if the message is null and throw an exception if it is
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }
    
        // Initialize a StringBuilder to store the encrypted message
        StringBuilder encryptedMessage = new StringBuilder();
    
        // Iterate through each character in the input message
        for (char ch : message.toCharArray()) {
            // If the character is not alphabetic, append it to the encrypted message without modification
            if (!Character.isAlphabetic(ch)) {
                encryptedMessage.append(ch);
                continue;
            }
    
            // Determine the offset value based on whether the character is uppercase or lowercase
            int offset = Character.isUpperCase(ch) ? 'A' : 'a';
    
            // Calculate the new position of the character in the alphabet after applying the shift
            int alphaPos = (ch - offset + shift) % Constants.ALPHABET_LENGTH;
    
            // If the new position is negative, add the alphabet length to make it positive
            if (alphaPos < 0) {
                alphaPos += Constants.ALPHABET_LENGTH;
            }
    
            // Calculate the encrypted character by adding the offset and new position
            char encryptedChar = (char) (offset + alphaPos);
    
            // Append the encrypted character to the encrypted message
            encryptedMessage.append(encryptedChar);
        }
    
        // Return the encrypted message as a string
        return encryptedMessage.toString();
    }
}
