package team5.game.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SkeletonTest {
    @Test
    public void testSkeletonConstructor() {
        final Skeleton s = new Skeleton("Bones");
        assertTrue(s.getName().equals("Bones"));
        assertTrue(s.getHealth() == 100);
        assertTrue(s.getMaxDamage() == 10);
    }
}
