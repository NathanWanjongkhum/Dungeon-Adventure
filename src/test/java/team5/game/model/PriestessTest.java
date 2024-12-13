package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Tests for priestess hero class
 * 
 * @author Holden Tsang and Nathan Wanjongkhum
 * @version 12 Dec 2024
 */
public class PriestessTest {
    /** The priestess health */
    private static final int PRIESTESS_HEALTH = 100;
    /** The priestess damage */
    private static final int PRIESTESS_DAMAGE = 20;
    /** The priestess min damage */
    private static final int PRIESTESS_MIN_DAMAGE = 15;
    /** The priestess max damage */
    private static final int PRIESTESS_MAX_DAMAGE = 25;
    /** The priestess speed */
    private static final int PRIESTESS_SPEED = 25;
    /** The hero */
    private Hero myHero;

    /** Sets up the priestess creation before each test */
    @BeforeEach
    public void setUp() {
        myHero = new Priestess("P");;
    }
    /** Test for Priestess Constructor */
    @Test
    public void testPriestessConstructor() {
        assertTrue(myHero.getName().equals("P"));
        assertTrue(myHero.getHealth() == PRIESTESS_HEALTH);
        assertTrue(myHero.getMaxDamage() == PRIESTESS_MAX_DAMAGE);
        assertTrue(myHero.getMinDamage() == PRIESTESS_MIN_DAMAGE);
        assertTrue(myHero.getSpeed() == PRIESTESS_SPEED);
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
        int damage = myHero.checkVulnerableDamage(m, PRIESTESS_DAMAGE);
        assertEquals(damage, PRIESTESS_DAMAGE);
    }

    /** Test for Warrior checkVulnerableDamage method */
    @Test
    public void testCheckVulnerableDamageVulnerable() {
        final Monster m = new Ogre("Ogre");
        m.getStatusEffects().setVulnerableDuration(1);
        int damage = myHero.checkVulnerableDamage(m, PRIESTESS_DAMAGE);
        assertEquals(damage, PRIESTESS_DAMAGE * 2);
    }

    /** Test for Warrior addMinDamage method */
    @Test
    public void testAddMinDamage() {
        myHero.addMinDamage(10);
        assertEquals(myHero.getMinDamage(), PRIESTESS_MIN_DAMAGE + 10);
    }

    /** Test for Warrior addMaxDamage method */
    @Test
    public void testAddMaxDamage() {
        myHero.addMaxDamage(10);
        assertEquals(myHero.getMaxDamage(), PRIESTESS_MAX_DAMAGE + 10);
    }
}
