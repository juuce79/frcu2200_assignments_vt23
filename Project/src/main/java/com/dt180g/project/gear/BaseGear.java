package com.dt180g.project.gear;

import java.util.ArrayList;
import java.util.List;

import com.dt180g.project.characters.enemies.LichLord;
import com.dt180g.project.characters.enemies.SkeletonArcher;
import com.dt180g.project.characters.enemies.SkeletonMage;
import com.dt180g.project.characters.enemies.SkeletonWarrior;
import com.dt180g.project.characters.heroes.*;
import com.dt180g.project.stats.BaseStat;

/**
 * The BaseGear class is the abstract class for all gear types.
 *
 * @author Frank Curwen
 */
public abstract class BaseGear {
    private final String type; // The type of gear
    private final String gearName; // The name of the gear
    private final List<Class<?>> classRestrictions; // The class restrictions for the gear

    /**
     * Constructor for the BaseGear class. Sets the gear's type, name, and class restrictions.
     *
     * @param type The type of gear
     * @param gearName The name of the gear
     * @param classRestrictions The class restrictions for the gear
     */
    protected BaseGear(String type, String gearName, String classRestrictions) {
        this.type = type; // Set the gear's type
        this.gearName = gearName; // Set the gear's name
        this.classRestrictions = new ArrayList<>(); // Create a new list of class restrictions

        String[] restrictions = classRestrictions.split(","); // Split the class restrictions into an array
        for (String restriction : restrictions) { // Iterate through the class restrictions
            try { // Try to add the class restriction
                Class<?> characterClass = BaseHero.class; // Set the character class to the BaseHero class
                String packageName = characterClass.getPackageName(); // Get the package name of the character class
                // Get the class restriction by combining the package name and the restriction
                Class<?> restrictedClass = Class.forName(packageName + "." + restriction);
                this.classRestrictions.add(restrictedClass); // Add the class restriction to the list
            } catch (ClassNotFoundException e) { // Catch the class not found exception
                e.printStackTrace(); // Print the stack trace
            }
        }
    }

    /**
     * Get the type of gear.
     *
     * @return The type of gear
     */
    public String getType() {
        return type;
    }

    /**
     * Get the class restrictions for the gear.
     *
     * @return The class restrictions for the gear
     */
    public List<Class<?>> getClassRestrictions() {
        return classRestrictions;
    }

    /**
     * Check if the gear has class restrictions.
     *
     * @return True if the gear has class restrictions, false otherwise
     */
    public boolean checkClassRestriction(Class<?> characterClass) {
        return getClassRestrictions().contains(characterClass);
    }

    /**
     * Abstract method to get the stat of the gear.
     *
     * @return The stat of the gear
     */
    public abstract BaseStat getStat();

    /**
     * Override the toString method to return the name of the gear.
     *
     * @return The name of the gear
     */
    @Override
    public String toString() {
        return gearName;
    }
}
