# Final Project

## Environments & Tools

## Purpose

This project seeks to develop a role-playing game (RPG) simulation that showcases the application of object-oriented design (OOP) principles. The game centers on a group of heroes navigating progressively difficult dungeon levels, combating undead adversaries with the ultimate aim of facing and defeating the final boss. To effectively realize this simulation, the project has several concrete goals:

### Concrete Goals

#### Follow the class design and assignment instructions to achieve the following:

* **Implement a Turn-Based Control Structure:**
    * Utilize the  `GameRunner` class which acts as the main interface for player input and game simulation logic.
    * Utilize the `GameEngine` class to manage game state, rules, and mechanics.
    * Utilize `AppConfig` for flexible game configuration.
    * Structure gameplay around discrete rounds using character action points (AP).

* **Create a Customizable Character System:**
    * Build a class hierarchy with a `BaseCharacter` abstract class as the foundation.
    * Design distinct `Hero` and `Enemy` classes with subclasses for specialization (e.g., Warrior, Ranger, SkeletonWarrior, LichLord).
    * Manage characters' stats, traits, and abilities using `StatsManager`, `BaseStat`, `Attribute`, `Trait`, and `BaseAbility` classes.
    * Implement an equipment system (*Gear* classes) with JSON data loading and randomization.

* **Develop a Menu System:**
    * Implement a main menu offering respite, access to character stats, and the ability to continue or exit gameplay.

* **Construct a Robust Combat System**
    * Introduce combat stats that are calculated dynamically based on character attributes, traits, skills, and equipment.
    * Implement a diverse set of abilities for heroes and enemies (e.g., physical attacks, magic spells, healing).
    * Ensure actions are balanced based on a cost system using action points and energy resources.

* **Validate Implementation with Unit Testing**
    * Utilize the provided unit tests to ensure the integrity and stability of individual code components.

## Procedures

### Stats

#### StatsManager

The `StatsManager` class is a singleton class that manages the stats of the characters. It contains methods for getting random attribute and trait names, as well as lists of all the attribute, trait, and combat stat names. The `StatsManager` class is used to ensure that the attribute, trait, and combat stat names are consistent across the application.

The `StatsManager` constructor sets the attribute, trait, and combat stat names. The attribute names are set, from the `AppCongig` class, to `strength`, `dexterity`, `intelligence`, and `willpower`. The trait names are set to `vitality`, `energy`, `attack rate`, and `defense rate`. The combat stat names are set to `action points`, `physical power`, `magic power`, and `healing power`.

```java
private StatsManager() {
    // Set the attribute names
    this.attributeNames = new ArrayList<>();
    attributeNames.add(AppConfig.ATTRIBUTE_STRENGTH);
    attributeNames.add(AppConfig.ATTRIBUTE_DEXTERITY);
    attributeNames.add(AppConfig.ATTRIBUTE_INTELLIGENCE);
    attributeNames.add(AppConfig.ATTRIBUTE_WILLPOWER);

    // Set the trait names
    this.traitNames = new ArrayList<>();
    traitNames.add(AppConfig.TRAIT_VITALITY);
    traitNames.add(AppConfig.TRAIT_ENERGY);
    traitNames.add(AppConfig.TRAIT_ATTACK_RATE);
    traitNames.add(AppConfig.TRAIT_DEFENSE_RATE);

    // Set the combat stat names
    this.combatStatNames = new ArrayList<>();
    combatStatNames.add(AppConfig.COMBAT_STAT_ACTION_POINTS);
    combatStatNames.add(AppConfig.COMBAT_STAT_PHYSICAL_POWER);
    combatStatNames.add(AppConfig.COMBAT_STAT_MAGIC_POWER);
    combatStatNames.add(AppConfig.COMBAT_STAT_HEALING_POWER);
}
```

The class also contains methods for getting random attribute and trait names, as well as lists of all the attribute, trait, and combat stat names.

#### BaseStat

The `BaseStat` class is an abstract class for the stats of the characters. It contains methods for getting the name, base value, modified value, total modifier, static modifier, and dynamic modifier of the stat, as well as adjusting the static and dynamic modifiers, and resetting the dynamic modifier. The `BaseStat` class is used to ensure that the stats of the characters are consistent across the application.

