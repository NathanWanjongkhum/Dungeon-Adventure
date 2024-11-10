package team5.game.model;

public class Hero extends AbstractDungeonCharacter {
    private static final int ITEM_COUNT = 3; 
    /** The direction the hero is facing */
    private Direction myDirection;

    private Item[] myItems;

    protected Hero(String theName, int theHealth, int theDamage, int theSpeed) {
        super(theName, theHealth, theDamage, theSpeed);
        myDirection = Direction.NORTH;
        myItems = new Item[ITEM_COUNT];
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
     * Gets the item bag
     * 
     * @return all items in bag
     */
    public Item[] getItem() {
        Item[] copy = new Item[ITEM_COUNT];
        for (int i = 0; i < myItems.length; i++) {
            copy[i] = myItems[i];
        }
        return copy;
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