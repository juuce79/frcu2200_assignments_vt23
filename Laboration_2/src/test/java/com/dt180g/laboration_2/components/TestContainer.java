package com.dt180g.laboration_2.components;

import com.dt180g.laboration_2.TestBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests isolated to container object.
 * @author Erik Ström
 */
public class TestContainer extends TestBase {

    /**
     * Test the Container's ability to encrypt a message
     * with a base encryption value of 10.
     */
    @Test
    public void testContainerBaseEncryption10() {
        assertEquals("Ybsqsxkv Wocckqo",
                new Container(baseMsg, 10).getMessage());
    }

    /**
     * Test the Container's ability to encrypt a message
     * with a base encryption value of 30.
     */
    @Test
    public void testContainerBaseEncryption30() {
        assertEquals("Svmkmrep Qiwweki",
                new Container(baseMsg, 30).getMessage());
    }

    /**
     * Test the Container's behavior when given a negative
     * encryption value.
     */
    @Test
    public void testContainerDefaultEncryptionNegativeValue() {
        assertEquals("Ybsqsxkv Wocckqo",
                new Container(baseMsg, -15).getMessage());
    }

    /**
     * Test the Container's behavior when given an encryption
     * value of zero.
     */
    @Test
    public void testContainerDefaultEncryptionZeroValue() {
        assertEquals(baseMsg,
                new Container(baseMsg, 0).getMessage());
    }

    /** Test the Container's ability to handle an empty message. */
    @Test
    public void testContainerEmptyString() {
        String emptyMsg = "";
        assertEquals(emptyMsg, new Container(emptyMsg, 10).getMessage());
    }

    /** Test the Container's ability to handle a complete rotation. */
    @Test
    public void testContainerCompleteRotation() {
        assertEquals(baseMsg,
                new Container(baseMsg, 26).getMessage());
    }

    /** Test the Container's ability to handle special characters. */
    @Test
    public void testContainerSpecialCharacters() {
        assertEquals("rSt123!@#",
                new Container("aBc123!@#", 17).getMessage());
    }
}