The `BaseStat` constructor sets the stat's name and base value. The class also contains an overridden `toString` method to return the stat's name, modified value, and total modifier.

```java
public String toString() {
    List<List<String>> tableData = new ArrayList<>();
    List<String> rowData = new ArrayList<>();
    rowData.add(AppConfig.ANSI_GREEN + getStatName() + AppConfig.ANSI_RESET);
    rowData.add(AppConfig.ANSI_CYAN + getModifiedValue() + AppConfig.ANSI_RESET);
    rowData.add(AppConfig.ANSI_YELLOW + "+" + getTotalModifier() + AppConfig.ANSI_RESET);
    tableData.add(rowData);
    return IOHelper.formatAsTable(tableData);
}
```

The output of this then looks like this:

```

```

#### Attribute

The `Attribute` class is a class for the attributes of the characters. It extends the `BaseStat` class and contains a constructor for setting the attribute's name and base value. The `Attribute` class is used to ensure that the attributes of the characters are consistent across the application.

#### Trait

The `Trait` class is a class for the traits of the characters. It extends the `BaseStat` class and contains a constructor for setting the trait's name and base value. The `Trait` class is used to ensure that the traits of the characters are consistent across the application.

#### CombatStat

The `CombatStat` class is a class for the combat stats of the characters. It extends the `BaseStat` class and contains a constructor for setting the combat stat's name, attribute reliance, and trait reliance. The base value is calculated as a weighted sum of the attribute and trait reliance, where each reliance's modified value is multiplied by the combat stat multiplier. It overrides the `getBaseValue` method from the `BaseStat` class to get the base value of the combat stat.

```java
@Override
public int getBaseValue() {
    int sum = 0;
    sum += (int) Math.round(AppConfig.COMBAT_STAT_MULTIPLIER * attributeReliance.getModifiedValue());
    sum += (int) Math.round(AppConfig.COMBAT_STAT_MULTIPLIER * traitReliance.getModifiedValue());
    return sum;
}
```

The `CombatStat` class is used to ensure that the combat stats of the characters are consistent across the application.

### Gear

#### GearManager

The `GearManager` class is the manager for all gear types. It contains methods for getting all mapped armor pieces and weapons, weapons of a specific type, a random weapon for a specific character class, a random weapon from a list of weapon types, a random one-handed weapon for a specific character class, a random one-handed weapon from a list of weapon types, all armor for a specific character class, and a random armor of a specific type for a specific character class. The `GearManager` class is used to ensure that the gear of the characters is consistent across the application.

The `GearManager` constructor loads all mapped armor pieces and creates a new map of weapons. The `loadAllMappedArmorPieces` method loads all mapped armor pieces from the `gear_armor.json` file. The `getAllMappedArmorPieces` method gets all mapped armor pieces. The `getAllMappedWeapons` method gets all mapped weapons. The `getWeaponsOfType` method gets all weapons of a specific type.

There are two methods for getting a random weapon, `getRandomWeapon(Class<?> characterClass)` and `getRandomWeapon(List<String> weaponTypes)`, and this is because the first method gets a random weapon for a specific character class, which is specifically used by heroes in the `equipHero` method from the `BaseHero` class, while the second method gets a random weapon from a list of weapon types, which is specifically used by enemies in the `equipEnemy` method from the `BaseEnemy` class.

There are also two methods for getting a random one-handed weapon, `getRandomOneHandedWeapon(Class<?> characterClass)` and `getRandomOneHandedWeapon(List<String> weaponTypes)`, and this is again because the first method gets a random one-handed weapon for a specific character class, which is specifically used by heroes in the `equipHero` method from the `BaseHero` class, while the second method gets a random one-handed weapon from a list of weapon types, which is specifically used by enemies in the `equipEnemy` method from the `BaseEnemy` class.

The `getAllArmorForRestriction` method gets all armor for a specific character class, while the `getRandomArmorOfType` method gets a random armor of a specific type for a specific character class.

#### BaseGear

The `BaseGear` class is an abstract class for all gear types. It contains methods for getting the type of gear, class restrictions for the gear, checking if the gear has class restrictions, and getting the stat of the gear. The `BaseGear` class is used to ensure that the gear of the characters is consistent across the application.

