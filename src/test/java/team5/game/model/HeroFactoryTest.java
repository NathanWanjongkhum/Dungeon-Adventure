package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
/**
 * Tests for hero factory
 * 
 * @author Holden Tsang
 * @version 12 Dec 2024
 */
public class HeroFactoryTest {

    /** Test for HeroFactory */
    @Test
    public void testHeroFactory() {
        final Hero archer = new Archer("HeroName");
        final Hero mage = new Mage("HeroName");
        final Hero priestess = new Priestess("HeroName");
        final Hero warrior = new Warrior("HeroName");

        GameState.getInstance().setName("HeroName");

        final Hero archer2 = HeroFactory.createHero("Archer");
        final Hero mage2 = HeroFactory.createHero("Mage");
        final Hero priestess2 = HeroFactory.createHero("Priestess");
        final Hero warrior2 = HeroFactory.createHero("Warrior");
        assertEquals(archer, archer2);
        assertEquals(mage, mage2);
        assertEquals(priestess, priestess2);
        assertEquals(warrior, warrior2);
    }
}
