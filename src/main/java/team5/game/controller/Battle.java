package team5.game.controller;

import java.util.Random;

import team5.game.model.DungeonCharacter;
import team5.game.model.Hero;
import team5.game.model.Monster;

public class Battle {
    private Hero myHero;
    private Monster myMonster;
    private String myText;
    private int myCount;

    public Battle(Hero theHero, Monster theMonster) {
        myHero = theHero;
        myMonster = theMonster;
        myText = "";
        myCount = 1;
    }
    
    public void battle() {
        myText = String.format("Round %d:\n", myCount);
        if (myHero.getSpeed() >= myMonster.getSpeed()) {
            attack(myHero, myMonster);
            if (myMonster.isAlive()) {
                attack(myMonster, myHero);
            }
        } else {
            attack(myMonster, myHero);
            if (myHero.isAlive()) {
                attack(myHero, myMonster);
            }
        }
        if (myHero.getStatusEffects().isRegen()) {
            healed(myHero.getSpecialAttack().getDamage());
        }
        checkStatus(myMonster, myHero);
        myText += "\n";
        myCount++;
    }
    private void attack(DungeonCharacter theAttacker, DungeonCharacter theDefender) {
        final int damage = theAttacker.attack(theDefender);
        changeHP(theAttacker, theDefender, damage);
        
    }
    public void battleSpecial() {
        myText = String.format("Round %d:\n", myCount);
        if (myHero.getSpeed() >= myMonster.getSpeed()) {
            special(myHero, myMonster);
            if (myMonster.isAlive()) {
                attack(myMonster, myHero);
            }
        } else {
            attack(myMonster, myHero);
            if (myHero.isAlive()) {
                special(myHero, myMonster);
            }
        }
        if (myHero.getStatusEffects().isRegen()) {
            healed(myHero.getSpecialAttack().getDamage());
        }
        checkStatus(myMonster, myHero);
        myText += "\n";
        myCount++;                
    }
    private void special(Hero theHero, Monster theMonster) {
        final int damage = theHero.useSpecialAttack(theMonster);
        int charge = theHero.getSpecialAttack().getTurns();
        if (charge > 0) {
            myText += String.format("%s is charging their special attack!\n", myHero.getName());
            myHero.getSpecialAttack().setTurns(myHero.getSpecialAttack().getTurns() - 1);
        } else if (myHero.getStatusEffects().isRegen()){
            myText += String.format("%s casted a healing and restored %d HP!\n", 
                        myHero.getName(), myHero.getSpecialAttack().getDamage());
        } else {
            changeHP(theHero, theMonster, damage);
        }
        

    }
    private void healed(int theHeal) {
            myHero.heal(theHeal);
            myText += String.format("%s has healed %d HP!\n", myHero.getName(), theHeal);
    }
    private void addAttackText(DungeonCharacter theAttacker, DungeonCharacter theDefender, int theDamage) {
        myText += String.format("%s has hit %s for %d\n", theAttacker.getName(), theDefender.getName(), theDamage);
    }
    private void checkStatus(DungeonCharacter theFirst, DungeonCharacter theSecond) {
        if (theFirst.getStatusEffects().isVulnerable()) {
            theFirst.getStatusEffects().setVulnerableDuration(theFirst.getStatusEffects().getVulurableDuration() - 1);
        }
        if (theFirst.getStatusEffects().isRegen()) {
            theFirst.getStatusEffects().setRegenDuration(theFirst.getStatusEffects().getRegenDuration() - 1);
        }
        if (theSecond.getStatusEffects().isVulnerable()) {
            theSecond.getStatusEffects().setVulnerableDuration(theSecond.getStatusEffects().getVulurableDuration() - 1);
        }
        if (theSecond.getStatusEffects().isRegen()) {
            theSecond.getStatusEffects().setRegenDuration(theSecond.getStatusEffects().getRegenDuration() - 1);
        }
    }
    public boolean isOver() {
        return !myHero.isAlive() || !myMonster.isAlive();
    }
    public String actionPerformed() {
        return myText;
    }
    public void changeHP(DungeonCharacter theAttacker, DungeonCharacter theDefender,  final int theDamage) {
        int hp = theDefender.getHealth();
        if (hp > 0) {
            theDefender.setHealth(hp);
            addAttackText(theAttacker, theDefender, theDamage);
        } else {
            theDefender.setHealth(0);
            addAttackText(theAttacker, theDefender, theDamage);
            myText += String.format("%s has defeated %s!\n", theAttacker.getName(), theDefender.getName());
        }
    }
    public void item() {

    }

}
