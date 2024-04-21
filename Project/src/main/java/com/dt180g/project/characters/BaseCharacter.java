package com.dt180g.project.characters;

import com.dt180g.project.abilities.BaseAbility;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.Randomizer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * BaseCharacter is an abstract class that represents the base for all characters in the game.
 * It contains the character's stats, equipment, abilities and methods for executing actions.
 * It also contains methods for registering damage and healing, resetting the character's stats
 * and a method for determining which actions to perform.
 *
 * @author Frank Curwen
 */
public abstract class BaseCharacter {
    private final CharacterStats characterStats; // Character's stats
    private final CharacterEquipment equipment; // Character's equipment
    private List<BaseAbility> abilities; // Character's abilities

    /**
     * Constructor for BaseCharacter.
     *
     * @param characterStats The character's stats.
     */
    protected BaseCharacter(CharacterStats characterStats) {
        this.characterStats = characterStats; // Set the character's stats
        this.equipment = new CharacterEquipment(); // Create a new CharacterEquipment object
        this.abilities = new ArrayList<>(); // Create a new ArrayList for the character's abilities
    }

    /**
     * Method for adding abilities to the character from a list of abilities.
     *
     * @param addAbilities The abilities to add to the character.
     */
    protected void addAbilities(List<BaseAbility> addAbilities) {
        abilities = addAbilities;
    }

    /**
     * Method for getting the character's turn information.
     *
     * @param turnInfo The turn information to display.
     * @return The character's turn information.
     */
    protected String getTurnInformation(String turnInfo) {
        return turnInfo + " | " + getActionPoints() + " AP | " + getHitPoints() + " HP | " +
                getEnergyLevel() + " " + AppConfig.TRAIT_ENERGY;
    }

    /**
     * Method for executing the character's actions.
     *
     * @param targetEnemies Whether the character should target enemies or not.
     */
    protected void executeActions(boolean targetEnemies) {
        Deque<BaseAbility> actions = determineActions(); // Determine the actions to perform

        for (BaseAbility action : actions) { // For each action in the actions
            // If the character has enough action points and energy to perform the action
            if (characterStats.getCurrentActionPoints() >= action.getActionPointCost() &&
                    characterStats.getCurrentEnergyLevel() >= action.getEnergyCost()) {
                int actionValue; // The value of the action
                boolean successfulAction; // Whether the action was successful or not

                if (action.isHeal()) { // If the action is a healing action
                    // The action value is the character's healing power
                    actionValue = characterStats.getStatValue(AppConfig.TRAIT_ATTACK_RATE) +
                            characterStats.getStatValue(AppConfig.COMBAT_STAT_HEALING_POWER);
                    // The action is successful if the action is executed with the action value
                    successfulAction = action.execute(-actionValue, !targetEnemies);
                } else if (action.isMagic()) { // If the action is a magic action
                    // The action value is the character's attack rate and magic power
                    actionValue = characterStats.getStatValue(AppConfig.TRAIT_ATTACK_RATE) +
                            characterStats.getStatValue(AppConfig.COMBAT_STAT_MAGIC_POWER);
                    // The action is successful if the action is executed with the action value
                    successfulAction = action.execute(actionValue, targetEnemies);
                } else { // If the action is a physical action
                    // The action value is the character's total weapon damage and attack rate
                    actionValue = equipment.getTotalWeaponDamage() +
                            characterStats.getStatValue(AppConfig.TRAIT_ATTACK_RATE);
                    // The action is successful if the action is executed with the action value
                    successfulAction = action.execute(actionValue, targetEnemies);
                }

                if (successfulAction) { // If the action was successful
                    characterStats.adjustHitPoints(-action.getActionPointCost()); // Adjust the character's action points
                    characterStats.adjustEnergyLevel(-action.getEnergyCost()); // Adjust the character's energy level
                }
            }
        }
    }

    /**
     * Method for determining the actions to perform.
     *
     * @return The actions to perform.
     */
    private Deque<BaseAbility> determineActions() {
        Deque<BaseAbility> abilitiesToPerform = new ArrayDeque<>(); // Create a new ArrayDeque for the abilities to perform
        int numberOfAbilities = AppConfig.ACTIONS_PER_TURN; // The number of abilities to perform

        for (int i = 0; i < numberOfAbilities; i++) { // For each ability to perform
            int index = Randomizer.INSTANCE.getRandomValue(abilities.size() - 1); // Get a random index
            abilitiesToPerform.add(abilities.get(index)); // Add the ability at the index to the abilities to perform
        }
        return abilitiesToPerform; // Return the abilities to perform
    }

