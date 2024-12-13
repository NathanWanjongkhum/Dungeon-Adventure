package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
/**
 * Tests for archer hero class
 * 
 * @author Holden Tsang and Nathan Wanjongkhum
 * @version 12 Dec 2024
 */
public class ArcherTest {
    /** The archer health */
    private static final int ARCHER_HEALTH = 150;
    /** The archer damage */
    private static final int ARCHER_DAMAGE = 10;
    /** The archer min damage */
    private static final int ARCHER_MIN_DAMAGE = 5;
    /** The archer max damage */
    private static final int ARCHER_MAX_DAMAGE = 15;
    /** The archer speed */
    private static final int ARCHER_SPEED = 20;

    /** Test for Archer Constructor */
    @Test
    public void testArcherConstructor() {
        final Archer a = new Archer("Arch");
        assertEquals(a.getName(), "Arch");
        assertEquals(a.getHealth(), ARCHER_HEALTH);
        assertEquals(a.getMaxDamage(), ARCHER_MAX_DAMAGE);
        assertEquals(a.getMinDamage(), ARCHER_MIN_DAMAGE);
        assertEquals(a.getSpeed(), ARCHER_SPEED);
    }

    /** Test for Archer setHealth method */
    @Test
    public void testSetHealth() {
        final Archer a = new Archer("Arch");
        a.setHealth(5);
        assertEquals(a.getHealth(), 5);
    }

    /** Test for Archer setMinDamage method */
    @Test
    public void testSetMinDamage() {
        final Archer a = new Archer("Arch");
        a.setMinDamage(5);
        assertEquals(a.getMinDamage(), 5);
    }

    /** Test for Archer setMaxDamage method */
    @Test
    public void testSetMaxDamage() {
        final Archer a = new Archer("Arch");
        a.setMaxDamage(5);
        assertEquals(a.getMaxDamage(), 5);
    }

    /** Test for Archer setSpeed method */
    @Test
    public void testSetSpeed() {
        final Archer a = new Archer("Arch");
        a.setSpeed(5);
        assertEquals(a.getSpeed(), 5);
    }

    /** Test for Archer useSpecialAttack method */
    @Test
    public void testUseSpecialAttack() {
        final Archer a = new Archer("Arch");
        final Monster m = new Skeleton("Skeleton");
        int damage = a.useSpecialAttack(m);
        assertEquals(ARCHER_DAMAGE, damage);
    }

    /** Test for Archer useSpecialAttack method */
    @Test
    public void testUseSpecialAttackVulnerable() {
        final Archer a = new Archer("Arch");
        final Monster m = new Ogre("Ogre");
        int damage = a.useSpecialAttack(m);
        assertEquals(ARCHER_DAMAGE, damage);
    }

    /** Test for Archer getStats method */
    @Test
    public void testGetStats() {
        final Archer a = new Archer("Arch");
        String stats = a.getStats();
        assertEquals(stats,
                "Name: Arch\nClass: Archer\nHP: 150/150\nDamage Range: 5 - 15\nSpeed: 20\nSpecial Move: Does low damage, but makes enemies take double damage on the next attack\n");
    }

    /** Test for Archer specialAttackText method */
    @Test
    public void testSpecialAttackText() {
        final Archer a = new Archer("Arch");
        String text = a.specialAttackText();
        assertEquals(text, "Vulnerabled the enemy for the next attack!\n");
    }

    /** Test for Archer attack method */
    @Test
    public void testAttack() {
        final Archer a = new Archer("Arch");
        final Monster m = new Ogre("Ogre");
        int damage = a.attack(m);
        boolean inExpectedRange = damage >= a.getMinDamage() && damage <= a.getMaxDamage();
        assertTrue(inExpectedRange);
    }

    /** Test for Archer heal method */
    @Test
    public void testHeal() {
        final Archer a = new Archer("Arch");
        a.setHealth(0);
        a.heal(10);
        assertEquals(a.getHealth(), 10);
    }

    /** Test for Archer checkVulnerableDamage method */
    @Test
    public void testCheckVulnerableDamage() {
        final Archer a = new Archer("Arch");
        final Monster m = new Ogre("Ogre");
        int damage = a.checkVulnerableDamage(m, ARCHER_DAMAGE);
        assertEquals(damage, ARCHER_DAMAGE);
    }

    /** Test for Archer checkVulnerableDamage method */
    @Test
    public void testCheckVulnerableDamageVulnerable() {
        final Archer a = new Archer("Arch");
        final Monster m = new Ogre("Ogre");
        m.getStatusEffects().setVulnerableDuration(1);
        int damage = a.checkVulnerableDamage(m, ARCHER_DAMAGE);
        assertEquals(damage, ARCHER_DAMAGE * 2);
    }

    /** Test for Archer addMinDamage method */
    @Test
    public void testAddMinDamage() {
        final Archer a = new Archer("Arch");
        a.addMinDamage(10);
        assertEquals(a.getMinDamage(), 15);
    }

    /** Test for Archer addMaxDamage method */
    @Test
    public void testAddMaxDamage() {
        final Archer a = new Archer("Arch");
        a.addMaxDamage(10);
        assertEquals(a.getMaxDamage(), 25);
    }

    /** Test for Archer setX method */
    @Test
    public void testSetX() {
        final Archer a = new Archer("Arch");
        a.setX(10);
        assertEquals(a.getX(), 10);
    }

    /** Test for Archer setY method */
    @Test
    public void testSetY() {
        final Archer a = new Archer("Arch");
        a.setY(10);
        assertEquals(a.getY(), 10);
    }

    /** Test for Archer setDirection method */
    @Test
    public void testSetDirection() {
        final Archer a = new Archer("Arch");
        a.setDirection(Direction.NORTH);
        assertEquals(a.getDirection(), Direction.NORTH);
    }

    /** Test for Archer getDirectionsLocation method */
    @Test
    public void testGetDirectionsLocation() {
        final Archer a = new Archer("Arch");
        int[] location = a.getDirectionsLocation(Direction.NORTH);
        assertEquals(location[0], 0);
        assertEquals(location[1], -1);
    }

    /** Test for Archer moveTo method */
    @Test
    public void testMoveTo() {
        final Archer a = new Archer("Arch");
        a.moveTo(Direction.NORTH);
        assertEquals(a.getDirection(), Direction.NORTH);
    }

}
