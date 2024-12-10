
package team5.game.model;

/**
 * Item is an interface that all items must implement.
 */
public interface Item{
    public boolean isConsumable();
    public boolean isPillar();
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
    public Item copy();
}
