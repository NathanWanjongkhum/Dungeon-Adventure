package team5.game.model;

public class Hero extends AbstractDungeonCharacter {
    /** The directions the hero can move in */
    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    /** The direction the hero is facing */
    private Direction myDirection;

    protected Hero(String theName, int theHealth, int theDamage, int theSpeed) {
        super(theName, theHealth, theDamage, theSpeed);
        myDirection = Direction.UP;
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