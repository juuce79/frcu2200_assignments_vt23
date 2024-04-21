package com.dt180g.project.gear;

import com.dt180g.project.support.IOHelper;
import com.dt180g.project.support.Randomizer;

import java.util.*;

/**
 * The GearManager class is the manager for all gear types.
 *
 * @author Frank Curwen
 */
public class GearManager {
    public static final GearManager INSTANCE = new GearManager(); // The instance of the GearManager class
    private final Map<String, List<Weapon>> weapons; // The map of weapons
    private final Map<String, List<Armor>> armorPieces; // The map of armor pieces

    /**
     * Constructor for the GearManager class. Loads all mapped armor pieces and creates a new map of weapons.
     */
    private GearManager() {
        weapons = new HashMap<>(); // Create a new map of weapons
        armorPieces = loadAllMappedArmorPieces(); // Load all mapped armor pieces
    }

    /**
     * Load all mapped armor pieces from the gear_armor.json file.
     *
     * @return The map of armor pieces
     */
    private Map<String, List<Armor>> loadAllMappedArmorPieces() {
        String armorFilePath = "gear_armor.json"; // The file path for the armor file
        List<Map<String, String>> armorData = IOHelper.readFromFile(armorFilePath); // Read the armor file
        Map<String, List<Armor>> armorPieces = new HashMap<>(); // Create a new map of armor pieces

        for (Map<String, String> armorDetails : armorData) { // Iterate through the armor data
            String type = armorDetails.get("type"); // Get the type of armor
            Armor armor = new Armor(armorDetails); // Create a new armor object

            if (!armorPieces.containsKey(type)) { // If the armor pieces map does not contain the type
                armorPieces.put(type, new ArrayList<>()); // Add the type to the armor pieces map
            }
            armorPieces.get(type).add(armor); // Add the armor to the armor pieces map
        }

        return armorPieces; // Return the map of armor pieces
    }

    /**
     * Get all mapped armor pieces.
     *
     * @return The map of armor pieces
     */
    public Map<String, List<Armor>> getAllMappedArmorPieces() {
        return armorPieces;
    }

    /**
     * Get all mapped weapons.
     *
     * @return The map of weapons
     */
    public Map<String, List<Weapon>> getAllMappedWeapons() {
        String weaponFilePath = "gear_weapons.json"; // The file path for the weapons file
        List<Map<String, String>> weaponData = IOHelper.readFromFile(weaponFilePath); // Read the weapons file

        for (Map<String, String> weaponDetails : weaponData) { // Iterate through the weapon data
            String type = weaponDetails.get("type"); // Get the type of weapon
            Weapon weapon = new Weapon(weaponDetails); // Create a new weapon object

            if (!weapons.containsKey(type)) { // If the weapons map does not contain the type
                weapons.put(type, new ArrayList<>()); // Add the type to the weapons map
            }
            weapons.get(type).add(weapon); // Add the weapon to the weapons map
        }

        return weapons; // Return the map of weapons
    }

    /**
     * Get all weapons of a specific type.
     *
     * @param type The type of weapon
     * @return The list of weapons of the specified type
     */
    public List<Weapon> getWeaponsOfType(String type) {
        List<Weapon> weaponsOfType = new ArrayList<>(); // Create a new list of weapons of the specified type
        if (getAllMappedWeapons().containsKey(type)) { // If the weapons map contains the type
            weaponsOfType.addAll(getAllMappedWeapons().get(type)); // Add the weapons of the specified type to the list
        }
        return weaponsOfType; // Return the list of weapons of the specified type
    }

    /**
     * Get a random weapon for a specific character class.
     *
     * @param characterClass The character class
     * @return A random weapon for the character class
     */
    public Weapon getRandomWeapon(Class<?> characterClass) {
        List<Weapon> suitableWeapons = new ArrayList<>(); // Create a new list of suitable weapons
        for (List<Weapon> weaponList : getAllMappedWeapons().values()) { // Iterate through the weapons map
            for (Weapon weapon : weaponList) { // Iterate through the weapon list
                if (weapon.getClassRestrictions().contains(characterClass)) { // If the weapon has class restrictions
                    suitableWeapons.add(weapon); // Add the weapon to the list
                }
            }
        }

        // Get a random index for the list of suitable weapons
        int randomIndex = Randomizer.INSTANCE.getRandomValue(suitableWeapons.size() - 1);
        return suitableWeapons.get(randomIndex); // Return the random weapon
    }

