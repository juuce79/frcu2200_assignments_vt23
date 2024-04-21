package com.dt180g.project.characters.heroes;

import java.util.ArrayList;
import java.util.List;

import com.dt180g.project.abilities.BaseAbility;
import com.dt180g.project.abilities.WeaponAttack;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.abilities.FocusedHeal;
import com.dt180g.project.abilities.GroupHeal;

/**
 * Class for the Cleric hero.
 *
 * @author Frank Curwen
 */
public class Cleric extends BaseHero {
    /**
     * Constructor for the Cleric class. Sets the hero's name, attributes, and abilities.
     *
     * @param characterName The name of the hero
     */
    public Cleric(String characterName) {
        // Set the hero's name, attributes, and abilities
        super(characterName + " The " + AppConfig.HERO_CLERIC, AppConfig.ATTRIBUTE_VALUES_CLERIC_HERO);

        equipHero(Cleric.class); // Equip the hero with weapons and armor

        List<BaseAbility> abilities = new ArrayList<>(); // Create a new list of abilities
        abilities.add(new WeaponAttack()); // Add the weapon attack ability to the list
        abilities.add(new FocusedHeal()); // Add the focused heal ability to the list
        abilities.add(new GroupHeal()); // Add the group heal ability to the list
        addAbilities(abilities); // Add the abilities to the hero
    }
}
