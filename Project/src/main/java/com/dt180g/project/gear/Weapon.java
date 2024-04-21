package com.dt180g.project.gear;

import java.util.Map;

import com.dt180g.project.stats.Attribute;
import com.dt180g.project.stats.BaseStat;
import com.dt180g.project.stats.StatsManager;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.Randomizer;

/**
 * Class for the Weapon gear.
 *
 * @author Frank Curwen
 */
public class Weapon extends BaseGear {
    private final int damage; // The damage value of the weapon
    private final String wield; // The wield type of the weapon
    private final Attribute attribute; // The attribute of the weapon

    /**
     * Constructor for the Weapon class. Sets the weapon's type, name, restriction, damage, and wield type.
     *
     * @param mappedValues The mapped values for the weapon
     */
    public Weapon(Map<String, String> mappedValues) {
        // Set the weapon's type, name, and restriction
        super(mappedValues.get("type"), mappedValues.get("name"), mappedValues.get("restriction"));
        this.damage = Integer.parseInt(mappedValues.get("damage")); // Set the weapon's damage value
        this.wield = mappedValues.get("wield"); // Set the weapon's wield type
        String attributeName = StatsManager.INSTANCE.getRandomAttributeName(); // Get a random attribute name
        this.attribute = new Attribute(attributeName, Randomizer.INSTANCE.getRandomValue(
                AppConfig.WEAPON_ATTRIBUTE_VALUE_UPPER_BOUND)); // Set the weapon's attribute
    }

    /**
     * Get the damage value of the weapon.
     *
     * @return The damage value of the weapon
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Get the wield type of the weapon.
     *
     * @return The wield type of the weapon
     */
    public String getWield() {
        return wield;
    }

    /**
     * Override the getStat method from the BaseGear class.
     *
     * @return The attribute of the weapon
     */
    @Override
    public BaseStat getStat() {
        return attribute;
    }

    /**
     * Check if the weapon is two handed.
     *
     * @return True if the weapon is two handed, false otherwise
     */
    public boolean isTwoHanded() {
        return getWield().equals("Two Handed Melee") || getWield().equals("Two Handed Ranged");
    }

    /**
     * Override the toString method from the BaseGear class.
     *
     * @return The string representation of the weapon
     */
    @Override
    public String toString() {
        return super.toString() + " of " + getStat().getStatName();
    }
}
