package team5.game.model;

/**
 * A controller class for battles.
 * Heros have 3 actions while monsters only attack. 
 * First compares the speed to determine who goes first
 * Status effects 
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
    /** The consumable item */
    private Consumable myConsumable;
    /**
     * Constructor for battle
     * 
     * @param theHero the hero controlled by player
     * @param theMonster the monster object
     */
    public Battle(final Hero theHero, final Monster theMonster) {
        myHero = theHero;
        myMonster = theMonster;
        myText = "";
        myAction = "";
        myCount = 1;
        myConsumable = null;
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
                item(myHero, myConsumable);
                break;
        }
    }
    /**
     * Attacking action for battle
     * 
     * @param theAttacker the attacking character
     * @param theDefender the defending character
     */
    private void attack(final DungeonCharacter theAttacker, final DungeonCharacter theDefender) {
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
    private void special(final Hero theHero, final Monster theMonster) {
        final int damage = theHero.useSpecialAttack(theMonster);
        int charge = theHero.getSpecialAttack().getTurns();
        if (charge > 0) {
            myText += String.format("%s is charging their special attack!\n", myHero.getName());
            myHero.getSpecialAttack().addTurns(-1);
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
     * The Item action for battle
     * 
     * @param theHero the hero using item
     * @param theConsu the consumable being used
     */
    private void item(Hero theHero, Consumable theConsu) {
        if(theConsu.getName().equals("Bomb")) {
            final int damage = theConsu.useItem(myMonster);
            myText += String.format("%s used Bomb\nIt dealt %d to %s\n", theHero.getName(), 
                                    damage, myMonster.getName());
            if (myMonster.getHealth() < 1) {
                myText += String.format("%s has defeated %s!\n", theHero.getName(), myMonster.getName());
            } 
        } else {
            theConsu.useItem(theHero);
            myText += String.format("%s used a %s.\n", theHero.getName(), getConsumableName(theConsu.getName()));
            if (theConsu.getName().equals("AttackPotion")) {
                checkAttackIncrease(theHero); //Sees if buff should be applied
                myText += String.format("%s's damage was increased by %d for %d turns\n", theHero.getName(),
                                        ((AttackPotion)theConsu).getAttackIncrease(), 
                                        ((AttackPotion)theConsu).getEffectTurns());
            } else if (theConsu.getName().equals("HealingPotion")) {
                myText += String.format("%s's health has restored %d HP\n", theHero.getName(),
                                        ((HealingPotion)theConsu).getHealthRestore());
            }
        }
        theConsu.setCount(theConsu.getCount() - 1);
    }
    /**
     * Adds the action performed to the text.
     * 
     * @param theAttacker the attacking character
     * @param theDefender the defending character
     * @param theDamage the damage the attacker dealt
     */
    private void addAttackText(final DungeonCharacter theAttacker, final DungeonCharacter theDefender, final int theDamage) {
        myText += String.format("%s has hit %s for %d\n", theAttacker.getName(), theDefender.getName(), theDamage);
    }
    /**
     * Checks if character has a status condition and updates if needed
     * 
     * @param theCharacter the dungeon character whose status condition needs to be checked
     */
    private void checkStatus(final DungeonCharacter theCharacter) {
        if (theCharacter.getStatusEffects().isVulnerable()) {
            //If still vulnerable, then reduce the turn count
            theCharacter.getStatusEffects().setVulnerableDuration(theCharacter.getStatusEffects().getVulnerableDuration() - 1);
            //If status is not longer active, add message to the string
            if (!theCharacter.getStatusEffects().isVulnerable()) {
                myText += String.format("%s is not longer vulnerable", theCharacter.getName());
            }
        }
        if (theCharacter.getStatusEffects().isRegen()) {
            //If still has regen status, then heal and reduce the turns
            final int heal = theCharacter.getStatusEffects().getRegenAmount();
            theCharacter.heal(heal);
            myText += String.format("%s has healed %d HP!\n", theCharacter.getName(), heal);
            theCharacter.getStatusEffects().setRegenDuration(theCharacter.getStatusEffects().getRegenDuration() - 1);
            //If status is not longer active, add message to the string
            if (!theCharacter.getStatusEffects().isRegen()) {
                myText += String.format("%s's regeneration has wore off", theCharacter.getName());
            }
        }
        if (theCharacter.getStatusEffects().isDamageIncrease()) {
            //Updates the duration of the damage increase
            theCharacter.getStatusEffects().setDamageDuration(theCharacter.getStatusEffects().getDamageIncreaseDuration() - 1);
            //If duration is over, then remove the damage increase
            if(!theCharacter.getStatusEffects().isDamageIncrease()) {
                //Negative value
                theCharacter.addMaxDamage(-theCharacter.getStatusEffects().getDamageAmount());
                theCharacter.addMinDamage(-theCharacter.getStatusEffects().getDamageAmount());
                myText += String.format("%s's attack buff has wore off", theCharacter.getName());
            }
        }
    }
    private void checkAttackIncrease(DungeonCharacter theCharacter) {
        if (theCharacter.getStatusEffects().isDamageIncrease()) {
            theCharacter.addMaxDamage(theCharacter.getStatusEffects().getDamageAmount());
            theCharacter.addMinDamage(theCharacter.getStatusEffects().getDamageAmount());
        } 
    }
     /**
     * Checks if hp is below 0, in which would be set to 0 and add text if battle is over.
     * 
     * @param theAttacker
     * @param theDefender
     * @param theDamage
     */
    private void changeHP(final DungeonCharacter theAttacker, final DungeonCharacter theDefender,  final int theDamage) {
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
    /**
     * A method to get a cleaner name of items
     * 
     * @param theName the class name that implements Consumable
     * @return a cleaner name of the class
     */
    //Kind of unnecessary
    private String getConsumableName(final String theName) {
        String name = "";
        if (theName.equals("AttackPotion")) {
            name = "Attack Potion";
        } else if (theName.equals("HealingPotion")) {
            name = "Healing Potion";
        }
        return name;
    }
    /**
     * Returns true if one character's hp is 0.
     * 
     * @return true if battle is over, false otherwise
     */
    public boolean isOver() {
        boolean over = !myHero.isAlive() || !myMonster.isAlive();
        if (over) {
            resetEffects(myHero);
            //Kind of unnecessary as new monsters are created each battle
            resetEffects(myMonster);
        }
        return over;
    }
    private void resetEffects(final DungeonCharacter theCharacter) {
        if (theCharacter.getStatusEffects().isDamageIncrease()) {
            theCharacter.getStatusEffects().setDamageDuration(0);
            theCharacter.addMaxDamage(-theCharacter.getStatusEffects().getDamageAmount());
            theCharacter.addMinDamage(-theCharacter.getStatusEffects().getDamageAmount());
        }
        if (theCharacter.getStatusEffects().isRegen()) {
            theCharacter.getStatusEffects().setRegenDuration(0);
        }
        if (theCharacter.getStatusEffects().isVulnerable()) {
            theCharacter.getStatusEffects().setVulnerableDuration(0);
        }

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
     * Sets the consumable being selected
     * 
     * @param theConsumable the consumable being used
     */
    public void setConsumable(Consumable theConsumable) {
        myConsumable = theConsumable;
    }

}
