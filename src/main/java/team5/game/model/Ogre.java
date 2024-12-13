package team5.game.model;

/**
 * The Ogre Monster Class. Used to combat the player.
 */
public class Ogre extends Monster {
    /**
     * The ogre constructor
     * 
     * @param theName the name of the ogre
     */
    protected Ogre(final String theName) {
        super(theName, 200, 5, 10);
    }
}
