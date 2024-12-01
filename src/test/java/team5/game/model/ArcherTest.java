package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ArcherTest {
    /** Test for Archer Constructor */
    @Test
    public void testArcherConstructor() {
        final Archer a = new Archer("Arch");
        assertTrue(a.getName().equals("Arch"));
        assertTrue(a.getHealth() == 150);
        assertTrue(a.getMaxDamage() == 15);
        assertTrue(a.getMinDamage() == 5);
        assertTrue(a.getSpeed() == 20);
    }

    @Test
    /** Test for Archer Setter methods */
    public void testArchersetters() {
        final Archer a = new Archer("Arch");
        a.setHealth(5);
        assertTrue(a.getHealth() == 5);
        a.setMaxDamage(0);
        a.setMinDamage(0);
        assertTrue(a.getMaxDamage() == 0);
        assertTrue(a.getMinDamage() == 0);
        a.setSpeed(10);
        assertTrue(a.getSpeed() == 10);
    }
}
