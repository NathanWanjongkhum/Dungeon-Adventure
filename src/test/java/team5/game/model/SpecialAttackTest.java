package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
/**
 * Tests for special attack object
 * 
 * @author Holden Tsang
 * @version 12 Dec 2024
 */
public class SpecialAttackTest {
    /** Test for Special Attack Constructor */
    @Test
    public void testSpecialAttackConstructor() {
        final SpecialAttack special = new SpecialAttack();

        assertEquals(special.getDamage(), 0);
        assertEquals(special.getMaxTurns(), 1);
        assertEquals(special.getTurns(), 1);

        final SpecialAttack special2 = new SpecialAttack(10, 20);

        assertEquals(special2.getDamage(), 20);
        assertEquals(special2.getMaxTurns(), 10);
        assertEquals(special2.getTurns(), 10);
    }

    @Test
    /** Test for Special Attack Setter methods */
    public void testBombPotionSetters() {
        final SpecialAttack special = new SpecialAttack();

        assertEquals(special.getDamage(), 0);
        assertEquals(special.getMaxTurns(), 1);
        assertEquals(special.getTurns(), 1);

        special.setDamage(10);
        special.setTurns(10);

        assertEquals(special.getDamage(), 10);
        assertEquals(special.getMaxTurns(), 10);
        assertEquals(special.getTurns(), 10);

        special.addTurns(-1);
        assertEquals(special.getMaxTurns(), 10);
        assertEquals(special.getTurns(), 9);
    }
}
