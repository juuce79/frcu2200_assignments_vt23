package com.dt180g.project.characters.heroes;

import java.util.ArrayList;
import java.util.List;

import com.dt180g.project.abilities.BaseAbility;
import com.dt180g.project.abilities.WeaponAttack;
import com.dt180g.project.abilities.HeavyAttack;
import com.dt180g.project.abilities.Whirlwind;
import com.dt180g.project.support.AppConfig;

/**
 * Class for the Warrior hero.
 *
 * @author Frank Curwen
 */
public class Warrior extends BaseHero {
    /**
     * Constructor for the Warrior class. Sets the hero's name, attributes, and abilities.
     *
     * @param characterName The name of the hero
     */
    public Warrior(String characterName) {
        // Set the hero's name, attributes, and abilities
        super(characterName + " The " + AppConfig.HERO_WARRIOR, AppConfig.ATTRIBUTE_VALUES_WARRIOR_HERO);

        equipHero(Warrior.class); // Equip the hero with weapons and armor

        List<BaseAbility> abilities = new ArrayList<>(); // Create a new list of abilities
        abilities.add(new WeaponAttack()); // Add the weapon attack ability to the list
        abilities.add(new HeavyAttack()); // Add the heavy attack ability to the list
        abilities.add(new Whirlwind()); // Add the whirlwind ability to the list
        addAbilities(abilities); // Add the abilities to the hero
    }
}
