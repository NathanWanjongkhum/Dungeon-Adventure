package team5.game.model;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
    @Test
    /** Test for Priestess Setter methods */
    public void testArchersetters() {
        final Priestess p = new Priestess("P");
        p.setHealth(5);
        assertTrue(p.getHealth() == 5);
        p.setMaxDamage(0);
        p.setMinDamage(0);
        assertTrue(p.getMaxDamage() == 0);
        assertTrue(p.getMinDamage() == 0);
        p.setSpeed(10);
        assertTrue(p.getSpeed() == 10);
        p.setHealth(10);
        p.heal(10);
        assertTrue(p.getHealth() == 20);    
    }
}
