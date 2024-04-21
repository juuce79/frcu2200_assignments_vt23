package com.dt180g.project.abilities;

import com.dt180g.project.support.AppConfig;

/**
 * ElementalBolt is a magical ability that targets a single enemy.
 * It has a medium cost in action points and a low energy cost.
 *
 * @author Frank Curwen
 */
public class ElementalBolt extends BaseAbility {
    private final String magicalPhrase; // The magical phrase that is used to cast the spell.
    private final String element; // The element of the bolt.

    /**
     * Constructor for the ElementalBolt ability which sets the cost in action points and energy to use the ability.
     * It also sets the magical phrase and the element of the bolt.
     *
     * @param element the element of the bolt.
     */
    public ElementalBolt(String element) {
        super(AppConfig.MEDIUM_AP_COST, AppConfig.LOW_ENERGY_COST);
        this.magicalPhrase = AppConfig.MAGICAL_PHRASE_2;
        this.element = element;
    }

    /**
     * Override of the isMagic method which decides if the ability is magical or not.
     *
     * @return true because the ability is magical.
     */
    @Override
    public boolean isMagic() {
        return true;
    }

    /**
     * Override of the isHeal method which decides if the ability heals or not.
     *
     * @return false because the ability does not heal.
     */
    @Override
    public boolean isHeal() {
        return false;
    }

    /**
     * Override of the getAmountOfTargets method which returns the amount of targets for the ability.
     *
     * @return the amount of targets for the ability which is a single enemy.
     */
    @Override
    public int getAmountOfTargets() {
        return AppConfig.ABILITY_SINGLE_TARGET;
    }

    /**
     * Override of the toString method which returns a magical phrase and the element of the bolt.
     *
     * @return a string representing a magical phrase and the element of the bolt.
     */
    @Override
    public String toString() {
        return magicalPhrase + ": " + element + " " + AppConfig.ABILITY_ELEMENTAL_BOLT;
    }
}
