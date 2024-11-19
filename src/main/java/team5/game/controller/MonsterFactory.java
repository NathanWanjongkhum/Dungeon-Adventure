package team5.game.controller;

import team5.game.model.Goblin;
import team5.game.model.Monster;
import team5.game.model.Ogre;
import team5.game.model.Skeleton;

/**
 * The Monster Factory to create monsters
 * 
 * @author Holden Tsang
 * @version
 */
public class MonsterFactory {
    /**
     * Takes the char for the type of monster and a string for the name,
     * then return the monster created
     * 
     * @param theMonster the char for monster type
     * @param theName the string for monster name
     * @return a monster object of a certain type
     */
    public static Monster createHero(final char theMonster, String theName) {
        switch(theMonster) {
            case 'O':
                return new Ogre(theName);
            case 'G':
                return new Goblin(theName);
            default:
                return new Skeleton(theName);
        }
    }
}
