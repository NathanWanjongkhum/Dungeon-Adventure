package team5.game.model;

import java.io.Serializable;

/**
 * Special attack object
 */
public class SpecialAttack implements Serializable {
    /** The amount of turns the special attack needs */
    private int myTurns;
    /** The damage (or heal) amount of special attack */
    private int myDamage;
    /** The amount of turns for special attack */
    private int myMaxTurns;

    /** The serial version UID for serialization */
    private static final long serialVersionUID = 1L;

    /**
     * Default Constructor for SpecialAttack
     */
    protected SpecialAttack() {
        myTurns = 1;
        myDamage = 0;
        myMaxTurns = 1;
    }

    /**
     * Overloaded Constructor for SpecialAttack
     * 
     * @param theTurns  the windup time for special attack
     * @param theDamage damage or heal of special attack
     */
    public SpecialAttack(final int theTurns, final int theDamage) {
        myTurns = theTurns;
        myDamage = theDamage;
        myMaxTurns = theTurns;
    }

    /**
     * Gets the current turns until special attack
     * 
     * @return an int of turns
     */
    public int getTurns() {
        return myTurns;
    }

    /**
     * Gets the damage/ heal amount for special attack
     * 
     * @return the damage amount
     */
    public int getDamage() {
        return myDamage;
    }

    /**
     * The starting amount of turns for special attacks
     * 
     * @return the starting amount of turns
     */
    public int getMaxTurns() {
        return myMaxTurns;
    }

    /**
     * Sets the turns for the special attacks
     * 
     * @param theTurns
     */
    protected void setTurns(final int theTurns) {
        myTurns = theTurns;
        myMaxTurns = theTurns;
    }

    /**
     * Adds turns to the special attack
     *
     * @param theTurns the turns to add
     */
    protected void addTurns(final int theTurns) {
        myTurns += theTurns;
    }

    /**
     * Change damage of special attacks
     * 
     * @param theDamage
     */
    protected void setDamage(final int theDamage) {
        myDamage = theDamage;
    }

    /**
     * Adds damage to the special attack
     * 
     * @param theAddedDamage
     */
    protected void addDamage(final int theAddedDamage) {
        myDamage += theAddedDamage;
    }
}
