package com.dt180g.project.abilities;

import com.dt180g.project.support.AppConfig;

/**
 * GroupHeal is a magical ability that targets a group of heroes.
 * It is a high-cost ability that heals a group of heroes.
 *
 * @author Frank Curwen
 */
public class GroupHeal extends BaseAbility {
    private final String magicalPhrase; // The magical phrase that is used to cast the spell.

    /**
     * The constructor for the GroupHeal ability which sets the cost in action points and energy to use the ability.
     * It also sets the magical phrase that is used to cast the spell.
     */
    public GroupHeal() {
        super(AppConfig.HIGHEST_AP_COST, AppConfig.HIGH_ENERGY_COST);
        this.magicalPhrase = AppConfig.MAGICAL_PHRASE_3;
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
     * @return the amount of targets which is a group of heroes.
     */
    @Override
    public int getAmountOfTargets() {
        return AppConfig.ABILITY_GROUP_TARGET;
    }

    /**
     * Override of the execute method which executes the ability.
     *
     * @param targetCount the amount of targets for the ability.
     * @param magicPhrase the magical phrase that is used to cast the spell.
     * @return true because the ability is executed.
     */
    @Override
    public boolean execute(int targetCount, boolean magicPhrase) {
        return true;
    }

    /**
     * Override of the toString method which returns a magical phrase and the ability name.
     *
     * @return a string representing a magical phrase and the ability name.
     */
    @Override
    public String toString() {
        return magicalPhrase + ": " + AppConfig.ABILITY_GROUP_HEAL;
    }
}