The `BaseGear` constructor sets the gear's type, name, and class restrictions. The class restrictions are split into an array and added to a list of class restrictions. The `getStat` method is an abstract method to get the stat of the gear, and it is overridden by the `Weapon` and `Armor` classes.

#### Armor

The `Armor` class is a class for the armor gear. It extends the `BaseGear` class and contains a constructor for setting the armor's type, name, restriction, protection, material, and trait. The `getProtection` method gets the protection value of the armor, while the `getMaterial` method gets the material the armor is made of. The `getStat` method overrides the `getStat` method from the `BaseGear` class to get the trait of the armor, and the `toString` method overrides the `toString` method from the `BaseGear` class to return the string representation of the armor.

Regarding the constructor, this method is important because it sets the armor's trait to a random trait name and a random trait value using the `StatsManager`, `Randomizer` and `AppConfig` classes.

#### Weapon

The `Weapon` class is a class for the weapon gear. It extends the `BaseGear` class and contains a constructor for setting the weapon's type, name, restriction, damage, and wield type. The `getDamage` method gets the damage value of the weapon, while the `getWield` method gets the wield type of the weapon. The `getStat` method overrides the `getStat` method from the `BaseGear` class to get the attribute of the weapon, and the `isTwoHanded` method checks if the weapon is two handed. The `toString` method overrides the `toString` method from the `BaseGear` class to return the string representation of the weapon.

Regarding the constructor, this method is also important because like in th `Armor` class, it sets the weapon's attribute to a random attribute name and a random attribute value using the `StatsManager`, `Randomizer` and `AppConfig` classes.

### Abilities

#### BaseAbility

The `BaseAbility` class is an abstract class for all abilities. It contains methods for performing the ability, getting the action point cost, energy cost, whether the ability is magical, whether the ability regards healing, the amount of targets, and executing the ability. The `BaseAbility` class is used to ensure that the abilities of the characters are consistent across the application.

The `BaseAbility` constructor sets the action point cost and energy cost of the ability. The `performAbility` method performs the ability and creates a log message for the ability. The `execute` method executes the ability.

The `PerformAbility` method is important because it is used to perform the ability and create a log message for the ability. The `execute` method is also important because it is used to execute the ability.

#### The abilities

The abilities are all classes that extend the `BaseAbility` class and, in alphabetical order, are the following:

- `ElementalBlast` is a magical ability that targets a group of enemies. It has the highest AP cost, and it has a high energy cost.
- `ElementalBolt` is a magical ability that targets a single enemy. It has a medium cost in action points and a low energy cost.
- `FocusedHeal` is a magical ability that targets a single hero. It has a medium cost in action points and a low energy cost.
- `FocusedShot` is a non-magical ability that targets a single enemy. It has a medium cost in action points and a low energy cost.
- `GroupHeal` is a magical ability that targets a group of heroes. It is a high-cost ability that heals a group of heroes.
- `HeavyAttack` is a non-magical ability that targets a single enemy. It has a medium cost in action points and a low energy cost.
- `SprayOfArrows` is a non-magical ability that targets a group of enemies. It has the highest action point cost and a high energy cost.
- `WeaponAttack` is a non-magical ability that targets a single enemy. It has the lowest cost in action points and no energy cost.
- `Whirlwind` is a non-magical ability that targets a group of enemies. It has the highest cost in action points and a high energy cost.

The abilities are important because they are used to perform actions in the game, and they are used to ensure that the abilities of the characters are consistent across the application.

### Characters

#### BaseCharacter

The `BaseCharacter` class serves as the architectural foundation for all characters within the game. It houses essential attributes like stats, equipment, and abilities, along with the logic necessary for performing actions, managing damage and healing, and resetting its state for each round. Key methods within this class allow for ability assignment, turn information retrieval, action execution, and action determination.

At its core, the `BaseCharacter` constructor initializes a character's stats and establishes storage for their equipment and potential abilities. This structure, along with the carefully designed methods, provides a robust framework upon which diverse and engaging character types can be built.  Here's a brief overview of some of these methods:

