package com.dt180g.project.abilities;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static com.dt180g.project.support.TestSupportExtra.assertReturnType;
import static com.dt180g.project.support.TestSupportExtra.confirmClassIsAbstract;
import static com.dt180g.project.support.TestSupportExtra.confirmPrivateFields;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestAbilitiesExtra {
    @Test
    public void testBaseAbilityNoInstantiation() {  // verify that we cannot create object instances
        assertThrows(InstantiationException.class, BaseAbility.class::newInstance,
                "BaseAbility needs to be purely abstract");
    }
    @Test
    void testBaseAbilityProtectedConstructor() throws NoSuchMethodException {
        Constructor<BaseAbility> constructor = BaseAbility.class.getDeclaredConstructor(int.class, int.class);
        int modifiers = constructor.getModifiers();
        assertEquals(Modifier.PROTECTED, modifiers, "BaseAbility needs to have protected constructor.");
    }

    @Test
    public void testPerformAbilityModifier() throws NoSuchMethodException {
        assertTrue(Modifier.isProtected(BaseAbility.class.getDeclaredMethod("performAbility", String.class, int.class, int.class, boolean.class).getModifiers()),
                "Method 'BaseAbility::performAbility' needs to be protected.");
    }

    @Test
    public void testBaseAbilityIsAbstract() {
        confirmClassIsAbstract(BaseAbility.class);
    }

    @Test
    public void testBaseAbilityMethodReturnTypes() {
        assertAll(
                () -> assertReturnType(BaseAbility.class.getDeclaredMethod("getActionPointCost"), int.class, Integer.class),
                () -> assertReturnType(BaseAbility.class.getDeclaredMethod("getEnergyCost"), int.class, Integer.class),
                () -> assertReturnType(BaseAbility.class.getDeclaredMethod("isMagic"), boolean.class, Boolean.class),
                () -> assertReturnType(BaseAbility.class.getDeclaredMethod("isHeal"), boolean.class, Boolean.class),
                () -> assertReturnType(BaseAbility.class.getDeclaredMethod("getAmountOfTargets"), int.class, Integer.class),
                () -> assertReturnType(BaseAbility.class.getDeclaredMethod("execute", int.class, boolean.class), boolean.class, Boolean.class)
        );
    }

    @Test
    public void testAllFieldsArePrivate() {
        assertAll(
                () -> confirmPrivateFields(BaseAbility.class),
                () -> confirmPrivateFields(ElementalBlast.class),
                () -> confirmPrivateFields(ElementalBolt.class),
                () -> confirmPrivateFields(FocusedHeal.class),
                () -> confirmPrivateFields(GroupHeal.class),
                () -> confirmPrivateFields(FocusedShot.class),
                () -> confirmPrivateFields(SprayOfArrows.class),
                () -> confirmPrivateFields(HeavyAttack.class),
                () -> confirmPrivateFields(Whirlwind.class),
                () -> confirmPrivateFields(WeaponAttack.class)
        );
    }

    @Test
    public void testAllDerivatives() {
        assertAll(
                () -> assertTrue(BaseAbility.class.isAssignableFrom(ElementalBlast.class), "ElementalBlast needs to derive from BaseAbility"),
                () -> assertTrue(BaseAbility.class.isAssignableFrom(ElementalBolt.class), "ElementalBolt needs to derive from BaseAbility"),
                () -> assertTrue(BaseAbility.class.isAssignableFrom(FocusedHeal.class), "FocusedHeal needs to derive from BaseAbility"),
                () -> assertTrue(BaseAbility.class.isAssignableFrom(GroupHeal.class), "GroupHeal needs to derive from BaseAbility"),
                () -> assertTrue(BaseAbility.class.isAssignableFrom(FocusedShot.class), "FocusedShot needs to derive from BaseAbility"),
                () -> assertTrue(BaseAbility.class.isAssignableFrom(SprayOfArrows.class), "SprayOfArrows needs to derive from BaseAbility"),
                () -> assertTrue(BaseAbility.class.isAssignableFrom(HeavyAttack.class), "HeavyAttack needs to derive from BaseAbility"),
                () -> assertTrue(BaseAbility.class.isAssignableFrom(Whirlwind.class), "Whirlwind needs to derive from BaseAbility"),
                () -> assertTrue(BaseAbility.class.isAssignableFrom(WeaponAttack.class), "WeaponAttack needs to derive from BaseAbility")
        );
    }
}
