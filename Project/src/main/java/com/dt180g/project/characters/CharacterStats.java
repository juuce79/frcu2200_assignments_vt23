package com.dt180g.project.characters;

import com.dt180g.project.stats.*;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.IOHelper;

import java.util.*;

/**
 * Class to handle the stats of a character.
 *
 * @author Frank Curwen
 */
public class CharacterStats {
    private final Map<String, BaseStat> stats; // Map of all stats

    /**
     * Constructor to create a new CharacterStats object
     *
     * @param attributes List of attribute values
     */
    public CharacterStats(List<Integer> attributes) {
        stats = new HashMap<>(); // Create a new map of stats

        // Add all attributes to the map
        List<String> attributeNames = StatsManager.INSTANCE.getAttributeNames(); // Get the list of attribute names
        for (int i = 0; i < attributeNames.size(); i++) { // Loop through all attribute names
            stats.put(attributeNames.get(i), new Attribute(attributeNames.get(i),
                    attributes.get(i) * AppConfig.ATTRIBUTE_BASE_VALUE)); // Add the attribute to the map
        }

        // Add all traits to the map
        List<String> traitNames = StatsManager.INSTANCE.getTraitNames(); // Get the list of trait names
        List<Integer> traitBaseValues = Arrays.asList( // Create a list of trait base values
                AppConfig.TRAIT_VITALITY_BASE_VALUE,
                AppConfig.TRAIT_ENERGY_BASE_VALUE,
                AppConfig.TRAIT_ATTACK_RATE_BASE_VALUE,
                AppConfig.TRAIT_DEFENCE_RATE_BASE_VALUE
        );
        for(int i = 0; i < traitNames.size(); i++) { // Loop through all trait names
            stats.put(traitNames.get(i), new Trait(traitNames.get(i), traitBaseValues.get(i))); // Add the trait to the map
        }

        // Add all combat stats to the map
        Map<String, String> combatStatToAttributeMapping = Map.of( // Create a map of combat stat to attribute mappings
                AppConfig.COMBAT_STAT_ACTION_POINTS, AppConfig.ATTRIBUTE_DEXTERITY,
                AppConfig.COMBAT_STAT_PHYSICAL_POWER, AppConfig.ATTRIBUTE_STRENGTH,
                AppConfig.COMBAT_STAT_MAGIC_POWER, AppConfig.ATTRIBUTE_INTELLIGENCE,
                AppConfig.COMBAT_STAT_HEALING_POWER, AppConfig.ATTRIBUTE_WILLPOWER
        );
        for (Map.Entry<String, String> entry : combatStatToAttributeMapping.entrySet()) { // Loop through all combat stat to attribute mappings
            stats.put(entry.getKey(), new CombatStat(entry.getKey(), stats.get(entry.getValue()),
                    stats.get(AppConfig.TRAIT_ATTACK_RATE))); // Add the combat stat to the map
        }
    }

    /**
     * Method to display the stats of the character
     */
    @Override
    public String toString() {
        List<List<String>> tableData = new ArrayList<>(); // Create a new list of table rows
        String delimiter = "|"; // Delimiter for the table
        List<String> attributes = StatsManager.INSTANCE.getAttributeNames(); // Get the list of attribute names
        List<String> traits = StatsManager.INSTANCE.getTraitNames(); // Get the list of trait names
        List<String> combatStats = StatsManager.INSTANCE.getCombatStatNames(); // Get the list of combat stat names

        for (int i = 0; i < attributes.size(); i++) { // Loop through all attributes
            List<String> tableRows = new ArrayList<>(); // Create a new list of table rows
            String attributeBaseStat = stats.get(attributes.get(i)).toString(); // Get the base stat of the attribute
            tableRows.add(attributeBaseStat); // Add the base stat to the list
            tableRows.add(delimiter); // Add the delimiter to the list
            String traitBaseStat = stats.get(traits.get(i)).toString(); // Get the base stat of the trait
            tableRows.add(traitBaseStat); // Add the base stat to the list
            tableRows.add(delimiter); // Add the delimiter to the list
            tableRows.add(stats.get(combatStats.get(i)).toString()); // Add the combat stat to the list
            tableData.add(tableRows); // Add the list to the table data
        }
        return IOHelper.formatAsTable(tableData); // Return the table data as a string
    }

    /**
     * Method to get a stat from the map
     *
     * @param name Name of the stat
     * @return The stat
     */
    public BaseStat getStat(String name) {
        return stats.get(name);
    }

    /**
     * Method to get the modified value of a stat
     *
     * @param name Name of the stat
     * @return The modified value of the stat
     */
    public int getStatValue(String name) {
        return stats.get(name).getModifiedValue();
    }

