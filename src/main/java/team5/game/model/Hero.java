package team5.game.model;

public class Hero extends AbstractDungeonCharacter {

    /** The direction the hero is facing */
    private Direction myDirection;

    protected Hero(final String theName, final int theHealth, final int theDamage, final int theSpeed) {
        super(theName, theHealth, theDamage, theSpeed);
        myDirection = Direction.NORTH;
    }

    /**
     * Get the direction the hero is facing
     * 
     * @return the direction
     */
    public Direction getDirection() {
        return myDirection;
    }

    /**
     * Set the direction the hero is facing
     * 
     * @param direction the direction
     */
    public void setDirection(Direction direction) {
        myDirection = direction;
    }
}