* `addAbilities`: Lets you equip the character with a collection of abilities.
* `getTurnInformation`:  Compiles and formats information relevant to the character's current turn, such as action points and health.
* `executeActions`: Orchestrates the execution of the character's chosen actions during their turn, factoring in resource costs (like action points and energy) and success chances. This is achieved by iterating through the character's chosen actions and checking if they have enough resources (action points and energy) to perform them. If the resources are sufficient, it calculates the action value based on the action type and character stats. Finally, it calls the `execute` method of the `BaseAbility` to perform the action and potentially modify the target's health points.
* `determineActions`: Defines the logic behind how the character selects which actions to perform during their turn.
* `registerDamage`: Calculates and applies damage sustained by the character, considering their defense stats and armor protection.
* `registerHealing`: Processes incoming healing effects, ensuring the character's health does not exceed their maximum capacity.
* `roundReset`: Resets the character's action points and energy level in preparation for the next round.
* `doTurn`: The central method that coordinates the character's actions during their turn.
* `toString`: Generates a comprehensive string representation of the character, including their name, stats, equipment, and current health.

Finally, the `BaseCharacter` class implements several public accessor methods. These methods grant read-only access to critical character information, including the character's name, statistics, equipment, action points, health, energy level, abilities, and even its death status. By providing this controlled access, the `BaseCharacter` class ensures that other components of the game system can interact with and comprehend a character's state without jeopardizing the integrity of the character object itself. This promotes loose coupling and information hiding, which are fundamental principles of object-oriented programming that contribute to the maintainability and reusability of the code.

#### CharacterEquipment

The `CharacterEquipment` class provides a structured approach to managing a character's inventory of weapons and armor within the game system. It achieves this through the use of key data structures and methods.

The class leverages two primary data structures. First, a `List` named `weapons` stores equipped `Weapon` objects. This enforces the rule that characters can wield up to two one-handed weapons or a single two-handed weapon. Second, a `Map` named `armorPieces` uses `String` keys (representing body parts) and `Armor` object values. This structure allows for flexible assignment of armor pieces to specific locations on the character's body (e.g., "head", "torso").

Methods like `addWeapon()` and `addArmorPiece()` implement the logic for equipping items. These methods consider constraints based on a weapon's "handedness" and the availability of armor slots corresponding to body parts. For instance, the `addWeapon()` method will only allow a two-handed weapon to be equipped if the character is not already wielding any weapons.

The class also provides methods to calculate the character's overall combat effectiveness based on their equipment. The `getTotalWeaponDamage()` and `getTotalArmorProtection()` methods iterate over the equipped items, summing up their individual damage and protection attributes, respectively. This provides valuable information for game logic elsewhere in the system.

In addition to managing the inventory and calculating attributes, the `CharacterEquipment` class offers methods to determine a character's capacity to equip additional items. The `amountOfEmptyWeaponSlots()` and `amountOfEmptyArmorSlots()` methods serve this purpose. These methods offer a concise way to query the available space for new weapons or armor.

Finally, the `toString()` method facilitates the generation of a user-friendly presentation of the character's currently equipped gear. This method combines information about weapon/armor types, damage/protection values, and other relevant attributes, presented in a tabular format.

#### CharacterStats

The `CharacterStats` class serves as a blueprint for managing a character's statistics within the RPG system. It offers a comprehensive set of methods to model, calculate, and manipulate these statistics, providing a foundation for various character abilities and actions.

The class employs a central data member named `stats`. This member is a `Map` that associates names of statistics (e.g., "strength", "defense_rate") with their corresponding `BaseStat` objects. These `BaseStat` objects encapsulate the core attributes of a particular statistic, including its base value, static modifiers, and dynamic modifiers.

The class constructor initializes the `stats` map by processing a list of attribute values. It iterates through a list of attribute names and creates corresponding `Attribute` objects. Each `Attribute` object is assigned a base value calculated by multiplying the provided attribute value by a game-specific constant (`AppConfig.ATTRIBUTE_BASE_VALUE`).

Following this, the constructor creates `Trait` objects for core character traits like vitality and energy. Finally, it iterates over a predefined mapping between combat stats (e.g., "action_points") and their corresponding attributes. For each combat stat, a new `CombatStat` object is created. This `CombatStat` object links the combat stat to its relevant attribute (e.g., "dexterity" for "action_points") and the character's attack rate.

The `CharacterStats` class offers a rich set of public accessor methods for querying, modifying, and retrieving character statistics:

