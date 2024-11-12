package team5.game.model;

public class Hero extends AbstractDungeonCharacter {
    private static final int ITEM_COUNT = 3;
    /** The direction the hero is facing */
    private Direction myDirection;
    /** The inventory of the hero */
    private Inventory myInventory;

    protected Hero(final String theName, final int theHealth, final int theDamage, final int theSpeed) {
        super(theName, theHealth, theDamage, theSpeed);
        myDirection = Direction.NORTH;
        myInventory = new Inventory(ITEM_COUNT);
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
     * Get the inventory of the hero
     * 
     * @return the inventory
     */
    public Inventory getInventory() {
        return myInventory.clone();
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