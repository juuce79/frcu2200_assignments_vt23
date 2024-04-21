package com.dt180g.project.stats;

import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.IOHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * The BaseStat class is the abstract class for the stats of the characters.
 *
 * @author Frank Curwen
 */
public abstract class BaseStat {
    private final String statName; // The name of the stat
    private final int baseValue; // The base value of the stat
    private int staticModifier; // The static modifier of the stat
    private int dynamicModifier; // The dynamic modifier of the stat

    /**
     * Constructor for the BaseStat class. Sets the stat's name and base value.
     *
     * @param statName The name of the stat
     * @param baseValue The base value of the stat
     */
    protected BaseStat(String statName, int baseValue) {
        this.statName = statName; // Set the stat's name
        this.baseValue = baseValue; // Set the stat's base value
    }

    /**
     * Get the name of the stat.
     *
     * @return The name of the stat
     */
    public String getStatName() {
        return statName;
    }

    /**
     * Get the base value of the stat.
     *
     * @return The base value of the stat
     */
    public int getBaseValue() {
        return baseValue;
    }

    /**
     * Get the modified value of the stat.
     *
     * @return The modified value of the stat by adding the base value and the static and dynamic modifiers
     */
    public int getModifiedValue() {
        return baseValue + staticModifier + dynamicModifier;
    }

    /**
     * Get the total modifier of the stat.
     *
     * @return The total modifier of the stat by adding the static and dynamic modifiers
     */
    public int getTotalModifier() {
        return staticModifier + dynamicModifier;
    }

    /**
     * Get the static modifier of the stat.
     *
     * @return The static modifier of the stat
     */
    public int getStaticModifier() {
        return staticModifier;
    }

    /**
     * This method is used to adjust the static modifier of the stat and if the modifier is positive,
     * the static modifier is increased while if the modifier is negative, the static modifier is decreased.
     *
     * @param modifier The modifier to adjust the static modifier by
     */
    public void adjustStaticModifier(int modifier) {
        staticModifier += modifier;
    }

    /**
     * This method is used to adjust the dynamic modifier of the stat and if the modifier is positive,
     * the dynamic modifier is increased while if the modifier is negative, the dynamic modifier is decreased.
     *
     * @param modifier The modifier to adjust the dynamic modifier by
     */
    public void adjustDynamicModifier(int modifier) {
        dynamicModifier += modifier;
    }

    /**
     * This method is used to reset the dynamic modifier of the stat.
     */
    public void resetDynamicModifier() {
        dynamicModifier = 0;
    }

    /**
     * Overridden toString method to return the stat's name, modified value, and total modifier.
     *
     * @return The string representation of the stat
     */
    @Override
    public String toString() {
        List<List<String>> tableData = new ArrayList<>(); // Create a new list of lists of strings
        List<String> rowData = new ArrayList<>(); // Create a new list of strings
        rowData.add(AppConfig.ANSI_GREEN + getStatName() + AppConfig.ANSI_RESET); // Add the stat's name to the row
        rowData.add(AppConfig.ANSI_CYAN + getModifiedValue() + AppConfig.ANSI_RESET); // Add the modified value to the row
        rowData.add(AppConfig.ANSI_YELLOW + "+" + getTotalModifier() + AppConfig.ANSI_RESET); // Add the total modifier to the row
        tableData.add(rowData); // Add the row to the table
        return IOHelper.formatAsTable(tableData); // Return the table as a string
    }
}
