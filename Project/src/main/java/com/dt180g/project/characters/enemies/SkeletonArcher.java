package com.dt180g.project.characters.enemies;

import java.util.ArrayList;
import java.util.List;

import com.dt180g.project.abilities.BaseAbility;
import com.dt180g.project.abilities.WeaponAttack;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.abilities.FocusedShot;
import com.dt180g.project.abilities.SprayOfArrows;

/**
 * Class for the Skeleton Archer enemy.
 *
 * @Author Frank Curwen
 */
public class SkeletonArcher extends BaseEnemy {
    /**
     * Constructor for the SkeletonArcher class. Sets the enemy's name, attributes, and abilities.
     *
     * @param seqNum The sequence number of the enemy
     */
    public SkeletonArcher(int seqNum) {
        // Set the enemy's name, attributes, and abilities
        super(AppConfig.ENEMY_SKELETON_ARCHER + " " + seqNum, AppConfig.ATTRIBUTE_VALUES_SKELETON_ARCHER);

        List<String> acceptableWeaponTypes = new ArrayList<>(); // Create a new list of acceptable weapon types
        acceptableWeaponTypes.add(AppConfig.WEAPON_BOW); // Add the bow weapon type to the list
        acceptableWeaponTypes.add(AppConfig.WEAPON_CROSSBOW); // Add the crossbow weapon type to the list
        equipEnemy(acceptableWeaponTypes); // Equip the enemy with weapons and armor

        List<BaseAbility> abilities = new ArrayList<>(); // Create a new list of abilities
        abilities.add(new WeaponAttack()); // Add the weapon attack ability to the list
        abilities.add(new FocusedShot()); // Add the focused shot ability to the list
        abilities.add(new SprayOfArrows()); // Add the spray of arrows ability to the list
        addAbilities(abilities); // Add the abilities to the enemy
    }
}
