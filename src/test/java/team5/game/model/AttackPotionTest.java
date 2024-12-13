package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
/**
 * Tests for attack potion item class
 * 
 * @author Holden Tsang
 * @version 12 Dec 2024
 */
public class AttackPotionTest {
    /** Test for AttackPotion Constructor */
    @Test
    public void testAttackPotionConstructor() {
        final AttackPotion pot = new AttackPotion();
        assertTrue(pot.getAttackIncrease() == 20);
        assertTrue(pot.getEffectTurns() == 2);
        assertTrue(pot.getCount() == 1);
        
    }

    @Test
    /** Test for AttackPotion Setter methods */
    public void testHealingPotionsetters() {
        final AttackPotion pot = new AttackPotion();
        pot.setAttackIncrease(10);
        pot.setTurns(10);
        pot.setCount(5);
        assertTrue(pot.getAttackIncrease() == 10);
        assertTrue(pot.getEffectTurns() == 10);
        assertTrue(pot.getCount() == 5);

    }
    @Test
    /** Test for AttackPotion useItem method */
    public void testHealingPotionUseitem() {
        final AttackPotion pot = new AttackPotion();
        final Hero hero = new Archer("Arch");
        final int attack = pot.useItem(hero);
        assertTrue(attack == 20);
        assertTrue(hero.getStatusEffects().isDamageIncrease());
    }
}
