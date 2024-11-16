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
    /** sets the health of the character. */
    void setHealth(final int theHealth);

    /** sets the minimum damage of the character. */
    void setMinDamage(final int theDamage);

    /** sets the maximum damage of the character. */
    void setMaxDamage(final int theDamage);

    /** sets the speed of the character. */
    void setSpeed(final int theSpeed);
}
