package team5.game.model;

/**
 * Consumables is an item that can be collected and consumed by the player.
 */
public abstract class AbstractConsumables implements Item {
    /** The count of consumables */
    private int myCount;

    /**
     * Consumables constructor
     * 
     * @param count
     */
    public AbstractConsumables(int count) {
        myCount = count;
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
