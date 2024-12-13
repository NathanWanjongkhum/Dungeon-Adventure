package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Tests for noob hero class
 * 
 * @author Holden Tsang and Nathan Wanjongkhum
 * @version 12 Dec 2024
 */
public class NoobTest {
    /** The noob health */
    private static final int NOOB_HEALTH = 1;
    /** The noob damage */
    private static final int NOOB_DAMAGE = 0;
    /** The noob min damage */
    private static final int NOOB_MIN_DAMAGE = 0;
    /** The noob max damage */
    private static final int NOOB_MAX_DAMAGE = 5;
    /** The noob speed */
    private static final int NOOB_SPEED = 1;
    /** The hero */
    private Hero myHero;
    /** Creates noob before each test */
    @BeforeEach
    public void setUp() {
        myHero = new Noob("No");
    }
    
    /** Test for Noob Constructor */
    @Test
    public void testMageConstructor() {
        assertEquals(myHero.getName(), "No");
        assertEquals(myHero.getHealth(), NOOB_HEALTH);
        assertEquals(myHero.getMaxDamage(), NOOB_MAX_DAMAGE);
        assertEquals(myHero.getMinDamage(), NOOB_MIN_DAMAGE);
        assertEquals(myHero.getSpeed(), NOOB_SPEED);
    }

    /** Test for Noob setHealth method */
    @Test
    public void testSetHealth() {
        myHero.setHealth(5);
        assertEquals(myHero.getHealth(), 5);
    }

    /** Test for Noob setMinDamage method */
    @Test
    public void testSetMinDamage() {
        myHero.setMinDamage(5);
        assertEquals(myHero.getMinDamage(), 5);
    }

    /** Test for Noob setMaxDamage method */
    @Test
    public void testSetMaxDamage() {
        myHero.setMaxDamage(5);
        assertEquals(myHero.getMaxDamage(), 5);
    }

    /** Test for Noob setSpeed method */
    @Test
    public void testSetSpeed() {
        myHero.setSpeed(5);
        assertEquals(myHero.getSpeed(), 5);
    }

    /** Test for Noob useSpecialAttack method */
    @Test
    public void testUseSpecialAttack() {
        final Monster m = new Skeleton("Skeleton");
        int damage = myHero.useSpecialAttack(m);
        assertEquals(0, damage);
    }

    /** Test for Noob getStats method */
    @Test
    public void testGetStats() {
        String stats = myHero.getStats();
        assertEquals(stats,
                "Name: No\nClass: Noob\nHP: 1/1\nDamage Range: 0 - 5\nSpeed: 1\nSpecial Move: Does nothing\n");
    }

    /** Test for Noob specialAttackText method */
    @Test
    public void testSpecialAttackText() {
        String text = myHero.specialAttackText();
        assertEquals(text, "Awaiting death\n");
    }

    /** Test for Noob attack method */
    @Test
    public void testAttack() {
        final Monster m = new Ogre("Ogre");
        int damage = myHero.attack(m);
        boolean inExpectedRange = damage >= myHero.getMinDamage() && damage <= myHero.getMaxDamage();
        assertTrue(inExpectedRange);
    }

    /** Test for Noob heal method */
    @Test
    public void testHeal() {
        myHero.setHealth(0);
        myHero.heal(10);
        assertEquals(myHero.getHealth(), 1);
    }

    /** Test for Noob checkVulnerableDamage method */
    @Test
    public void testCheckVulnerableDamage() {
        final Monster m = new Ogre("Ogre");
        int damage = myHero.checkVulnerableDamage(m, NOOB_DAMAGE);
        assertEquals(damage, NOOB_DAMAGE);
    }

    /** Test for Noob checkVulnerableDamage method */
    @Test
    public void testCheckVulnerableDamageVulnerable() {
        final Monster m = new Ogre("Ogre");
        m.getStatusEffects().setVulnerableDuration(1);
        int damage = myHero.checkVulnerableDamage(m, NOOB_DAMAGE);
        assertEquals(damage, NOOB_DAMAGE * 2);
    }

    /** Test for Noob addMinDamage method */
    @Test
    public void testAddMinDamage() {
        myHero.addMinDamage(10);
        assertEquals(myHero.getMinDamage(), NOOB_MIN_DAMAGE + 10);
    }

    /** Test for Noob addMaxDamage method */
    @Test
    public void testAddMaxDamage() {
        myHero.addMaxDamage(10);
        assertEquals(myHero.getMaxDamage(), NOOB_MAX_DAMAGE + 10);
    }

    /** Test for Noob setX method */
    @Test
    public void testSetX() {
        myHero.setX(10);
        assertEquals(myHero.getX(), 10);
    }

    /** Test for Noob setY method */
    @Test
    public void testSetY() {
        myHero.setY(10);
        assertEquals(myHero.getY(), 10);
    }

    /** Test for Noob setDirection method */
    @Test
    public void testSetDirection() {
        myHero.setDirection(Direction.NORTH);
        assertEquals(myHero.getDirection(), Direction.NORTH);
    }

    /** Test for Noob getDirectionsLocation method */
    @Test
    public void testGetDirectionsLocation() {
        int[] location = myHero.getDirectionsLocation(Direction.NORTH);
        assertEquals(location[0], 0);
        assertEquals(location[1], -1);
    }

    /** Test for Noob moveTo method */
    @Test
    public void testMoveTo() {
        myHero.moveTo(Direction.NORTH);
        assertEquals(myHero.getDirection(), Direction.NORTH);
    }

}
