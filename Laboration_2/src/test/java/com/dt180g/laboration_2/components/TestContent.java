package com.dt180g.laboration_2.components;

import com.dt180g.laboration_2.TestBase;
import com.dt180g.laboration_2.components.Container;
import com.dt180g.laboration_2.components.Content;
import com.dt180g.laboration_2.decorators.Spy;
import com.dt180g.laboration_2.support.InvalidAuthorizationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests isolated to Content object.
 * @author Erik Ström
 */
public class TestContent extends TestBase {

    /**
     * Verify that content is truly abstract, i.e. cannot be instantiated.
     */
    @Test
    public void TestNoInstantiation() {
        assertThrows(InstantiationException.class,
                () -> Content.class.getDeclaredConstructor().newInstance());
    }

    /**
     * Verify that only spy masters may access value of encryption depth, and that
     * the error message is correct.
     */
    @Test
    public void testInvalidAuthorization() {
        RuntimeException exception = assertThrows(InvalidAuthorizationException.class,
                () -> Content.getEncryptionLevel(new Spy(new Container("message", 10))));

        String expectedMessage = "Only spy masters may access encryption depth.";
        assertEquals(expectedMessage, exception.getMessage());
    }
}
