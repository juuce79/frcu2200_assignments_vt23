package com.dt180g.project.abilities;

import com.dt180g.project.support.AppConfig;

/**
 * WeaponAttack is a non-magical ability that targets a single enemy.
 * It has the lowest cost in action points and no energy cost.
 *
 * @author Frank Curwen
 */
public class WeaponAttack extends BaseAbility {
    /**
     * The constructor for the WeaponAttack ability which sets the cost in action points to use the ability.
     */
    public WeaponAttack() {
        super(AppConfig.LOWEST_AP_COST, 0);
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
        return AppConfig.ABILITY_WEAPON_ATTACK;
    }
}
