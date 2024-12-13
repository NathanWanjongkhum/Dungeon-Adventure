package team5.game.model;

/**
 * The Monster Class. Used by the game to represent monsters.
 */
public class Monster extends AbstractDungeonCharacter {

    /**
     * The constructor for Monsters
     * 
     * @param theName   the name of the monster
     * @param theHealth the health of the monster
     * @param theDamage the damage of the monster
     * @param theSpeed  the speed of the monster
     */
    protected Monster(final String theName, final int theHealth, final int theDamage, final int theSpeed) {
        super(theName, theHealth, theDamage, theSpeed);
    }

}