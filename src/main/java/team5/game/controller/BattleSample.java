package team5.game.controller;

import java.util.Random;

import team5.game.model.DungeonCharacter;
import team5.game.model.Hero;
import team5.game.model.Monster;

public class BattleSample {
    private Hero myHero;
    private Monster myMonster;
    private String myText;

    public BattleSample(Hero theHero, Monster theMonster) {
        myHero = theHero;
        myMonster = theMonster;
        myText = "";
    }
    
    public void battle() {
        myText = "";
        if (myHero.getSpeed() > myMonster.getSpeed()) {
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
    }
    private void attack(DungeonCharacter theAttacker, DungeonCharacter theDefender) {
        Random rand = new Random();
        final int damage = rand.nextInt(theAttacker.getMaxDamage() - theAttacker.getMinDamage() + 1) + theAttacker.getMinDamage();
        int hp = theDefender.getHealth() - damage;
        if (hp < 1) {
            theDefender.setHealth(0);
            addText(theAttacker, theDefender, damage);
            myText += String.format("%s has defeated %s\n", theAttacker.getName(), theDefender.getName());
        } else {
            theDefender.setHealth(hp);
            addText(theAttacker, theDefender, damage);
        }
        
    }
    private void addText(DungeonCharacter theAttacker, DungeonCharacter theDefender, int theDamage) {
        myText += String.format("%s has hit %s for %d\n", theAttacker.getName(), theDefender.getName(), theDamage);
    }
    public boolean isOver() {
        return !myHero.isAlive() || !myMonster.isAlive();
    }
    public String actionPerformed() {
        return myText;
    }
    protected void item() {

    }
}
