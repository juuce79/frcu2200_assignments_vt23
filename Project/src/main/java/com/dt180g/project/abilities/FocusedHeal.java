package com.dt180g.project.abilities;

import com.dt180g.project.support.AppConfig;

/**
 * FocusedHeal is a magical ability that targets a single hero.
 * It has a medium cost in action points and a low energy cost.
 *
 * @author Frank Curwen
 */
public class FocusedHeal extends BaseAbility {
    private final String magicalPhrase; // The magical phrase that is used to cast the spell.

    /**
     * The constructor for the FocusedHeal ability which sets the cost in action points and energy to use the ability.
     * It also sets the magical phrase that is used to cast the spell.
     */
    public FocusedHeal() {
        super(AppConfig.MEDIUM_AP_COST, AppConfig.LOW_ENERGY_COST);
        this.magicalPhrase = AppConfig.MAGICAL_PHRASE_4;
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
        return true;
    }

    /**
     * Override of the getAmountOfTargets method which returns the amount of targets for the ability.
     *
     * @return the amount of targets for the ability which is a single hero.
     */
    @Override
    public int getAmountOfTargets() {
        return AppConfig.ABILITY_SINGLE_TARGET;
    }

    /**
     * Override of the toString method which returns a magical phrase and the ability name.
     *
     * @return a string representing the magical phrase and the ability name.
     */
    @Override
    public String toString() {
        return magicalPhrase + ": " + AppConfig.ABILITY_FOCUSED_HEAL;
    }
}
