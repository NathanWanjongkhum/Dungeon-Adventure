package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
/**
 * Tests for healing potion item class
 * 
 * @author Holden Tsang and Nathan Wanjongkhum
 * @version 12 Dec 2024
 */
public class HealingPotionTest {
    /** Test for HealingPotion Constructor */
    @Test
    public void testHealingPotionConstructor() {
        final HealingPotion p = new HealingPotion();
        assertTrue(p.getCount() == 1);
        assertTrue(p.getHealthRestore() == 20);
    }

    @Test
    /** Test for HealingPotion Setter methods */
    public void testHealingPotionsetters() {
        final HealingPotion p = new HealingPotion();
        p.setCount(2);
        assertTrue(p.getCount() == 2);

    }
    @Test
    /** Test HealingPotion use item */
    public void testHealingPotionUseItem() {
        final HealingPotion p = new HealingPotion();
        final Hero hero = new Archer("Arch");
        hero.setHealth(10);
        final int healed = p.useItem(hero);
        assertEquals(healed, p.getHealthRestore());
        assertEquals(hero.getHealth(), 30);
    }
}
