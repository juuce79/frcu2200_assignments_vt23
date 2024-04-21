package com.dt180g.project.characters.enemies;

import java.util.ArrayList;
import java.util.List;

import com.dt180g.project.abilities.BaseAbility;
import com.dt180g.project.abilities.WeaponAttack;
import com.dt180g.project.abilities.HeavyAttack;
import com.dt180g.project.abilities.Whirlwind;
import com.dt180g.project.support.AppConfig;

/**
 * Class for the Skeleton Warrior enemy.
 *
 * @Author Frank Curwen
 */
public class SkeletonWarrior extends BaseEnemy {
    /**
     * Constructor for the SkeletonWarrior class. Sets the enemy's name, attributes, and abilities.
     *
     * @param seqNum The sequence number of the enemy
     */
    public SkeletonWarrior(int seqNum) {
        // Set the enemy's name, attributes, and abilities
        super(AppConfig.ENEMY_SKELETON_WARRIOR + " " + seqNum, AppConfig.ATTRIBUTE_VALUES_SKELETON_WARRIOR);

        List<String> acceptableWeaponTypes = new ArrayList<>(); // Create a new list of acceptable weapon types
        acceptableWeaponTypes.add(AppConfig.WEAPON_AXE); // Add the axe weapon type to the list
        acceptableWeaponTypes.add(AppConfig.WEAPON_SWORD); // Add the sword weapon type to the list
        acceptableWeaponTypes.add(AppConfig.WEAPON_SHIELD); // Add the shield weapon type to the list
        equipEnemy(acceptableWeaponTypes); // Equip the enemy with weapons and armor

        List<BaseAbility> abilities = new ArrayList<>(); // Create a new list of abilities
        abilities.add(new WeaponAttack()); // Add the weapon attack ability to the list
        abilities.add(new HeavyAttack()); // Add the heavy attack ability to the list
        abilities.add(new Whirlwind()); // Add the whirlwind ability to the list
        addAbilities(abilities); // Add the abilities to the enemy
    }
}
