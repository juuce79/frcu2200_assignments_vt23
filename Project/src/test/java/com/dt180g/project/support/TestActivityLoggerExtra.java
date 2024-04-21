package com.dt180g.project.support;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestActivityLoggerExtra {
    @Test
    public void testActivityLoggerSingletonAccess() {
        assertThrows(IllegalAccessException.class, ActivityLogger.class::newInstance);
    }

    @Test
    void testLoggerEagerSingleton() throws NoSuchFieldException {
        // Get the INSTANCE field of the singleton class using reflection
        Field instanceField = ActivityLogger.class.getDeclaredField("INSTANCE");

        // Check if the field is public, static and final
        int modifiers = instanceField.getModifiers();
        assertEquals(Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL, modifiers, "INSTANCE field should be public, static and final");

        // Check if the field is not null (meaning it has been properly initialized)
        ActivityLogger singletonInstance = null;
        try {
            singletonInstance = (ActivityLogger) instanceField.get(null);
        } catch (IllegalAccessException e) {
            // This exception should not occur since the field needs to be public
        }
        assertNotNull(singletonInstance, "INSTANCE field should not be null");
    }

    @Test
    public void testLoggerMethodsModifiers() {
        assertAll(
                () -> assertTrue(
                        Modifier.isPrivate(ActivityLogger.class.getDeclaredConstructor().getModifiers()),
                        "ActivityLogger needs to have private constructor."
                ),
                () -> assertTrue(
                        Modifier.isPrivate(ActivityLogger.class.getDeclaredMethod("delayExecution").getModifiers()),
                        "Method 'ActivityLogger::delayExecution' needs to be private."
                ),
                () -> assertTrue(
                        Modifier.isPrivate(ActivityLogger.class.getDeclaredMethod("performLog", String.class).getModifiers()),
                        "Method 'ActivityLogger::performLog' needs to be private."
                )
        );
    }
}
