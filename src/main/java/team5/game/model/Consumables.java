package team5.game.model;

/**
 * Consumables is an item that can be collected and consumed by the player.
 */
public class Consumables implements Item {
    /** The count of consumables */
    private int myCount;

    /**
     * Consumables constructor
     * 
     * @param count
     */
    public Consumables(int count) {
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
