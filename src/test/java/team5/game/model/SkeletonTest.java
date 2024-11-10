package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SkeletonTest {
    /** Test for Skeleton Constructor */
    @Test
    public void testSkeletonConstructor() {
        final Skeleton s = new Skeleton("Bones");
        assertTrue(s.getName().equals("Bones"));
        assertTrue(s.getHealth() == 100);
        assertTrue(s.getMaxDamage() == 15);
        assertTrue(s.getMinDamage() == 5);
        assertTrue(s.getSpeed() == 5);
    }

    /** Test for Skeleton Setter methods */
    @Test
    public void testSkeletonSetters() {
        final Skeleton s = new Skeleton("Bones");
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
