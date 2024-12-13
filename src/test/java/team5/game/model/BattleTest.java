package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Tests for battle class
 * 
 * @author Holden Tsang
 * @version 12 Dec 2024
 */
public class BattleTest {
    /** The hero */
    private Hero myHero;
    /** The monster */
    private Monster myMonster;

    /** Sets up monster before each tests */
    @BeforeEach
    public void setUp() {
        myHero = new Archer("Arch");
        myMonster = new Ogre("Og");
    }
    /** The battle constructor test */
    @Test
    public void testBattleAttackHero() {
        final Battle battle = new Battle(myHero, myMonster);
        battle.battle("attack");
        final int damage = myHero.getMaxHealth() - myHero.getHealth();
        assertTrue(myMonster.getMinDamage() <= damage && myMonster.getMaxDamage() >= damage);
    }
    /** The battle attack test */
    @Test
    public void testBattleAttackMonster() {
        final Battle battle = new Battle(myHero, myMonster);
        battle.battle("attack");
        final int damage = myMonster.getMaxHealth() - myMonster.getHealth();
        assertTrue(myHero.getMinDamage() <= damage && myHero.getMaxDamage() >= damage);
    }
    /** The battle special test */
    @Test
    public void testBattleSpecial() {
        final Battle battle = new Battle(myHero, myMonster);
        battle.battle("special");
        final int damage = myHero.getSpecialAttack().getDamage();
        assertTrue(myMonster.getMinDamage() <= damage && myMonster.getMaxDamage() >= damage);
        assertTrue(myMonster.getStatusEffects().isVulnerable());
    }
    /** The battle bomb item test */
    @Test
    public void testBattleItemBomb() {
        final Battle battle = new Battle(myHero, myMonster);
        final Bomb bomb = new Bomb();

        myHero.setHealth(1000);
        final int startHealth = myHero.getHealth();

        battle.setConsumable(bomb);
        battle.battle("item");

        assertEquals(bomb.getDamage(), startHealth - myMonster.getHealth());
    }
    /** The battle bomb test for hp less than 1000*/
    @Test
    public void testBattleItemBomb2() {
        final Battle battle = new Battle(myHero, myMonster);
        final Bomb bomb = new Bomb();

        battle.setConsumable(bomb);
        battle.battle("item");

        assertEquals(0, myMonster.getHealth());
    }
    /** The battle healing potion item test */
    @Test
    public void testBattleItemHealingPot() {
        final Battle battle = new Battle(myHero, myMonster);
        myHero.setHealth(20);
        assertTrue(myHero.getHealth() == 20);
        final HealingPotion heal = new HealingPotion();
        battle.setConsumable(heal);
        battle.battle("item");
        final int monsterdamage = myMonster.getMaxDamage() - myMonster.getMinDamage();
        assertTrue(myHero.getHealth() <= 40);
        assertTrue(myHero.getHealth() >= 40 - monsterdamage);
    }
    /** The battle attack potion item test */
    @Test
    public void testBattleItemAttackPot() {
        final Battle battle = new Battle(myHero, myMonster);
        final int startingmin = myHero.getMinDamage();
        final AttackPotion pot = new AttackPotion();
        battle.setConsumable(pot);
        battle.battle("item");
        assertTrue(myHero.getStatusEffects().isDamageIncrease());
        assertTrue(myHero.getMinDamage() == startingmin + pot.getAttackIncrease());
    }
    /** The battle is over test */
    @Test
    public void testBattleOver() {
        final Battle battle = new Battle(myHero, myMonster);
        myMonster.setHealth(0);
        assertTrue(battle.isOver());
    }
}
