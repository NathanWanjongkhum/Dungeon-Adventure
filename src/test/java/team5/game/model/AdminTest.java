package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Tests for admin hero class
 * 
 * @author Holden Tsang
 * @version 12 Dec 2024
 */
public class AdminTest {
    /** The admin health */
    private static final int ADMIN_HEALTH = 100000;
    /** The admin damage */
    private static final int ADMIN_DAMAGE = 10000;
    /** The admin min damage */
    private static final int ADMIN_MIN_DAMAGE = 9995;
    /** The admin max damage */
    private static final int ADMIN_MAX_DAMAGE = 10005;
    /** The admin speed */
    private static final int ADMIN_SPEED = 20000;
    /** The hero */
    private Hero myHero;
    /** Sets up creation of admin before each tests */
    @BeforeEach
    public void setUp() {
        myHero = new Admin("Ad");
    }
    
    /** Test for Admin Constructor */
    @Test
    public void testMageConstructor() {
        assertEquals(myHero.getName(), "Ad");
        assertEquals(myHero.getHealth(), ADMIN_HEALTH);
        assertEquals(myHero.getMaxDamage(), ADMIN_MAX_DAMAGE);
        assertEquals(myHero.getMinDamage(), ADMIN_MIN_DAMAGE);
        assertEquals(myHero.getSpeed(), ADMIN_SPEED);
    }

    /** Test for Admin setHealth method */
    @Test
    public void testSetHealth() {
        myHero.setHealth(5);
        assertEquals(myHero.getHealth(), 5);
    }

    /** Test for Admin setMinDamage method */
    @Test
    public void testSetMinDamage() {
        myHero.setMinDamage(5);
        assertEquals(myHero.getMinDamage(), 5);
    }

    /** Test for Admin setMaxDamage method */
    @Test
    public void testSetMaxDamage() {
        myHero.setMaxDamage(5);
        assertEquals(myHero.getMaxDamage(), 5);
    }

    /** Test for Admin setSpeed method */
    @Test
    public void testSetSpeed() {
        myHero.setSpeed(5);
        assertEquals(myHero.getSpeed(), 5);
    }

    /** Test for Admin useSpecialAttack method */
    @Test
    public void testUseSpecialAttack() {
        final Monster m = new Skeleton("Skeleton");
        int damage = myHero.useSpecialAttack(m);
        assertEquals(m.getMaxHealth() * 2, damage);
    }

    /** Test for Admin getStats method */
    @Test
    public void testGetStats() {
        String stats = myHero.getStats();
        assertEquals(stats,
                "Name: Ad\nClass: Admin\nHP: 100000/100000\nDamage Range: 9995 - 10005\nSpeed: 20000\nSpecial Move: Instakills\n");
    }

    /** Test for Admin specialAttackText method */
    @Test
    public void testSpecialAttackText() {
        String text = myHero.specialAttackText();
        assertEquals(text, "Death\n");
    }

    /** Test for Admin attack method */
    @Test
    public void testAttack() {
        final Monster m = new Ogre("Ogre");
        int damage = myHero.attack(m);
        boolean inExpectedRange = damage >= myHero.getMinDamage() && damage <= myHero.getMaxDamage();
        assertTrue(inExpectedRange);
    }

    /** Test for Admin heal method */
    @Test
    public void testHeal() {
        myHero.setHealth(0);
        myHero.heal(10);
        assertEquals(myHero.getHealth(), 10);
    }

    /** Test for Admin checkVulnerableDamage method */
    @Test
    public void testCheckVulnerableDamage() {
        final Monster m = new Ogre("Ogre");
        int damage = myHero.checkVulnerableDamage(m, ADMIN_DAMAGE);
        assertEquals(damage, ADMIN_DAMAGE);
    }

    /** Test for Admin checkVulnerableDamage method */
    @Test
    public void testCheckVulnerableDamageVulnerable() {
        final Monster m = new Ogre("Ogre");
        m.getStatusEffects().setVulnerableDuration(1);
        int damage = myHero.checkVulnerableDamage(m, ADMIN_DAMAGE);
        assertEquals(damage, ADMIN_DAMAGE * 2);
    }

    /** Test for Admin addMinDamage method */
    @Test
    public void testAddMinDamage() {
        myHero.addMinDamage(10);
        assertEquals(myHero.getMinDamage(), ADMIN_MIN_DAMAGE + 10);
    }

    /** Test for Admin addMaxDamage method */
    @Test
    public void testAddMaxDamage() {
        myHero.addMaxDamage(10);
        assertEquals(myHero.getMaxDamage(), ADMIN_MAX_DAMAGE + 10);
    }

    /** Test for Admin setX method */
    @Test
    public void testSetX() {
        myHero.setX(10);
        assertEquals(myHero.getX(), 10);
    }

    /** Test for Admin setY method */
    @Test
    public void testSetY() {
        myHero.setY(10);
        assertEquals(myHero.getY(), 10);
    }

    /** Test for Admin setDirection method */
    @Test
    public void testSetDirection() {
        myHero.setDirection(Direction.NORTH);
        assertEquals(myHero.getDirection(), Direction.NORTH);
    }

    /** Test for Admin getDirectionsLocation method */
    @Test
    public void testGetDirectionsLocation() {
        int[] location = myHero.getDirectionsLocation(Direction.NORTH);
        assertEquals(location[0], 0);
        assertEquals(location[1], -1);
    }

    /** Test for Admin moveTo method */
    @Test
    public void testMoveTo() {
        myHero.moveTo(Direction.NORTH);
        assertEquals(myHero.getDirection(), Direction.NORTH);
    }

}