    /**
     * Get a random weapon from a list of weapon types.
     *
     * @param weaponTypes The weapon types
     * @return A random weapon for the weapon types
     */
    public Weapon getRandomWeapon(List<String> weaponTypes) {
        List<Weapon> weapons = new ArrayList<>(); // Create a new list of weapons

        for (String type : weaponTypes) { // Iterate through the weapon types
            if (getAllMappedWeapons().containsKey(type)) { // If the weapons map contains the type
                weapons.addAll(getAllMappedWeapons().get(type)); // Add the weapons of the specified type to the list
            }
        }

        // Get a random index for the list of weapons
        int randomIndex = Randomizer.INSTANCE.getRandomValue(weapons.size() - 1);
        return weapons.get(randomIndex); // Return the random weapon
    }

    /**
     * Get a random one-handed weapon for a specific character class.
     *
     * @param characterClass The character class
     * @return A random one-handed weapon for the character class
     */
    public Weapon getRandomOneHandedWeapon(Class<?> characterClass) {
        List<Weapon> suitableOneHandedWeapons = new ArrayList<>(); // Create a new list of suitable one-handed weapons

        for (List<Weapon> weaponList : getAllMappedWeapons().values()) { // Iterate through the weapons map
            for (Weapon weapon : weaponList) { // Iterate through the weapon list
                // If the weapon is one-handed and has class restrictions
                if ((weapon.getWield().equals("One Handed Melee") || weapon.getWield().equals("One Handed Ranged")) &&
                        weapon.getClassRestrictions().contains(characterClass)) {
                    suitableOneHandedWeapons.add(weapon); // Add the weapon to the list
                }
            }
        }

        // Get a random index for the list of suitable one-handed weapons
        int randomIndex = Randomizer.INSTANCE.getRandomValue(suitableOneHandedWeapons.size() - 1);
        return suitableOneHandedWeapons.get(randomIndex); // Return the random one-handed weapon
    }

    /**
     * Get a random one-handed weapon from a list of weapon types.
     *
     * @param weaponTypes The weapon types
     * @return A random one-handed weapon for the weapon types
     */
    public Weapon getRandomOneHandedWeapon(List<String> weaponTypes) {
        List<Weapon> oneHandedWeapons = new ArrayList<>(); // Create a new list of one-handed weapons
        for (String type : weaponTypes) { // Iterate through the weapon types
            if (getAllMappedWeapons().containsKey(type)) { // If the weapons map contains the type
                List<Weapon> weapons = getAllMappedWeapons().get(type); // Get the weapons of the specified type
                for (Weapon weapon : weapons) { // Iterate through the weapons
                    // If the weapon is one-handed
                    if (weapon.getWield().equals("One Handed Melee") || weapon.getWield().equals("One Handed Ranged")) {
                        oneHandedWeapons.add(weapon); // Add the weapon to the list
                    }
                }
            }
        }

        // Get a random index for the list of one-handed weapons
        int randomIndex = Randomizer.INSTANCE.getRandomValue(oneHandedWeapons.size() - 1);
        return oneHandedWeapons.get(randomIndex); // Return the random one-handed weapon
    }

    /**
     * Get all armor for a specific character class.
     *
     * @param characterClass The character class
     * @return A list of armor for the character class
     */
    public List<Armor> getAllArmorForRestriction(Class<?> characterClass) {

        List<Armor> suitableArmor = new ArrayList<>(); // Create a new list of suitable armor
        Map<String, List<Armor>> allMappedArmorPieces = getAllMappedArmorPieces(); // Get all mapped armor pieces

        for (List<Armor> armorList : allMappedArmorPieces.values()) { // Iterate through the armor pieces map
            for (Armor armor : armorList) { // Iterate through the armor list
                if (armor.getClassRestrictions().contains(characterClass)) { // If the armor has class restrictions
                    suitableArmor.add(armor); // Add the armor to the list
                }
            }
        }

        return suitableArmor; // Return the list of suitable armor
    }

    /**
     * Get a random armor of a specific type for a specific character class.
     *
     * @param type The type of armor
     * @param characterClass The character class
     * @return A random armor of the specified type for the character class
     */
    public Armor getRandomArmorOfType(String type, Class<?> characterClass) {
        List<Armor> suitableArmor = new ArrayList<>(); // Create a new list of suitable armor
        Map<String, List<Armor>> allMappedArmorPieces = getAllMappedArmorPieces(); // Get all mapped armor pieces

        if (allMappedArmorPieces.containsKey(type)) { // If the armor pieces map contains the type
            List<Armor> allMappedArmor = allMappedArmorPieces.get(type); // Get the armor of the specified type
            for (Armor armor : allMappedArmor) { // Iterate through the armor
                if (armor.getClassRestrictions().contains(characterClass)) { // If the armor has class restrictions
                    suitableArmor.add(armor); // Add the armor to the list
                }
            }
        }

        // Get a random index for the list of suitable armor
        int randomIndex = Randomizer.INSTANCE.getRandomValue(suitableArmor.size() - 1);
        return suitableArmor.get(randomIndex); // Return the random armor
    }
}
