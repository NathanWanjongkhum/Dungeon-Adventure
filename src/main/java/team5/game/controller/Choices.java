package team5.game.controller;


import team5.game.model.Hero;
/**
 * A singleton class to get the choices that occur when starting
 * 
 * @author Holden Tsang
 * @version
 */
public class Choices {
    /** Creates a new instance of this class */
    private static Choices instance = new Choices();
    /** The hero's name */
    private String myName;
    /** The Hero selected */
    private Hero myHero;
    /**
     * Private constructor for choices
     */
    private Choices() {

    }
    /**
     * Gets the name the player chooses
     * 
     * @return a string of the name
     */
    public String getName() {
        return myName;
    }
    /**
     * Gets the hero the player chooses
     * 
     * @return the hero that was chosen
     */
    public Hero getHero() {
        return myHero;
    }   
    /**
     * Sets the name to the player's choice
     * 
     * @param theName the string of the name
     */
    public void setName(String theName) {
        myName = theName;
    }
    /**
     * Sets the hero to the player's choice
     * 
     * @param theHero the hero object
     */
    public void setHero(Hero theHero) {
        myHero = theHero;
    }
    /**
     * Gets the instance of this object
     * 
     * @return the instance of this object
     */
    public static Choices getChoices() {
        return instance;
    }
}
