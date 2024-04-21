package com.dt180g.project.abilities;

import com.dt180g.project.GameEngine;
import com.dt180g.project.support.AppConfig;

/**
 * Base class for all abilities. Contains the basic information for all abilities.
 *
 * @author Frank Curwen
 */
public abstract class BaseAbility {
    private final int actionPointCost; // The cost in action points to use the ability.
    private final int energyCost; // The cost in energy to use the ability.

    /**
     * Constructor for BaseAbility.
     *
     * @param actionPointCost the cost in action points to use the ability.
     * @param energyCost the cost in energy to use the ability.
     */
    protected BaseAbility(int actionPointCost, int energyCost) {
        this.actionPointCost = actionPointCost; // The cost in action points to use the ability.
        this.energyCost = energyCost; // The cost in energy to use the ability.
    }

    /**
     * Perform the ability.
     *
     * @param info the information about the ability.
     * @param amountOfTargets the number of targets for the ability.
     * @param damage the amount of attack damage.
     * @param targetEnemies whether enemies or heroes should be targeted.
     * @return true if the ability was performed, false otherwise.
     */
    protected boolean performAbility(String info, int amountOfTargets, int damage, boolean targetEnemies) {
        // Create a log message for the ability.
        String logMessage =
                info + " (-" + getActionPointCost() + " AP, -" + getEnergyCost() + " " + AppConfig.TRAIT_ENERGY + ")";

        // Create an AbilityInfo object to pass to the game
        AbilityInfo abilityInfo =
                new AbilityInfo(logMessage, amountOfTargets, damage, targetEnemies, isMagic(), isHeal());

        return GameEngine.INSTANCE.characterAttack(abilityInfo); // Perform the ability.
    }

    /**
     * Accessor for action point cost.
     *
     * @return the action point cost.
     */
    public int getActionPointCost() {
        return actionPointCost;
    }

    /**
     * Accessor for energy cost.
     *
     * @return the energy cost.
     */
    public int getEnergyCost() {
        return energyCost;
    }

    /**
     * Whether the ability is magical.
     *
     * @return true if magic, false otherwise.
     */
    public abstract boolean isMagic();

    /**
     * Whether the ability regards healing.
     *
     * @return true if healing, false otherwise.
     */
    public abstract boolean isHeal();

    /**
     * Accessor for the amount of targets.
     *
     * @return the amount of targets.
     */
    public abstract int getAmountOfTargets();

    /**
     * Execute the ability.
     *
     * @param baseValue the base value of the ability.
     * @param targetEnemies whether enemies or heroes should be targeted.
     * @return true if the ability was performed, false otherwise.
     */
    public boolean execute(int baseValue, boolean targetEnemies) {
        String abilityInfo = this.toString(); // Get the ability information.
        int amountOfTargets = getAmountOfTargets(); // Get the amount of targets.
        int damage = baseValue * getAmountOfTargets(); // Get the damage value.
        return performAbility(abilityInfo, amountOfTargets, damage, targetEnemies); // Perform the ability.
    }
}
