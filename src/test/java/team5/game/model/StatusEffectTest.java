package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
/**
 * Tests for status effect object 
 * 
 * @author Holden Tsang
 * @version 12 Dec 2024
 */
public class StatusEffectTest {
    /** Test for Status Effect Constructor */
    @Test
    public void testSpecialAttackConstructor() {
        final StatusEffects effect = new StatusEffects();

        assertFalse(effect.isDamageIncrease());
        assertFalse(effect.isRegen());
        assertFalse(effect.isVulnerable());
    }

    @Test
    /** Test for Status Effect Setter methods */
    public void testStatusEffectSetters() {
        final StatusEffects effect = new StatusEffects();
        effect.setDamageDuration(1);
        effect.setDamageIncrease(2);
        effect.setRegenAmount(3);
        effect.setRegenDuration(4);
        effect.setVulnerableDuration(5);

        assertTrue(effect.isDamageIncrease());
        assertTrue(effect.isRegen());
        assertTrue(effect.isVulnerable());
        assertEquals(effect.getDamageIncreaseDuration(), 1);
        assertEquals(effect.getDamageAmount(), 2);
        assertEquals(effect.getRegenAmount(), 3);
        assertEquals(effect.getRegenDuration(), 4);
        assertEquals(effect.getVulnerableDuration(), 5);
    }
}
