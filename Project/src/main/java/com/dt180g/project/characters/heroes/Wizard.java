package com.dt180g.project.characters.heroes;

import java.util.ArrayList;
import java.util.List;

import com.dt180g.project.abilities.BaseAbility;
import com.dt180g.project.abilities.WeaponAttack;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.abilities.ElementalBolt;
import com.dt180g.project.abilities.ElementalBlast;

/**
 * Class for the Wizard hero.
 *
 * @author Frank Curwen
 */
public class Wizard extends BaseHero {
    /**
     * Constructor for the Wizard class. Sets the hero's name, attributes, and abilities.
     *
     * @param characterName The name of the hero
     */
    public Wizard(String characterName) {
        // Set the hero's name, attributes, and abilities
        super(characterName + " The " + AppConfig.HERO_WIZARD, AppConfig.ATTRIBUTE_VALUES_WIZARD_HERO);

        equipHero(Wizard.class); // Equip the hero with weapons and armor

        List<BaseAbility> abilities = new ArrayList<>(); // Create a new list of abilities
        WeaponAttack weaponAttack = new WeaponAttack(); // Create a new weapon attack ability
        abilities.add(weaponAttack); // Add the weapon attack ability to the list
        abilities.add(new ElementalBolt(AppConfig.ELEMENT_FIRE)); // Add the fire elemental bolt ability to the list
        abilities.add(new ElementalBolt(AppConfig.ELEMENT_ICE)); // Add the ice elemental bolt ability to the list
        abilities.add(new ElementalBolt(AppConfig.ELEMENT_AIR)); // Add the air elemental bolt ability to the list
        abilities.add(new ElementalBlast(AppConfig.ELEMENT_FIRE)); // Add the fire elemental blast ability to the list
        abilities.add(new ElementalBlast(AppConfig.ELEMENT_ICE)); // Add the ice elemental blast ability to the list
        abilities.add(new ElementalBlast(AppConfig.ELEMENT_AIR)); // Add the air elemental blast ability to the list
        addAbilities(abilities); // Add the abilities to the hero
    }
}
