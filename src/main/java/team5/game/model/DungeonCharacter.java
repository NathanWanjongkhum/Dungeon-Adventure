package team5.game.model;

import javafx.scene.image.Image;

/**
 * DungeonCharacter is an interface that represents a character in the dungeon.
 */
public interface DungeonCharacter {
    /**
     * Returns the name of the character.
     *
     * @return the name of the character.
     */
    String getName();

    /**
     * Returns the health of the character.
     *
     * @return the health of the character.
     */
    int getHealth();
    /**
     * Returns the max health of the character.
     *
     * @return the max health of the character.
     */
    int getMaxHealth();

    /**
     * Returns the minimum damage of the character.
     *
     * @return the minimum damage of the character.
     */
    int getMinDamage();

    /**
     * Returns the maximum damage of the character.
     *
     * @return the maximum damage of the character.
     */
    int getMaxDamage();

    /**
     * Returns the speed of the character.
     *
     * @return the speed of the character.
     */
    int getSpeed();
    /**
     * Returns the image file name
     * 
     * @return a string of an image file
     */
    public Image getImage();
    /**
     * Returns if the character is alive or not
     * 
     * @return true if character is alive, false otherwise
     */
    boolean isAlive();
    /**
     * Sets the health of the character.
     * 
     * @param theHealth the health of the character
     */
    void setHealth(final int theHealth);

    /**
     * sets the minimum damage of the character.
     * 
     * @param theDamage the minimum damage
     */
    void setMinDamage(final int theDamage);
    /**
     * sets the maximum damage of the character.
     * 
     * @param theDamage the max damage
     */
    void setMaxDamage(final int theDamage);
    /**
     * Sets the speed of the character.
     * 
     * @param theSpeed the speed of the character
     */
    void setSpeed(final int theSpeed);
    /**
     * Returns the damage dealt of the character to other character 
     * 
     * @param theOther the other dungeon character
     * @return the attack number
     */
    int attack(final DungeonCharacter theOther);
    /**
     * Returns a string of all stat values 
     * 
     * @return the string of all stats
     */
    public String getStats();
    /**
     * Returns the status effect object of the dungeon character 
     * 
     * @return the status effect object of the character
     */
    public StatusEffects getStatusEffects();
    /**
     * Heals the character
     * 
     * @param theHeal the amount healed
     */
    public void heal(final int theHeal);
    /**
     * Checks the defending character has the vulnerable effect
     * 
     * @param theOther the other character
     * @param theDamage the damage dealt
     * @return the new int of damage if vulnerable
     */
    public int checkVulnerableDamage(final DungeonCharacter theOther, final int theDamage);
    
}
