package com.dt180g.project.abilities;

import com.dt180g.project.support.AppConfig;

/**
 * ElementalBlast is a magical ability that targets a group of enemies.
 * It has the highest AP cost, and it has a high energy cost.
 *
 * @author Frank Curwen
 */
public class ElementalBlast extends BaseAbility {
    private final String magicalPhrase; // The magical phrase that is used to cast the spell.
    private final String element; // The element of the blast.

    /**
     * Constructor for the ElementalBlast ability which sets the cost in action points and energy to use the ability.
     * It also sets the magical phrase and the element of the blast.
     *
     * @param element the element of the blast.
     */
    public ElementalBlast(String element) {
        super(AppConfig.HIGHEST_AP_COST, AppConfig.HIGH_ENERGY_COST);
        this.magicalPhrase = AppConfig.MAGICAL_PHRASE_1;
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
     * @return true because the ability heals.
     */
    @Override
    public boolean isHeal() {
        return false;
    }

    /**
     * Override of the getAmountOfTargets method which returns the amount of targets for the ability.
     *
     * @return the amount of targets for the ability which is a group of enemies.
     */
    @Override
    public int getAmountOfTargets() {
        return AppConfig.ABILITY_GROUP_TARGET;
    }

    /**
     * Override of the toString method which returns a magical phrase and the element of the blast.
     *
     * @return a string representing a magical phrase and the element of the blast.
     */
    @Override
    public String toString() {
        return magicalPhrase + ": " + element + " " + AppConfig.ABILITY_ELEMENTAL_BLAST;
    }
}
