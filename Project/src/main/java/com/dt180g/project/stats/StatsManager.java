package com.dt180g.project.stats;

import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.Randomizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The StatsManager class is the singleton class for managing the stats of the characters.
 *
 * @author Frank Curwen
 */
public class StatsManager {
    public static final StatsManager INSTANCE = new StatsManager(); // The singleton instance of the StatsManager class
    private final List<String> attributeNames; // The list of attribute names
    private final List<String> traitNames; // The list of trait names
    private final List<String> combatStatNames; // The list of combat stat names

    /**
     * Constructor for the StatsManager class. Sets the attribute, trait, and combat stat names.
     */
    private StatsManager() {
        // Set the attribute names
        this.attributeNames = new ArrayList<>(); // The list of attribute names
        attributeNames.add(AppConfig.ATTRIBUTE_STRENGTH); // Add the strength attribute name
        attributeNames.add(AppConfig.ATTRIBUTE_DEXTERITY); // Add the dexterity attribute name
        attributeNames.add(AppConfig.ATTRIBUTE_INTELLIGENCE); // Add the intelligence attribute name
        attributeNames.add(AppConfig.ATTRIBUTE_WILLPOWER); // Add the willpower attribute name

        // Set the trait names
        this.traitNames = new ArrayList<>(); // The list of trait names
        traitNames.add(AppConfig.TRAIT_VITALITY); // Add the vitality trait name
        traitNames.add(AppConfig.TRAIT_ENERGY); // Add the energy trait name
        traitNames.add(AppConfig.TRAIT_ATTACK_RATE); // Add the attack rate trait name
        traitNames.add(AppConfig.TRAIT_DEFENSE_RATE); // Add the defense rate trait name

        // Set the combat stat names
        this.combatStatNames = new ArrayList<>(); // The list of combat stat names
        combatStatNames.add(AppConfig.COMBAT_STAT_ACTION_POINTS); // Add the action points combat stat name
        combatStatNames.add(AppConfig.COMBAT_STAT_PHYSICAL_POWER); // Add the physical power combat stat name
        combatStatNames.add(AppConfig.COMBAT_STAT_MAGIC_POWER); // Add the magic power combat stat name
        combatStatNames.add(AppConfig.COMBAT_STAT_HEALING_POWER); // Add the healing power combat stat name
    }

    /**
     * Get a random attribute name.
     *
     * @return A random attribute name
     */
    public String getRandomAttributeName() {
        return attributeNames.get(Randomizer.INSTANCE.getRandomValue(attributeNames.size() - 1));
    }

    /**
     * Get a random trait name.
     *
     * @return A random trait name
     */
    public String getRandomTraitName() {
        return traitNames.get(Randomizer.INSTANCE.getRandomValue(traitNames.size() - 1));
    }

    /**
     * Get a list of all the attribute names.
     *
     * @return A list of all the attribute names
     */
    public List<String> getAttributeNames() {
        return attributeNames;
    }

    /**
     * Get a list of all the trait names.
     *
     * @return A list of all the trait names
     */
    public List<String> getTraitNames() {
        return traitNames;
    }

    /**
     * Get a list of all the combat stat names.
     *
     * @return A list of all the combat stat names
     */
    public List<String> getCombatStatNames() {
        return combatStatNames;
    }
}
