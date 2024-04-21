package com.dt180g.project.characters;

import com.dt180g.project.abilities.BaseAbility;
import com.dt180g.project.gear.Armor;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.stats.BaseStat;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.dt180g.project.support.TestSupportExtra.assertReturnType;
import static com.dt180g.project.support.TestSupportExtra.confirmPrivateFields;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestEquipmentAndStatsExtra {
    @Test
    public void testEquipmentFieldsArePrivate() {
        confirmPrivateFields(CharacterEquipment.class);
    }

    @Test
    public void testStatsFieldsArePrivate() {
        confirmPrivateFields(CharacterStats.class);
    }

    @Test
    public void testEquipmentMethodReturnTypes() {
        assertAll(
                () -> assertTrue(
                        List.class.isAssignableFrom(CharacterEquipment.class.getDeclaredMethod("getWeapons").getReturnType()),
                        "Return type of 'CharacterEquipment::getWeapons' needs to be List."
                ),
                () -> assertTrue(
                        List.class.isAssignableFrom(CharacterEquipment.class.getDeclaredMethod("getArmorPieces").getReturnType()),
                        "Return type of 'CharacterEquipment::getArmorPieces' needs to be List."
                ),
                () -> assertReturnType(CharacterEquipment.class.getDeclaredMethod("getTotalWeaponDamage"), int.class, Integer.class),
                () -> assertReturnType(CharacterEquipment.class.getDeclaredMethod("getTotalArmorProtection"), int.class, Integer.class),
                () -> assertReturnType(CharacterEquipment.class.getDeclaredMethod("amountOfEmptyWeaponSlots"), int.class, Integer.class),
                () -> assertReturnType(CharacterEquipment.class.getDeclaredMethod("amountOfEmptyArmorSlots"), int.class, Integer.class),
                () -> assertReturnType(CharacterEquipment.class.getDeclaredMethod("addWeapon", Weapon.class), boolean.class, Boolean.class),
                () -> assertReturnType(CharacterEquipment.class.getDeclaredMethod("addArmorPiece", String.class, Armor.class), boolean.class, Boolean.class)
        );
    }

    @Test
    public void testStatsMethodReturnTypes() {
        assertAll(
                () -> assertTrue(
                        BaseStat.class.isAssignableFrom(CharacterStats.class.getDeclaredMethod("getStat", String.class).getReturnType()),
                        "Return type of 'CharacterStats::getStat' needs to be BaseStat."
                ),
                () -> assertTrue(
                        int.class.isAssignableFrom(CharacterStats.class.getDeclaredMethod("getStatValue", String.class).getReturnType()),
                        "Return type of 'CharacterStats::getStatValue' needs to be int."
                ),
                () -> assertTrue(
                        int.class.isAssignableFrom(CharacterStats.class.getDeclaredMethod("getTotalActionPoints").getReturnType()),
                        "Return type of 'CharacterStats::getTotalActionPoints' needs to be int."
                ),
                () -> assertTrue(
                        int.class.isAssignableFrom(CharacterStats.class.getDeclaredMethod("getCurrentActionPoints").getReturnType()),
                        "Return type of 'CharacterStats::getCurrentActionPoints' needs to be int."
                ),
                () -> assertTrue(
                        int.class.isAssignableFrom(CharacterStats.class.getDeclaredMethod("getTotalHitPoints").getReturnType()),
                        "Return type of 'CharacterStats::getTotalHitPoints' needs to be int."
                ),
                () -> assertTrue(
                        int.class.isAssignableFrom(CharacterStats.class.getDeclaredMethod("getCurrentHitPoints").getReturnType()),
                        "Return type of 'CharacterStats::getCurrentHitPoints' needs to be int."
                ),
                () -> assertTrue(
                        int.class.isAssignableFrom(CharacterStats.class.getDeclaredMethod("getTotalEnergyLevel").getReturnType()),
                        "Return type of 'CharacterStats::getTotalEnergyLevel' needs to be int."
                ),
                () -> assertTrue(
                        int.class.isAssignableFrom(CharacterStats.class.getDeclaredMethod("getCurrentEnergyLevel").getReturnType()),
                        "Return type of 'CharacterStats::getCurrentEnergyLevel' needs to be int."
                ),
                () -> assertTrue(
                        int.class.isAssignableFrom(CharacterStats.class.getDeclaredMethod("getDefenceRate").getReturnType()),
                        "Return type of 'CharacterStats::getDefenceRate' needs to be int."
                ),
                () -> assertTrue(
                        int.class.isAssignableFrom(CharacterStats.class.getDeclaredMethod("getAttackRate").getReturnType()),
                        "Return type of 'CharacterStats::getAttackRate' needs to be int."
                ),
                () -> assertTrue(
                        int.class.isAssignableFrom(CharacterStats.class.getDeclaredMethod("getPhysicalPower").getReturnType()),
                        "Return type of 'CharacterStats::getPhysicalPower' needs to be int."
                ),
                () -> assertTrue(
                        int.class.isAssignableFrom(CharacterStats.class.getDeclaredMethod("getMagicPower").getReturnType()),
                        "Return type of 'CharacterStats::getMagicPower' needs to be int."
                ),
                () -> assertTrue(
                        int.class.isAssignableFrom(CharacterStats.class.getDeclaredMethod("getHealingPower").getReturnType()),
                        "Return type of 'CharacterStats::getHealingPower' needs to be int."
                )
        );
    }
}
