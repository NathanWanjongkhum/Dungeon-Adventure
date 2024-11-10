package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class HealingPotionTest {
    /** Test for HealingPotion Constructor */
    @Test
    public void testHealingPotionConstructor() {
        final HealingPotion p = new HealingPotion();
        assertTrue(p.getCount() == 0);
        assertTrue(p.getHealthRestore() == 20);
    }

    @Test
    /** Test for Archer Setter methods */
    public void testHealingPotionsetters() {
        final HealingPotion p = new HealingPotion();
        p.setCount(2);
        assertTrue(p.getCount() == 2);

    }
}