* `getStat(String name)`: Retrieves the `BaseStat` object associated with a given statistic name from the `stats` map.
* `getStatValue(String name)`: Returns the current modified value of a particular statistic.
* `getTotalActionPoints()`, `getTotalHitPoints()`, `getTotalEnergyLevel()`: Calculate and return the total values of these core attributes, factoring in base values and static modifiers.
* `getCurrentActionPoints()`, `getCurrentHitPoints()`, `getCurrentEnergyLevel()`: Return the current modified values of these attributes, reflecting any dynamic adjustments.
* `getDefenseRate()`, `getAttackRate()`: Fetch the current modified values of defense rate and attack rate.
* `getPhysicalPower()`, `getMagicPower()`, `getHealingPower()`: Return the character's physical power, magic power, and healing power, respectively.

These methods provide a versatile way to access and manipulate character statistics throughout the game logic.

The class also provides mutator methods to adjust character statistics dynamically. Methods like `adjustActionPoints(int amount)` and `adjustHitPoints(int amount)` allow for adjustments to the character's action points or hit points, respectively. These adjustments are tied to gameplay events.

Other mutator methods include `adjustStatStaticModifier(String name, int amount)` and `adjustStatDynamicModifier(String name, int amount)`. These methods allow for fine-grained control over a statistic's modifiers.

The class provides methods to reset specific stats to their base values. For instance, `resetActionPoints()` resets the character's action points to their unmodified value. This could be useful for simulating the beginning of a new round in a turn-based game.

The `toString()` method plays a role in generating a user-friendly representation of the character's statistics. It iterates over the `stats` map, formatting the base values, static modifiers, and dynamic modifiers of each stat into a table-like structure.

### Heroes

#### BaseHero

The `BaseHero` class serves as the foundation for all playable hero entities within the game. It inherits from the abstract `BaseCharacter` class, providing hero-specific attributes, functionalities, and gameplay mechanics.

**Data Members:**

* `String characterName`: Stores the name of the hero character, used for identification purposes.

**Constructor:**

* `BaseHero(String characterName, List<Integer> attributes)`: This constructor takes two arguments: `characterName` (a string) and `attributes` (a list of integers). It assigns the provided name to the `characterName` member. Then, it creates a new `CharacterStats` object using the provided `attributes` list and passes it to the base class constructor (`super`) to initialize the inherited character statistics.

**Methods:**

* `equipHero(Class<?> characterClass)`: Equips the hero with appropriate weapons and armor for their chosen class. It collaborates with the `GearManager` class to retrieve a selection of weapons and armor pieces.  The `GearManager` considers restrictions based on the hero's class (`characterClass`) to ensure the hero receives equipment they can proficiently use. For example, a Warrior might be outfitted with heavier armor and two-handed weapons like axes or swords, while a Ranger might receive lighter armor, prioritizing agility, and weapons suited for ranged combat, such as bows or crossbows.

* `resetHeroStats()`: This method resets the hero's stats (action points, hit points, and energy level).

* `getCharacterName()`: This method simply returns the value of the `characterName` data member, providing access to the hero's name.

* `doTurn()`: This method orchestrates the hero's turn during gameplay. Firstly it retrieves the hero's name using the `getCharacterName()` method. This name is used to identify the hero in turn information logs or messages displayed to the player. Next, the method constructs a string representation of the hero's turn information. This string includes details like the hero's name, actions performed during the turn, and any relevant outcomes (damage dealt, healing received, etc.). The constructed turn information is then logged using the `ActivityLogger` class. This allows the game to track the flow of the battle and display this information to the player in a combat log or history. Finally, the method calls the `executeActions(true)` method. This triggers the hero to perform the actions they have chosen for their current turn. The `executeActions` method interacts with the hero's ability list and stats to resolve the chosen actions and produce their effects on the game world (e.g., attacking enemies, casting spells).

#### Descendant Hero Classes

The descendant classes (`Warrior`, `Ranger`, `Cleric`, and `Wizard`) represent specialized archetypes of heroes that players can choose:

##### Warrior

* Focuses on melee combat with powerful weapons.
* In the constructor, equips itself using appropriate weapons (e.g., axes, swords) from the `GearManager`.
* Possesses abilities centered around physical attacks, like `WeaponAttack`, `HeavyAttack`, and area-of-effect abilities like `Whirlwind`.

##### Ranger

