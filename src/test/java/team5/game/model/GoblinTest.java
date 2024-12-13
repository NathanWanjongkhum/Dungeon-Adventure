package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests for goblin monster class
 * 
 * @author Holden Tsang and Nathan Wanjongkhum
 * @version 12 Dec 2024
 */
public class GoblinTest {

    /** Test for Goblin Constructor */
    @Test
    public void testSkeletonConstructor() {
        final Goblin s = new Goblin("Gob");
        assertTrue(s.getName().equals("Gob"));
        assertTrue(s.getHealth() == 50);
        assertTrue(s.getMaxDamage() == 10);
        assertTrue(s.getMinDamage() == 0);
        assertTrue(s.getSpeed() == 3);
    }

    /** Test for Goblin Setter methods */
    @Test
    public void testSkeletonSetters() {
        final Goblin s = new Goblin("Gob");
        s.setHealth(5);
        assertTrue(s.getHealth() == 5);
        s.setMaxDamage(0);
        s.setMinDamage(0);
        assertTrue(s.getMaxDamage() == 0);
        assertTrue(s.getMinDamage() == 0);
        s.setSpeed(10);
        assertTrue(s.getSpeed() == 10);
    }
}
