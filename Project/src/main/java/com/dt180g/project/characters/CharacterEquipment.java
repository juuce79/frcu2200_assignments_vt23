package com.dt180g.project.characters;

import com.dt180g.project.gear.Armor;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.IOHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to handle the equipment of a character. It contains a list of weapons
 * and a map of armor pieces. It also contains methods to calculate the total
 * damage and protection of the character, as well as the amount of empty slots
 * for weapons and armor.
 *
 * @author Frank Curwen
 */
public class CharacterEquipment {
    private final List<Weapon> weapons = new ArrayList<>(); // A character can wield two one-handed weapons or one two-handed weapon
    private final Map<String, Armor> armorPieces = new HashMap<>(); // A character can wear one armor piece per body part

    /**
     * Method to get the list of weapons
     *
     * @return List of weapons
     */
    public List<Weapon> getWeapons() {
        return weapons;
    }

    /**
     * Method to get the list of armor pieces
     *
     * @return List of armor pieces
     */
    public List<Armor> getArmorPieces() {
        return new ArrayList<>(armorPieces.values());
    }

    /**
     * Method to get the total damage of the character
     *
     * @return Total damage
     */
    public int getTotalWeaponDamage() {
        int totalDamage = 0; // Total damage of all weapons
        for (Weapon weapon : weapons) { // Loop through all weapons
            totalDamage += weapon.getDamage(); // Add the damage of the weapon to the total damage
        }
        return totalDamage; // Return the total damage
    }

    /**
     * Method to get the total armor protection of the character
     *
     * @return Total protection
     */
    public int getTotalArmorProtection() {
        int totalProtection = 0; // Total protection of all armor pieces
        for (Armor armor : armorPieces.values()) { // Loop through all armor pieces
            totalProtection += armor.getProtection(); // Add the protection of the armor piece to the total protection
        }
        return totalProtection; // Return the total protection
    }

    /**
     * Method to get amount of empty weapon slots
     *
     * @return Amount of empty weapon slots
     */
    public int amountOfEmptyWeaponSlots() {
        int emptyWeaponSlots = 2; // A character can wield two one-handed weapons or one two-handed weapon
        for (Weapon weapon : weapons) { // Loop through all weapons
            if (weapon.isTwoHanded()) { // If the weapon is two-handed
                emptyWeaponSlots -= 2; // Reduce the amount of empty weapon slots by 2
            } else { // If the weapon is one-handed
                emptyWeaponSlots -= 1; // Reduce the amount of empty weapon slots by 1
            }
        }
        return emptyWeaponSlots; // Return the amount of empty weapon slots
    }

    /**
     * Method to get amount of empty armor slots
     *
     * @return Amount of empty armor slots
     */
    public int amountOfEmptyArmorSlots() {
        return 5 - armorPieces.size();
    }

    /**
     * Method to add a weapon to the character
     *
     * @param weaponToAdd Weapon to add
     * @return True if the weapon was added, false if not
     */
    public boolean addWeapon(Weapon weaponToAdd) {
        if (weaponToAdd.isTwoHanded() && weapons.isEmpty()) { // If the weapon is two-handed and the character is not wielding any weapons
            weapons.add(weaponToAdd); // Add the weapon to the character
            return true; // Return true to indicate that the weapon was added
        } else if (!weaponToAdd.isTwoHanded() && amountOfEmptyWeaponSlots() > 0) { // If the weapon is one-handed and there are empty weapon slots
            weapons.add(weaponToAdd); // Add the weapon to the character
            return true; // Return true to indicate that the weapon was added
        } else { // If the weapon was not added
            return false; // Return false to indicate that the weapon was not added
        }
    }

    /**
     * Method to add an armor piece to the character
     *
     * @param bodyPart Body part to add the armor piece to (head, torso, arms, legs, feet)
     * @param armorPiece Armor piece to add
     * @return True if the armor piece was added, false if not
     */
    public boolean addArmorPiece(String bodyPart, Armor armorPiece) {
        if (!armorPieces.containsKey(bodyPart) && amountOfEmptyArmorSlots() > 0) { // If the body part is not already covered and there are empty armor slots
            armorPieces.put(bodyPart, armorPiece); // Add the armor piece to the character
            return true; // Return true to indicate that the armor piece was added
        } else { // If the armor piece was not added
            return false; // Return false to indicate that the armor piece was not added
        }
    }

    /**
     * Method to display the character's equipment as a table
     *
     * @return String representation of the character's equipment
     */
    @Override
    public String toString() {
        List<List<String>> tableData = new ArrayList<>(); // List to hold the data for the table
        String delimiter = "|"; // Delimiter to separate the columns in the table

        for (Weapon weapon : weapons) { // Loop through all weapons
            List<String> weaponData = new ArrayList<>(); // List to hold the data for the weapon
            weaponData.add("[" + weapon.getType() + "]"); // Add the type of the weapon to the list
            weaponData.add(delimiter); // Add the delimiter to the list
            // Add the wield and material of the weapon to the list
            weaponData.add(AppConfig.ANSI_PURPLE + weapon.getWield() + AppConfig.ANSI_RESET);
            weaponData.add(delimiter); // Add the delimiter to the list
            weaponData.add(AppConfig.ANSI_RED + "Damage" + AppConfig.ANSI_RESET); // Add the damage label to the list
            // Add the damage of the weapon to the list
            weaponData.add(AppConfig.ANSI_GREEN + "+" + weapon.getDamage() + AppConfig.ANSI_RESET);
            weaponData.add(delimiter); // Add the delimiter to the list
            weaponData.add(AppConfig.ANSI_CYAN + weapon + AppConfig.ANSI_RESET); // Add the name of the weapon to the list
            // Add the base value of the weapon to the list
            weaponData.add(AppConfig.ANSI_YELLOW + "+" + weapon.getStat().getBaseValue() + AppConfig.ANSI_RESET);
            tableData.add(weaponData); // Add the weapon data to the table data
        }

        armorPieces.forEach((key, armor) -> { // Loop through all armor pieces
            List<String> armorData = new ArrayList<>(); // List to hold the data for the armor piece
            armorData.add("[" + armor.getType() + "]"); // Add the type of the armor piece to the list
            armorData.add(delimiter); // Add the delimiter to the list
            // Add the material of the armor piece to the list
            armorData.add(AppConfig.ANSI_PURPLE + armor.getMaterial() + AppConfig.ANSI_RESET);
            armorData.add(delimiter); // Add the delimiter to the list
            armorData.add(AppConfig.ANSI_RED + "Protection" + AppConfig.ANSI_RESET); // Add the protection label to the list
            // Add the protection of the armor piece to the list
            armorData.add(AppConfig.ANSI_GREEN + "+" + armor.getProtection() + AppConfig.ANSI_RESET);
            armorData.add(delimiter); // Add the delimiter to the list
            armorData.add(AppConfig.ANSI_CYAN + armor + AppConfig.ANSI_RESET); // Add the name of the armor piece to the list
            // Add the base value of the armor piece to the list
            armorData.add(AppConfig.ANSI_YELLOW + "+" + armor.getStat().getBaseValue() + AppConfig.ANSI_RESET);
            tableData.add(armorData); // Add the armor piece data to the table data
        });
        return IOHelper.formatAsTable(tableData); // Return the table data as a formatted table
    }
}