* Excels at ranged combat with bows and crossbows.
* The constructor equips the Ranger appropriately.
* Expected abilities include `WeaponAttack`, `FocusedShot` (for precision), and `SprayofArrows` for targeting multiple enemies.

##### Cleric

* A support/healer archetype.
* The constructor focuses on equipping staves/wands and appropriate armor.
* Abilities center around healing, such as `FocusedHeal` (to restore health to one target) and `GroupHeal` although it also contains the `WeaponAttack` ability.

##### Wizard

* The classic magic-user.
* The constructor ensures the Wizard is equipped with staves and wands.
* Abilities revolve around elemental magic, such as `ElementalBolt` (with variations for fire, ice, air) and more potent area-of-effect spells like `ElementalBlast`.

### Enemies

#### BaseEnemy

The `BaseEnemy` class serves as the foundation for all enemy entities within the game. It inherits from the abstract `BaseCharacter` class, providing enemy-specific attributes and functionalities. The class offers core methods for equipping enemies, retrieving enemy information, and handling enemy turns.

**Data Members:**

* `characterName` (String): Stores the name of the enemy character, used for identification purposes.

**Constructor:**

* `BaseEnemy(String characterName, List<Integer> attributes)`: This constructor takes two arguments: `characterName` (a string) and `attributes` (a list of integers). It assigns the provided name to the `characterName` member. Then, it creates a new `CharacterStats` object using the provided `attributes` list and passes it to the base class constructor (`super`) to initialize the inherited character statistics.

**Methods:**

* `equipEnemy(List<String> allowedWeaponTypes)`: This method equips the enemy with weapons and armor. It takes a list of allowed weapon types as input (e.g., ["axe", "sword"]). The method iterates until there are no more empty weapon slots on the enemy. Within each iteration, it retrieves a random weapon of an allowed type from the `GearManager` class and adds it to the enemy's equipment (using a `CharacterEquipment` object). If the weapon is not two-handed, the method retrieves and equips another random one-handed weapon following the same logic.

* `getCharacterName()`: This method simply returns the value of the `characterName` data member, providing access to the enemy's name.

* `doTurn()`: This method orchestrates the enemy's turn during gameplay. It starts by retrieving the enemy's character name using the `getCharacterName()` method. Next, it constructs a string representation of the enemy's turn information. Then, it logs the turn information using the `ActivityLogger` class. Finally, it calls the `executeActions(false)` method to instruct the enemy to perform its actions for the current turn.

#### Descendant Enemy Classes

The four descendant classes (`SkeletonWarrior`, `SkeletonArcher`, `SkeletonMage`, and `LichLord`) inherit from the `BaseEnemy` class and provide concrete implementations of enemy characters with unique attributes and abilities.

##### SkeletonWarrior

* Extends `BaseEnemy`
* Focuses on close-quarter combat abilities.
* In the constructor, it equips itself with weapons allowed for the `SkeletonWarrior` enemy type (using `AppConfig.WEAPON_AXE`, `AppConfig.WEAPON_SWORD`, and `AppConfig.WEAPON_SHIELD` constants).
* It has abilities like `WeaponAttack`, `HeavyAttack`, and `Whirlwind`.

##### SkeletonArcher

* Extends `BaseEnemy`
* Focuses on ranged attacks.
* In the constructor, it equips itself with weapons allowed for the `SkeletonArcher` enemy type (using `AppConfig.WEAPON_BOW` and `AppConfig.WEAPON_CROSSBOW` constants).
* It has abilities like `WeaponAttack`, `FocusedShot`, and `SprayOfArrows`.

##### SkeletonMage

* Extends `BaseEnemy`
* Focuses on magic attacks.
* In the constructor, it equips itself with weapons allowed for the `SkeletonMage` enemy type (using `AppConfig.WEAPON_STAFF` and `AppConfig.WEAPON_WAND` constants).
* It has abilities like `WeaponAttack`, various elemental bolts (fire, ice, air), and elemental blasts (fire, ice, air).

##### LichLord

* Extends `BaseEnemy`
* Represents a more powerful enemy character, or in other words, a boss.
* In the constructor, it equips itself with weapons allowed for the `LichLord` enemy type (using `AppConfig.WEAPON_AXE`, `AppConfig.WEAPON_SWORD`, and `AppConfig.WEAPON_SHIELD` constants). It also adjusts its health stat using a boss health multiplier.
* It has a wider range of abilities including `WeaponAttack`, `HeavyAttack`, `Whirlwind`, `FocusedHeal` (for healing itself), and a fire elemental bolt and blast.

