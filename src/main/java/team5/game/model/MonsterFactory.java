package team5.game.model;

/**
 * The Monster Factory to create monsters
 * 
 * @author
 * @version
 */
public class MonsterFactory {
    /**
     * Takes the char for the type of monster and a string for the name,
     * then return the monster created
     * 
     * @param theMonster the char for monster type
     * @param theName    the string for monster name
     * @return a monster object of a certain type
     */
    public static Monster createMonster(final char theMonster, final String theName) {
        switch (theMonster) {
            case 'O':
                return new Ogre(theName);
            case 'G':
                return new Goblin(theName);
            default:
                return new Skeleton(theName);
        }
    }
}
