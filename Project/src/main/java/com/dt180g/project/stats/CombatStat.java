package com.dt180g.project.stats;

import com.dt180g.project.support.AppConfig;

/**
 * The CombatStat class is the class for the combat stats of the characters.
 *
 * @author Frank Curwen
 */
public class CombatStat extends BaseStat {
    private final BaseStat attributeReliance; // The attribute the combat stat relies on
    private final BaseStat traitReliance; // The trait the combat stat relies on

    /**
     * Constructor for the CombatStat class. Sets the combat stat's name, attribute reliance, and trait reliance.
     * The base value is calculated as a weighted sum of the attribute and trait reliance, where each reliance's
     * modified value is multiplied by the combat stat multiplier.
     *
     * @param statName The name of the combat stat
     * @param attributeReliance The attribute the combat stat relies on
     * @param traitReliance The trait the combat stat relies on
     */
    public CombatStat(String statName, BaseStat attributeReliance, BaseStat traitReliance) {
        // Set the combat stat's name and base value
        super(statName, (int) Math.round((attributeReliance.getModifiedValue() * AppConfig.COMBAT_STAT_MULTIPLIER)
                + (int) Math.round(traitReliance.getModifiedValue() * AppConfig.COMBAT_STAT_MULTIPLIER)));
        this.attributeReliance = attributeReliance; // Set the combat stat's attribute reliance
        this.traitReliance =  traitReliance; // Set the combat stat's trait reliance
    }

    /**
     * Overidden method to get the base value of the combat stat.
     *
     * @return The base value of the combat stat
     */
    @Override
    public int getBaseValue() {
        int sum = 0; // The sum of the modified attribute and trait reliances
        // Add the combat stat multiplier multiplied by the modified attribute reliance to the sum
        sum += (int) Math.round(AppConfig.COMBAT_STAT_MULTIPLIER * attributeReliance.getModifiedValue());
        // Add the combat stat multiplier multiplied by the modified trait reliance to the sum
        sum += (int) Math.round(AppConfig.COMBAT_STAT_MULTIPLIER * traitReliance.getModifiedValue());
        return sum; // Return the sum
    }
}
