package com.dt180g.project.characters;

import com.dt180g.project.characters.enemies.BaseEnemy;
import com.dt180g.project.characters.enemies.LichLord;
import com.dt180g.project.characters.enemies.SkeletonArcher;
import com.dt180g.project.characters.enemies.SkeletonMage;
import com.dt180g.project.characters.enemies.SkeletonWarrior;
import com.dt180g.project.characters.heroes.BaseHero;
import com.dt180g.project.characters.heroes.Cleric;
import com.dt180g.project.characters.heroes.Ranger;
import com.dt180g.project.characters.heroes.Warrior;
import com.dt180g.project.characters.heroes.Wizard;

import com.dt180g.project.stats.BaseStat;
import com.dt180g.project.support.ActivityLogger;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.Deque;
import java.util.List;

import static com.dt180g.project.support.TestSupportExtra.confirmClassIsAbstract;
import static com.dt180g.project.support.TestSupportExtra.confirmPrivateFields;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCharactersExtra {
    @Test
    public void testBaseCharacterNoInstantiation() {  // verify that we cannot create object instances
        assertThrows(InstantiationException.class, BaseCharacter.class::newInstance,
                "BaseCharacter needs to be purely abstract");
    }

    @Test
    public void testBaseCharacterIsAbstract() {
        confirmClassIsAbstract(BaseCharacter.class);
    }

    @Test
    public void testBaseCharacterMethodsModifiers() {
        assertAll(
                () -> assertTrue(
                        Modifier.isProtected(BaseCharacter.class.getDeclaredConstructor(CharacterStats.class).getModifiers()),
                        "BaseCharacter needs to have protected constructor."
                ),
                () -> assertTrue(
                        Modifier.isProtected(BaseCharacter.class.getDeclaredMethod("addAbilities", List.class).getModifiers()),
                        "Method 'BaseCharacter::addAbilities' needs to be protected."
                ),
                () -> assertTrue(
                        Modifier.isProtected(BaseCharacter.class.getDeclaredMethod("getTurnInformation", String.class).getModifiers()),
                        "Method 'BaseCharacter::getTurnInformation' needs to be protected."
                ),
                () -> assertTrue(
                        Modifier.isProtected(BaseCharacter.class.getDeclaredMethod("executeActions", boolean.class).getModifiers()),
                        "Method 'BaseCharacter::executeActions' needs to be protected."
                ),
                () -> assertTrue(
                        Modifier.isPrivate(BaseCharacter.class.getDeclaredMethod("determineActions").getModifiers()),
                        "Method 'BaseCharacter::determineActions' needs to be private."
                )
        );
    }

    @Test
    public void testBaseCharacterFieldsArePrivate() {
        confirmPrivateFields(BaseCharacter.class);
    }

    @Test
    public void testBaseCharacterMethodReturnTypes() {
        assertAll(
                () -> assertTrue(
                        String.class.isAssignableFrom(BaseCharacter.class.getDeclaredMethod("getTurnInformation", String.class).getReturnType()),
                        "Return type of 'BaseCharacter::getTurnInformation' needs to be String."
                ),
                () -> assertTrue(
                        List.class.isAssignableFrom(BaseCharacter.class.getDeclaredMethod("getAbilities").getReturnType()),
                        "Return type of 'BaseCharacter::getAbilities' needs to be List."
                ),
                () -> assertTrue(
                        Deque.class.isAssignableFrom(BaseCharacter.class.getDeclaredMethod("determineActions").getReturnType()),
                        "Return type of 'BaseCharacter::determineActions' needs to be Deque."
                ),
                () -> assertTrue(
                        List.class.isAssignableFrom(BaseCharacter.class.getDeclaredMethod("registerDamage", int.class, boolean.class).getReturnType()),
                        "Return type of 'BaseCharacter::registerDamage' needs to be List."
                )
        );
    }

    @Test
    public void testBaseDerivatives() {
        assertAll(
                () -> assertTrue(BaseCharacter.class.isAssignableFrom(BaseHero.class), "BaseHero needs to derive from BaseCharacter"),
                () -> assertTrue(BaseCharacter.class.isAssignableFrom(BaseEnemy.class), "BaseEnemy needs to derive from BaseCharacter")
        );
    }

    @Test
    public void testBaseHeroIsAbstract() {
        confirmClassIsAbstract(BaseHero.class);
    }

    @Test
    public void testBaseEnemyIsAbstract() {
        confirmClassIsAbstract(BaseEnemy.class);
    }

    @Test
    public void testBaseDerivativesMethodsModifiers() {
        assertAll(
                () -> assertTrue(
                        Modifier.isProtected(BaseHero.class.getDeclaredConstructor(String.class, List.class).getModifiers()),
                        "BaseHero needs to have protected constructor."
                ),
                () -> assertTrue(
                        Modifier.isProtected(BaseEnemy.class.getDeclaredConstructor(String.class, List.class).getModifiers()),
                        "BaseEnemy needs to have protected constructor."
                ),
                () -> assertTrue(
                        Modifier.isProtected(BaseHero.class.getDeclaredMethod("equipHero", Class.class).getModifiers()),
                        "Method 'BaseHero::equipHero' needs to be protected."
                ),
                () -> assertTrue(
                        Modifier.isProtected(BaseEnemy.class.getDeclaredMethod("equipEnemy", List.class).getModifiers()),
                        "Method 'BaseEnemy::equipEnemy' needs to be protected."
                )
        );
    }

    @Test
    public void testConcreteDerivatives() {
        assertAll(
                () -> assertTrue(BaseHero.class.isAssignableFrom(Cleric.class), "Cleric needs to derive from BaseHero."),
                () -> assertTrue(BaseHero.class.isAssignableFrom(Ranger.class), "Ranger needs to derive from BaseHero."),
                () -> assertTrue(BaseHero.class.isAssignableFrom(Warrior.class), "Warrior needs to derive from BaseHero."),
                () -> assertTrue(BaseHero.class.isAssignableFrom(Wizard.class), "Wizard needs to derive from BaseHero."),
                () -> assertTrue(BaseEnemy.class.isAssignableFrom(SkeletonArcher.class), "SkeletonArcher needs to derive from BaseHero."),
                () -> assertTrue(BaseEnemy.class.isAssignableFrom(SkeletonMage.class), "SkeletonMage needs to derive from BaseHero."),
                () -> assertTrue(BaseEnemy.class.isAssignableFrom(SkeletonWarrior.class), "SkeletonWarrior needs to derive from BaseHero."),
                () -> assertTrue(BaseEnemy.class.isAssignableFrom(LichLord.class), "LichLord needs to derive from BaseHero.")
        );
    }
}
