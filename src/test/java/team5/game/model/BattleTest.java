package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import team5.game.controller.Battle;

public class BattleTest {
    private Hero myHero;
    private Monster myMonster;

    @BeforeEach
    public void setUp() {
        myHero = new Archer("Arch");
        myMonster = new Ogre("Og");
    }

    @Test
    public void testBattleAttackHero() {
        final Battle battle = new Battle(myHero, myMonster);
        battle.battle("attack");
        final int damage = myHero.getMaxHealth() - myHero.getHealth();
        assertTrue(myMonster.getMinDamage() <= damage && myMonster.getMaxDamage() >= damage);
    }

    @Test
    public void testBattleAttackMonster() {
        final Battle battle = new Battle(myHero, myMonster);
        battle.battle("attack");
        final int damage = myMonster.getMaxHealth() - myMonster.getHealth();
        assertTrue(myHero.getMinDamage() <= damage && myHero.getMaxDamage() >= damage);
    }

    @Test
    public void testBattleSpecial() {
        final Battle battle = new Battle(myHero, myMonster);
        battle.battle("special");
        final int damage = myHero.getSpecialAttack().getDamage();
        assertTrue(myMonster.getMinDamage() <= damage && myMonster.getMaxDamage() >= damage);
        assertTrue(myMonster.getStatusEffects().isVulnerable());
    }

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

    @Test
    public void testBattleOver() {
        final Battle battle = new Battle(myHero, myMonster);
        myMonster.setHealth(0);
        assertTrue(battle.isOver());
    }
}
