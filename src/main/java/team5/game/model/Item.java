
package team5.game.model;

/**
 * Item is an interface that all items must implement.
 */
public interface Item {
    /**
     * Get the count of consumables
     * 
     * @return the count
     */
    public boolean isConsumable();

    /**
     * Get the count of consumables
     * 
     * @return the count
     */
    public boolean isPillar();

    /**
     * Get the name of the item
     * 
     * @return the name
     */
    public String getName();

    /**
     * Get the count of consumables
     * 
     * @return the count
     */
    public int getCount();

    /**
     * Set the count of consumables
     * 
     * @param count the count
     */
    public void setCount(final int theCount);

    /**
     * Copy the item
     * 
     * @return the copy
     */
    public Item copy();
}
