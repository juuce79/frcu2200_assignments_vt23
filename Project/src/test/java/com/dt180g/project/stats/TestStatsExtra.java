package com.dt180g.project.stats;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static com.dt180g.project.support.TestSupportExtra.confirmClassIsAbstract;
import static com.dt180g.project.support.TestSupportExtra.confirmPrivateFields;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestStatsExtra {
    @Test
    public void testStatsManagerSingletonAccess() {
        assertThrows(IllegalAccessException.class, StatsManager.class::newInstance);
    }
    @Test
    void testStatsManagerEagerSingleton() throws NoSuchFieldException {
        // Get the INSTANCE field of the singleton class using reflection
        Field instanceField = StatsManager.class.getDeclaredField("INSTANCE");

        // Check if the field is public, static and final
        int modifiers = instanceField.getModifiers();
        assertEquals(Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL, modifiers, "INSTANCE field should be public, static and final");

        // Check if the field is not null (meaning it has been properly initialized)
        StatsManager singletonInstance = null;
        try {
            singletonInstance = (StatsManager) instanceField.get(null);
        } catch (IllegalAccessException e) {
            // This exception should not occur since the field needs to be public
        }
        assertNotNull(singletonInstance, "INSTANCE field should not be null");
    }

    @Test
    public void testBaseStatNoInstantiation() {  // verify that we cannot create object instances
        assertThrows(InstantiationException.class, BaseStat.class::newInstance,
                "BaseStat needs to be purely abstract");
    }

    @Test
    void testBaseStatProtectedConstructor() throws NoSuchMethodException {
        Constructor<BaseStat> constructor = BaseStat.class.getDeclaredConstructor(String.class, int.class);
        int modifiers = constructor.getModifiers();
        assertEquals(Modifier.PROTECTED, modifiers, "BaseStat needs to have protected constructor.");
    }

    @Test
    public void testBaseStatIsAbstract() {
        confirmClassIsAbstract(BaseStat.class);
    }

    @Test
    public void testAllFieldsArePrivate() {
        assertAll(
                () -> confirmPrivateFields(BaseStat.class),
                () -> confirmPrivateFields(Attribute.class),
                () -> confirmPrivateFields(Trait.class),
                () -> confirmPrivateFields(CombatStat.class)
        );
    }

    @Test
    public void testAllDerivatives() {
        assertAll(
                () -> assertTrue(BaseStat.class.isAssignableFrom(Attribute.class), "Attribute needs to derive from BaseStat"),
                () -> assertTrue(BaseStat.class.isAssignableFrom(Trait.class), "Trait needs to derive from BaseStat"),
                () -> assertTrue(BaseStat.class.isAssignableFrom(CombatStat.class), "CombatStat needs to derive from BaseStat")
        );
    }
}
