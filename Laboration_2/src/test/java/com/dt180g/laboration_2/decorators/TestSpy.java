package com.dt180g.laboration_2.decorators;

import com.dt180g.laboration_2.TestBase;
import com.dt180g.laboration_2.components.Container;
import com.dt180g.laboration_2.components.Content;
import com.dt180g.laboration_2.decorators.Spy;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Unit tests isolated to spy operatives.
 * @author Erik Ström
 */
public class TestSpy extends TestBase {

    /**
     * Validate that the spy applies encryption to message.
     */
    @Test
    void testSpyEncryption() {
        Content content = new Container(baseMsg, 0); // No encryption from the container

        // Check if the message is encrypted (i.e., not equal to the original message)
        assertNotEquals(baseMsg, new Spy(content).getMessage());
    }

    /**
     * Validate that there are variations to the random encryption levels of spies.
     */
    @Test
    void testSpyEncryptionVariation() {
        Content content = new Container(baseMsg, 0); // No encryption from the container
        List<String> messages = IntStream.range(0, 5)
                .mapToObj(i -> new Spy(content).getMessage())
                .toList();

        // Check if the encryption is varied enough. Not all spies should have the same encryption.
        assertNotEquals(1, messages.stream().distinct().count());
    }
}