### ActivityLogger

The `ActivityLogger` class is a utility class designed to centralize and manage the logging of game activities throughout the application. It provides a structured approach for recording messages about various game events, facilitating easier debugging, analysis, and potential implementation of functionalities like a combat log for the player.

The `ActivityLogger` class adheres to the Singleton design pattern by maintaining a static, final `ActivityLogger` field named `INSTANCE`. This ensures only one instance of the logger exists throughout the application, promoting centralized logging functionality. Internally, the class utilizes a private, final `Logger` object obtained via `Logger.getLogger(ActivityLogger.class.getName())` to serve as the core mechanism for recording messages.

The `ActivityLogger()` constructor is declared private, adhering to the Singleton pattern to prevent external classes from creating new instances of the `ActivityLogger`. Within the constructor, the logger is configured to disable inherited handlers from its parent loggers, ensuring isolated logging behavior. Furthermore, a `ConsoleHandler` with a custom formatter is set up to prepend a newline character to each log message for improved readability. Finally, the configured `ConsoleHandler` is attached to the logger, directing log messages to the console.

The `delayExecution()` method introduces a purposeful delay using `Thread.sleep(AppConfig.SLEEP_DELAY)`. While the specific intent of this delay isn't clear from the provided code, it may be used to simulate a more realistic pace of game events during development or testing. The `performLog(String message)` method handles the primary logging operation. If `AppConfig.USE_SLEEP_DELAY` is enabled, it introduces a delay using `delayExecution()`. It then logs the message using `logger.info(message)`, indicating an informational message useful for debugging or monitoring. The class exposes several public logging methods like `logRoundInfo()`, `logTurnInfo()`, `logAttack()`, `logDamage()`, `logDeath()`, and `logHealing()`. These internally call `performLog()`, after prepending formatted message prefixes using ANSI escape codes for clear color-coded differentiation in a console environment.

## Discussion

### Purpose Fulfillment

The project successfully fulfills its primary purpose of developing a role-playing game (RPG) simulation that showcases the application of object-oriented design (OOP) principles. The solution adheres to the specifications outlined in the class diagrams, ensuring alignment with the predefined requirements and design principles.

The implementation effectively models the core elements of an RPG, including characters (heroes and enemies), equipment (weapons and armor), abilities, and combat mechanics. The object-oriented approach is exemplified through well-structured class hierarchies, inheritance, polymorphism, and encapsulation.

The game mechanics are effectively simulated, with heroes navigating progressively difficult dungeon levels, engaging in combat with undead adversaries, and ultimately facing a powerful final boss. The control structure, implemented through classes like `GameRunner` and `GameEngine`, orchestrates the game flow, translating user input into actions and governing the underlying logic and rules.

Character customization and diversity are achieved through the implementation of distinct hero and enemy archetypes, each with unique attributes, equipment restrictions, and specialized abilities. The randomization of equipment assignment and the interplay between attributes, traits, and combat stats contribute to the strategic depth and replayability of the game.

Overall, the solution successfully meets the concrete goals outlined in the project specifications, delivering a functional, maintainable, and well-documented RPG simulation that showcases the application of object-oriented design principles.

### Purpose Fulfillment

The project successfully fulfills its primary purpose of developing a role-playing game (RPG) simulation that showcases the application of object-oriented design (OOP) principles. The solution adheres to the specifications outlined in the class diagrams, ensuring alignment with the predefined requirements and design principles. All assignment requirements have been implemented, and the provided unit tests pass successfully, validating the correctness and adherence to the specifications.

The implementation effectively models the core elements of an RPG, including characters (heroes and enemies), equipment (weapons and armor), abilities, and combat mechanics. The object-oriented approach is exemplified through well-structured class hierarchies, inheritance, polymorphism, and encapsulation.

The game mechanics are effectively simulated, with heroes navigating progressively difficult dungeon levels, engaging in combat with undead adversaries, and ultimately facing a powerful final boss. The control structure, implemented through the already provided classes `GameRunner` and `GameEngine`, orchestrates the game flow, translating user input into actions and governing the underlying logic and rules.

