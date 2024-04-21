package com.dt180g.project.gear;

import java.util.Map;

import com.dt180g.project.stats.BaseStat;
import com.dt180g.project.stats.StatsManager;
import com.dt180g.project.stats.Trait;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.Randomizer;

/**
 * Class for the Armor gear.
 *
 * @author Frank Curwen
 */
public class Armor extends BaseGear {
    private final int protection; // The protection value of the armor
    private final String material; // The material the armor is made of
    private final Trait trait; // The trait of the armor

    /**
     * Constructor for the Armor class. Sets the armor's type, name, restriction, protection, material, and trait.
     *
     * @param mappedValues The mapped values for the armor
     */
    public Armor(Map<String, String> mappedValues) {
        // Set the armor's type, name, and restriction
        super(mappedValues.get("type"), mappedValues.get("name"), mappedValues.get("restriction"));
        this.protection = Integer.parseInt(mappedValues.get("protection")); // Set the armor's protection value
        this.material = mappedValues.get("material"); // Set the armor's material

        String traitName = StatsManager.INSTANCE.getRandomTraitName(); // Get a random trait name
        this.trait = new Trait(traitName, Randomizer.INSTANCE.getRandomValue(
                AppConfig.ARMOR_STAT_VALUE_UPPER_BOUND - 1) + 1); // Set the armor's trait
    }

    /**
     * Get the protection value of the armor.
     *
     * @return The protection value of the armor
     */
    public int getProtection() {
        return protection;
    }

    /**
     * Get the material the armor is made of.
     *
     * @return The material the armor is made of
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Override the getStat method from the BaseGear class.
     *
     * @return The trait of the armor
     */
    @Override
    public BaseStat getStat() {
        return trait;
    }

    /**
     * Override the toString method from the BaseGear class.
     *
     * @return The string representation of the armor
     */
    @Override
    public String toString() {
        return super.toString() + " of " + getStat().getStatName();
    }
}
