package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
/**
 * Tests for ogre monster class
 * 
 * @author Holden Tsang and Nathan Wanjongkhum
 * @version 12 Dec 2024
 */
public class OgreTest {

    /** Test for Ogre Constructor */
    @Test
    public void testSkeletonConstructor() {
        final Ogre s = new Ogre("Og");
        assertTrue(s.getName().equals("Og"));
        assertTrue(s.getHealth() == 200);
        assertTrue(s.getMaxDamage() == 10);
        assertTrue(s.getMinDamage() == 0);
        assertTrue(s.getSpeed() == 10);
    }

    /** Test for Ogre Setter methods */
    @Test
    public void testSkeletonSetters() {
        final Ogre s = new Ogre("Og");
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