Character customization and diversity are achieved through the implementation of distinct hero and enemy archetypes, each with unique attributes, equipment restrictions, and specialized abilities. The randomization of equipment assignment and the interplay between attributes, traits, and combat stats contribute to the strategic depth and replayability of the game.

Overall, the solution successfully meets the concrete goals outlined in the project specifications, delivering a functional, maintainable, and well-documented RPG simulation that showcases the application of object-oriented design principles. The successful passing of all provided unit tests and the implementation of all assignment requirements further validate the adherence to the project specifications.

### Alternative Approaches

While the current implementation effectively addresses the project requirements, alternative approaches could be explored to further enhance the solution or address potential limitations. These solutions are however not in the scope of the project as this project had a very specific assignment description and class diagram to follow, this section is only to demonstrate that this topic has been considered.

1. **User Interface**: The current implementation focuses on the underlying game logic and mechanics, with the user interface being text-based and console-oriented. An alternative approach could involve the development of a graphical user interface (GUI) to provide a more immersive and visually appealing experience for the player.

2. **Persistence and Serialization**: The game state is currently transient, meaning that progress and character data are not persisted between sessions. Implementing serialization mechanisms, such as saving and loading game data to files or databases, could allow players to continue their adventures across multiple sessions.

3. **Procedural Content Generation**: While the current implementation relies on predefined dungeon levels and enemy configurations, procedural content generation techniques could be employed to dynamically generate levels, enemy encounters, and loot distributions. This would enhance the game's replayability and introduce an element of unpredictability.

4. **Networking and Multiplayer**: The game currently supports single-player gameplay. Introducing networking capabilities and implementing a multiplayer mode would allow players to engage in cooperative or competitive gameplay, potentially enhancing the overall gaming experience.

5. **Artificial Intelligence and Decision-Making**: The current implementation relies on random selection for enemy actions. Incorporating more advanced artificial intelligence techniques and decision-making algorithms could lead to more challenging and adaptive enemy behavior, further increasing the game's difficulty and strategic depth.

### Personal Reflections

Working on this RPG simulation project has been an incredibly difficult experience. It challenged me to apply various object-oriented design principles and software engineering concepts in a practical and engaging context, allowing me to utilize the skills I had already learned. Throughout the development process, I encountered several challenges that pushed me to think critically, problem-solve, and continuously refine my coding skills.

One of the notable challenges I faced was implementing the character stats system and the associated points system. Ensuring the correct calculation and application of attributes, traits, and combat stats, as well as managing their interplay, required a deep understanding of the project requirements and careful planning. Overcoming this challenge has significantly improved my ability to design and implement complex systems effectively.

Another area that presented difficulties was getting the `toString` methods to display the character information correctly. Formatting the output in a readable and visually appealing manner, while adhering to the specified requirements, required attention to detail and a thorough understanding of string manipulation techniques.

Time management was another valuable lesson I learned during this project. Balancing the various components, adhering to deadlines, and ensuring proper documentation and testing required careful planning and effective time allocation. This experience has reinforced the importance of time management skills in software development projects.

Furthermore, following the provided class diagrams and adhering to the specifications was a crucial aspect of this project. It taught me the significance of thoroughly understanding and adhering to design requirements, as well as the value of clear and concise documentation in ensuring successful project implementation.

Throughout the project, I gained a newfound appreciation for the value of thorough documentation and commenting. Maintaining comprehensive documentation not only facilitated code comprehension and collaboration but also served as a valuable reference during the development and testing phases.

Looking back, I can confidently say that this project has been an invaluable learning experience. It has reinforced my understanding of object-oriented programming principles, design patterns, and software engineering best practices. Additionally, it has emphasized the importance of planning, documentation, and testing in delivering high-quality, maintainable software solutions.

Moving forward, I plan to continue honing my skills in object-oriented design, exploring more advanced design patterns, and staying updated with the latest software engineering practices. I also aim to delve deeper into areas such as user interface development, persistence mechanisms, and procedural content generation to further enhance the gaming experience and broaden my skillset.

Overall, this project has been a testament to the power of object-oriented programming in creating complex, extensible, and maintainable software systems. I am grateful for the opportunity to apply these principles in a practical and engaging context, and I look forward to leveraging the lessons learned in future endeavors.
