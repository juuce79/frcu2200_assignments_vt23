package com.dt180g.laboration_2.decorators;

import com.dt180g.laboration_2.TestBase;
import com.dt180g.laboration_2.components.Container;
import com.dt180g.laboration_2.components.Content;
import com.dt180g.laboration_2.decorators.SpyMaster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for spy masters.
 * Validation will be based on initial encryption values of 10 and 30,
 * both with and without spies.
 * @author Erik Ström
 */
public class TestSpyMaster extends TestBase {
    private int spiesAmount;

    /**
     * Set up the test environment.
     * Initialize the number of spies for each test.
     */
    @BeforeEach
    public void setUp() {
        spiesAmount = 10;
    }

    /**
     * Test the SpyMaster's ability to decrypt a message encrypted
     * with a base encryption value of 10, without any spies.
     */
    @Test
    public void testSpyMasterOnContainerBaseEncryption10() {
        Content content = new Container(baseMsg, 10);
        assertEquals(baseMsg, new SpyMaster(content).getMessage());
    }

    /**
     * Test the SpyMaster's ability to decrypt a message encrypted
     * with a base encryption value of 30, without any spies.
     */
    @Test
    public void testSpyMasterOnContainerBaseEncryption30() {
        Content content = new Container(baseMsg, 30);
        assertEquals(baseMsg, new SpyMaster(content).getMessage());
    }

    /**
     * Test the SpyMaster's ability to decrypt a message encrypted
     * with a base encryption value of 10 and with additional spies.
     */
    @Test
    public void testSpyMasterOnSpiesBaseEncryption10() {
        int rot = 10;

        Content content = new Container(baseMsg, rot);
        content = super.addSpiesToContent(content, spiesAmount);

        assertEquals(baseMsg, new SpyMaster(content).getMessage());
    }

    /**
     * Test the SpyMaster's ability to decrypt a message encrypted
     * with a base encryption value of 30 and with additional spies.
     */
    @Test
    public void testSpyMasterOnSpiesBaseEncryption30() {
        int rot = 30;

        Content content = new Container(baseMsg, rot);
        content = super.addSpiesToContent(content, spiesAmount);

        assertEquals(baseMsg, new SpyMaster(content).getMessage());
    }
}
