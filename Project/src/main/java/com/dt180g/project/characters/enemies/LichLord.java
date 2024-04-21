package com.dt180g.project.characters.enemies;

import java.util.ArrayList;
import java.util.List;

import com.dt180g.project.abilities.*;
import com.dt180g.project.support.AppConfig;

/**
 * Class for the Lich Lord enemy.
 *
 * @author Frank Curwen
 */
public class LichLord extends BaseEnemy {
    /**
     * Constructor for the LichLord class. Sets the enemy's name, attributes, and abilities.
     */
    public LichLord() {
        // Set the enemy's name, attributes, and abilities
        super(AppConfig.ENEMY_LICH_LORD, AppConfig.ATTRIBUTE_VALUES_LICH_LORD);

        List<String> acceptableWeaponTypes = new ArrayList<>(); // Create a new list of acceptable weapon types
        acceptableWeaponTypes.add(AppConfig.WEAPON_AXE); // Add the axe weapon type to the list
        acceptableWeaponTypes.add(AppConfig.WEAPON_SWORD); // Add the sword weapon type to the list
        acceptableWeaponTypes.add(AppConfig.WEAPON_SHIELD); // Add the shield weapon type to the list
        equipEnemy(acceptableWeaponTypes); // Equip the enemy with weapons and armor

        List<BaseAbility> abilities = new ArrayList<>(); // Create a new list of abilities
        abilities.add(new WeaponAttack()); // Add the weapon attack ability to the list
        abilities.add(new HeavyAttack()); // Add the heavy attack ability to the list
        abilities.add(new Whirlwind()); // Add the whirlwind ability to the list
        abilities.add(new FocusedHeal()); // Add the focused heal ability to the list
        abilities.add(new ElementalBolt(AppConfig.ELEMENT_FIRE)); // Add the fire elemental bolt ability to the list
        abilities.add(new ElementalBlast(AppConfig.ELEMENT_FIRE)); // Add the fire elemental blast ability to the list
        addAbilities(abilities);

        // Adjust the enemy's stats
        getCharacterStats().adjustStatStaticModifier(AppConfig.TRAIT_VITALITY, (AppConfig.TRAIT_VITALITY_BASE_VALUE *
                AppConfig.BOSS_HEALTH_MULTIPLIER));
    }
}
