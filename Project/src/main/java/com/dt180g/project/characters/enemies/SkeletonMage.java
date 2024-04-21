package com.dt180g.project.characters.enemies;

import java.util.ArrayList;
import java.util.List;

import com.dt180g.project.abilities.BaseAbility;
import com.dt180g.project.abilities.WeaponAttack;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.abilities.ElementalBolt;
import com.dt180g.project.abilities.ElementalBlast;

/**
 * Class for the Skeleton Mage enemy.
 *
 * @Author Frank Curwen
 */
public class SkeletonMage extends BaseEnemy {
    /**
     * Constructor for the SkeletonMage class. Sets the enemy's name, attributes, and abilities.
     *
     * @param seqNum The sequence number of the enemy
     */
    public SkeletonMage(int seqNum) {
        // Set the enemy's name, attributes, and abilities
        super(AppConfig.ENEMY_SKELETON_MAGE + " " + seqNum, AppConfig.ATTRIBUTE_VALUES_SKELETON_MAGE);

        List<String> acceptableWeaponTypes = new ArrayList<>(); // Create a new list of acceptable weapon types
        acceptableWeaponTypes.add(AppConfig.WEAPON_STAFF); // Add the staff weapon type to the list
        acceptableWeaponTypes.add(AppConfig.WEAPON_WAND); // Add the wand weapon type to the list
        equipEnemy(acceptableWeaponTypes); // Equip the enemy with weapons and armor

        List<BaseAbility> abilities = new ArrayList<>(); // Create a new list of abilities
        abilities.add(new WeaponAttack()); // Add the weapon attack ability to the list
        abilities.add(new ElementalBolt(AppConfig.ELEMENT_FIRE)); // Add the fire elemental bolt ability to the list
        abilities.add(new ElementalBolt(AppConfig.ELEMENT_ICE)); // Add the ice elemental bolt ability to the list
        abilities.add(new ElementalBolt(AppConfig.ELEMENT_AIR)); // Add the air elemental bolt ability to the list
        abilities.add(new ElementalBlast(AppConfig.ELEMENT_FIRE)); // Add the fire elemental blast ability to the list
        abilities.add(new ElementalBlast(AppConfig.ELEMENT_ICE)); // Add the ice elemental blast ability to the list
        abilities.add(new ElementalBlast(AppConfig.ELEMENT_AIR)); // Add the air elemental blast ability to the list
        addAbilities(abilities); // Add the abilities to the enemy
    }
}
