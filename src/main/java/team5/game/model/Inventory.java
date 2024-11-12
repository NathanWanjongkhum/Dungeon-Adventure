package team5.game.model;

/**
 * Inventory is a class that holds and handles items.
 */
public class Inventory {
    /** The maximum number of items in the inventory */
    private static final int MAX_INVENTORY_SIZE = 2;
    /** The items in the inventory */
    private Item[] myItems;

    /**
     * Create an empty inventory
     */
    public Inventory() {
        myItems = new Item[MAX_INVENTORY_SIZE];
    }

    /**
     * Add the item to the first open slot in the inventory
     * 
     * @param item the item to add
     */
    public void addItem(final Item item) {
        for (int i = 0; i < this.myItems.length; i++) {
            if (myItems[i] == null) {
                myItems[i] = item;
                return;
            }
        }
    }

    /**
     * Remove the item at the specified slot
     * 
     * @param theSlot the position in the inventory
     */
    public void removeItem(final int theSlot) {
        myItems[theSlot] = null;
    }

    /**
     * Get the items in the inventory
     * 
     * @return the items in the inventory
     */
    public Item[] getItems() {
        return myItems;
    }

    /**
     * Get the item at the specified slot
     * 
     * @param theSlot the position in the inventory
     * @return the item at the slot
     */
    public Item getItem(final int theSlot) {
        return myItems[theSlot];
    }
}
