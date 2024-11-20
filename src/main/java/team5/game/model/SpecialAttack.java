package team5.game.model;
/**
 * Special attack object
 * 
 * @author Holden Tsang
 * @version
 */
public class SpecialAttack {
    /** The amount of turns the special attack needs */
    private int myTurns;
    /** The damage (or heal) amount of special attack */
    private int myDamage;
    /** The amount of turns for special attack */
    private int myMaxTurns;
    /**
     * Default Constructor for SpecialAttack
     */
    public SpecialAttack() {
        myTurns = 1;
        myDamage = 0;
        myMaxTurns = 1;
    }
    /**
     * Overloaded Constructor for SpecialAttack
     * 
     * @param theTurns the windup time for special attack
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
    public void setTurns(final int theTurns) {
        myTurns = theTurns;
    }
    /**
     * Change damage of special attacks
     * 
     * @param theDamage
     */
    public void setDamage(final int theDamage) {
        myDamage = theDamage;
    }
}
