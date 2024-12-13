package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
/**
 * Tests for bomb item class
 * 
 * @author Holden Tsang and Nathan Wanjongkhum
 * @version 12 Dec 2024
 */
public class BombTest {
    /** Test for Bomb Constructor */
    @Test
    public void testBombConstructor() {
        final Bomb b = new Bomb();
        assertTrue(b.getCount() == 1);
        assertTrue(b.getDamage() == 1000);
        assertTrue(b.getRadius() == 1);
    }

    @Test
    /** Test for Bomb Setter methods */
    public void testBombPotionSetters() {
        final Bomb b = new Bomb();
        b.setDamage(1000);
        assertTrue(b.getDamage() == 1000);

    }
    @Test
    /** Test Bomb use item */
    public void testBombUseItem() {
        final Bomb b = new Bomb();
        final Monster mon = new Ogre("Og");
        b.setDamage(10);
        final int damage = b.useItem(mon);
        assertEquals(mon.getMaxHealth() - damage, mon.getHealth());
    }
}
