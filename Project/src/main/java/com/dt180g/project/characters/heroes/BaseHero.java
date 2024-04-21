package com.dt180g.project.characters.heroes;

import java.util.List;

import com.dt180g.project.characters.BaseCharacter;
import com.dt180g.project.characters.CharacterEquipment;
import com.dt180g.project.characters.CharacterStats;
import com.dt180g.project.gear.GearManager;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.gear.Armor;
import com.dt180g.project.support.ActivityLogger;

/**
 * Base class for all heroes in the game. Contains methods for equipping the
 * hero with weapons and armor, and for executing the hero's turn.
 *
 * @author Frank Curwen
 */
public abstract class BaseHero extends BaseCharacter {
    private final String characterName; // Name of the hero

    /**
     * Constructor for the BaseHero class. Takes a character name and a list of
     * attributes as parameters.
     *
     * @param characterName Name of the hero
     * @param attributes List of attributes for the hero
     */
    protected BaseHero(String characterName, List<Integer> attributes) {
        super(new CharacterStats(attributes)); // Create a new CharacterStats object with the given attributes
        this.characterName = characterName; // Set the character name
    }

    /**
     * Method for equipping the hero with weapons and armor. Takes a character class
     * as a parameter.
     *
     * @param characterClass The class of the hero
     */
    protected void equipHero(Class<?> characterClass) {
        // Get all armor pieces that are allowed for the hero's class
        List<Armor> armorForClassRestriction = GearManager.INSTANCE.getAllArmorForRestriction(characterClass);

        while (getEquipment().amountOfEmptyWeaponSlots() != 0) { // While there are empty weapon slots
            // Add a random weapon to the character's equipment
            Weapon weapon1 = GearManager.INSTANCE.getRandomWeapon(characterClass);
            getEquipment().addWeapon(weapon1); // Add the weapon to the character's equipment
            if (!weapon1.isTwoHanded()) { // If the weapon is not two-handed
                // Add a random one-handed weapon to the character's equipment
                Weapon weapon2 = GearManager.INSTANCE.getRandomOneHandedWeapon(characterClass);
                getEquipment().addWeapon(weapon2); // Add the weapon to the character's equipment
            }
        }
        List<String> bodyParts = List.of(AppConfig.ARMOR_HEAD, AppConfig.ARMOR_CHEST, AppConfig.ARMOR_LEGS,
                AppConfig.ARMOR_HANDS, AppConfig.ARMOR_FEET); // Create a list of body parts

        while (getEquipment().amountOfEmptyArmorSlots() != 0) { // While there are empty armor slots
            for (String bodyPart : bodyParts) { // For each body part
                // Add a random armor piece to the character's equipment
                Armor armor = GearManager.INSTANCE.getRandomArmorOfType(bodyPart, characterClass);
                if (armorForClassRestriction.contains(armor)) { // If the armor is allowed for the hero's class
                    getEquipment().addArmorPiece(bodyPart, armor); // Add the armor piece to the character's equipment
                }
            }
        }
    }

    /**
     * Method for resetting the hero's stats.
     */
    public void resetHeroStats() {
        getCharacterStats().resetActionPoints(); // Reset the hero's action points
        getCharacterStats().resetHitPoints(); // Reset the hero's hit points
        getCharacterStats().resetEnergyLevel(); // Reset the hero's energy level
    }

    /**
     * Method for getting the name of the hero.
     *
     * @return Name of the hero
     */
    public String getCharacterName() {
        return characterName;
    }

    /**
     * Method for executing the hero's turn. Logs the turn information and executes
     * the hero's actions.
     */
    public void doTurn() {
        String turnInfo = "[HERO TURN] " + getCharacterName(); // Get the character name
        String turnInformation = getTurnInformation(turnInfo); // Get the turn information
        ActivityLogger.INSTANCE.logTurnInfo(turnInformation); // Log the turn information
        executeActions(true); // Execute the hero's actions
    }
}
