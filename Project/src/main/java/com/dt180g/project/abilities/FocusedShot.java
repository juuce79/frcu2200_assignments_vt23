package com.dt180g.project.abilities;

import com.dt180g.project.support.AppConfig;

/**
 * FocusedShot is a non-magical ability that targets a single enemy.
 * It has a medium cost in action points and a low energy cost.
 *
 * @author Frank Curwen
 */
public class FocusedShot extends BaseAbility {
    /**
     * The constructor for the FocusedShot ability which sets the cost in action points and energy to use the ability.
     */
    public FocusedShot() {
        super(AppConfig.MEDIUM_AP_COST, AppConfig.LOW_ENERGY_COST);
    }

    /**
     * Override of the isMagic method which decides if the ability is magical or not.
     *
     * @return false because the ability is not magical.
     */
    @Override
    public boolean isMagic() {
        return false;
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
     * @return the amount of targets which is a single enemy.
     */
    @Override
    public int getAmountOfTargets() {
        return AppConfig.ABILITY_SINGLE_TARGET;
    }

    /**
     * Override of the toString method which returns the ability name.
     *
     * @return a string representing the ability name.
     */
    @Override
    public String toString() {
        return AppConfig.ABILITY_FOCUSED_SHOT;
    }
}
