package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class BombTest {
    /** Test for HealingPotion Constructor */
    @Test
    public void testBombConstructor() {
        final Bomb b = new Bomb();
        assertTrue(b.getCount() == 1);
        assertTrue(b.getDamage() == 1000);
        assertTrue(b.getRadius() == 1);
    }

    @Test
    /** Test for Archer Setter methods */
    public void testHealingPotionsetters() {
        final Bomb b = new Bomb();
        b.setDamage(1000);
        assertTrue(b.getDamage() == 1000);

    }
}
