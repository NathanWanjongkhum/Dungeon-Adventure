package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PriestessTest {
    /** Test for Priestess Constructor */
    @Test
    public void testArcherConstructor() {
        final Priestess p = new Priestess("P");
        assertTrue(p.getName().equals("P"));
        assertTrue(p.getHealth() == 100);
        assertTrue(p.getMaxDamage() == 25);
        assertTrue(p.getMinDamage() == 15);
        assertTrue(p.getSpeed() == 25);
    }

}
