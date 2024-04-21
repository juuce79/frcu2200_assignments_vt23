package com.dt180g.project.gear;

import com.dt180g.project.abilities.BaseAbility;
import com.dt180g.project.stats.BaseStat;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import static com.dt180g.project.support.TestSupportExtra.assertReturnType;
import static com.dt180g.project.support.TestSupportExtra.confirmClassIsAbstract;
import static com.dt180g.project.support.TestSupportExtra.confirmPrivateFields;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGearExtra {
    @Test
    public void testGearManagerSingletonAccess() {
        assertThrows(IllegalAccessException.class, GearManager.class::newInstance);
    }

    @Test
    void testGearManagerEagerSingleton() throws NoSuchFieldException {
        // Get the INSTANCE field of the singleton class using reflection
        Field instanceField = GearManager.class.getDeclaredField("INSTANCE");

        // Check if the field is public, static and final
        int modifiers = instanceField.getModifiers();
        assertEquals(Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL, modifiers, "INSTANCE field should be public, static and final");

        // Check if the field is not null (meaning it has been properly initialized)
        GearManager singletonInstance = null;
        try {
            singletonInstance = (GearManager) instanceField.get(null);
        } catch (IllegalAccessException e) {
            // This exception should not occur since the field needs to be public
        }
        assertNotNull(singletonInstance, "INSTANCE field should not be null");
    }

    @Test
    void testBaseGearProtectedConstructor() throws NoSuchMethodException {
        Constructor<BaseGear> constructor = BaseGear.class.getDeclaredConstructor(String.class, String.class, String.class);
        int modifiers = constructor.getModifiers();
        assertEquals(Modifier.PROTECTED, modifiers, "BaseGear needs to have protected constructor.");
    }

    @Test
    public void testBaseGearIsAbstract() {
        confirmClassIsAbstract(BaseGear.class);
    }

    @Test
    public void testBaseGearFieldsArePrivate() {
        confirmPrivateFields(BaseGear.class);
    }

    @Test
    public void testBaseGearMethodReturnTypes() {
        assertAll(
                () -> assertTrue(
                        String.class.isAssignableFrom(BaseGear.class.getDeclaredMethod("getType").getReturnType()),
                        "Return type of 'BaseGear::getType' needs to be String."
                ),
                () -> assertTrue(
                        List.class.isAssignableFrom(BaseGear.class.getDeclaredMethod("getClassRestrictions").getReturnType()),
                        "Return type of 'BaseGear::getClassRestrictions' needs to be List."
                ),
                () -> assertTrue(
                        boolean.class.isAssignableFrom(BaseGear.class.getDeclaredMethod("checkClassRestriction", Class.class).getReturnType()),
                        "Return type of 'BaseGear::checkClassRestriction' needs to be boolean."
                ),
                () -> assertTrue(
                        BaseStat.class.isAssignableFrom(BaseGear.class.getDeclaredMethod("getStat").getReturnType()),
                        "Return type of 'BaseGear::getStat' needs to be BaseStat."
                )
        );
    }

    @Test
    public void testArmorDerivative() {  // verify that Armor derives from BaseGear
        assertTrue(BaseGear.class.isAssignableFrom(Armor.class),
                "Armor needs to derive from BaseGear");
    }

    @Test
    public void testArmorFieldsArePrivate() {
        confirmPrivateFields(Armor.class);
    }

    @Test
    public void testWeaponDerivative() {  // verify that Armor derives from BaseGear
        assertTrue(BaseGear.class.isAssignableFrom(Weapon.class),
                "Weapon needs to derive from BaseGear");
    }

    @Test
    public void testWeaponFieldsArePrivate() {
        confirmPrivateFields(Weapon.class);
    }

    @Test
    public void testArmorAndWeaponMethodReturnTypes() {
        assertAll(
                () -> assertReturnType(Armor.class.getDeclaredMethod("getProtection"), int.class, Integer.class),
                () -> assertTrue(
                        String.class.isAssignableFrom(Armor.class.getDeclaredMethod("getMaterial").getReturnType()),
                        "Return type of 'Armor::getMaterial' needs to be String."
                ),
                () -> assertTrue(
                        BaseStat.class.isAssignableFrom(Armor.class.getDeclaredMethod("getStat").getReturnType()),
                        "Return type of 'Armor::getStat' needs to be BaseStat."
                ),
                () -> assertReturnType(Weapon.class.getDeclaredMethod("getDamage"), int.class, Integer.class),
                () -> assertTrue(
                        String.class.isAssignableFrom(Weapon.class.getDeclaredMethod("getWield").getReturnType()),
                        "Return type of 'Armor::getWield' needs to be String."
                ),
                () -> assertTrue(
                        BaseStat.class.isAssignableFrom(Weapon.class.getDeclaredMethod("getStat").getReturnType()),
                        "Return type of 'Armor::getStat' needs to be BaseStat."
                ),
                () -> assertReturnType(Weapon.class.getDeclaredMethod("isTwoHanded"), boolean.class, Boolean.class)
        );
    }
}
