package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ArcherTest {
    private static final int ARCHER_HEALTH = 150;
    private static final int ARCHER_DAMAGE = 10;
    private static final int ARCHER_MIN_DAMAGE = 5;
    private static final int ARCHER_MAX_DAMAGE = 15;
    private static final int ARCHER_SPEED = 20;

    /** Test for Archer Constructor */
    @Test
    public void testArcherConstructor() {
        final Archer a = new Archer("Arch");
        assertTrue(a.getName().equals("Arch"));
        assertTrue(a.getHealth() == ARCHER_HEALTH);
        assertTrue(a.getMaxDamage() == ARCHER_MAX_DAMAGE);
        assertTrue(a.getMinDamage() == ARCHER_MIN_DAMAGE);
        assertTrue(a.getSpeed() == ARCHER_SPEED);
    }

    /** Test for Archer setHealth method */
    @Test
    public void testSetHealth() {
        final Archer a = new Archer("Arch");
        a.setHealth(5);
        assertTrue(a.getHealth() == 5);
    }

    /** Test for Archer setMinDamage method */
    @Test
    public void testSetMinDamage() {
        final Archer a = new Archer("Arch");
        a.setMinDamage(5);
        assertTrue(a.getMinDamage() == 5);
    }

    /** Test for Archer setMaxDamage method */
    @Test
    public void testSetMaxDamage() {
        final Archer a = new Archer("Arch");
        a.setMaxDamage(5);
        assertTrue(a.getMaxDamage() == 5);
    }

    /** Test for Archer setSpeed method */
    @Test
    public void testSetSpeed() {
        final Archer a = new Archer("Arch");
        a.setSpeed(5);
        assertTrue(a.getSpeed() == 5);
    }
}
