package team5.game.model;

/**
 * Consumables is an item that can be collected and consumed by the player.
 */
public abstract class AbstractConsumables implements Item {
    /** The count of consumables */
    private int myCount;

    /**
     * Consumables constructor
     */
    protected AbstractConsumables() {
        myCount = 0;
    }
    /**
     * Consumables constructor with count
     * 
     * @param count
     */
    protected AbstractConsumables(int theCount) { //Not sure if we want a consumable constructor with a count as we update counts during the game
        myCount = theCount;
    }

    /**
     * Get the count of consumables
     * 
     * @return the count
     */
    public int getCount() {
        return myCount;
    }

    /**
     * Set the count of consumables
     * 
     * @param count the count
     */
    public void setCount(int count) {
        myCount = count;
    }
}