    /**
     * Method for registering damage to the character.
     *
     * @param damage The damage to register.
     * @param isMagical Whether the damage is magical or not.
     * @return The damage mitigation and actual damage.
     */
    public List<Integer> registerDamage(int damage, boolean isMagical) {
        int defenseRate = characterStats.getStatValue(AppConfig.TRAIT_DEFENSE_RATE); // The character's defense rate
        int damageMitigation; // The damage mitigation
        int actualDamage; // The actual damage

        if (isMagical) { // If the damage is magical
            damageMitigation = defenseRate; // The damage mitigation is the character's defense rate
        } else { // If the damage is physical
            int armorProtection = equipment.getTotalArmorProtection(); // The character's total armor protection
            // The damage mitigation is the character's defense rate and armor protection
            damageMitigation = defenseRate + armorProtection;
        }
        actualDamage = damage - damageMitigation; // The actual damage is the damage minus the damage mitigation
        characterStats.adjustHitPoints(-actualDamage); // Adjust the character's hit points by the actual damage

        return List.of(damageMitigation, actualDamage); // Return the damage mitigation and actual damage
    }

    /**
     * Method for registering healing to the character.
     *
     * @param healing The healing to register.
     * @return The character's current hit points.
     */
    public int registerHealing(int healing) {
        characterStats.adjustHitPoints(healing); // Adjust the character's hit points by the healing
        // If the character's current hit points are greater than the character's total hit points
        if (characterStats.getCurrentHitPoints() > characterStats.getTotalHitPoints()) {
            characterStats.resetHitPoints(); // Reset the character's hit points
        }
        return characterStats.getCurrentHitPoints(); // Return the character's current hit points
    }

    /**
     * Method for resetting the character's stats.
     */
    public void roundReset() {
        characterStats.adjustActionPoints(AppConfig.ROUND_RESET_AP); // Adjust the character's action points
        characterStats.adjustEnergyLevel(AppConfig.ROUND_RESET_ENERGY); // Adjust the character's energy level
    }

    /**
     * Method for performing the character's turn.
     */
    public abstract void doTurn();

    /**
     * Method to display the character's information.
     *
     * @return The character's information.
     */
    @Override
    public String toString() {
        int characterLength = getCharacterName().length(); // The character's name length
        StringBuilder sb = new StringBuilder(); // Create a new StringBuilder
        characterLength += 16; // Add 16 to the character's name length
        sb.append("*".repeat(Math.max(0, characterLength))); // Append the character's name length to the StringBuilder
        // Create a header with the character's name
        String header = String.format("%s\n\t%s\t\n%s", sb, getCharacterName().toUpperCase(), sb);
        // Return the header, character stats and equipment of the character
        return AppConfig.ANSI_WHITE + header +
                "\n" + AppConfig.ANSI_RESET + AppConfig.ANSI_BLUE + "STATISTICS" + AppConfig.ANSI_RESET +
                "\n" + characterStats.toString() + "\n" + AppConfig.ANSI_BLUE + "EQUIPMENT" + AppConfig.ANSI_RESET +
                "\n" + getEquipment().toString();
    }

    /**
     * Method for getting the character's name.
     *
     * @return The character's name.
     */
    public abstract String getCharacterName();

    /**
     * Method to get the character's stats.
     *
     * @return The character's stats.
     */
    public CharacterStats getCharacterStats() {
        return characterStats;
    }

    /**
     * Method to get the character's equipment.
     *
     * @return The character's equipment.
     */
    public CharacterEquipment getEquipment() {
        return equipment;
    }

    /**
     * Method to get the character's action points.
     *
     * @return The character's action points.
     */
    public int getActionPoints() {
        return characterStats.getCurrentActionPoints();
    }

    /**
     * Method to get the character's hit points.
     *
     * @return The character's hit points.
     */
    public int getHitPoints() {
        return characterStats.getCurrentHitPoints();
    }

    /**
     * Method to get the character's energy level.
     *
     * @return The character's energy level.
     */
    public int getEnergyLevel() {
        return characterStats.getCurrentEnergyLevel();
    }

    /**
     * Method to get the character's abilities.
     *
     * @return The character's abilities.
     */
    public List<BaseAbility> getAbilities() {
        return abilities;
    }

    /**
     * Method to check if the character is dead.
     *
     * @return Whether the character is dead or not.
     */
    public boolean isDead() {
        return characterStats.getStatValue(AppConfig.TRAIT_VITALITY) <= 0;
    }
}