    /**
     * Method to get the total action points of the character
     *
     * @return Total action points
     */
    public int getTotalActionPoints() {
        // Add the base value and static modifier of the action points to get the total action points
        return getStat(AppConfig.COMBAT_STAT_ACTION_POINTS).getBaseValue() +
                getStat(AppConfig.COMBAT_STAT_ACTION_POINTS).getStaticModifier();
    }

    /**
     * Method to get the current action points of the character
     *
     * @return Current action points
     */
    public int getCurrentActionPoints() {
        return getStatValue(AppConfig.COMBAT_STAT_ACTION_POINTS);
    }

    /**
     * Method to get the total hit points of the character
     *
     * @return Total hit points
     */
    public int getTotalHitPoints() {
        // Add the base value and static modifier of the vitality to get the total hit points
        return getStat(AppConfig.TRAIT_VITALITY).getBaseValue() +
                getStat(AppConfig.TRAIT_VITALITY).getStaticModifier();
    }

    /**
     * Method to get the current hit points of the character
     *
     * @return Current hit points
     */
    public int getCurrentHitPoints() {
        return getStatValue(AppConfig.TRAIT_VITALITY);
    }

    /**
     * Method to get the total energy level of the character
     *
     * @return Total energy level
     */
    public int getTotalEnergyLevel() {
        // Add the base value and static modifier of the energy to get the total energy level
        return getStat(AppConfig.TRAIT_ENERGY).getBaseValue() +
                getStat(AppConfig.TRAIT_ENERGY).getStaticModifier();
    }

    /**
     * Method to get the current energy level of the character
     *
     * @return Current energy level
     */
    public int getCurrentEnergyLevel() {
        return getStatValue(AppConfig.TRAIT_ENERGY);
    }

    /**
     * Method to get the total defence rate of the character
     *
     * @return Total defence rate
     */
    public int getDefenceRate() {
        return getStatValue(AppConfig.TRAIT_DEFENSE_RATE);
    }

    /**
     * Method to get the total attack rate of the character
     *
     * @return Total attack rate
     */
    public int getAttackRate() {
        return getStatValue(AppConfig.TRAIT_ATTACK_RATE);
    }

    /**
     * Method to get the physical power of the character
     *
     * @return Physical power
     */
    public int getPhysicalPower() {
        return getStatValue(AppConfig.COMBAT_STAT_PHYSICAL_POWER);
    }

    /**
     * Method to get the magic power of the character
     *
     * @return Magic power
     */
    public int getMagicPower() {
        return getStatValue(AppConfig.COMBAT_STAT_MAGIC_POWER);
    }

    /**
     * Method to get the healing power of the character
     *
     * @return Healing power
     */
    public int getHealingPower() {
        return getStatValue(AppConfig.COMBAT_STAT_HEALING_POWER);
    }

    /**
     * Method to adjust the action points of the character
     *
     * @param amount Amount to adjust the action points by
     */
    public void adjustActionPoints(int amount) {
        adjustStatDynamicModifier(AppConfig.COMBAT_STAT_ACTION_POINTS, amount);
    }

    /**
     * Method to adjust the hit points of the character
     *
     * @param amount Amount to adjust the hit points by
     */
    public void adjustHitPoints(int amount) {
        adjustStatDynamicModifier(AppConfig.TRAIT_VITALITY, amount);
    }

    /**
     * Method to adjust the energy level of the character
     *
     * @param amount Amount to adjust the energy level by
     */
    public void adjustEnergyLevel(int amount) {
        adjustStatDynamicModifier(AppConfig.TRAIT_ENERGY, amount);
    }

    /**
     * Method to adjust the static modifier of a stat
     *
     * @param name Name of the stat
     * @param amount Amount to adjust the static modifier by
     */
    public void adjustStatStaticModifier(String name, int amount) {
        getStat(name).adjustStaticModifier(amount);
    }

    /**
     * Method to adjust the dynamic modifier of a stat
     *
     * @param name Name of the stat
     * @param amount Amount to adjust the dynamic modifier by
     */
    public void adjustStatDynamicModifier(String name, int amount) {
        getStat(name).adjustDynamicModifier(amount);
    }

    /**
     * Method to reset the action points of the character
     */
    public void resetActionPoints() {
        getStat(AppConfig.COMBAT_STAT_ACTION_POINTS).resetDynamicModifier();
    }

    /**
     * Method to reset the hit points of the character
     */
    public void resetHitPoints() {
        getStat(AppConfig.TRAIT_VITALITY).resetDynamicModifier();
    }

    /**
     * Method to reset the energy level of the character
     */
    public void resetEnergyLevel() {
        getStat(AppConfig.TRAIT_ENERGY).resetDynamicModifier();
    }
}
