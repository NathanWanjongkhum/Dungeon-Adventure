package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Tests for warrior hero class
 * 
 * @author Holden Tsang and Nathan Wanjongkhum
 * @version 12 Dec 2024
 */
public class WarriorTest {
    /** The warrior health */
    private static final int WARRIOR_HEALTH = 500;
    /** The warrior damage */
    private static final int WARRIOR_DAMAGE = 20;
    /** The warrior min damage */
    private static final int WARRIOR_MIN_DAMAGE = 15;
    /** The warrior max damage */
    private static final int WARRIOR_MAX_DAMAGE = 25;
    /** The warrior speed */
    private static final int WARRIOR_SPEED = 8;
    /** The hero */
    private Hero myHero;
    /** Sets up creation of warrior before each test */
    @BeforeEach
    public void setUp() {
        myHero = new Warrior("War");
    }
    
    /** Test for Warrior Constructor */
    @Test
    public void testWarriorConstructor() {
        assertEquals(myHero.getName(), "War");
        assertEquals(myHero.getHealth(), WARRIOR_HEALTH);
        assertEquals(myHero.getMaxDamage(), WARRIOR_MAX_DAMAGE);
        assertEquals(myHero.getMinDamage(), WARRIOR_MIN_DAMAGE);
        assertEquals(myHero.getSpeed(), WARRIOR_SPEED);
    }

    /** Test for Warrior setHealth method */
    @Test
    public void testSetHealth() {
        myHero.setHealth(5);
        assertEquals(myHero.getHealth(), 5);
    }

    /** Test for Warrior setMinDamage method */
    @Test
    public void testSetMinDamage() {
        myHero.setMinDamage(5);
        assertEquals(myHero.getMinDamage(), 5);
    }

    /** Test for Warrior setMaxDamage method */
    @Test
    public void testSetMaxDamage() {
        myHero.setMaxDamage(5);
        assertEquals(myHero.getMaxDamage(), 5);
    }

    /** Test for Warrior setSpeed method */
    @Test
    public void testSetSpeed() {
        myHero.setSpeed(5);
        assertEquals(myHero.getSpeed(), 5);
    }
    /** Test for Warrior getStats method */
    @Test
    public void testGetStats() {
        String stats = myHero.getStats();
        assertEquals(stats,
                "Name: War\nClass: Warrior\nHP: 500/500\nDamage Range: 15 - 25\nSpeed: 8\nSpecial Move: Does average damage, but has a chance to do something special (or not)!\n");
    }

    /** Test for Warrior attack method */
    @Test
    public void testAttack() {
        final Monster m = new Ogre("Ogre");
        int damage = myHero.attack(m);
        boolean inExpectedRange = damage >= myHero.getMinDamage() && damage <= myHero.getMaxDamage();
        assertTrue(inExpectedRange);
    }

    /** Test for Warrior heal method */
    @Test
    public void testHeal() {
        myHero.setHealth(0);
        myHero.heal(10);
        assertEquals(myHero.getHealth(), 10);
    }

    /** Test for Warrior checkVulnerableDamage method */
    @Test
    public void testCheckVulnerableDamage() {
        final Monster m = new Ogre("Ogre");
        int damage = myHero.checkVulnerableDamage(m, WARRIOR_DAMAGE);
        assertEquals(damage, WARRIOR_DAMAGE);
    }

    /** Test for Warrior checkVulnerableDamage method */
    @Test
    public void testCheckVulnerableDamageVulnerable() {
        final Monster m = new Ogre("Ogre");
        m.getStatusEffects().setVulnerableDuration(1);
        int damage = myHero.checkVulnerableDamage(m, WARRIOR_DAMAGE);
        assertEquals(damage, WARRIOR_DAMAGE * 2);
    }

    /** Test for Warrior addMinDamage method */
    @Test
    public void testAddMinDamage() {
        myHero.addMinDamage(10);
        assertEquals(myHero.getMinDamage(), WARRIOR_MIN_DAMAGE + 10);
    }

    /** Test for Warrior addMaxDamage method */
    @Test
    public void testAddMaxDamage() {
        myHero.addMaxDamage(10);
        assertEquals(myHero.getMaxDamage(), WARRIOR_MAX_DAMAGE + 10);
    }

    /** Test for Warrior setX method */
    @Test
    public void testSetX() {
        myHero.setX(10);
        assertEquals(myHero.getX(), 10);
    }

    /** Test for Warrior setY method */
    @Test
    public void testSetY() {
        myHero.setY(10);
        assertEquals(myHero.getY(), 10);
    }

    /** Test for Warrior setDirection method */
    @Test
    public void testSetDirection() {
        myHero.setDirection(Direction.NORTH);
        assertEquals(myHero.getDirection(), Direction.NORTH);
    }

    /** Test for Warrior getDirectionsLocation method */
    @Test
    public void testGetDirectionsLocation() {
        int[] location = myHero.getDirectionsLocation(Direction.NORTH);
        assertEquals(location[0], 0);
        assertEquals(location[1], -1);
    }

    /** Test for Warrior moveTo method */
    @Test
    public void testMoveTo() {
        myHero.moveTo(Direction.NORTH);
        assertEquals(myHero.getDirection(), Direction.NORTH);
    }

}
