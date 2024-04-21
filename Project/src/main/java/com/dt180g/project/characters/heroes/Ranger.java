package com.dt180g.project.characters.heroes;

import java.util.ArrayList;
import java.util.List;

import com.dt180g.project.abilities.BaseAbility;
import com.dt180g.project.abilities.WeaponAttack;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.abilities.FocusedShot;
import com.dt180g.project.abilities.SprayOfArrows;

/**
 * Class for the Ranger hero.
 *
 * @author Frank Curwen
 */
public class Ranger extends BaseHero {
    /**
     * Constructor for the Ranger class. Sets the hero's name, attributes, and abilities.
     *
     * @param characterName The name of the hero
     */
    public Ranger(String characterName) {
        // Set the hero's name, attributes, and abilities
        super(characterName + " The " + AppConfig.HERO_RANGER, AppConfig.ATTRIBUTE_VALUES_RANGER_HERO);

        equipHero(Ranger.class); // Equip the hero with weapons and armor

        List<BaseAbility> abilities = new ArrayList<>(); // Create a new list of abilities
        abilities.add(new WeaponAttack()); // Add the weapon attack ability to the list
        abilities.add(new FocusedShot()); // Add the focused shot ability to the list
        abilities.add(new SprayOfArrows()); // Add the spray of arrows ability to the list
        addAbilities(abilities); // Add the abilities to the hero
    }
}
