package com.dt180g.project.characters.enemies;

import java.util.List;

import com.dt180g.project.characters.BaseCharacter;
import com.dt180g.project.characters.CharacterEquipment;
import com.dt180g.project.characters.CharacterStats;
import com.dt180g.project.gear.GearManager;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.support.ActivityLogger;

/**
 * Base class for all enemies in the game. Contains methods for equipping the
 * enemy with weapons and armor, and for executing the enemy's turn.
 *
 * @author Frank Curwen
 */
public abstract class BaseEnemy extends BaseCharacter {
    private final String characterName; // Name of the enemy

    /**
     * Constructor for the BaseEnemy class. Takes a character name and a list of
     * attributes as parameters.
     *
     * @param characterName Name of the enemy
     * @param attributes List of attributes for the enemy
     */
    protected BaseEnemy(String characterName, List<Integer> attributes) {
        super(new CharacterStats(attributes));  // Create a new CharacterStats object with the given attributes
        this.characterName = characterName; // Set the character name
    }

    /**
     * Method for equipping the enemy with weapons and armor. Takes a list of
     * allowed weapon types as a parameter.
     *
     * @param allowedWeaponTypes List of allowed weapon types
     */
    protected void equipEnemy(List<String> allowedWeaponTypes) {
        CharacterEquipment characterEquipment = new CharacterEquipment(); // Create a new CharacterEquipment object
        while (characterEquipment.amountOfEmptyWeaponSlots() > 0) { // While there are empty weapon slots
            // Add a random weapon to the character's equipment
            Weapon weapon1 = GearManager.INSTANCE.getRandomWeapon(allowedWeaponTypes);
            characterEquipment.addWeapon(weapon1); // Add the weapon to the character's equipment
            if (!weapon1.isTwoHanded()) { // If the weapon is not two-handed
                // Add a random one-handed weapon to the character's equipment
                Weapon weapon2 = GearManager.INSTANCE.getRandomOneHandedWeapon(allowedWeaponTypes);
                characterEquipment.addWeapon(weapon2); // Add the weapon to the character's equipment
            }
        }
    }

    /**
     * Method for getting the name of the enemy.
     *
     * @return Name of the enemy
     */
    public String getCharacterName() {
        return this.characterName;
    }

    /**
     * Method for executing the enemy's turn. Logs the turn information and executes
     * the enemy's actions.
     */
    public void doTurn() {
        String info = getCharacterName(); // Get the character name
        String information = getTurnInformation(info); // Get the turn information
        ActivityLogger.INSTANCE.logTurnInfo(information); // Log the turn information
        executeActions(false); // Execute the enemy's actions
    }
}
