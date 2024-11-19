package team5.game.controller;

import team5.game.model.DungeonCharacter;
import team5.game.model.Hero;
import team5.game.model.Item;
import team5.game.model.Monster;
/**
 * A controller class for battles
 * 
 * @author Holden Tsang
 * @version
 */
public class Battle {
    /** The hero object */
    private Hero myHero;
    /** The monster object */
    private Monster myMonster;
    /** The text of actions performed */
    private String myText;
    /** The count of turns */
    private int myCount;
    /** The action of the hero */
    private String myAction;
    /**
     * Constructor for battle
     * 
     * @param theHero the hero controlled by player
     * @param theMonster the monster object
     */
    public Battle(Hero theHero, Monster theMonster) {
        myHero = theHero;
        myMonster = theMonster;
        myText = "";
        myAction = "";
        myCount = 1;
    }
    /**
     * The method to perform an action in battle
     * First compares the speed then chooses who acts first
     * 
     * @param theAction the action/choice by the player
     */
    public void battle(final String theAction) {
        myText = String.format("Round %d:\n", myCount);
        if (myHero.getSpeed() >= myMonster.getSpeed()) {
            HeroActFirst(theAction);
        } else {
            MonsterActFirst(theAction);
        }
        checkStatus(myMonster);
        checkStatus(myHero);
        myText += "\n";
        myCount++;
    }
    /**
     * The method for actions when monster goes first
     * 
     * @param theHeroAction the choice of the player's actions
     */
    private void MonsterActFirst(final String theHeroAction) {
        attack(myMonster, myHero);
        if (myHero.isAlive()) {
            myAction = theHeroAction;
            HeroAction();
        }
    }
    /**
     * The method for actions when hero goes first
     * 
     * @param theHeroAction the choice of the player's actions
     */
    private void HeroActFirst(final String theHeroAction) {
        myAction = theHeroAction;
        HeroAction();
        if (myMonster.isAlive()) {
            attack(myMonster, myHero);
        }
    }
    /**
     * The action to be performed for the hero
     */
    private void HeroAction() {
        switch (myAction) {
            case "attack":
                attack(myHero, myMonster);
                break;
            case "special":
                special(myHero, myMonster);
                break;
            case "item":
                // item(myHero);
                break;
        }
    }
    /**
     * Attacking action for battle
     * 
     * @param theAttacker the attacking character
     * @param theDefender the defending character
     */
    private void attack(DungeonCharacter theAttacker, DungeonCharacter theDefender) {
        final int damage = theAttacker.attack(theDefender);
        changeHP(theAttacker, theDefender, damage);
        
    }
    /**
     * Special attack action for battle
     * 
     * @param theHero the hero's object
     * @param theMonster the monster object
     */
    //Probably could just use the instance field myHero instead of using a parameter as private
    private void special(Hero theHero, Monster theMonster) {
        final int damage = theHero.useSpecialAttack(theMonster);
        int charge = theHero.getSpecialAttack().getTurns();
        if (charge > 0) {
            myText += String.format("%s is charging their special attack!\n", myHero.getName());
            myHero.getSpecialAttack().setTurns(myHero.getSpecialAttack().getTurns() - 1);
        } else if (myHero.getStatusEffects().isRegen() && damage == 0){ //Priestess;
            myText += myHero.specialAttackText();
            theHero.resetTurns();
        } else {
            myText += myHero.specialAttackText();
            changeHP(theHero, theMonster, damage);
            theHero.resetTurns();
        }
        
    }
    /**
     * Adds the action performed to the text.
     * 
     * @param theAttacker the attacking character
     * @param theDefender the defending character
     * @param theDamage the damage the attacker dealt
     */
    private void addAttackText(DungeonCharacter theAttacker, DungeonCharacter theDefender, int theDamage) {
        myText += String.format("%s has hit %s for %d\n", theAttacker.getName(), theDefender.getName(), theDamage);
    }
    /**
     * Checks if character has a status condition and updates if needed
     * 
     * @param theCharacter the dungeon character whose status condition needs to be checked
     */
    private void checkStatus(DungeonCharacter theCharacter) {
        checkHeal(theCharacter);
        if (theCharacter.getStatusEffects().isVulnerable()) {
            theCharacter.getStatusEffects().setVulnerableDuration(theCharacter.getStatusEffects().getVulurableDuration() - 1);
        }
        if (theCharacter.getStatusEffects().isRegen()) {
            theCharacter.getStatusEffects().setRegenDuration(theCharacter.getStatusEffects().getRegenDuration() - 1);
        }
    }
    /**
     * Checks if the character needs to be healed.
     * 
     * @param theCharacter the dungeon character who is checked to be healed
     */
    private void checkHeal(DungeonCharacter theCharacter) {
        if (theCharacter.getStatusEffects().isRegen()) {
            healed(theCharacter.getStatusEffects().getRegenAmount(), theCharacter);
        }
    }
    /**
     * Heals the selected character
     * 
     * @param theHeal the heal amount
     * @param theCharacter the targeted character
     */
    private void healed(int theHeal, DungeonCharacter theCharacter) {
        theCharacter.heal(theHeal);
        myText += String.format("%s has healed %d HP!\n", theCharacter.getName(), theHeal);
    }
    /**
     * Returns true if one character's hp is 0.
     * 
     * @return true if battle is over, false otherwise
     */
    public boolean isOver() {
        return !myHero.isAlive() || !myMonster.isAlive();
    }
    /**
     * returns the action text of all actions that occured during the turn
     * 
     * @return a string of the actions 
     */
    public String actionPerformed() {
        return myText;
    }
    /**
     * 
     * @param theAttacker
     * @param theDefender
     * @param theDamage
     */
    private void changeHP(DungeonCharacter theAttacker, DungeonCharacter theDefender,  final int theDamage) {
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
    public void item(Hero theHero, Item theItem) {

    }

}
