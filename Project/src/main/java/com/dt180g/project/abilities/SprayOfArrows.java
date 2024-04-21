package com.dt180g.project.abilities;

import com.dt180g.project.support.AppConfig;

/**
 * SprayOfArrows is a non-magical ability that targets a group of enemies.
 * It has the highest action point cost and a high energy cost.
 *
 * @author Frank Curwen
 */
public class SprayOfArrows extends BaseAbility {
    /**
     * The constructor for the SprayOfArrows ability which sets the cost in action points and energy to use the ability.
     */
    public SprayOfArrows() {
        super(AppConfig.HIGHEST_AP_COST, AppConfig.HIGH_ENERGY_COST);
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
     * @return the amount of targets for the ability which is a group of enemies.
     */
    @Override
    public int getAmountOfTargets() {
        return AppConfig.ABILITY_GROUP_TARGET;
    }

    /**
     * Override of the toString method which returns the ability name.
     *
     * @return a string representing the ability name.
     */
    @Override
    public String toString() {
        return AppConfig.ABILITY_SPRAY_OF_ARROWS;
    }
}
