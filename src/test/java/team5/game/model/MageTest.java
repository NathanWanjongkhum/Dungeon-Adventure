package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Tests for mage hero class
 * 
 * @author Holden Tsang and Nathan Wanjongkhum
 * @version 12 Dec 2024
 */
public class MageTest {
    private static final int MAGE_HEALTH = 250;
    private static final int MAGE_DAMAGE = 50;
    private static final int MAGE_MIN_DAMAGE = 45;
    private static final int MAGE_MAX_DAMAGE = 55;
    private static final int MAGE_SPEED = 10;

    private Hero myHero;

    @BeforeEach
    public void setUp() {
        myHero = new Mage("Mag");
    }
    
    /** Test for Mage Constructor */
    @Test
    public void testMageConstructor() {
        assertEquals(myHero.getName(), "Mag");
        assertEquals(myHero.getHealth(), MAGE_HEALTH);
        assertEquals(myHero.getMaxDamage(), MAGE_MAX_DAMAGE);
        assertEquals(myHero.getMinDamage(), MAGE_MIN_DAMAGE);
        assertEquals(myHero.getSpeed(), MAGE_SPEED);
    }

    /** Test for Mage setHealth method */
    @Test
    public void testSetHealth() {
        myHero.setHealth(5);
        assertEquals(myHero.getHealth(), 5);
    }

    /** Test for Mage setMinDamage method */
    @Test
    public void testSetMinDamage() {
        myHero.setMinDamage(5);
        assertEquals(myHero.getMinDamage(), 5);
    }

    /** Test for Mage setMaxDamage method */
    @Test
    public void testSetMaxDamage() {
        myHero.setMaxDamage(5);
        assertEquals(myHero.getMaxDamage(), 5);
    }

    /** Test for Mage setSpeed method */
    @Test
    public void testSetSpeed() {
        myHero.setSpeed(5);
        assertEquals(myHero.getSpeed(), 5);
    }

    /** Test for Mage useSpecialAttack method */
    @Test
    public void testUseSpecialAttack() {
        final Monster m = new Skeleton("Skeleton");
        int damage = myHero.useSpecialAttack(m);
        assertEquals(0, damage);
        assertTrue(myHero.getStatusEffects().isVulnerable());
    }

    /** Test for Mage getStats method */
    @Test
    public void testGetStats() {
        String stats = myHero.getStats();
        assertEquals(stats,
                "Name: Mag\nClass: Mage\nHP: 250/250\nDamage Range: 45 - 55\nSpeed: 10\nSpecial Move: Takes double damage for 3 turns, then does massive damage to enemies on the third attack!\n");
    }

    /** Test for Mage specialAttackText method */
    @Test
    public void testSpecialAttackText() {
        String text = myHero.specialAttackText();
        assertEquals(text, "Unleashed their magic!\n");
    }

    /** Test for Mage attack method */
    @Test
    public void testAttack() {
        final Monster m = new Ogre("Ogre");
        int damage = myHero.attack(m);
        boolean inExpectedRange = damage >= myHero.getMinDamage() && damage <= myHero.getMaxDamage();
        assertTrue(inExpectedRange);
    }

    /** Test for Mage heal method */
    @Test
    public void testHeal() {
        myHero.setHealth(0);
        myHero.heal(10);
        assertEquals(myHero.getHealth(), 10);
    }

    /** Test for Mage checkVulnerableDamage method */
    @Test
    public void testCheckVulnerableDamage() {
        final Monster m = new Ogre("Ogre");
        int damage = myHero.checkVulnerableDamage(m, MAGE_DAMAGE);
        assertEquals(damage, MAGE_DAMAGE);
    }

    /** Test for Mage checkVulnerableDamage method */
    @Test
    public void testCheckVulnerableDamageVulnerable() {
        final Monster m = new Ogre("Ogre");
        m.getStatusEffects().setVulnerableDuration(1);
        int damage = myHero.checkVulnerableDamage(m, MAGE_DAMAGE);
        assertEquals(damage, MAGE_DAMAGE * 2);
    }

    /** Test for Mage addMinDamage method */
    @Test
    public void testAddMinDamage() {
        myHero.addMinDamage(10);
        assertEquals(myHero.getMinDamage(), 55);
    }

    /** Test for Mage addMaxDamage method */
    @Test
    public void testAddMaxDamage() {
        myHero.addMaxDamage(10);
        assertEquals(myHero.getMaxDamage(), 65);
    }

    /** Test for Mage setX method */
    @Test
    public void testSetX() {
        myHero.setX(10);
        assertEquals(myHero.getX(), 10);
    }

    /** Test for Mage setY method */
    @Test
    public void testSetY() {
        myHero.setY(10);
        assertEquals(myHero.getY(), 10);
    }

    /** Test for Mage setDirection method */
    @Test
    public void testSetDirection() {
        myHero.setDirection(Direction.NORTH);
        assertEquals(myHero.getDirection(), Direction.NORTH);
    }

    /** Test for Mage getDirectionsLocation method */
    @Test
    public void testGetDirectionsLocation() {
        int[] location = myHero.getDirectionsLocation(Direction.NORTH);
        assertEquals(location[0], 0);
        assertEquals(location[1], -1);
    }

    /** Test for Mage moveTo method */
    @Test
    public void testMoveTo() {
        myHero.moveTo(Direction.NORTH);
        assertEquals(myHero.getDirection(), Direction.NORTH);
    }

}
